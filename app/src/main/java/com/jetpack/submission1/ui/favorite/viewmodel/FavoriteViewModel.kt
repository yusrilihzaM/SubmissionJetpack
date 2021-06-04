package com.jetpack.submission1.ui.favorite.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jetpack.submission1.data.AppRepostory
import com.jetpack.submission1.data.source.local.entity.MovieEntity
import com.jetpack.submission1.data.source.local.entity.TvEntity
import com.jetpack.submission1.vo.Resource

class FavoriteViewModel(private val appRepostory: AppRepostory):ViewModel() {
    fun getFavMovies(): LiveData<List<MovieEntity>> = appRepostory.getFavMovie()

    fun getFavTv(): LiveData<List<TvEntity>> = appRepostory.getFavTv()
}