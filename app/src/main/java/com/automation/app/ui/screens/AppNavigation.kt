package com.automation.app.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

object Routes {
    const val DASHBOARD = "dashboard"
    const val CREATE_AUTOMATION = "create_automation/{automationId}"
    const val SETTINGS = "settings"

    fun createAutomation(automationId: Long = -1L) = "create_automation/$automationId"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.DASHBOARD) {
        composable(Routes.DASHBOARD) {
            DashboardScreen(
                onCreateNew = { navController.navigate(Routes.createAutomation()) },
                onEditAutomation = { id -> navController.navigate(Routes.createAutomation(id)) },
                onOpenSettings = { navController.navigate(Routes.SETTINGS) }
            )
        }
        composable(
            route = Routes.CREATE_AUTOMATION,
            arguments = listOf(navArgument("automationId") { type = NavType.LongType })
        ) { backStackEntry ->
            val automationId = backStackEntry.arguments?.getLong("automationId") ?: -1L
            CreateAutomationScreen(
                automationId = automationId,
                onSaved = { navController.popBackStack() },
                onBack = { navController.popBackStack() }
            )
        }
        composable(Routes.SETTINGS) {
            SettingsScreen(onBack = { navController.popBackStack() })
        }
    }
}
