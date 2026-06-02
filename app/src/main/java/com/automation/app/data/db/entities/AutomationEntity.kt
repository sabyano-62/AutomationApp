package com.automation.app.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "automations")
data class AutomationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val enabled: Boolean = true,
    // Trigger type: "time", "location", "wifi", "charging", "battery"
    val triggerType: String,
    // Time trigger fields
    val triggerHour: Int? = null,
    val triggerMinute: Int? = null,
    val triggerDaysOfWeek: String? = null, // comma-separated: "1,2,3" (Mon, Tue, Wed)
    // Location trigger fields
    val triggerLatitude: Double? = null,
    val triggerLongitude: Double? = null,
    val triggerRadius: Float? = null, // meters
    // Wi-Fi trigger field
    val triggerSsid: String? = null,
    // Battery trigger fields
    val triggerBatteryLevel: Int? = null, // percentage
    val triggerBatteryCharging: Boolean? = null, // true=charging, false=discharging, null=either
    // Action type: "sms", "notification", "wifi_toggle", "bluetooth_toggle", "volume", "brightness", "launch_app"
    val actionType: String,
    // SMS fields
    val actionPhoneNumber: String? = null,
    val actionMessage: String? = null,
    // Notification fields
    val actionNotificationTitle: String? = null,
    val actionNotificationBody: String? = null,
    // Wi-Fi / Bluetooth toggle
    val actionToggleOn: Boolean? = null,
    // Volume / brightness (0-100)
    val actionLevel: Int? = null,
    // Launch app
    val actionPackageName: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)
