package com.architecture.sample_core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.architecture.sample_core.database.dao.EmployeeDao
import com.architecture.sample_core.model.EmployeeData
import javax.inject.Singleton

@Database(
    entities = [EmployeeData::class],
    version = 1,
    exportSchema = false)
@Singleton
abstract class AppDataBase: RoomDatabase() {
    abstract val employeeDao: EmployeeDao
}