package com.jetpack.submission1.api

import com.jetpack.submission1.data.source.remote.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/top_rated")
    fun getMovies(
        @Query("api_key")apiKey:String
    ):Call<MoviesResponse>

    @GET("tv/top_rated")
    fun getTvs(
        @Query("api_key")apiKey:String
    ):Call<TvResponse>


}