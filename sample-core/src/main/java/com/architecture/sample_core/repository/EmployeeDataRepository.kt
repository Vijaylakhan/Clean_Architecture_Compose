package com.architecture.sample_core.repository

import com.architecture.sample_core.model.EmployeeData
import com.architecture.sample_core.utility.UpdateDataState
import kotlinx.coroutines.flow.Flow

interface EmployeeDataRepository {
    suspend fun getEmployeeData(cacheData:Boolean = true) : Flow<UpdateDataState<List<EmployeeData>>>
}