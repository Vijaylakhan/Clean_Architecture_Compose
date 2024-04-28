package com.architecture.sample_core.repository

import com.architecture.sample_core.model.EmployeeData
import com.architecture.sample_core.utility.UpdateDataState
import kotlinx.coroutines.flow.Flow

interface EmployeeDataRepository {
    suspend fun getEmployeeDbData() : Flow<List<EmployeeData>>
    suspend fun getEmployeeNetworkData() : UpdateDataState<List<EmployeeData>>
    suspend fun compareDataChange(previousData:List<EmployeeData>?, nextData:List<EmployeeData>):Boolean
}