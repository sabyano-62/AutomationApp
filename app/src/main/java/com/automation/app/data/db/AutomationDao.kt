package com.automation.app.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.automation.app.data.db.entities.AutomationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AutomationDao {
    @Query("SELECT * FROM automations ORDER BY createdAt DESC")
    fun getAllAutomations(): Flow<List<AutomationEntity>>

    @Query("SELECT * FROM automations WHERE id = :id")
    suspend fun getAutomationById(id: Long): AutomationEntity?

    @Query("SELECT * FROM automations WHERE enabled = 1")
    suspend fun getEnabledAutomations(): List<AutomationEntity>

    @Query("SELECT * FROM automations WHERE triggerType = :triggerType AND enabled = 1")
    suspend fun getEnabledAutomationsByTriggerType(triggerType: String): List<AutomationEntity>

    @Query("SELECT * FROM automations WHERE enabled = 1 AND triggerType IN (:triggerTypes)")
    suspend fun getEnabledAutomationsByTriggerTypes(triggerTypes: List<String>): List<AutomationEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(automation: AutomationEntity): Long

    @Update
    suspend fun update(automation: AutomationEntity)

    @Delete
    suspend fun delete(automation: AutomationEntity)

    @Query("UPDATE automations SET enabled = :enabled WHERE id = :id")
    suspend fun setEnabled(id: Long, enabled: Boolean)

    @Query("SELECT * FROM automations ORDER BY createdAt DESC")
    suspend fun getAllAutomationsList(): List<AutomationEntity>
}
