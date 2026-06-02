package com.automation.app.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

object PermissionHelper {

    val requiredPermissions = mapOf(
        "sms" to listOf(Manifest.permission.SEND_SMS),
        "notification" to listOf(Manifest.permission.POST_NOTIFICATIONS),
        "location" to listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_BACKGROUND_LOCATION
        ),
        "wifi" to listOf(
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.CHANGE_WIFI_STATE
        ),
        "bluetooth" to listOf(
            Manifest.permission.BLUETOOTH,
            Manifest.permission.BLUETOOTH_ADMIN,
            Manifest.permission.BLUETOOTH_CONNECT
        ),
        "volume" to listOf(Manifest.permission.WRITE_SETTINGS),
        "brightness" to listOf(Manifest.permission.WRITE_SETTINGS),
        "boot" to listOf(Manifest.permission.RECEIVE_BOOT_COMPLETED)
    )

    fun hasAllPermissions(context: Context, triggerType: String, actionType: String): Boolean {
        val needed = mutableListOf<String>()
        when (triggerType) {
            "location" -> needed.addAll(requiredPermissions["location"] ?: emptyList())
            "wifi" -> needed.addAll(requiredPermissions["wifi"] ?: emptyList())
        }
        when (actionType) {
            "sms" -> needed.addAll(requiredPermissions["sms"] ?: emptyList())
            "notification" -> needed.addAll(requiredPermissions["notification"] ?: emptyList())
            "wifi_toggle" -> needed.addAll(requiredPermissions["wifi"] ?: emptyList())
            "bluetooth_toggle" -> needed.addAll(requiredPermissions["bluetooth"] ?: emptyList())
            "volume", "brightness" -> needed.addAll(requiredPermissions["volume"] ?: emptyList())
        }

        return needed.all { permission ->
            ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
        }
    }
}
