package com.jetpack.submission1.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.jetpack.submission1.data.source.local.entity.MovieEntity
import com.jetpack.submission1.data.source.local.entity.TvEntity
import com.jetpack.submission1.data.source.local.room.AppDao

class LocalDataSource private constructor(private val appDao: AppDao)  {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(appDao: AppDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(appDao)
    }

    fun getMovies(): LiveData<List<MovieEntity>> = appDao.getMovies()

    fun getTv(): LiveData<List<TvEntity>> = appDao.getTv()

    fun getFavMovies(): LiveData<List<MovieEntity>> = appDao.getFavMovies()

    fun getFavTv():LiveData<List<TvEntity>> = appDao.getFavTv()

    fun setFavMovie(movie: MovieEntity, newState: Boolean) {
        movie.favorite = newState
        appDao.updateFavMovie(movie)
    }

    fun setFavTv(tv: TvEntity, newState: Boolean) {
        tv.favorite = newState
        appDao.updateFavTv(tv)
    }

    fun insertMovie(movie: List<MovieEntity>) = appDao.insertMovies(movie)

    fun insertTv(tv: List<TvEntity>) = appDao.insertTv(tv)

    fun getMovieFavId(id: Int): LiveData<List<MovieEntity>> =
        appDao.getMovieFavId(id)
    fun getFavFavId(id: Int): LiveData<List<TvEntity>> =
        appDao.getTvFavId(id)
}