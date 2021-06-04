package com.jetpack.submission1.data

import androidx.lifecycle.LiveData
import com.jetpack.submission1.data.source.local.entity.MovieEntity
import com.jetpack.submission1.data.source.local.entity.TvEntity
import com.jetpack.submission1.data.source.remote.response.*
import com.jetpack.submission1.vo.Resource

interface AppDataSource {
    fun getMovies(): LiveData<Resource<List<MovieEntity>>>
    fun getFavMovie(): LiveData<List<MovieEntity>>
    fun setMovieFav(movieEntity: MovieEntity, state: Boolean)

    fun getTv(): LiveData<Resource<List<TvEntity>>>
    fun getFavTv(): LiveData<List<TvEntity>>
    fun setTvFav(tvEntity: TvEntity, state: Boolean)
}