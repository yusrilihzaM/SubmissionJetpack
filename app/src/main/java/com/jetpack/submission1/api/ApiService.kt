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

    @GET("movie/{movie_id}")
    fun getMovieById(
        @Path("movie_id") movieId:Int,
        @Query("api_key")apiKey:String
    ):Call<MovieByIdResponse>

    @GET("movie/{movie_id}/images")
    fun getImageMovie(
        @Path("movie_id") movieId:Int,
        @Query("api_key")apiKey:String
    ):Call<MovieImageResponse>

    @GET("tv/top_rated")
    fun getTvs(
        @Query("api_key")apiKey:String
    ):Call<TvResponse>

    @GET("tv/{tv_id}")
    fun getTvById(
        @Path("tv_id") tvId:Int,
        @Query("api_key")apiKey:String
    ):Call<TvByIdResponse>

    @GET("tv/{tv_id}/images")
    fun getImageTv(
        @Path("tv_id") tvId:Int,
        @Query("api_key")apiKey:String
    ):Call<TvImageResponse>
}