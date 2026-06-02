package com.automation.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.automation.app.AutomationApp
import com.automation.app.data.db.entities.AutomationEntity
import com.automation.app.data.repository.AutomationRepository
import com.automation.app.triggers.TriggerScheduler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AutomationViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = AutomationRepository(
        (application as AutomationApp).database.automationDao()
    )

    private val _automations = MutableStateFlow<List<AutomationEntity>>(emptyList())
    val automations: StateFlow<List<AutomationEntity>> = _automations.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            repo.allAutomations.collect { list ->
                _automations.value = list
            }
        }
    }

    fun saveAutomation(automation: AutomationEntity) {
        viewModelScope.launch {
            _isLoading.value = true
            val id = if (automation.id == 0L) {
                repo.insert(automation)
            } else {
                repo.update(automation)
                automation.id
            }
            if (automation.enabled) {
                TriggerScheduler.schedule(getApplication(), repo, id)
            }
            _isLoading.value = false
        }
    }

    fun toggleEnabled(id: Long, enabled: Boolean) {
        viewModelScope.launch {
            repo.setEnabled(id, enabled)
            if (enabled) {
                TriggerScheduler.schedule(getApplication(), repo, id)
            } else {
                TriggerScheduler.cancel(getApplication(), id)
            }
        }
    }

    fun deleteAutomation(automation: AutomationEntity) {
        viewModelScope.launch {
            repo.delete(automation)
            TriggerScheduler.cancel(getApplication(), automation.id)
        }
    }

    suspend fun getAutomation(id: Long): AutomationEntity? = repo.getAutomation(id)
}
