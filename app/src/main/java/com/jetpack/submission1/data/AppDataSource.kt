package com.jetpack.submission1.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.jetpack.submission1.data.source.local.entity.MovieEntity
import com.jetpack.submission1.data.source.local.entity.TvEntity
import com.jetpack.submission1.data.source.remote.response.*
import com.jetpack.submission1.vo.Resource

interface AppDataSource {
    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>>
    fun getFavMovie(): LiveData<PagedList<MovieEntity>>
    fun getMovieFavId(id: Int): LiveData<List<MovieEntity>>
    fun setMovieFav(movieEntity: MovieEntity, state: Boolean)

    fun getTv(): LiveData<Resource<PagedList<TvEntity>>>
    fun getFavTv(): LiveData<PagedList<TvEntity>>
    fun getTvFavId(id: Int): LiveData<List<TvEntity>>
    fun setTvFav(tvEntity: TvEntity, state: Boolean)
}