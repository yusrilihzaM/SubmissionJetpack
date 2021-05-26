package com.jetpack.submission1.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jetpack.submission1.data.source.AppRepostory
import com.jetpack.submission1.data.source.remote.response.MoviesResultsItem
import com.jetpack.submission1.data.source.remote.response.TvResultsItem

class HomeViewModel(private val appRepostory: AppRepostory): ViewModel() {
    fun getMovies(): LiveData<List<MoviesResultsItem>> = appRepostory.getMovies()

    fun getTv(): LiveData<List<TvResultsItem>> = appRepostory.getTv()
}