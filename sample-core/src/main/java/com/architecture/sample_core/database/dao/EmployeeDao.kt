package com.architecture.sample_core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.architecture.sample_core.model.EmployeeData
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDao {
    @Transaction
    suspend fun insertEmployeeData(employeeData:List<EmployeeData>){
        deleteEmployeeData()
        insertEmployeeDataList(employeeData)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployeeDataList(mList: List<EmployeeData>)

    @Query("DELETE FROM EmployeeData")
    suspend fun deleteEmployeeData()

    @Query("select * from EmployeeData")
    fun getEmployeeData(): Flow<List<EmployeeData>>
}