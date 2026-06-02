package com.automation.app.actions

import android.app.PendingIntent
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.net.wifi.WifiManager
import android.provider.Settings
import android.telephony.SmsManager
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.automation.app.AutomationApp
import com.automation.app.data.db.entities.AutomationEntity

object ActionExecutor {

    fun execute(context: Context, automation: AutomationEntity) {
        try {
            when (automation.actionType) {
                "sms" -> sendSms(context, automation)
                "notification" -> showNotification(context, automation)
                "wifi_toggle" -> toggleWifi(context, automation)
                "bluetooth_toggle" -> toggleBluetooth(context, automation)
                "volume" -> setVolume(context, automation)
                "brightness" -> setBrightness(context, automation)
                "launch_app" -> launchApp(context, automation)
            }
        } catch (e: Exception) {
            android.util.Log.e("ActionExecutor", "Failed to execute action", e)
        }
    }

    @Suppress("DEPRECATION")
    private fun sendSms(context: Context, automation: AutomationEntity) {
        val number = automation.actionPhoneNumber ?: return
        val message = automation.actionMessage ?: ""
        try {
            SmsManager.getDefault().sendTextMessage(number, null, message, null, null)
        } catch (e: SecurityException) {
            Toast.makeText(context, "SMS permission not granted", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showNotification(context: Context, automation: AutomationEntity) {
        val title = automation.actionNotificationTitle ?: "Automation Triggered"
        val body = automation.actionNotificationBody ?: automation.name

        val intent = context.packageManager.getLaunchIntentForPackage(context.packageName)
            ?: Intent(context, com.automation.app.MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }

        val pendingIntent = PendingIntent.getActivity(
            context, automation.id.toInt(), intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, AutomationApp.CHANNEL_AUTOMATION)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(title)
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        try {
            NotificationManagerCompat.from(context).notify(automation.id.toInt(), notification)
        } catch (e: SecurityException) {
            android.util.Log.w("ActionExecutor", "Notification permission not granted")
        }
    }

    @Suppress("DEPRECATION")
    private fun toggleWifi(context: Context, automation: AutomationEntity) {
        val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val turnOn = automation.actionToggleOn ?: true
        wifiManager.setWifiEnabled(turnOn)
    }

    @Suppress("DEPRECATION")
    private fun toggleBluetooth(context: Context, automation: AutomationEntity) {
        val manager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        val adapter = manager.adapter ?: return
        val turnOn = automation.actionToggleOn ?: true
        if (turnOn) {
            adapter.enable()
        } else {
            adapter.disable()
        }
    }

    private fun setVolume(context: Context, automation: AutomationEntity) {
        val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val level = automation.actionLevel ?: 50
        val maxVol = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        val vol = (level * maxVol) / 100
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, vol, 0)
    }

    private fun setBrightness(context: Context, automation: AutomationEntity) {
        val level = automation.actionLevel ?: 50
        val brightness = (level * 255) / 100
        Settings.System.putInt(
            context.contentResolver,
            Settings.System.SCREEN_BRIGHTNESS,
            brightness
        )
    }

    private fun launchApp(context: Context, automation: AutomationEntity) {
        val packageName = automation.actionPackageName ?: return
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        if (intent != null) {
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        } else {
            Toast.makeText(context, "App not found: $packageName", Toast.LENGTH_SHORT).show()
        }
    }
}
