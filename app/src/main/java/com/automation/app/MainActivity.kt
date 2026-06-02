package com.automation.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.automation.app.ui.screens.AppNavigation
import com.automation.app.ui.screens.LockScreen
import com.automation.app.ui.theme.AutomationTheme
import com.automation.app.util.SecurityManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AutomationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val securityManager = remember { SecurityManager(this@MainActivity) }
                    var unlocked by remember { mutableStateOf(!securityManager.isLockEnabled) }

                    if (unlocked) {
                        AppNavigation()
                    } else {
                        LockScreen(onUnlocked = { unlocked = true })
                    }
                }
            }
        }
    }
}
