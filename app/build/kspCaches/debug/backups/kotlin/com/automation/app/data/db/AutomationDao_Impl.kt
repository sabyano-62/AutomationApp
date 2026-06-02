package com.automation.app.`data`.db

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.appendPlaceholders
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.automation.app.`data`.db.entities.AutomationEntity
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Double
import kotlin.Float
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlin.text.StringBuilder
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class AutomationDao_Impl(
  __db: RoomDatabase,
) : AutomationDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfAutomationEntity: EntityInsertAdapter<AutomationEntity>

  private val __deleteAdapterOfAutomationEntity: EntityDeleteOrUpdateAdapter<AutomationEntity>

  private val __updateAdapterOfAutomationEntity: EntityDeleteOrUpdateAdapter<AutomationEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfAutomationEntity = object : EntityInsertAdapter<AutomationEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `automations` (`id`,`name`,`enabled`,`triggerType`,`triggerHour`,`triggerMinute`,`triggerDaysOfWeek`,`triggerLatitude`,`triggerLongitude`,`triggerRadius`,`triggerSsid`,`triggerBatteryLevel`,`triggerBatteryCharging`,`actionType`,`actionPhoneNumber`,`actionMessage`,`actionNotificationTitle`,`actionNotificationBody`,`actionToggleOn`,`actionLevel`,`actionPackageName`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: AutomationEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.name)
        val _tmp: Int = if (entity.enabled) 1 else 0
        statement.bindLong(3, _tmp.toLong())
        statement.bindText(4, entity.triggerType)
        val _tmpTriggerHour: Int? = entity.triggerHour
        if (_tmpTriggerHour == null) {
          statement.bindNull(5)
        } else {
          statement.bindLong(5, _tmpTriggerHour.toLong())
        }
        val _tmpTriggerMinute: Int? = entity.triggerMinute
        if (_tmpTriggerMinute == null) {
          statement.bindNull(6)
        } else {
          statement.bindLong(6, _tmpTriggerMinute.toLong())
        }
        val _tmpTriggerDaysOfWeek: String? = entity.triggerDaysOfWeek
        if (_tmpTriggerDaysOfWeek == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpTriggerDaysOfWeek)
        }
        val _tmpTriggerLatitude: Double? = entity.triggerLatitude
        if (_tmpTriggerLatitude == null) {
          statement.bindNull(8)
        } else {
          statement.bindDouble(8, _tmpTriggerLatitude)
        }
        val _tmpTriggerLongitude: Double? = entity.triggerLongitude
        if (_tmpTriggerLongitude == null) {
          statement.bindNull(9)
        } else {
          statement.bindDouble(9, _tmpTriggerLongitude)
        }
        val _tmpTriggerRadius: Float? = entity.triggerRadius
        if (_tmpTriggerRadius == null) {
          statement.bindNull(10)
        } else {
          statement.bindDouble(10, _tmpTriggerRadius.toDouble())
        }
        val _tmpTriggerSsid: String? = entity.triggerSsid
        if (_tmpTriggerSsid == null) {
          statement.bindNull(11)
        } else {
          statement.bindText(11, _tmpTriggerSsid)
        }
        val _tmpTriggerBatteryLevel: Int? = entity.triggerBatteryLevel
        if (_tmpTriggerBatteryLevel == null) {
          statement.bindNull(12)
        } else {
          statement.bindLong(12, _tmpTriggerBatteryLevel.toLong())
        }
        val _tmpTriggerBatteryCharging: Boolean? = entity.triggerBatteryCharging
        val _tmp_1: Int? = _tmpTriggerBatteryCharging?.let { if (it) 1 else 0 }
        if (_tmp_1 == null) {
          statement.bindNull(13)
        } else {
          statement.bindLong(13, _tmp_1.toLong())
        }
        statement.bindText(14, entity.actionType)
        val _tmpActionPhoneNumber: String? = entity.actionPhoneNumber
        if (_tmpActionPhoneNumber == null) {
          statement.bindNull(15)
        } else {
          statement.bindText(15, _tmpActionPhoneNumber)
        }
        val _tmpActionMessage: String? = entity.actionMessage
        if (_tmpActionMessage == null) {
          statement.bindNull(16)
        } else {
          statement.bindText(16, _tmpActionMessage)
        }
        val _tmpActionNotificationTitle: String? = entity.actionNotificationTitle
        if (_tmpActionNotificationTitle == null) {
          statement.bindNull(17)
        } else {
          statement.bindText(17, _tmpActionNotificationTitle)
        }
        val _tmpActionNotificationBody: String? = entity.actionNotificationBody
        if (_tmpActionNotificationBody == null) {
          statement.bindNull(18)
        } else {
          statement.bindText(18, _tmpActionNotificationBody)
        }
        val _tmpActionToggleOn: Boolean? = entity.actionToggleOn
        val _tmp_2: Int? = _tmpActionToggleOn?.let { if (it) 1 else 0 }
        if (_tmp_2 == null) {
          statement.bindNull(19)
        } else {
          statement.bindLong(19, _tmp_2.toLong())
        }
        val _tmpActionLevel: Int? = entity.actionLevel
        if (_tmpActionLevel == null) {
          statement.bindNull(20)
        } else {
          statement.bindLong(20, _tmpActionLevel.toLong())
        }
        val _tmpActionPackageName: String? = entity.actionPackageName
        if (_tmpActionPackageName == null) {
          statement.bindNull(21)
        } else {
          statement.bindText(21, _tmpActionPackageName)
        }
        statement.bindLong(22, entity.createdAt)
      }
    }
    this.__deleteAdapterOfAutomationEntity = object : EntityDeleteOrUpdateAdapter<AutomationEntity>() {
      protected override fun createQuery(): String = "DELETE FROM `automations` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: AutomationEntity) {
        statement.bindLong(1, entity.id)
      }
    }
    this.__updateAdapterOfAutomationEntity = object : EntityDeleteOrUpdateAdapter<AutomationEntity>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `automations` SET `id` = ?,`name` = ?,`enabled` = ?,`triggerType` = ?,`triggerHour` = ?,`triggerMinute` = ?,`triggerDaysOfWeek` = ?,`triggerLatitude` = ?,`triggerLongitude` = ?,`triggerRadius` = ?,`triggerSsid` = ?,`triggerBatteryLevel` = ?,`triggerBatteryCharging` = ?,`actionType` = ?,`actionPhoneNumber` = ?,`actionMessage` = ?,`actionNotificationTitle` = ?,`actionNotificationBody` = ?,`actionToggleOn` = ?,`actionLevel` = ?,`actionPackageName` = ?,`createdAt` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: AutomationEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.name)
        val _tmp: Int = if (entity.enabled) 1 else 0
        statement.bindLong(3, _tmp.toLong())
        statement.bindText(4, entity.triggerType)
        val _tmpTriggerHour: Int? = entity.triggerHour
        if (_tmpTriggerHour == null) {
          statement.bindNull(5)
        } else {
          statement.bindLong(5, _tmpTriggerHour.toLong())
        }
        val _tmpTriggerMinute: Int? = entity.triggerMinute
        if (_tmpTriggerMinute == null) {
          statement.bindNull(6)
        } else {
          statement.bindLong(6, _tmpTriggerMinute.toLong())
        }
        val _tmpTriggerDaysOfWeek: String? = entity.triggerDaysOfWeek
        if (_tmpTriggerDaysOfWeek == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpTriggerDaysOfWeek)
        }
        val _tmpTriggerLatitude: Double? = entity.triggerLatitude
        if (_tmpTriggerLatitude == null) {
          statement.bindNull(8)
        } else {
          statement.bindDouble(8, _tmpTriggerLatitude)
        }
        val _tmpTriggerLongitude: Double? = entity.triggerLongitude
        if (_tmpTriggerLongitude == null) {
          statement.bindNull(9)
        } else {
          statement.bindDouble(9, _tmpTriggerLongitude)
        }
        val _tmpTriggerRadius: Float? = entity.triggerRadius
        if (_tmpTriggerRadius == null) {
          statement.bindNull(10)
        } else {
          statement.bindDouble(10, _tmpTriggerRadius.toDouble())
        }
        val _tmpTriggerSsid: String? = entity.triggerSsid
        if (_tmpTriggerSsid == null) {
          statement.bindNull(11)
        } else {
          statement.bindText(11, _tmpTriggerSsid)
        }
        val _tmpTriggerBatteryLevel: Int? = entity.triggerBatteryLevel
        if (_tmpTriggerBatteryLevel == null) {
          statement.bindNull(12)
        } else {
          statement.bindLong(12, _tmpTriggerBatteryLevel.toLong())
        }
        val _tmpTriggerBatteryCharging: Boolean? = entity.triggerBatteryCharging
        val _tmp_1: Int? = _tmpTriggerBatteryCharging?.let { if (it) 1 else 0 }
        if (_tmp_1 == null) {
          statement.bindNull(13)
        } else {
          statement.bindLong(13, _tmp_1.toLong())
        }
        statement.bindText(14, entity.actionType)
        val _tmpActionPhoneNumber: String? = entity.actionPhoneNumber
        if (_tmpActionPhoneNumber == null) {
          statement.bindNull(15)
        } else {
          statement.bindText(15, _tmpActionPhoneNumber)
        }
        val _tmpActionMessage: String? = entity.actionMessage
        if (_tmpActionMessage == null) {
          statement.bindNull(16)
        } else {
          statement.bindText(16, _tmpActionMessage)
        }
        val _tmpActionNotificationTitle: String? = entity.actionNotificationTitle
        if (_tmpActionNotificationTitle == null) {
          statement.bindNull(17)
        } else {
          statement.bindText(17, _tmpActionNotificationTitle)
        }
        val _tmpActionNotificationBody: String? = entity.actionNotificationBody
        if (_tmpActionNotificationBody == null) {
          statement.bindNull(18)
        } else {
          statement.bindText(18, _tmpActionNotificationBody)
        }
        val _tmpActionToggleOn: Boolean? = entity.actionToggleOn
        val _tmp_2: Int? = _tmpActionToggleOn?.let { if (it) 1 else 0 }
        if (_tmp_2 == null) {
          statement.bindNull(19)
        } else {
          statement.bindLong(19, _tmp_2.toLong())
        }
        val _tmpActionLevel: Int? = entity.actionLevel
        if (_tmpActionLevel == null) {
          statement.bindNull(20)
        } else {
          statement.bindLong(20, _tmpActionLevel.toLong())
        }
        val _tmpActionPackageName: String? = entity.actionPackageName
        if (_tmpActionPackageName == null) {
          statement.bindNull(21)
        } else {
          statement.bindText(21, _tmpActionPackageName)
        }
        statement.bindLong(22, entity.createdAt)
        statement.bindLong(23, entity.id)
      }
    }
  }

  public override suspend fun insert(automation: AutomationEntity): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfAutomationEntity.insertAndReturnId(_connection, automation)
    _result
  }

  public override suspend fun delete(automation: AutomationEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfAutomationEntity.handle(_connection, automation)
  }

  public override suspend fun update(automation: AutomationEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfAutomationEntity.handle(_connection, automation)
  }

  public override fun getAllAutomations(): Flow<List<AutomationEntity>> {
    val _sql: String = "SELECT * FROM automations ORDER BY createdAt DESC"
    return createFlow(__db, false, arrayOf("automations")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfEnabled: Int = getColumnIndexOrThrow(_stmt, "enabled")
        val _columnIndexOfTriggerType: Int = getColumnIndexOrThrow(_stmt, "triggerType")
        val _columnIndexOfTriggerHour: Int = getColumnIndexOrThrow(_stmt, "triggerHour")
        val _columnIndexOfTriggerMinute: Int = getColumnIndexOrThrow(_stmt, "triggerMinute")
        val _columnIndexOfTriggerDaysOfWeek: Int = getColumnIndexOrThrow(_stmt, "triggerDaysOfWeek")
        val _columnIndexOfTriggerLatitude: Int = getColumnIndexOrThrow(_stmt, "triggerLatitude")
        val _columnIndexOfTriggerLongitude: Int = getColumnIndexOrThrow(_stmt, "triggerLongitude")
        val _columnIndexOfTriggerRadius: Int = getColumnIndexOrThrow(_stmt, "triggerRadius")
        val _columnIndexOfTriggerSsid: Int = getColumnIndexOrThrow(_stmt, "triggerSsid")
        val _columnIndexOfTriggerBatteryLevel: Int = getColumnIndexOrThrow(_stmt, "triggerBatteryLevel")
        val _columnIndexOfTriggerBatteryCharging: Int = getColumnIndexOrThrow(_stmt, "triggerBatteryCharging")
        val _columnIndexOfActionType: Int = getColumnIndexOrThrow(_stmt, "actionType")
        val _columnIndexOfActionPhoneNumber: Int = getColumnIndexOrThrow(_stmt, "actionPhoneNumber")
        val _columnIndexOfActionMessage: Int = getColumnIndexOrThrow(_stmt, "actionMessage")
        val _columnIndexOfActionNotificationTitle: Int = getColumnIndexOrThrow(_stmt, "actionNotificationTitle")
        val _columnIndexOfActionNotificationBody: Int = getColumnIndexOrThrow(_stmt, "actionNotificationBody")
        val _columnIndexOfActionToggleOn: Int = getColumnIndexOrThrow(_stmt, "actionToggleOn")
        val _columnIndexOfActionLevel: Int = getColumnIndexOrThrow(_stmt, "actionLevel")
        val _columnIndexOfActionPackageName: Int = getColumnIndexOrThrow(_stmt, "actionPackageName")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _result: MutableList<AutomationEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AutomationEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpEnabled: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEnabled).toInt()
          _tmpEnabled = _tmp != 0
          val _tmpTriggerType: String
          _tmpTriggerType = _stmt.getText(_columnIndexOfTriggerType)
          val _tmpTriggerHour: Int?
          if (_stmt.isNull(_columnIndexOfTriggerHour)) {
            _tmpTriggerHour = null
          } else {
            _tmpTriggerHour = _stmt.getLong(_columnIndexOfTriggerHour).toInt()
          }
          val _tmpTriggerMinute: Int?
          if (_stmt.isNull(_columnIndexOfTriggerMinute)) {
            _tmpTriggerMinute = null
          } else {
            _tmpTriggerMinute = _stmt.getLong(_columnIndexOfTriggerMinute).toInt()
          }
          val _tmpTriggerDaysOfWeek: String?
          if (_stmt.isNull(_columnIndexOfTriggerDaysOfWeek)) {
            _tmpTriggerDaysOfWeek = null
          } else {
            _tmpTriggerDaysOfWeek = _stmt.getText(_columnIndexOfTriggerDaysOfWeek)
          }
          val _tmpTriggerLatitude: Double?
          if (_stmt.isNull(_columnIndexOfTriggerLatitude)) {
            _tmpTriggerLatitude = null
          } else {
            _tmpTriggerLatitude = _stmt.getDouble(_columnIndexOfTriggerLatitude)
          }
          val _tmpTriggerLongitude: Double?
          if (_stmt.isNull(_columnIndexOfTriggerLongitude)) {
            _tmpTriggerLongitude = null
          } else {
            _tmpTriggerLongitude = _stmt.getDouble(_columnIndexOfTriggerLongitude)
          }
          val _tmpTriggerRadius: Float?
          if (_stmt.isNull(_columnIndexOfTriggerRadius)) {
            _tmpTriggerRadius = null
          } else {
            _tmpTriggerRadius = _stmt.getDouble(_columnIndexOfTriggerRadius).toFloat()
          }
          val _tmpTriggerSsid: String?
          if (_stmt.isNull(_columnIndexOfTriggerSsid)) {
            _tmpTriggerSsid = null
          } else {
            _tmpTriggerSsid = _stmt.getText(_columnIndexOfTriggerSsid)
          }
          val _tmpTriggerBatteryLevel: Int?
          if (_stmt.isNull(_columnIndexOfTriggerBatteryLevel)) {
            _tmpTriggerBatteryLevel = null
          } else {
            _tmpTriggerBatteryLevel = _stmt.getLong(_columnIndexOfTriggerBatteryLevel).toInt()
          }
          val _tmpTriggerBatteryCharging: Boolean?
          val _tmp_1: Int?
          if (_stmt.isNull(_columnIndexOfTriggerBatteryCharging)) {
            _tmp_1 = null
          } else {
            _tmp_1 = _stmt.getLong(_columnIndexOfTriggerBatteryCharging).toInt()
          }
          _tmpTriggerBatteryCharging = _tmp_1?.let { it != 0 }
          val _tmpActionType: String
          _tmpActionType = _stmt.getText(_columnIndexOfActionType)
          val _tmpActionPhoneNumber: String?
          if (_stmt.isNull(_columnIndexOfActionPhoneNumber)) {
            _tmpActionPhoneNumber = null
          } else {
            _tmpActionPhoneNumber = _stmt.getText(_columnIndexOfActionPhoneNumber)
          }
          val _tmpActionMessage: String?
          if (_stmt.isNull(_columnIndexOfActionMessage)) {
            _tmpActionMessage = null
          } else {
            _tmpActionMessage = _stmt.getText(_columnIndexOfActionMessage)
          }
          val _tmpActionNotificationTitle: String?
          if (_stmt.isNull(_columnIndexOfActionNotificationTitle)) {
            _tmpActionNotificationTitle = null
          } else {
            _tmpActionNotificationTitle = _stmt.getText(_columnIndexOfActionNotificationTitle)
          }
          val _tmpActionNotificationBody: String?
          if (_stmt.isNull(_columnIndexOfActionNotificationBody)) {
            _tmpActionNotificationBody = null
          } else {
            _tmpActionNotificationBody = _stmt.getText(_columnIndexOfActionNotificationBody)
          }
          val _tmpActionToggleOn: Boolean?
          val _tmp_2: Int?
          if (_stmt.isNull(_columnIndexOfActionToggleOn)) {
            _tmp_2 = null
          } else {
            _tmp_2 = _stmt.getLong(_columnIndexOfActionToggleOn).toInt()
          }
          _tmpActionToggleOn = _tmp_2?.let { it != 0 }
          val _tmpActionLevel: Int?
          if (_stmt.isNull(_columnIndexOfActionLevel)) {
            _tmpActionLevel = null
          } else {
            _tmpActionLevel = _stmt.getLong(_columnIndexOfActionLevel).toInt()
          }
          val _tmpActionPackageName: String?
          if (_stmt.isNull(_columnIndexOfActionPackageName)) {
            _tmpActionPackageName = null
          } else {
            _tmpActionPackageName = _stmt.getText(_columnIndexOfActionPackageName)
          }
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          _item = AutomationEntity(_tmpId,_tmpName,_tmpEnabled,_tmpTriggerType,_tmpTriggerHour,_tmpTriggerMinute,_tmpTriggerDaysOfWeek,_tmpTriggerLatitude,_tmpTriggerLongitude,_tmpTriggerRadius,_tmpTriggerSsid,_tmpTriggerBatteryLevel,_tmpTriggerBatteryCharging,_tmpActionType,_tmpActionPhoneNumber,_tmpActionMessage,_tmpActionNotificationTitle,_tmpActionNotificationBody,_tmpActionToggleOn,_tmpActionLevel,_tmpActionPackageName,_tmpCreatedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAutomationById(id: Long): AutomationEntity? {
    val _sql: String = "SELECT * FROM automations WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfEnabled: Int = getColumnIndexOrThrow(_stmt, "enabled")
        val _columnIndexOfTriggerType: Int = getColumnIndexOrThrow(_stmt, "triggerType")
        val _columnIndexOfTriggerHour: Int = getColumnIndexOrThrow(_stmt, "triggerHour")
        val _columnIndexOfTriggerMinute: Int = getColumnIndexOrThrow(_stmt, "triggerMinute")
        val _columnIndexOfTriggerDaysOfWeek: Int = getColumnIndexOrThrow(_stmt, "triggerDaysOfWeek")
        val _columnIndexOfTriggerLatitude: Int = getColumnIndexOrThrow(_stmt, "triggerLatitude")
        val _columnIndexOfTriggerLongitude: Int = getColumnIndexOrThrow(_stmt, "triggerLongitude")
        val _columnIndexOfTriggerRadius: Int = getColumnIndexOrThrow(_stmt, "triggerRadius")
        val _columnIndexOfTriggerSsid: Int = getColumnIndexOrThrow(_stmt, "triggerSsid")
        val _columnIndexOfTriggerBatteryLevel: Int = getColumnIndexOrThrow(_stmt, "triggerBatteryLevel")
        val _columnIndexOfTriggerBatteryCharging: Int = getColumnIndexOrThrow(_stmt, "triggerBatteryCharging")
        val _columnIndexOfActionType: Int = getColumnIndexOrThrow(_stmt, "actionType")
        val _columnIndexOfActionPhoneNumber: Int = getColumnIndexOrThrow(_stmt, "actionPhoneNumber")
        val _columnIndexOfActionMessage: Int = getColumnIndexOrThrow(_stmt, "actionMessage")
        val _columnIndexOfActionNotificationTitle: Int = getColumnIndexOrThrow(_stmt, "actionNotificationTitle")
        val _columnIndexOfActionNotificationBody: Int = getColumnIndexOrThrow(_stmt, "actionNotificationBody")
        val _columnIndexOfActionToggleOn: Int = getColumnIndexOrThrow(_stmt, "actionToggleOn")
        val _columnIndexOfActionLevel: Int = getColumnIndexOrThrow(_stmt, "actionLevel")
        val _columnIndexOfActionPackageName: Int = getColumnIndexOrThrow(_stmt, "actionPackageName")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _result: AutomationEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpEnabled: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEnabled).toInt()
          _tmpEnabled = _tmp != 0
          val _tmpTriggerType: String
          _tmpTriggerType = _stmt.getText(_columnIndexOfTriggerType)
          val _tmpTriggerHour: Int?
          if (_stmt.isNull(_columnIndexOfTriggerHour)) {
            _tmpTriggerHour = null
          } else {
            _tmpTriggerHour = _stmt.getLong(_columnIndexOfTriggerHour).toInt()
          }
          val _tmpTriggerMinute: Int?
          if (_stmt.isNull(_columnIndexOfTriggerMinute)) {
            _tmpTriggerMinute = null
          } else {
            _tmpTriggerMinute = _stmt.getLong(_columnIndexOfTriggerMinute).toInt()
          }
          val _tmpTriggerDaysOfWeek: String?
          if (_stmt.isNull(_columnIndexOfTriggerDaysOfWeek)) {
            _tmpTriggerDaysOfWeek = null
          } else {
            _tmpTriggerDaysOfWeek = _stmt.getText(_columnIndexOfTriggerDaysOfWeek)
          }
          val _tmpTriggerLatitude: Double?
          if (_stmt.isNull(_columnIndexOfTriggerLatitude)) {
            _tmpTriggerLatitude = null
          } else {
            _tmpTriggerLatitude = _stmt.getDouble(_columnIndexOfTriggerLatitude)
          }
          val _tmpTriggerLongitude: Double?
          if (_stmt.isNull(_columnIndexOfTriggerLongitude)) {
            _tmpTriggerLongitude = null
          } else {
            _tmpTriggerLongitude = _stmt.getDouble(_columnIndexOfTriggerLongitude)
          }
          val _tmpTriggerRadius: Float?
          if (_stmt.isNull(_columnIndexOfTriggerRadius)) {
            _tmpTriggerRadius = null
          } else {
            _tmpTriggerRadius = _stmt.getDouble(_columnIndexOfTriggerRadius).toFloat()
          }
          val _tmpTriggerSsid: String?
          if (_stmt.isNull(_columnIndexOfTriggerSsid)) {
            _tmpTriggerSsid = null
          } else {
            _tmpTriggerSsid = _stmt.getText(_columnIndexOfTriggerSsid)
          }
          val _tmpTriggerBatteryLevel: Int?
          if (_stmt.isNull(_columnIndexOfTriggerBatteryLevel)) {
            _tmpTriggerBatteryLevel = null
          } else {
            _tmpTriggerBatteryLevel = _stmt.getLong(_columnIndexOfTriggerBatteryLevel).toInt()
          }
          val _tmpTriggerBatteryCharging: Boolean?
          val _tmp_1: Int?
          if (_stmt.isNull(_columnIndexOfTriggerBatteryCharging)) {
            _tmp_1 = null
          } else {
            _tmp_1 = _stmt.getLong(_columnIndexOfTriggerBatteryCharging).toInt()
          }
          _tmpTriggerBatteryCharging = _tmp_1?.let { it != 0 }
          val _tmpActionType: String
          _tmpActionType = _stmt.getText(_columnIndexOfActionType)
          val _tmpActionPhoneNumber: String?
          if (_stmt.isNull(_columnIndexOfActionPhoneNumber)) {
            _tmpActionPhoneNumber = null
          } else {
            _tmpActionPhoneNumber = _stmt.getText(_columnIndexOfActionPhoneNumber)
          }
          val _tmpActionMessage: String?
          if (_stmt.isNull(_columnIndexOfActionMessage)) {
            _tmpActionMessage = null
          } else {
            _tmpActionMessage = _stmt.getText(_columnIndexOfActionMessage)
          }
          val _tmpActionNotificationTitle: String?
          if (_stmt.isNull(_columnIndexOfActionNotificationTitle)) {
            _tmpActionNotificationTitle = null
          } else {
            _tmpActionNotificationTitle = _stmt.getText(_columnIndexOfActionNotificationTitle)
          }
          val _tmpActionNotificationBody: String?
          if (_stmt.isNull(_columnIndexOfActionNotificationBody)) {
            _tmpActionNotificationBody = null
          } else {
            _tmpActionNotificationBody = _stmt.getText(_columnIndexOfActionNotificationBody)
          }
          val _tmpActionToggleOn: Boolean?
          val _tmp_2: Int?
          if (_stmt.isNull(_columnIndexOfActionToggleOn)) {
            _tmp_2 = null
          } else {
            _tmp_2 = _stmt.getLong(_columnIndexOfActionToggleOn).toInt()
          }
          _tmpActionToggleOn = _tmp_2?.let { it != 0 }
          val _tmpActionLevel: Int?
          if (_stmt.isNull(_columnIndexOfActionLevel)) {
            _tmpActionLevel = null
          } else {
            _tmpActionLevel = _stmt.getLong(_columnIndexOfActionLevel).toInt()
          }
          val _tmpActionPackageName: String?
          if (_stmt.isNull(_columnIndexOfActionPackageName)) {
            _tmpActionPackageName = null
          } else {
            _tmpActionPackageName = _stmt.getText(_columnIndexOfActionPackageName)
          }
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          _result = AutomationEntity(_tmpId,_tmpName,_tmpEnabled,_tmpTriggerType,_tmpTriggerHour,_tmpTriggerMinute,_tmpTriggerDaysOfWeek,_tmpTriggerLatitude,_tmpTriggerLongitude,_tmpTriggerRadius,_tmpTriggerSsid,_tmpTriggerBatteryLevel,_tmpTriggerBatteryCharging,_tmpActionType,_tmpActionPhoneNumber,_tmpActionMessage,_tmpActionNotificationTitle,_tmpActionNotificationBody,_tmpActionToggleOn,_tmpActionLevel,_tmpActionPackageName,_tmpCreatedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getEnabledAutomations(): List<AutomationEntity> {
    val _sql: String = "SELECT * FROM automations WHERE enabled = 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfEnabled: Int = getColumnIndexOrThrow(_stmt, "enabled")
        val _columnIndexOfTriggerType: Int = getColumnIndexOrThrow(_stmt, "triggerType")
        val _columnIndexOfTriggerHour: Int = getColumnIndexOrThrow(_stmt, "triggerHour")
        val _columnIndexOfTriggerMinute: Int = getColumnIndexOrThrow(_stmt, "triggerMinute")
        val _columnIndexOfTriggerDaysOfWeek: Int = getColumnIndexOrThrow(_stmt, "triggerDaysOfWeek")
        val _columnIndexOfTriggerLatitude: Int = getColumnIndexOrThrow(_stmt, "triggerLatitude")
        val _columnIndexOfTriggerLongitude: Int = getColumnIndexOrThrow(_stmt, "triggerLongitude")
        val _columnIndexOfTriggerRadius: Int = getColumnIndexOrThrow(_stmt, "triggerRadius")
        val _columnIndexOfTriggerSsid: Int = getColumnIndexOrThrow(_stmt, "triggerSsid")
        val _columnIndexOfTriggerBatteryLevel: Int = getColumnIndexOrThrow(_stmt, "triggerBatteryLevel")
        val _columnIndexOfTriggerBatteryCharging: Int = getColumnIndexOrThrow(_stmt, "triggerBatteryCharging")
        val _columnIndexOfActionType: Int = getColumnIndexOrThrow(_stmt, "actionType")
        val _columnIndexOfActionPhoneNumber: Int = getColumnIndexOrThrow(_stmt, "actionPhoneNumber")
        val _columnIndexOfActionMessage: Int = getColumnIndexOrThrow(_stmt, "actionMessage")
        val _columnIndexOfActionNotificationTitle: Int = getColumnIndexOrThrow(_stmt, "actionNotificationTitle")
        val _columnIndexOfActionNotificationBody: Int = getColumnIndexOrThrow(_stmt, "actionNotificationBody")
        val _columnIndexOfActionToggleOn: Int = getColumnIndexOrThrow(_stmt, "actionToggleOn")
        val _columnIndexOfActionLevel: Int = getColumnIndexOrThrow(_stmt, "actionLevel")
        val _columnIndexOfActionPackageName: Int = getColumnIndexOrThrow(_stmt, "actionPackageName")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _result: MutableList<AutomationEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AutomationEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpEnabled: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEnabled).toInt()
          _tmpEnabled = _tmp != 0
          val _tmpTriggerType: String
          _tmpTriggerType = _stmt.getText(_columnIndexOfTriggerType)
          val _tmpTriggerHour: Int?
          if (_stmt.isNull(_columnIndexOfTriggerHour)) {
            _tmpTriggerHour = null
          } else {
            _tmpTriggerHour = _stmt.getLong(_columnIndexOfTriggerHour).toInt()
          }
          val _tmpTriggerMinute: Int?
          if (_stmt.isNull(_columnIndexOfTriggerMinute)) {
            _tmpTriggerMinute = null
          } else {
            _tmpTriggerMinute = _stmt.getLong(_columnIndexOfTriggerMinute).toInt()
          }
          val _tmpTriggerDaysOfWeek: String?
          if (_stmt.isNull(_columnIndexOfTriggerDaysOfWeek)) {
            _tmpTriggerDaysOfWeek = null
          } else {
            _tmpTriggerDaysOfWeek = _stmt.getText(_columnIndexOfTriggerDaysOfWeek)
          }
          val _tmpTriggerLatitude: Double?
          if (_stmt.isNull(_columnIndexOfTriggerLatitude)) {
            _tmpTriggerLatitude = null
          } else {
            _tmpTriggerLatitude = _stmt.getDouble(_columnIndexOfTriggerLatitude)
          }
          val _tmpTriggerLongitude: Double?
          if (_stmt.isNull(_columnIndexOfTriggerLongitude)) {
            _tmpTriggerLongitude = null
          } else {
            _tmpTriggerLongitude = _stmt.getDouble(_columnIndexOfTriggerLongitude)
          }
          val _tmpTriggerRadius: Float?
          if (_stmt.isNull(_columnIndexOfTriggerRadius)) {
            _tmpTriggerRadius = null
          } else {
            _tmpTriggerRadius = _stmt.getDouble(_columnIndexOfTriggerRadius).toFloat()
          }
          val _tmpTriggerSsid: String?
          if (_stmt.isNull(_columnIndexOfTriggerSsid)) {
            _tmpTriggerSsid = null
          } else {
            _tmpTriggerSsid = _stmt.getText(_columnIndexOfTriggerSsid)
          }
          val _tmpTriggerBatteryLevel: Int?
          if (_stmt.isNull(_columnIndexOfTriggerBatteryLevel)) {
            _tmpTriggerBatteryLevel = null
          } else {
            _tmpTriggerBatteryLevel = _stmt.getLong(_columnIndexOfTriggerBatteryLevel).toInt()
          }
          val _tmpTriggerBatteryCharging: Boolean?
          val _tmp_1: Int?
          if (_stmt.isNull(_columnIndexOfTriggerBatteryCharging)) {
            _tmp_1 = null
          } else {
            _tmp_1 = _stmt.getLong(_columnIndexOfTriggerBatteryCharging).toInt()
          }
          _tmpTriggerBatteryCharging = _tmp_1?.let { it != 0 }
          val _tmpActionType: String
          _tmpActionType = _stmt.getText(_columnIndexOfActionType)
          val _tmpActionPhoneNumber: String?
          if (_stmt.isNull(_columnIndexOfActionPhoneNumber)) {
            _tmpActionPhoneNumber = null
          } else {
            _tmpActionPhoneNumber = _stmt.getText(_columnIndexOfActionPhoneNumber)
          }
          val _tmpActionMessage: String?
          if (_stmt.isNull(_columnIndexOfActionMessage)) {
            _tmpActionMessage = null
          } else {
            _tmpActionMessage = _stmt.getText(_columnIndexOfActionMessage)
          }
          val _tmpActionNotificationTitle: String?
          if (_stmt.isNull(_columnIndexOfActionNotificationTitle)) {
            _tmpActionNotificationTitle = null
          } else {
            _tmpActionNotificationTitle = _stmt.getText(_columnIndexOfActionNotificationTitle)
          }
          val _tmpActionNotificationBody: String?
          if (_stmt.isNull(_columnIndexOfActionNotificationBody)) {
            _tmpActionNotificationBody = null
          } else {
            _tmpActionNotificationBody = _stmt.getText(_columnIndexOfActionNotificationBody)
          }
          val _tmpActionToggleOn: Boolean?
          val _tmp_2: Int?
          if (_stmt.isNull(_columnIndexOfActionToggleOn)) {
            _tmp_2 = null
          } else {
            _tmp_2 = _stmt.getLong(_columnIndexOfActionToggleOn).toInt()
          }
          _tmpActionToggleOn = _tmp_2?.let { it != 0 }
          val _tmpActionLevel: Int?
          if (_stmt.isNull(_columnIndexOfActionLevel)) {
            _tmpActionLevel = null
          } else {
            _tmpActionLevel = _stmt.getLong(_columnIndexOfActionLevel).toInt()
          }
          val _tmpActionPackageName: String?
          if (_stmt.isNull(_columnIndexOfActionPackageName)) {
            _tmpActionPackageName = null
          } else {
            _tmpActionPackageName = _stmt.getText(_columnIndexOfActionPackageName)
          }
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          _item = AutomationEntity(_tmpId,_tmpName,_tmpEnabled,_tmpTriggerType,_tmpTriggerHour,_tmpTriggerMinute,_tmpTriggerDaysOfWeek,_tmpTriggerLatitude,_tmpTriggerLongitude,_tmpTriggerRadius,_tmpTriggerSsid,_tmpTriggerBatteryLevel,_tmpTriggerBatteryCharging,_tmpActionType,_tmpActionPhoneNumber,_tmpActionMessage,_tmpActionNotificationTitle,_tmpActionNotificationBody,_tmpActionToggleOn,_tmpActionLevel,_tmpActionPackageName,_tmpCreatedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getEnabledAutomationsByTriggerType(triggerType: String): List<AutomationEntity> {
    val _sql: String = "SELECT * FROM automations WHERE triggerType = ? AND enabled = 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, triggerType)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfEnabled: Int = getColumnIndexOrThrow(_stmt, "enabled")
        val _columnIndexOfTriggerType: Int = getColumnIndexOrThrow(_stmt, "triggerType")
        val _columnIndexOfTriggerHour: Int = getColumnIndexOrThrow(_stmt, "triggerHour")
        val _columnIndexOfTriggerMinute: Int = getColumnIndexOrThrow(_stmt, "triggerMinute")
        val _columnIndexOfTriggerDaysOfWeek: Int = getColumnIndexOrThrow(_stmt, "triggerDaysOfWeek")
        val _columnIndexOfTriggerLatitude: Int = getColumnIndexOrThrow(_stmt, "triggerLatitude")
        val _columnIndexOfTriggerLongitude: Int = getColumnIndexOrThrow(_stmt, "triggerLongitude")
        val _columnIndexOfTriggerRadius: Int = getColumnIndexOrThrow(_stmt, "triggerRadius")
        val _columnIndexOfTriggerSsid: Int = getColumnIndexOrThrow(_stmt, "triggerSsid")
        val _columnIndexOfTriggerBatteryLevel: Int = getColumnIndexOrThrow(_stmt, "triggerBatteryLevel")
        val _columnIndexOfTriggerBatteryCharging: Int = getColumnIndexOrThrow(_stmt, "triggerBatteryCharging")
        val _columnIndexOfActionType: Int = getColumnIndexOrThrow(_stmt, "actionType")
        val _columnIndexOfActionPhoneNumber: Int = getColumnIndexOrThrow(_stmt, "actionPhoneNumber")
        val _columnIndexOfActionMessage: Int = getColumnIndexOrThrow(_stmt, "actionMessage")
        val _columnIndexOfActionNotificationTitle: Int = getColumnIndexOrThrow(_stmt, "actionNotificationTitle")
        val _columnIndexOfActionNotificationBody: Int = getColumnIndexOrThrow(_stmt, "actionNotificationBody")
        val _columnIndexOfActionToggleOn: Int = getColumnIndexOrThrow(_stmt, "actionToggleOn")
        val _columnIndexOfActionLevel: Int = getColumnIndexOrThrow(_stmt, "actionLevel")
        val _columnIndexOfActionPackageName: Int = getColumnIndexOrThrow(_stmt, "actionPackageName")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _result: MutableList<AutomationEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AutomationEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpEnabled: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEnabled).toInt()
          _tmpEnabled = _tmp != 0
          val _tmpTriggerType: String
          _tmpTriggerType = _stmt.getText(_columnIndexOfTriggerType)
          val _tmpTriggerHour: Int?
          if (_stmt.isNull(_columnIndexOfTriggerHour)) {
            _tmpTriggerHour = null
          } else {
            _tmpTriggerHour = _stmt.getLong(_columnIndexOfTriggerHour).toInt()
          }
          val _tmpTriggerMinute: Int?
          if (_stmt.isNull(_columnIndexOfTriggerMinute)) {
            _tmpTriggerMinute = null
          } else {
            _tmpTriggerMinute = _stmt.getLong(_columnIndexOfTriggerMinute).toInt()
          }
          val _tmpTriggerDaysOfWeek: String?
          if (_stmt.isNull(_columnIndexOfTriggerDaysOfWeek)) {
            _tmpTriggerDaysOfWeek = null
          } else {
            _tmpTriggerDaysOfWeek = _stmt.getText(_columnIndexOfTriggerDaysOfWeek)
          }
          val _tmpTriggerLatitude: Double?
          if (_stmt.isNull(_columnIndexOfTriggerLatitude)) {
            _tmpTriggerLatitude = null
          } else {
            _tmpTriggerLatitude = _stmt.getDouble(_columnIndexOfTriggerLatitude)
          }
          val _tmpTriggerLongitude: Double?
          if (_stmt.isNull(_columnIndexOfTriggerLongitude)) {
            _tmpTriggerLongitude = null
          } else {
            _tmpTriggerLongitude = _stmt.getDouble(_columnIndexOfTriggerLongitude)
          }
          val _tmpTriggerRadius: Float?
          if (_stmt.isNull(_columnIndexOfTriggerRadius)) {
            _tmpTriggerRadius = null
          } else {
            _tmpTriggerRadius = _stmt.getDouble(_columnIndexOfTriggerRadius).toFloat()
          }
          val _tmpTriggerSsid: String?
          if (_stmt.isNull(_columnIndexOfTriggerSsid)) {
            _tmpTriggerSsid = null
          } else {
            _tmpTriggerSsid = _stmt.getText(_columnIndexOfTriggerSsid)
          }
          val _tmpTriggerBatteryLevel: Int?
          if (_stmt.isNull(_columnIndexOfTriggerBatteryLevel)) {
            _tmpTriggerBatteryLevel = null
          } else {
            _tmpTriggerBatteryLevel = _stmt.getLong(_columnIndexOfTriggerBatteryLevel).toInt()
          }
          val _tmpTriggerBatteryCharging: Boolean?
          val _tmp_1: Int?
          if (_stmt.isNull(_columnIndexOfTriggerBatteryCharging)) {
            _tmp_1 = null
          } else {
            _tmp_1 = _stmt.getLong(_columnIndexOfTriggerBatteryCharging).toInt()
          }
          _tmpTriggerBatteryCharging = _tmp_1?.let { it != 0 }
          val _tmpActionType: String
          _tmpActionType = _stmt.getText(_columnIndexOfActionType)
          val _tmpActionPhoneNumber: String?
          if (_stmt.isNull(_columnIndexOfActionPhoneNumber)) {
            _tmpActionPhoneNumber = null
          } else {
            _tmpActionPhoneNumber = _stmt.getText(_columnIndexOfActionPhoneNumber)
          }
          val _tmpActionMessage: String?
          if (_stmt.isNull(_columnIndexOfActionMessage)) {
            _tmpActionMessage = null
          } else {
            _tmpActionMessage = _stmt.getText(_columnIndexOfActionMessage)
          }
          val _tmpActionNotificationTitle: String?
          if (_stmt.isNull(_columnIndexOfActionNotificationTitle)) {
            _tmpActionNotificationTitle = null
          } else {
            _tmpActionNotificationTitle = _stmt.getText(_columnIndexOfActionNotificationTitle)
          }
          val _tmpActionNotificationBody: String?
          if (_stmt.isNull(_columnIndexOfActionNotificationBody)) {
            _tmpActionNotificationBody = null
          } else {
            _tmpActionNotificationBody = _stmt.getText(_columnIndexOfActionNotificationBody)
          }
          val _tmpActionToggleOn: Boolean?
          val _tmp_2: Int?
          if (_stmt.isNull(_columnIndexOfActionToggleOn)) {
            _tmp_2 = null
          } else {
            _tmp_2 = _stmt.getLong(_columnIndexOfActionToggleOn).toInt()
          }
          _tmpActionToggleOn = _tmp_2?.let { it != 0 }
          val _tmpActionLevel: Int?
          if (_stmt.isNull(_columnIndexOfActionLevel)) {
            _tmpActionLevel = null
          } else {
            _tmpActionLevel = _stmt.getLong(_columnIndexOfActionLevel).toInt()
          }
          val _tmpActionPackageName: String?
          if (_stmt.isNull(_columnIndexOfActionPackageName)) {
            _tmpActionPackageName = null
          } else {
            _tmpActionPackageName = _stmt.getText(_columnIndexOfActionPackageName)
          }
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          _item = AutomationEntity(_tmpId,_tmpName,_tmpEnabled,_tmpTriggerType,_tmpTriggerHour,_tmpTriggerMinute,_tmpTriggerDaysOfWeek,_tmpTriggerLatitude,_tmpTriggerLongitude,_tmpTriggerRadius,_tmpTriggerSsid,_tmpTriggerBatteryLevel,_tmpTriggerBatteryCharging,_tmpActionType,_tmpActionPhoneNumber,_tmpActionMessage,_tmpActionNotificationTitle,_tmpActionNotificationBody,_tmpActionToggleOn,_tmpActionLevel,_tmpActionPackageName,_tmpCreatedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getEnabledAutomationsByTriggerTypes(triggerTypes: List<String>): List<AutomationEntity> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("SELECT * FROM automations WHERE enabled = 1 AND triggerType IN (")
    val _inputSize: Int = triggerTypes.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    val _sql: String = _stringBuilder.toString()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        for (_item: String in triggerTypes) {
          _stmt.bindText(_argIndex, _item)
          _argIndex++
        }
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfEnabled: Int = getColumnIndexOrThrow(_stmt, "enabled")
        val _columnIndexOfTriggerType: Int = getColumnIndexOrThrow(_stmt, "triggerType")
        val _columnIndexOfTriggerHour: Int = getColumnIndexOrThrow(_stmt, "triggerHour")
        val _columnIndexOfTriggerMinute: Int = getColumnIndexOrThrow(_stmt, "triggerMinute")
        val _columnIndexOfTriggerDaysOfWeek: Int = getColumnIndexOrThrow(_stmt, "triggerDaysOfWeek")
        val _columnIndexOfTriggerLatitude: Int = getColumnIndexOrThrow(_stmt, "triggerLatitude")
        val _columnIndexOfTriggerLongitude: Int = getColumnIndexOrThrow(_stmt, "triggerLongitude")
        val _columnIndexOfTriggerRadius: Int = getColumnIndexOrThrow(_stmt, "triggerRadius")
        val _columnIndexOfTriggerSsid: Int = getColumnIndexOrThrow(_stmt, "triggerSsid")
        val _columnIndexOfTriggerBatteryLevel: Int = getColumnIndexOrThrow(_stmt, "triggerBatteryLevel")
        val _columnIndexOfTriggerBatteryCharging: Int = getColumnIndexOrThrow(_stmt, "triggerBatteryCharging")
        val _columnIndexOfActionType: Int = getColumnIndexOrThrow(_stmt, "actionType")
        val _columnIndexOfActionPhoneNumber: Int = getColumnIndexOrThrow(_stmt, "actionPhoneNumber")
        val _columnIndexOfActionMessage: Int = getColumnIndexOrThrow(_stmt, "actionMessage")
        val _columnIndexOfActionNotificationTitle: Int = getColumnIndexOrThrow(_stmt, "actionNotificationTitle")
        val _columnIndexOfActionNotificationBody: Int = getColumnIndexOrThrow(_stmt, "actionNotificationBody")
        val _columnIndexOfActionToggleOn: Int = getColumnIndexOrThrow(_stmt, "actionToggleOn")
        val _columnIndexOfActionLevel: Int = getColumnIndexOrThrow(_stmt, "actionLevel")
        val _columnIndexOfActionPackageName: Int = getColumnIndexOrThrow(_stmt, "actionPackageName")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _result: MutableList<AutomationEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item_1: AutomationEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpEnabled: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEnabled).toInt()
          _tmpEnabled = _tmp != 0
          val _tmpTriggerType: String
          _tmpTriggerType = _stmt.getText(_columnIndexOfTriggerType)
          val _tmpTriggerHour: Int?
          if (_stmt.isNull(_columnIndexOfTriggerHour)) {
            _tmpTriggerHour = null
          } else {
            _tmpTriggerHour = _stmt.getLong(_columnIndexOfTriggerHour).toInt()
          }
          val _tmpTriggerMinute: Int?
          if (_stmt.isNull(_columnIndexOfTriggerMinute)) {
            _tmpTriggerMinute = null
          } else {
            _tmpTriggerMinute = _stmt.getLong(_columnIndexOfTriggerMinute).toInt()
          }
          val _tmpTriggerDaysOfWeek: String?
          if (_stmt.isNull(_columnIndexOfTriggerDaysOfWeek)) {
            _tmpTriggerDaysOfWeek = null
          } else {
            _tmpTriggerDaysOfWeek = _stmt.getText(_columnIndexOfTriggerDaysOfWeek)
          }
          val _tmpTriggerLatitude: Double?
          if (_stmt.isNull(_columnIndexOfTriggerLatitude)) {
            _tmpTriggerLatitude = null
          } else {
            _tmpTriggerLatitude = _stmt.getDouble(_columnIndexOfTriggerLatitude)
          }
          val _tmpTriggerLongitude: Double?
          if (_stmt.isNull(_columnIndexOfTriggerLongitude)) {
            _tmpTriggerLongitude = null
          } else {
            _tmpTriggerLongitude = _stmt.getDouble(_columnIndexOfTriggerLongitude)
          }
          val _tmpTriggerRadius: Float?
          if (_stmt.isNull(_columnIndexOfTriggerRadius)) {
            _tmpTriggerRadius = null
          } else {
            _tmpTriggerRadius = _stmt.getDouble(_columnIndexOfTriggerRadius).toFloat()
          }
          val _tmpTriggerSsid: String?
          if (_stmt.isNull(_columnIndexOfTriggerSsid)) {
            _tmpTriggerSsid = null
          } else {
            _tmpTriggerSsid = _stmt.getText(_columnIndexOfTriggerSsid)
          }
          val _tmpTriggerBatteryLevel: Int?
          if (_stmt.isNull(_columnIndexOfTriggerBatteryLevel)) {
            _tmpTriggerBatteryLevel = null
          } else {
            _tmpTriggerBatteryLevel = _stmt.getLong(_columnIndexOfTriggerBatteryLevel).toInt()
          }
          val _tmpTriggerBatteryCharging: Boolean?
          val _tmp_1: Int?
          if (_stmt.isNull(_columnIndexOfTriggerBatteryCharging)) {
            _tmp_1 = null
          } else {
            _tmp_1 = _stmt.getLong(_columnIndexOfTriggerBatteryCharging).toInt()
          }
          _tmpTriggerBatteryCharging = _tmp_1?.let { it != 0 }
          val _tmpActionType: String
          _tmpActionType = _stmt.getText(_columnIndexOfActionType)
          val _tmpActionPhoneNumber: String?
          if (_stmt.isNull(_columnIndexOfActionPhoneNumber)) {
            _tmpActionPhoneNumber = null
          } else {
            _tmpActionPhoneNumber = _stmt.getText(_columnIndexOfActionPhoneNumber)
          }
          val _tmpActionMessage: String?
          if (_stmt.isNull(_columnIndexOfActionMessage)) {
            _tmpActionMessage = null
          } else {
            _tmpActionMessage = _stmt.getText(_columnIndexOfActionMessage)
          }
          val _tmpActionNotificationTitle: String?
          if (_stmt.isNull(_columnIndexOfActionNotificationTitle)) {
            _tmpActionNotificationTitle = null
          } else {
            _tmpActionNotificationTitle = _stmt.getText(_columnIndexOfActionNotificationTitle)
          }
          val _tmpActionNotificationBody: String?
          if (_stmt.isNull(_columnIndexOfActionNotificationBody)) {
            _tmpActionNotificationBody = null
          } else {
            _tmpActionNotificationBody = _stmt.getText(_columnIndexOfActionNotificationBody)
          }
          val _tmpActionToggleOn: Boolean?
          val _tmp_2: Int?
          if (_stmt.isNull(_columnIndexOfActionToggleOn)) {
            _tmp_2 = null
          } else {
            _tmp_2 = _stmt.getLong(_columnIndexOfActionToggleOn).toInt()
          }
          _tmpActionToggleOn = _tmp_2?.let { it != 0 }
          val _tmpActionLevel: Int?
          if (_stmt.isNull(_columnIndexOfActionLevel)) {
            _tmpActionLevel = null
          } else {
            _tmpActionLevel = _stmt.getLong(_columnIndexOfActionLevel).toInt()
          }
          val _tmpActionPackageName: String?
          if (_stmt.isNull(_columnIndexOfActionPackageName)) {
            _tmpActionPackageName = null
          } else {
            _tmpActionPackageName = _stmt.getText(_columnIndexOfActionPackageName)
          }
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          _item_1 = AutomationEntity(_tmpId,_tmpName,_tmpEnabled,_tmpTriggerType,_tmpTriggerHour,_tmpTriggerMinute,_tmpTriggerDaysOfWeek,_tmpTriggerLatitude,_tmpTriggerLongitude,_tmpTriggerRadius,_tmpTriggerSsid,_tmpTriggerBatteryLevel,_tmpTriggerBatteryCharging,_tmpActionType,_tmpActionPhoneNumber,_tmpActionMessage,_tmpActionNotificationTitle,_tmpActionNotificationBody,_tmpActionToggleOn,_tmpActionLevel,_tmpActionPackageName,_tmpCreatedAt)
          _result.add(_item_1)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAllAutomationsList(): List<AutomationEntity> {
    val _sql: String = "SELECT * FROM automations ORDER BY createdAt DESC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfEnabled: Int = getColumnIndexOrThrow(_stmt, "enabled")
        val _columnIndexOfTriggerType: Int = getColumnIndexOrThrow(_stmt, "triggerType")
        val _columnIndexOfTriggerHour: Int = getColumnIndexOrThrow(_stmt, "triggerHour")
        val _columnIndexOfTriggerMinute: Int = getColumnIndexOrThrow(_stmt, "triggerMinute")
        val _columnIndexOfTriggerDaysOfWeek: Int = getColumnIndexOrThrow(_stmt, "triggerDaysOfWeek")
        val _columnIndexOfTriggerLatitude: Int = getColumnIndexOrThrow(_stmt, "triggerLatitude")
        val _columnIndexOfTriggerLongitude: Int = getColumnIndexOrThrow(_stmt, "triggerLongitude")
        val _columnIndexOfTriggerRadius: Int = getColumnIndexOrThrow(_stmt, "triggerRadius")
        val _columnIndexOfTriggerSsid: Int = getColumnIndexOrThrow(_stmt, "triggerSsid")
        val _columnIndexOfTriggerBatteryLevel: Int = getColumnIndexOrThrow(_stmt, "triggerBatteryLevel")
        val _columnIndexOfTriggerBatteryCharging: Int = getColumnIndexOrThrow(_stmt, "triggerBatteryCharging")
        val _columnIndexOfActionType: Int = getColumnIndexOrThrow(_stmt, "actionType")
        val _columnIndexOfActionPhoneNumber: Int = getColumnIndexOrThrow(_stmt, "actionPhoneNumber")
        val _columnIndexOfActionMessage: Int = getColumnIndexOrThrow(_stmt, "actionMessage")
        val _columnIndexOfActionNotificationTitle: Int = getColumnIndexOrThrow(_stmt, "actionNotificationTitle")
        val _columnIndexOfActionNotificationBody: Int = getColumnIndexOrThrow(_stmt, "actionNotificationBody")
        val _columnIndexOfActionToggleOn: Int = getColumnIndexOrThrow(_stmt, "actionToggleOn")
        val _columnIndexOfActionLevel: Int = getColumnIndexOrThrow(_stmt, "actionLevel")
        val _columnIndexOfActionPackageName: Int = getColumnIndexOrThrow(_stmt, "actionPackageName")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _result: MutableList<AutomationEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AutomationEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpEnabled: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEnabled).toInt()
          _tmpEnabled = _tmp != 0
          val _tmpTriggerType: String
          _tmpTriggerType = _stmt.getText(_columnIndexOfTriggerType)
          val _tmpTriggerHour: Int?
          if (_stmt.isNull(_columnIndexOfTriggerHour)) {
            _tmpTriggerHour = null
          } else {
            _tmpTriggerHour = _stmt.getLong(_columnIndexOfTriggerHour).toInt()
          }
          val _tmpTriggerMinute: Int?
          if (_stmt.isNull(_columnIndexOfTriggerMinute)) {
            _tmpTriggerMinute = null
          } else {
            _tmpTriggerMinute = _stmt.getLong(_columnIndexOfTriggerMinute).toInt()
          }
          val _tmpTriggerDaysOfWeek: String?
          if (_stmt.isNull(_columnIndexOfTriggerDaysOfWeek)) {
            _tmpTriggerDaysOfWeek = null
          } else {
            _tmpTriggerDaysOfWeek = _stmt.getText(_columnIndexOfTriggerDaysOfWeek)
          }
          val _tmpTriggerLatitude: Double?
          if (_stmt.isNull(_columnIndexOfTriggerLatitude)) {
            _tmpTriggerLatitude = null
          } else {
            _tmpTriggerLatitude = _stmt.getDouble(_columnIndexOfTriggerLatitude)
          }
          val _tmpTriggerLongitude: Double?
          if (_stmt.isNull(_columnIndexOfTriggerLongitude)) {
            _tmpTriggerLongitude = null
          } else {
            _tmpTriggerLongitude = _stmt.getDouble(_columnIndexOfTriggerLongitude)
          }
          val _tmpTriggerRadius: Float?
          if (_stmt.isNull(_columnIndexOfTriggerRadius)) {
            _tmpTriggerRadius = null
          } else {
            _tmpTriggerRadius = _stmt.getDouble(_columnIndexOfTriggerRadius).toFloat()
          }
          val _tmpTriggerSsid: String?
          if (_stmt.isNull(_columnIndexOfTriggerSsid)) {
            _tmpTriggerSsid = null
          } else {
            _tmpTriggerSsid = _stmt.getText(_columnIndexOfTriggerSsid)
          }
          val _tmpTriggerBatteryLevel: Int?
          if (_stmt.isNull(_columnIndexOfTriggerBatteryLevel)) {
            _tmpTriggerBatteryLevel = null
          } else {
            _tmpTriggerBatteryLevel = _stmt.getLong(_columnIndexOfTriggerBatteryLevel).toInt()
          }
          val _tmpTriggerBatteryCharging: Boolean?
          val _tmp_1: Int?
          if (_stmt.isNull(_columnIndexOfTriggerBatteryCharging)) {
            _tmp_1 = null
          } else {
            _tmp_1 = _stmt.getLong(_columnIndexOfTriggerBatteryCharging).toInt()
          }
          _tmpTriggerBatteryCharging = _tmp_1?.let { it != 0 }
          val _tmpActionType: String
          _tmpActionType = _stmt.getText(_columnIndexOfActionType)
          val _tmpActionPhoneNumber: String?
          if (_stmt.isNull(_columnIndexOfActionPhoneNumber)) {
            _tmpActionPhoneNumber = null
          } else {
            _tmpActionPhoneNumber = _stmt.getText(_columnIndexOfActionPhoneNumber)
          }
          val _tmpActionMessage: String?
          if (_stmt.isNull(_columnIndexOfActionMessage)) {
            _tmpActionMessage = null
          } else {
            _tmpActionMessage = _stmt.getText(_columnIndexOfActionMessage)
          }
          val _tmpActionNotificationTitle: String?
          if (_stmt.isNull(_columnIndexOfActionNotificationTitle)) {
            _tmpActionNotificationTitle = null
          } else {
            _tmpActionNotificationTitle = _stmt.getText(_columnIndexOfActionNotificationTitle)
          }
          val _tmpActionNotificationBody: String?
          if (_stmt.isNull(_columnIndexOfActionNotificationBody)) {
            _tmpActionNotificationBody = null
          } else {
            _tmpActionNotificationBody = _stmt.getText(_columnIndexOfActionNotificationBody)
          }
          val _tmpActionToggleOn: Boolean?
          val _tmp_2: Int?
          if (_stmt.isNull(_columnIndexOfActionToggleOn)) {
            _tmp_2 = null
          } else {
            _tmp_2 = _stmt.getLong(_columnIndexOfActionToggleOn).toInt()
          }
          _tmpActionToggleOn = _tmp_2?.let { it != 0 }
          val _tmpActionLevel: Int?
          if (_stmt.isNull(_columnIndexOfActionLevel)) {
            _tmpActionLevel = null
          } else {
            _tmpActionLevel = _stmt.getLong(_columnIndexOfActionLevel).toInt()
          }
          val _tmpActionPackageName: String?
          if (_stmt.isNull(_columnIndexOfActionPackageName)) {
            _tmpActionPackageName = null
          } else {
            _tmpActionPackageName = _stmt.getText(_columnIndexOfActionPackageName)
          }
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          _item = AutomationEntity(_tmpId,_tmpName,_tmpEnabled,_tmpTriggerType,_tmpTriggerHour,_tmpTriggerMinute,_tmpTriggerDaysOfWeek,_tmpTriggerLatitude,_tmpTriggerLongitude,_tmpTriggerRadius,_tmpTriggerSsid,_tmpTriggerBatteryLevel,_tmpTriggerBatteryCharging,_tmpActionType,_tmpActionPhoneNumber,_tmpActionMessage,_tmpActionNotificationTitle,_tmpActionNotificationBody,_tmpActionToggleOn,_tmpActionLevel,_tmpActionPackageName,_tmpCreatedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun setEnabled(id: Long, enabled: Boolean) {
    val _sql: String = "UPDATE automations SET enabled = ? WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        val _tmp: Int = if (enabled) 1 else 0
        _stmt.bindLong(_argIndex, _tmp.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, id)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
