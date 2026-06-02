package com.automation.app.triggers

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.net.wifi.WifiManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.automation.app.AutomationApp
import com.automation.app.actions.ActionExecutor
import com.automation.app.data.db.entities.AutomationEntity
import com.automation.app.data.repository.AutomationRepository

class DeviceEventWorker(appContext: Context, params: WorkerParameters) : Worker(appContext, params) {

    override fun doWork(): Result {
        val db = (applicationContext as AutomationApp).database
        val repo = AutomationRepository(db.automationDao())

        val automations = kotlinx.coroutines.runBlocking {
            repo.getEnabledByTriggerTypes(listOf("wifi", "charging", "battery"))
        }

        for (automation in automations) {
            if (matchesCurrentState(automation)) {
                ActionExecutor.execute(applicationContext, automation)
            }
        }
        return Result.success()
    }

    @Suppress("DEPRECATION")
    private fun matchesCurrentState(automation: AutomationEntity): Boolean {
        return when (automation.triggerType) {
            "wifi" -> {
                val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
                val currentSsid = wifiManager.connectionInfo.ssid?.replace("\"", "")
                currentSsid == automation.triggerSsid
            }
            "charging" -> {
                val intent = applicationContext.registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
                val status = intent?.getIntExtra(BatteryManager.EXTRA_STATUS, -1) ?: -1
                status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL
            }
            "battery" -> {
                val intent = applicationContext.registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
                val level = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
                val target = automation.triggerBatteryLevel ?: 50
                level <= target
            }
            else -> false
        }
    }
}
