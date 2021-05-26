package com.jetpack.submission1.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jetpack.submission1.data.source.AppRepostory
import com.jetpack.submission1.data.source.remote.response.MovieByIdResponse
import com.jetpack.submission1.data.source.remote.response.MoviePostersItem
import com.jetpack.submission1.data.source.remote.response.PostersItem
import com.jetpack.submission1.data.source.remote.response.TvByIdResponse

class DetailViewModel(private val appRepostory: AppRepostory): ViewModel() {

    private lateinit var movieItem: LiveData<MovieByIdResponse>
    private lateinit var tvItem: LiveData<TvByIdResponse>
    private lateinit var movieImage: LiveData<MoviePostersItem>
    private lateinit var tvImage: LiveData<PostersItem>
    fun setSelectedItemMovie(movieId: Int) {
        movieItem=appRepostory.getMovieById(movieId)
    }
    fun setSelectedItemTv(tvId: Int) {
        tvItem=appRepostory.getTvById(tvId)
    }

    fun setSelectedImageMovie(movieId: Int) {
        movieImage=appRepostory.getImageMovies(movieId)
    }
    fun setSelectedImageTv(tvId: Int) {
        tvImage=appRepostory.getImageTv(tvId)
    }
    fun getMovieCoba(movieId: Int):LiveData<MovieByIdResponse>{
        return appRepostory.getMovieById(movieId)
    }
    fun getImageMovie(): LiveData<MoviePostersItem> = movieImage
    fun getImageTv(): LiveData<PostersItem> = tvImage
    fun getItemMovie(): LiveData<MovieByIdResponse> = movieItem
    fun getItemTv(): LiveData<TvByIdResponse> = tvItem

}