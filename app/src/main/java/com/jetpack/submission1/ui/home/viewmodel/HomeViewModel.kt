package com.jetpack.submission1.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.jetpack.submission1.data.AppRepostory
import com.jetpack.submission1.data.source.local.entity.MovieEntity
import com.jetpack.submission1.data.source.local.entity.TvEntity
import com.jetpack.submission1.data.source.remote.response.MoviesResultsItem
import com.jetpack.submission1.data.source.remote.response.TvResultsItem
import com.jetpack.submission1.vo.Resource

class HomeViewModel(private val appRepostory: AppRepostory): ViewModel() {
    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> = appRepostory.getMovies()

    fun getTv(): LiveData<Resource<PagedList<TvEntity>>> = appRepostory.getTv()
}