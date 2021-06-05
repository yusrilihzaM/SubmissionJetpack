package com.jetpack.submission1.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.jetpack.submission1.data.source.local.entity.MovieEntity
import com.jetpack.submission1.data.source.local.entity.TvEntity

@Dao
interface AppDao {
    @Query("SELECT * FROM MovieEntity")
    fun getMovies():  DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM MovieEntity WHERE favorite=1")
    fun getFavMovies():  DataSource.Factory<Int, MovieEntity>

    @Transaction
    @Query("SELECT * FROM MovieEntity WHERE idMovie=:idMovie AND favorite=1")
    fun getMovieFavId(idMovie: Int): LiveData<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Update
    fun updateFavMovie(movie: MovieEntity)

    @Query("SELECT * FROM TvEntity")
    fun getTv():  DataSource.Factory<Int, TvEntity>

    @Query("SELECT * FROM TvEntity WHERE favorite=1")
    fun getFavTv(): DataSource.Factory<Int, TvEntity>

    @Transaction
    @Query("SELECT * FROM TvEntity WHERE tvId=:tvId AND favorite=1")
    fun getTvFavId(tvId: Int): LiveData<List<TvEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTv(tv: List<TvEntity>)

    @Update
    fun updateFavTv(tv: TvEntity)
}