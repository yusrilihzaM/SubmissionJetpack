package com.jetpack.submission1.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.jetpack.submission1.data.source.local.entity.MovieEntity
import com.jetpack.submission1.data.source.local.entity.TvEntity

@Dao
interface AppDao {
    @Query("SELECT * FROM MovieEntity")
    fun getMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM MovieEntity WHERE favorite=1")
    fun getFavMovies(): LiveData<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Update
    fun updateFavMovie(movie: MovieEntity)

    @Query("SELECT * FROM TvEntity")
    fun getTv(): LiveData<List<TvEntity>>

    @Query("SELECT * FROM TvEntity WHERE favorite=1")
    fun getFavTv(): LiveData<List<TvEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTv(tv: List<TvEntity>)

    @Update
    fun updateFavTv(tv: TvEntity)
}