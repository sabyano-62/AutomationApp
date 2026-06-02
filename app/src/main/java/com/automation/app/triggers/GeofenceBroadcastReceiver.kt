package com.automation.app.triggers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingEvent

class GeofenceBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val geofencingEvent = GeofencingEvent.fromIntent(intent)
        if (geofencingEvent == null) return

        if (geofencingEvent.hasError()) {
            android.util.Log.e("GeofenceReceiver", "Geofence error: ${geofencingEvent.errorCode}")
            return
        }

        val transition = geofencingEvent.geofenceTransition
        if (transition != Geofence.GEOFENCE_TRANSITION_ENTER &&
            transition != Geofence.GEOFENCE_TRANSITION_DWELL
        ) return

        val triggeringGeofences = geofencingEvent.triggeringGeofences ?: return
        for (geofence in triggeringGeofences) {
            val automationId = geofence.requestId.toLongOrNull() ?: continue
            val db = (context.applicationContext as com.automation.app.AutomationApp).database
            val repo = com.automation.app.data.repository.AutomationRepository(db.automationDao())
            kotlinx.coroutines.runBlocking {
                val automation = repo.getAutomation(automationId) ?: return@runBlocking
                if (automation.enabled) {
                    com.automation.app.actions.ActionExecutor.execute(context, automation)
                }
            }
        }
    }
}
