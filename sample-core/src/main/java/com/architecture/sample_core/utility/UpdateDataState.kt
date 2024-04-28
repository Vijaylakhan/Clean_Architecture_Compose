package com.architecture.sample_core.utility

sealed class UpdateDataState<out R> {
    data class Success<out T>(val data: T, val isLoading: Boolean, val contentChange:Boolean = true) : UpdateDataState<T>()

    data class Error(val errorMessage: String) : UpdateDataState<Nothing>()

    data class Loading(val isLoading : Boolean) : UpdateDataState<Nothing>()
}