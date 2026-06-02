package com.automation.app.triggers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.automation.app.AutomationApp
import com.automation.app.data.repository.AutomationRepository

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != Intent.ACTION_BOOT_COMPLETED) return

        val db = (context.applicationContext as AutomationApp).database
        val repo = AutomationRepository(db.automationDao())
        val automations = kotlinx.coroutines.runBlocking {
            repo.getEnabledAutomations()
        }
        for (automation in automations) {
            TriggerScheduler.schedule(context, repo, automation.id)
        }
    }
}
