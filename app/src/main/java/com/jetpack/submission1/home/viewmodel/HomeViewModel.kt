package com.jetpack.submission1.home.viewmodel

import androidx.lifecycle.ViewModel
import com.jetpack.submission1.data.MovieEntity
import com.jetpack.submission1.data.TvEntity
import com.jetpack.submission1.util.DataDummyMovie
import com.jetpack.submission1.util.DataDummyTv

class HomeViewModel: ViewModel() {
    fun getMovies(): List<MovieEntity> = DataDummyMovie.generateDummyMovie()

    fun getTv(): List<TvEntity> = DataDummyTv.generateDummyTv()
}