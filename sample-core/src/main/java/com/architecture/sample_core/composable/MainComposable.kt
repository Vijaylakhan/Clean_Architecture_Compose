package com.architecture.sample_core.composable

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.architecture.sample_core.model.EmployeeData
import com.architecture.sample_core.utility.UpdateDataState
import com.architecture.sample_core.viewmodel.BaseViewModel

@Composable
fun MainComposable(){
    val viewModel = hiltViewModel<BaseViewModel>()
    val uiState = viewModel.employeeUiState.collectAsStateWithLifecycle()
    when(uiState.value){
        is UpdateDataState.Success -> {

        }
        is UpdateDataState.Error -> {

        }
        else -> {

        }
    }
}