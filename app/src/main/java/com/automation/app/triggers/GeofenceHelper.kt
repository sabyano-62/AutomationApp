package com.automation.app.triggers

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.GeofencingRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority

object GeofenceHelper {
    private const val GEOFENCE_RADIUS = 100f
    private const val DWELL_DELAY_MS = 5000
    private const val LOITERING_DELAY_MS = 30000

    fun registerGeofence(context: Context, intent: Intent) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) return

        val automationId = intent.getLongExtra("automation_id", -1L)
        val latitude = intent.getDoubleExtra("latitude", 0.0)
        val longitude = intent.getDoubleExtra("longitude", 0.0)
        val radius = intent.getFloatExtra("radius", GEOFENCE_RADIUS)

        if (automationId < 0 || latitude == 0.0 || longitude == 0.0) return

        val geofencingClient: GeofencingClient = LocationServices.getGeofencingClient(context)

        val geofence = Geofence.Builder()
            .setRequestId(automationId.toString())
            .setCircularRegion(latitude, longitude, radius)
            .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER or Geofence.GEOFENCE_TRANSITION_DWELL)
            .setLoiteringDelay(LOITERING_DELAY_MS)
            .setExpirationDuration(Geofence.NEVER_EXPIRE)
            .build()

        val geofencingRequest = GeofencingRequest.Builder()
            .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
            .addGeofence(geofence)
            .build()

        val geofencePendingIntent = android.app.PendingIntent.getBroadcast(
            context,
            automationId.toInt(),
            Intent(context, GeofenceBroadcastReceiver::class.java).apply {
                putExtra("automation_id", automationId)
            },
            android.app.PendingIntent.FLAG_UPDATE_CURRENT or android.app.PendingIntent.FLAG_IMMUTABLE
        )

        geofencingClient.addGeofences(geofencingRequest, geofencePendingIntent)
            .addOnSuccessListener {
                android.util.Log.d("GeofenceHelper", "Geofence added for automation $automationId")
            }
            .addOnFailureListener { e ->
                android.util.Log.e("GeofenceHelper", "Failed to add geofence", e)
            }
    }
}
