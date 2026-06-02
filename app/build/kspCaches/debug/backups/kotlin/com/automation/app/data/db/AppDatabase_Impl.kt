package com.automation.app.`data`.db

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class AppDatabase_Impl : AppDatabase() {
  private val _automationDao: Lazy<AutomationDao> = lazy {
    AutomationDao_Impl(this)
  }

  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(1, "0d7f02589900d6671fcf274a43dae0a5", "bb88de1394d847ca6553ebbadc1a2143") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `automations` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `enabled` INTEGER NOT NULL, `triggerType` TEXT NOT NULL, `triggerHour` INTEGER, `triggerMinute` INTEGER, `triggerDaysOfWeek` TEXT, `triggerLatitude` REAL, `triggerLongitude` REAL, `triggerRadius` REAL, `triggerSsid` TEXT, `triggerBatteryLevel` INTEGER, `triggerBatteryCharging` INTEGER, `actionType` TEXT NOT NULL, `actionPhoneNumber` TEXT, `actionMessage` TEXT, `actionNotificationTitle` TEXT, `actionNotificationBody` TEXT, `actionToggleOn` INTEGER, `actionLevel` INTEGER, `actionPackageName` TEXT, `createdAt` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '0d7f02589900d6671fcf274a43dae0a5')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `automations`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection): RoomOpenDelegate.ValidationResult {
        val _columnsAutomations: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAutomations.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAutomations.put("name", TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAutomations.put("enabled", TableInfo.Column("enabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAutomations.put("triggerType", TableInfo.Column("triggerType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAutomations.put("triggerHour", TableInfo.Column("triggerHour", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAutomations.put("triggerMinute", TableInfo.Column("triggerMinute", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAutomations.put("triggerDaysOfWeek", TableInfo.Column("triggerDaysOfWeek", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAutomations.put("triggerLatitude", TableInfo.Column("triggerLatitude", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAutomations.put("triggerLongitude", TableInfo.Column("triggerLongitude", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAutomations.put("triggerRadius", TableInfo.Column("triggerRadius", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAutomations.put("triggerSsid", TableInfo.Column("triggerSsid", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAutomations.put("triggerBatteryLevel", TableInfo.Column("triggerBatteryLevel", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAutomations.put("triggerBatteryCharging", TableInfo.Column("triggerBatteryCharging", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAutomations.put("actionType", TableInfo.Column("actionType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAutomations.put("actionPhoneNumber", TableInfo.Column("actionPhoneNumber", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAutomations.put("actionMessage", TableInfo.Column("actionMessage", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAutomations.put("actionNotificationTitle", TableInfo.Column("actionNotificationTitle", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAutomations.put("actionNotificationBody", TableInfo.Column("actionNotificationBody", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAutomations.put("actionToggleOn", TableInfo.Column("actionToggleOn", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAutomations.put("actionLevel", TableInfo.Column("actionLevel", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAutomations.put("actionPackageName", TableInfo.Column("actionPackageName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAutomations.put("createdAt", TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAutomations: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAutomations: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoAutomations: TableInfo = TableInfo("automations", _columnsAutomations, _foreignKeysAutomations, _indicesAutomations)
        val _existingAutomations: TableInfo = read(connection, "automations")
        if (!_infoAutomations.equals(_existingAutomations)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |automations(com.automation.app.data.db.entities.AutomationEntity).
              | Expected:
              |""".trimMargin() + _infoAutomations + """
              |
              | Found:
              |""".trimMargin() + _existingAutomations)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "automations")
  }

  public override fun clearAllTables() {
    super.performClear(false, "automations")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(AutomationDao::class, AutomationDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>): List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }

  public override fun automationDao(): AutomationDao = _automationDao.value
}
