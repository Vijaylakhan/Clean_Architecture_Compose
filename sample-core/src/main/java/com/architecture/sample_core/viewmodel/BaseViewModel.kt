package com.architecture.sample_core.viewmodel

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
    private val _employeeUiStateData = MutableStateFlow<UpdateDataState<List<EmployeeData>>>(UpdateDataState.Loading(true))
    val employeeUiState = _employeeUiStateData.asStateFlow()

    init {
        viewModelScope.launch {
            _employeeUiStateData.value = UpdateDataState.Loading(true)
            employeeDataRepository.getEmployeeData().collect {
                _employeeUiStateData.value = it
            }
        }
    }

    fun onRetryClick(){
        viewModelScope.launch {
            _employeeUiStateData.value = UpdateDataState.Loading(true)
            employeeDataRepository.getEmployeeData(false).collect {
                _employeeUiStateData.value = it
            }
        }
    }
}