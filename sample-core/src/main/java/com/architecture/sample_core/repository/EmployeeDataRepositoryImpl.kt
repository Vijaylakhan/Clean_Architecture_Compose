package com.architecture.sample_core.repository

import com.architecture.sample_core.database.AppDataBase
import com.architecture.sample_core.model.EmployeeData
import com.architecture.sample_core.network.NetworkService
import com.architecture.sample_core.utility.UpdateDataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmployeeDataRepositoryImpl @Inject constructor(
    private val appDataBase: AppDataBase,
    private val networkService: NetworkService
): EmployeeDataRepository {

    override suspend fun getEmployeeData(cacheData:Boolean):Flow<UpdateDataState<List<EmployeeData>>> = flow {
        emit(UpdateDataState.Error("Something went wrong"))
    }.flowOn(Dispatchers.IO).catch {
        emit(UpdateDataState.Error("Something went wrong"))
    }

//    private fun getHashValue(data:String):String{
//       return hashString(data).toHex()
//    }
//    private fun ByteArray.toHex() = joinToString(separator = "") { byte -> "%02x".format(byte) }
//
//    private fun hashString(str: String, algorithm: String = "SHA-256"): ByteArray =
//        MessageDigest.getInstance(algorithm).digest(str.toByteArray(UTF_8))
}