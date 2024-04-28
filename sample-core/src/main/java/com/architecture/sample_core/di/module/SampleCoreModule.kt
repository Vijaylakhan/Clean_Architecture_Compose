package com.architecture.sample_core.di.module

import android.content.Context
import androidx.room.Room
import com.architecture.sample_core.database.AppDataBase
import com.architecture.sample_core.network.NetworkService
import com.architecture.sample_core.repository.EmployeeDataRepository
import com.architecture.sample_core.repository.EmployeeDataRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SampleCoreModule {

    @Provides
    @Singleton
    fun provideEmployeeRepository(
        appDataBase: AppDataBase,
        networkService: NetworkService
    ): EmployeeDataRepository = EmployeeDataRepositoryImpl(appDataBase,networkService)


    @Provides
    @Singleton
    fun provideEmployeeDbClass(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(context,
            AppDataBase::class.java, "app_database")
            .build()
    }

    @Provides
    @Singleton
    fun provideEmployeeNetworkClass(): NetworkService {
        return Retrofit.Builder().baseUrl("https://dummy.restapiexample.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .readTimeout(20L, TimeUnit.SECONDS)
                    .writeTimeout(20L, TimeUnit.SECONDS)
                    .connectTimeout(20L, TimeUnit.SECONDS)
                    .addInterceptor { chain ->
                        chain.proceed(
                            chain.request().newBuilder().addHeader("Cache-Control", "no-cache")
                                .header("Content-Type", "application/json").build()
                        )
                    }
                    .build()
            ).build().create(NetworkService::class.java)
    }
}