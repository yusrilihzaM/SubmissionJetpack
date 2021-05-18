package com.jetpack.submission1.detail.viewmodel

import androidx.lifecycle.ViewModel
import com.jetpack.submission1.data.MovieEntity
import com.jetpack.submission1.data.TvEntity
import com.jetpack.submission1.util.DataDummyMovie
import com.jetpack.submission1.util.DataDummyTv

class DetailViewModel: ViewModel() {
    private lateinit var titleMovie: String
    private lateinit var titleTv: String
    fun setSelectedItemMovie(titleMovie: String) {
        this.titleMovie = titleMovie
    }
    fun setSelectedItemTv(titleTv: String) {
        this.titleTv = titleTv
    }
    fun getItemMovie(): MovieEntity {
        lateinit var movie: MovieEntity
        val movieEntities = DataDummyMovie.generateDummyMovie()
        for (movieEntity in movieEntities) {
            if (movieEntity.titleMovie == titleMovie) {
                movie = movieEntity
            }
        }
        return movie
    }
    fun getItemTv(): TvEntity {
        lateinit var tv: TvEntity
        val tvEntities = DataDummyTv.generateDummyTv()
        for (tvEntity in tvEntities) {
            if (tvEntity.titleTv == titleTv) {
                tv = tvEntity
            }
        }
        return tv
    }
}