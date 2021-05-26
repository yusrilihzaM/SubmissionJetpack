package com.jetpack.submission1.di

import android.content.Context
import com.jetpack.submission1.api.ApiConfig
import com.jetpack.submission1.data.source.AppRepostory
import com.jetpack.submission1.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(context: Context): AppRepostory {
        val apiConfig=ApiConfig.getApiService()
        val remoteDataSource = RemoteDataSource.getInstance(apiConfig)

        return AppRepostory.getInstance(remoteDataSource)
    }
}