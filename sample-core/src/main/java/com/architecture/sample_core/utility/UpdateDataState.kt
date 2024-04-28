package com.architecture.sample_core.utility

sealed class UpdateDataState<out R> {
    data class Success<out T>(val data: T) : UpdateDataState<T>()

    data class Error(val errorMessage: String) : UpdateDataState<Nothing>()

    data object Loading : UpdateDataState<Nothing>()
}