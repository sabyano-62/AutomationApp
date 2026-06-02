package com.automation.app.triggers

import android.Manifest
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.automation.app.data.db.entities.AutomationEntity
import com.automation.app.data.repository.AutomationRepository
import java.util.Calendar
import java.util.concurrent.TimeUnit

object TriggerScheduler {

    fun schedule(context: Context, repo: AutomationRepository, automationId: Long) {
        // This is called from a coroutine scope - we run the suspend call synchronously via runBlocking
        // In production, use a proper coroutine scope. For simplicity here we use a workaround.
        kotlinx.coroutines.runBlocking {
            val automation = repo.getAutomation(automationId) ?: return@runBlocking
            if (!automation.enabled) return@runBlocking

            when (automation.triggerType) {
                "time" -> scheduleTimeTrigger(context, automation)
                "location" -> scheduleLocationTrigger(context, automation)
                else -> scheduleDeviceEventTrigger(context, automation)
            }
        }
    }

    fun cancel(context: Context, automationId: Long) {
        WorkManager.getInstance(context).cancelUniqueWork("automation_$automationId")
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, TimeTriggerWorker::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context, automationId.toInt(), intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.cancel(pendingIntent)
    }

    private fun scheduleTimeTrigger(context: Context, automation: AutomationEntity) {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, automation.triggerHour ?: 8)
            set(Calendar.MINUTE, automation.triggerMinute ?: 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            if (before(Calendar.getInstance())) {
                add(Calendar.DAY_OF_YEAR, 1)
            }
        }

        val intent = Intent(context, TimeTriggerWorker::class.java).apply {
            putExtra("automation_id", automation.id)
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context, automation.id.toInt(), intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }

    private fun scheduleLocationTrigger(context: Context, automation: AutomationEntity) {
        // Geofence trigger is handled by GeofenceBroadcastReceiver
        val intent = Intent(context, GeofenceBroadcastReceiver::class.java).apply {
            putExtra("automation_id", automation.id)
            putExtra("latitude", automation.triggerLatitude ?: 0.0)
            putExtra("longitude", automation.triggerLongitude ?: 0.0)
            putExtra("radius", automation.triggerRadius ?: 100f)
        }
        GeofenceHelper.registerGeofence(context, intent)
    }

    private fun scheduleDeviceEventTrigger(context: Context, automation: AutomationEntity) {
        // Device events (Wi-Fi, charging, battery) use WorkManager with a foreground service constraint
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(false)
            .build()

        val request = OneTimeWorkRequestBuilder<DeviceEventWorker>()
            .setConstraints(constraints)
            .setBackoffCriteria(BackoffPolicy.LINEAR, 1, TimeUnit.MINUTES)
            .addTag("automation_${automation.id}")
            .build()

        WorkManager.getInstance(context).enqueueUniqueWork(
            "automation_${automation.id}",
            ExistingWorkPolicy.REPLACE,
            request
        )
    }
}
