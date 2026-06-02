package com.automation.app.ui.screens

import android.Manifest
import android.app.TimePickerDialog
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.automation.app.data.db.entities.AutomationEntity
import com.automation.app.viewmodel.AutomationViewModel

private val triggerOptions = listOf(
    "time" to "Scheduled Time",
    "location" to "Location (Geofence)",
    "wifi" to "Wi-Fi Connect",
    "charging" to "Charging State",
    "battery" to "Battery Level"
)

private val actionOptions = listOf(
    "notification" to "Show Notification",
    "sms" to "Send SMS",
    "wifi_toggle" to "Toggle Wi-Fi",
    "bluetooth_toggle" to "Toggle Bluetooth",
    "volume" to "Set Volume",
    "brightness" to "Set Brightness",
    "launch_app" to "Launch App"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAutomationScreen(
    automationId: Long,
    onSaved: () -> Unit,
    onBack: () -> Unit,
    viewModel: AutomationViewModel = viewModel()
) {
    val context = LocalContext.current
    var isEditing by remember { mutableStateOf(false) }

    var name by remember { mutableStateOf("") }
    var triggerType by remember { mutableStateOf("time") }
    var triggerExpanded by remember { mutableStateOf(false) }
    var actionType by remember { mutableStateOf("notification") }
    var actionExpanded by remember { mutableStateOf(false) }

    // Time trigger fields
    var triggerHour by remember { mutableStateOf(8) }
    var triggerMinute by remember { mutableStateOf(0) }
    var triggerDaysOfWeek by remember { mutableStateOf("") }
    val selectedDays = remember { mutableStateOf(setOf<Int>()) }

    // Location fields
    var triggerLatitude by remember { mutableStateOf("") }
    var triggerLongitude by remember { mutableStateOf("") }
    var triggerRadius by remember { mutableStateOf("100") }

    // Wi-Fi field
    var triggerSsid by remember { mutableStateOf("") }

    // Battery fields
    var triggerBatteryLevel by remember { mutableStateOf("50") }

    // SMS fields
    var actionPhoneNumber by remember { mutableStateOf("") }
    var actionMessage by remember { mutableStateOf("") }

    // Notification fields
    var actionNotificationTitle by remember { mutableStateOf("") }
    var actionNotificationBody by remember { mutableStateOf("") }

    // Toggle fields
    var actionToggleOn by remember { mutableStateOf(true) }

    // Level (volume / brightness)
    var actionLevel by remember { mutableStateOf("50") }

    // Launch app
    var actionPackageName by remember { mutableStateOf("") }
    var isRunning by remember { mutableStateOf(false) }

    // Load existing automation for editing
    LaunchedEffect(automationId) {
        if (automationId > 0) {
            isEditing = true
            val existing = viewModel.getAutomation(automationId)
            if (existing != null) {
                name = existing.name
                triggerType = existing.triggerType
                actionType = existing.actionType
                existing.triggerHour?.let { triggerHour = it }
                existing.triggerMinute?.let { triggerMinute = it }
                existing.triggerDaysOfWeek?.let {
                    triggerDaysOfWeek = it
                    selectedDays.value = it.split(",").mapNotNull { s -> s.toIntOrNull() }.toSet()
                }
                existing.triggerLatitude?.let { triggerLatitude = it.toString() }
                existing.triggerLongitude?.let { triggerLongitude = it.toString() }
                existing.triggerRadius?.let { triggerRadius = it.toInt().toString() }
                existing.triggerSsid?.let { triggerSsid = it }
                existing.triggerBatteryLevel?.let { triggerBatteryLevel = it.toString() }
                existing.actionPhoneNumber?.let { actionPhoneNumber = it }
                existing.actionMessage?.let { actionMessage = it }
                existing.actionNotificationTitle?.let { actionNotificationTitle = it }
                existing.actionNotificationBody?.let { actionNotificationBody = it }
                existing.actionToggleOn?.let { actionToggleOn = it }
                existing.actionLevel?.let { actionLevel = it.toString() }
                existing.actionPackageName?.let { actionPackageName = it }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (isEditing) "Edit Automation" else "New Automation") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Automation Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            // Trigger type dropdown
            ExposedDropdownMenuBox(
                expanded = triggerExpanded,
                onExpandedChange = { triggerExpanded = it }
            ) {
                OutlinedTextField(
                    value = triggerOptions.first { it.first == triggerType }.second,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Trigger") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = triggerExpanded) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = triggerExpanded,
                    onDismissRequest = { triggerExpanded = false }
                ) {
                    triggerOptions.forEach { (key, label) ->
                        DropdownMenuItem(
                            text = { Text(label) },
                            onClick = {
                                triggerType = key
                                triggerExpanded = false
                            }
                        )
                    }
                }
            }

            Spacer(Modifier.height(8.dp))

            // Trigger-specific fields
            when (triggerType) {
                "time" -> {
                    val timeText = String.format("%02d:%02d", triggerHour, triggerMinute)
                    OutlinedButton(
                        onClick = {
                            TimePickerDialog(
                                context,
                                { _, h, m -> triggerHour = h; triggerMinute = m },
                                triggerHour, triggerMinute, true
                            ).show()
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Pick Time: $timeText")
                    }
                    Spacer(Modifier.height(8.dp))
                    Text("Repeat on days (none = every day)", style = MaterialTheme.typography.bodySmall)
                    Spacer(Modifier.height(4.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        val dayLabels = listOf("Mon" to 1, "Tue" to 2, "Wed" to 3, "Thu" to 4, "Fri" to 5, "Sat" to 6, "Sun" to 7)
                        dayLabels.forEach { (label, dayNum) ->
                            val isSelected = dayNum in selectedDays.value
                            androidx.compose.material3.FilterChip(
                                selected = isSelected,
                                onClick = {
                                    selectedDays.value = if (isSelected) {
                                        selectedDays.value - dayNum
                                    } else {
                                        selectedDays.value + dayNum
                                    }
                                    triggerDaysOfWeek = selectedDays.value.sorted().joinToString(",")
                                },
                                label = { Text(label, style = MaterialTheme.typography.labelSmall) }
                            )
                        }
                    }
                }
                "location" -> {
                    OutlinedTextField(
                        value = triggerLatitude,
                        onValueChange = { triggerLatitude = it },
                        label = { Text("Latitude") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = triggerLongitude,
                        onValueChange = { triggerLongitude = it },
                        label = { Text("Longitude") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = triggerRadius,
                        onValueChange = { triggerRadius = it },
                        label = { Text("Radius (meters)") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true
                    )
                }
                "wifi" -> {
                    OutlinedTextField(
                        value = triggerSsid,
                        onValueChange = { triggerSsid = it },
                        label = { Text("Wi-Fi SSID") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                }
                "charging" -> {
                    Text("Trigger activates when charging state changes", style = MaterialTheme.typography.bodyMedium)
                }
                "battery" -> {
                    OutlinedTextField(
                        value = triggerBatteryLevel,
                        onValueChange = { triggerBatteryLevel = it },
                        label = { Text("Battery level (%)") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            // Action type dropdown
            ExposedDropdownMenuBox(
                expanded = actionExpanded,
                onExpandedChange = { actionExpanded = it }
            ) {
                OutlinedTextField(
                    value = actionOptions.first { it.first == actionType }.second,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Action") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = actionExpanded) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = actionExpanded,
                    onDismissRequest = { actionExpanded = false }
                ) {
                    actionOptions.forEach { (key, label) ->
                        DropdownMenuItem(
                            text = { Text(label) },
                            onClick = {
                                actionType = key
                                actionExpanded = false
                            }
                        )
                    }
                }
            }

            Spacer(Modifier.height(8.dp))

            // Action-specific fields
            when (actionType) {
                "sms" -> {
                    OutlinedTextField(
                        value = actionPhoneNumber,
                        onValueChange = { actionPhoneNumber = it },
                        label = { Text("Phone number") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = actionMessage,
                        onValueChange = { actionMessage = it },
                        label = { Text("Message") },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 2
                    )
                }
                "notification" -> {
                    OutlinedTextField(
                        value = actionNotificationTitle,
                        onValueChange = { actionNotificationTitle = it },
                        label = { Text("Notification title") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = actionNotificationBody,
                        onValueChange = { actionNotificationBody = it },
                        label = { Text("Notification body") },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 2
                    )
                }
                "wifi_toggle" -> {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Turn on")
                        androidx.compose.material3.Switch(
                            checked = actionToggleOn,
                            onCheckedChange = { actionToggleOn = it }
                        )
                    }
                }
                "bluetooth_toggle" -> {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Turn on")
                        androidx.compose.material3.Switch(
                            checked = actionToggleOn,
                            onCheckedChange = { actionToggleOn = it }
                        )
                    }
                }
                "volume" -> {
                    OutlinedTextField(
                        value = actionLevel,
                        onValueChange = { actionLevel = it },
                        label = { Text("Volume level (0-100)") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true
                    )
                }
                "brightness" -> {
                    OutlinedTextField(
                        value = actionLevel,
                        onValueChange = { actionLevel = it },
                        label = { Text("Brightness level (0-100)") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true
                    )
                }
                "launch_app" -> {
                    OutlinedTextField(
                        value = actionPackageName,
                        onValueChange = { actionPackageName = it },
                        label = { Text("App package name") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            var pendingEntity by remember { mutableStateOf<AutomationEntity?>(null) }

            val permissionLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestMultiplePermissions()
            ) { grants: Map<String, Boolean> ->
                val entity = pendingEntity
                if (entity != null && grants.values.all { it }) {
                    viewModel.saveAutomation(entity)
                    onSaved()
                } else if (entity != null) {
                    isRunning = false
                    Toast.makeText(context, "Permissions required for this action", Toast.LENGTH_SHORT).show()
                }
                pendingEntity = null
            }

            Button(
                onClick = {
                    val entity = AutomationEntity(
                        id = if (isEditing) automationId else 0,
                        name = name.ifBlank { "New Automation" },
                        triggerType = triggerType,
                        triggerHour = if (triggerType == "time") triggerHour else null,
                        triggerMinute = if (triggerType == "time") triggerMinute else null,
                        triggerDaysOfWeek = triggerDaysOfWeek.ifBlank { null },
                        triggerLatitude = if (triggerType == "location") triggerLatitude.toDoubleOrNull() else null,
                        triggerLongitude = if (triggerType == "location") triggerLongitude.toDoubleOrNull() else null,
                        triggerRadius = if (triggerType == "location") triggerRadius.toFloatOrNull() else null,
                        triggerSsid = if (triggerType == "wifi") triggerSsid else null,
                        triggerBatteryLevel = if (triggerType == "battery") triggerBatteryLevel.toIntOrNull() else null,
                        actionType = actionType,
                        actionPhoneNumber = if (actionType == "sms") actionPhoneNumber else null,
                        actionMessage = if (actionType == "sms") actionMessage else null,
                        actionNotificationTitle = if (actionType == "notification") actionNotificationTitle else null,
                        actionNotificationBody = if (actionType == "notification") actionNotificationBody else null,
                        actionToggleOn = if (actionType in listOf("wifi_toggle", "bluetooth_toggle")) actionToggleOn else null,
                        actionLevel = if (actionType in listOf("volume", "brightness")) actionLevel.toIntOrNull() else null,
                        actionPackageName = if (actionType == "launch_app") actionPackageName else null
                    )
                    isRunning = true
                    val needed = mutableListOf<String>()
                    if (entity.actionType == "sms") needed.add(Manifest.permission.SEND_SMS)
                    if (entity.actionType == "notification") needed.add(Manifest.permission.POST_NOTIFICATIONS)
                    if (entity.triggerType == "location") {
                        needed.add(Manifest.permission.ACCESS_FINE_LOCATION)
                    }

                    val missing = needed.filter {
                        context.checkSelfPermission(it) != PackageManager.PERMISSION_GRANTED
                    }
                    if (missing.isNotEmpty()) {
                        pendingEntity = entity
                        permissionLauncher.launch(missing.toTypedArray())
                    } else {
                        viewModel.saveAutomation(entity)
                        onSaved()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isRunning
            ) {
                Text(if (isEditing) "Update" else "Save")
            }
        }
    }
}
