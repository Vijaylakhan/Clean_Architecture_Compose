package com.architecture.sample_core.composable


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.architecture.sample_core.model.EmployeeData
import com.architecture.sample_core.utility.UpdateDataState
import com.architecture.sample_core.viewmodel.BaseViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun MainComposable(){
    val viewModel = hiltViewModel<BaseViewModel>()
    val uiState = viewModel.employeeUiState.collectAsStateWithLifecycle()
    when(uiState.value){
        is UpdateDataState.Success -> {
            EmployeeLayout((uiState.value as UpdateDataState.Success<List<EmployeeData>>).data.toImmutableList())
        }
        is UpdateDataState.Error -> {
            ErrorScreen((uiState.value as UpdateDataState.Error).errorMessage)
        }
        else -> {
            Loader()
        }
    }
}
@Composable
fun Loader(){
    val strokeWidth = 5.dp
    CircularProgressIndicator(
        modifier = Modifier.drawBehind {
            drawCircle(
                Color.Gray,
                radius = 50f,
                style = Stroke(strokeWidth.toPx())
            )
        },
        color = Color.LightGray,
        strokeWidth = strokeWidth
    )
}
@Composable
fun ErrorScreen(text:String){
    Box(modifier = Modifier.fillMaxSize().padding(24.dp)){
        Text(modifier = Modifier.fillMaxSize().align(alignment = Alignment.Center) , text = text)
    }
}
@Composable
fun EmployeeLayout(data:ImmutableList<EmployeeData>){
    LazyColumn {
        data.fastForEach {
            item(key = it.employee_name?:"") {
                Box(modifier = Modifier.padding(24.dp)){
                    Text(text = it.employee_name?:"")
                }
            }
        }
    }
}