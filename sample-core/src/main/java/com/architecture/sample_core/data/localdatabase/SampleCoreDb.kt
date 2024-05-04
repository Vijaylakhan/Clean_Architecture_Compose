package com.architecture.sample_core.data.localdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.architecture.sample_core.data.localdatabase.dao.EmployeeDao
import com.architecture.sample_core.domain.model.EmployeeData
import javax.inject.Singleton

@Database(
    entities = [EmployeeData::class],
    version = 1,
    exportSchema = false)
@Singleton
abstract class AppDataBase: RoomDatabase() {
    abstract val employeeDao: EmployeeDao
}