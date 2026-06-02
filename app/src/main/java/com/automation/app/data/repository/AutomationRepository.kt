package com.automation.app.data.repository

import com.automation.app.data.db.AutomationDao
import com.automation.app.data.db.entities.AutomationEntity
import kotlinx.coroutines.flow.Flow

class AutomationRepository(private val dao: AutomationDao) {

    val allAutomations: Flow<List<AutomationEntity>> = dao.getAllAutomations()

    suspend fun getAutomation(id: Long): AutomationEntity? = dao.getAutomationById(id)

    suspend fun getEnabledAutomations(): List<AutomationEntity> = dao.getEnabledAutomations()

    suspend fun getEnabledByTriggerType(type: String): List<AutomationEntity> =
        dao.getEnabledAutomationsByTriggerType(type)

    suspend fun getEnabledByTriggerTypes(types: List<String>): List<AutomationEntity> =
        dao.getEnabledAutomationsByTriggerTypes(types)

    suspend fun insert(automation: AutomationEntity): Long = dao.insert(automation)

    suspend fun update(automation: AutomationEntity) = dao.update(automation)

    suspend fun delete(automation: AutomationEntity) = dao.delete(automation)

    suspend fun setEnabled(id: Long, enabled: Boolean) = dao.setEnabled(id, enabled)
}
