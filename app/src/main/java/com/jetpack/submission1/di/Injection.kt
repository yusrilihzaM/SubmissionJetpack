package com.jetpack.submission1.di

import android.content.Context
import com.jetpack.submission1.api.ApiConfig
import com.jetpack.submission1.data.AppRepostory
import com.jetpack.submission1.data.source.local.LocalDataSource
import com.jetpack.submission1.data.source.local.room.AppDatabase
import com.jetpack.submission1.data.source.remote.RemoteDataSource
import com.jetpack.submission1.util.AppExecutors

object Injection {
    fun provideRepository(context: Context): AppRepostory {
        val apiConfig=ApiConfig.getApiService()
        val database = AppDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance(apiConfig)
        val localDataSource = LocalDataSource.getInstance(database.appDao())
        val appExecutors = AppExecutors()

        return AppRepostory.getInstance(remoteDataSource,localDataSource,appExecutors)
    }
}