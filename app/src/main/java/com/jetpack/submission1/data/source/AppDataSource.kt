package com.jetpack.submission1.data.source

import androidx.lifecycle.LiveData
import com.jetpack.submission1.data.source.remote.response.*

interface AppDataSource {
    fun getMovies():LiveData<List<MoviesResultsItem>>
    fun getMovieById(movieId:Int):LiveData<MovieByIdResponse>
    fun getImageMovies(movieId:Int):LiveData<MoviePostersItem>
    fun getTv():LiveData<List<TvResultsItem>>
    fun getTvById(tvId:Int):LiveData<TvByIdResponse>
    fun getImageTv(tvId:Int):LiveData<PostersItem>
}