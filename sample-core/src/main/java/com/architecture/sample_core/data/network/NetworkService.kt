package com.architecture.sample_core.data.network

import com.architecture.sample_core.domain.model.EmployeeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface NetworkService {
    @GET("api/v1/employees")
    @Headers(
        "Content-Type:application/json",
        "Cache-Control:no-cache"
    )
    suspend fun getEmployeeData(): Response<EmployeeResponse>
}