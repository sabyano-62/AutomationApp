package com.automation.app.triggers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.automation.app.AutomationApp
import com.automation.app.actions.ActionExecutor
import com.automation.app.data.repository.AutomationRepository

class TimeTriggerWorker : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val automationId = intent.getLongExtra("automation_id", -1L)
        if (automationId < 0) return

        val db = (context.applicationContext as AutomationApp).database
        val repo = AutomationRepository(db.automationDao())

        kotlinx.coroutines.runBlocking {
            val automation = repo.getAutomation(automationId) ?: return@runBlocking
            if (!automation.enabled) return@runBlocking
            ActionExecutor.execute(context, automation)
        }
    }
}
