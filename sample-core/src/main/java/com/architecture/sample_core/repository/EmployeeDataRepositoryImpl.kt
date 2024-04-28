package com.architecture.sample_core.repository

import android.util.Log
import androidx.compose.runtime.collectAsState
import com.architecture.sample_core.database.AppDataBase
import com.architecture.sample_core.model.EmployeeData
import com.architecture.sample_core.network.NetworkService
import com.architecture.sample_core.utility.UpdateDataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.security.MessageDigest
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.text.Charsets.UTF_8

@Singleton
class EmployeeDataRepositoryImpl @Inject constructor(
    private val appDataBase: AppDataBase,
    private val networkService: NetworkService
): EmployeeDataRepository {
    override suspend fun getEmployeeDbData(): Flow<List<EmployeeData>> = appDataBase.employeeDao.getEmployeeData().flowOn(Dispatchers.IO)

    override suspend fun getEmployeeNetworkData():UpdateDataState<List<EmployeeData>> {
        val response = withContext(Dispatchers.IO) {
            networkService.getEmployeeData()
        }
        if(response.isSuccessful&&!response.body()?.data.isNullOrEmpty()){
            Log.d("getEmployeeDbData","getEmployeeDbData5")
            appDataBase.employeeDao.insertEmployeeData(response.body()?.data?: listOf())
        }
        else{
            Log.d("getEmployeeDbData","getEmployeeDbData6")
            return UpdateDataState.Error("Something went wrong")
        }
        return UpdateDataState.Success(listOf<EmployeeData>())
    }

    override suspend fun compareDataChange(previousData:List<EmployeeData>?, nextData:List<EmployeeData>):Boolean{
        if(previousData.isNullOrEmpty()){
            return true
        }
        Log.d("getEmployeeDbData","getEmployeeDbData3")
        return getHashValue(previousData.toString())!=getHashValue(nextData.toString())
    }

    private fun getHashValue(data:String):String{
       return hashString(data).toHex()
    }
    private fun ByteArray.toHex() = joinToString(separator = "") { byte -> "%02x".format(byte) }

    private fun hashString(str: String, algorithm: String = "SHA-256"): ByteArray =
        MessageDigest.getInstance(algorithm).digest(str.toByteArray(UTF_8))
}