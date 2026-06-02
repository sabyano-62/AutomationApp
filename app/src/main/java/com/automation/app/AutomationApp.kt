package com.automation.app

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.work.Configuration
import com.automation.app.data.db.AppDatabase

class AutomationApp : Application(), Configuration.Provider {

    lateinit var database: AppDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = AppDatabase.getInstance(this)
        createNotificationChannels()
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.INFO)
            .build()

    private fun createNotificationChannels() {
        val channel = NotificationChannel(
            CHANNEL_AUTOMATION,
            "Automation Actions",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "Notifications triggered by your automations"
        }
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
    }

    companion object {
        const val CHANNEL_AUTOMATION = "automation_actions"

        lateinit var instance: AutomationApp
    }
}
