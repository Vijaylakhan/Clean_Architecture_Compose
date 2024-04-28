package com.architecture.sample_core.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.architecture.sample_core.model.EmployeeData
import com.architecture.sample_core.repository.EmployeeDataRepository
import com.architecture.sample_core.utility.UpdateDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BaseViewModel @Inject constructor(private val employeeDataRepository: EmployeeDataRepository) :
    ViewModel() {
    private val _employeeUiStateData = MutableStateFlow<UpdateDataState<List<EmployeeData>>>(UpdateDataState.Loading)
    val employeeUiState = _employeeUiStateData.asStateFlow()

    init {
        viewModelScope.launch {
            _employeeUiStateData.value = UpdateDataState.Loading
            employeeDataRepository.getEmployeeDbData().collect { list ->
                Log.d("getEmployeeDbData","getEmployeeDbData2")
                    if(employeeDataRepository.compareDataChange((_employeeUiStateData.value as? UpdateDataState.Success)?.data?: listOf<EmployeeData>(), list)){
                        Log.d("getEmployeeDbData","getEmployeeDbData4")
                        _employeeUiStateData.value = UpdateDataState.Success(list)
                    }
            }
        }
        viewModelScope.launch {
            employeeDataRepository.getEmployeeNetworkData().let {
                if(it !is UpdateDataState.Success){
                    _employeeUiStateData.value = it
                }
            }
        }
    }

    fun onRetryClick(){
        viewModelScope.launch {
            _employeeUiStateData.value = UpdateDataState.Loading
            employeeDataRepository.getEmployeeNetworkData().let {
                _employeeUiStateData.value = it
            }
        }
    }
}