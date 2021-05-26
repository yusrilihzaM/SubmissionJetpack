package com.jetpack.submission1.data.source.remote

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.jetpack.submission1.BuildConfig
import com.jetpack.submission1.api.ApiConfig
import com.jetpack.submission1.api.ApiService
import com.jetpack.submission1.data.source.remote.response.*
import com.jetpack.submission1.util.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource(apiService: ApiService) {
    private val handler = Handler(Looper.getMainLooper())

    private val apiConfig=ApiConfig

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        private const val API_KEY= BuildConfig.API_KEY

        @Volatile
        private var instance: RemoteDataSource? = null
        fun getInstance(apiService: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(apiService).apply { instance = this }
            }
    }


    interface LoadMoviesCallback{
        fun onMoviesRecevied(movieResponse: List<MoviesResultsItem>)
    }

    interface LoadMovieByIdCallback{
        fun onMovieByIdRecevied(movieByIdResponse: MovieByIdResponse)
    }
    interface LoadMovieImageCallback{
        fun onMovieImageRecevied(movieImageResponse: MoviePostersItem)
    }
    interface LoadTvImageCallback{
        fun onMovieTvRecevied(tvImageResponse: PostersItem)
    }
    interface LoadTvCallback{
        fun onTvRecevied(tvResponse: List<TvResultsItem>)
    }

    interface LoadTvByIdCallback{
        fun onTvByIdRecevied(tvByIdResponse: TvByIdResponse)
    }
    fun getImageMovie(movieId: Int,loadMovieImageCallback: LoadMovieImageCallback){
        EspressoIdlingResource.increment()
        handler.postDelayed({
            apiConfig.getApiService().getImageMovie(movieId, API_KEY).enqueue(object :Callback<MovieImageResponse>{
                override fun onResponse(
                    call: Call<MovieImageResponse>,
                    response: Response<MovieImageResponse>
                ) {
                    if (response.isSuccessful)
                    {
                        response.body()?.posters?.get(0).apply { this?.let { loadMovieImageCallback.onMovieImageRecevied(it) } }
                        Log.e("getMovies", response.body().toString())
                    } else{
                        Log.e("getMovies", "responseFail: ${response.message()}")
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<MovieImageResponse>, t: Throwable) {
                    Log.e("getMovies", "onFailure: ${t.message.toString()}")
                }
            })

        },SERVICE_LATENCY_IN_MILLIS)
    }
    fun getImageTv(tvId: Int,loadTvImageCallback: LoadTvImageCallback){
        EspressoIdlingResource.increment()
        handler.postDelayed({
            apiConfig.getApiService().getImageTv(tvId, API_KEY).enqueue(object :Callback<TvImageResponse>{
                override fun onResponse(
                    call: Call<TvImageResponse>,
                    response: Response<TvImageResponse>
                ) {
                    if (response.isSuccessful)
                    {
                        response.body()?.posters?.get(0).apply { this?.let { loadTvImageCallback.onMovieTvRecevied(it) } }
                        Log.e("getImageTv", response.body().toString())
                    } else{
                        Log.e("getImageTv", "responseFail: ${response.message()}")
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<TvImageResponse>, t: Throwable) {
                    Log.e("getMovies", "onFailure: ${t.message.toString()}")
                }
            })

        },SERVICE_LATENCY_IN_MILLIS)
    }
    fun getMovies(loadMovieCallback:LoadMoviesCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            apiConfig.getApiService().getMovies(API_KEY).enqueue(object : Callback<MoviesResponse>{
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                if (response.isSuccessful)
                {
                    response.body()?.results.apply { this?.let { loadMovieCallback.onMoviesRecevied(it) } }
                    Log.e("getMovies", response.body().toString())
                } else{
                    Log.e("getMovies", "responseFail: ${response.message()}")
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.e("getMovies", "onFailure: ${t.message.toString()}")
            }
        })

        },SERVICE_LATENCY_IN_MILLIS)
    }

    fun getMovieById(movieId: Int,loadMovieByIdCallback:LoadMovieByIdCallback ) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            apiConfig.getApiService().getMovieById(movieId, API_KEY).enqueue(object :Callback<MovieByIdResponse>{
                override fun onResponse(
                    call: Call<MovieByIdResponse>,
                    response: Response<MovieByIdResponse>
                ) {
                    if (response.isSuccessful)
                    {
                        response.body()?.apply { this?.let { loadMovieByIdCallback.onMovieByIdRecevied(it) } }
                        Log.e("getMovieById", response.body().toString())
                    } else{
                        Log.e("getMovieById", "responseFail: ${response.message()}")
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<MovieByIdResponse>, t: Throwable) {
                    Log.e("getMovieById", "onFailure: ${t.message.toString()}")
                }
            })

        },SERVICE_LATENCY_IN_MILLIS)

    }

    fun getTv(loadTvCallback: LoadTvCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            apiConfig.getApiService().getTvs(API_KEY).enqueue(object :Callback<TvResponse>{
                override fun onResponse(call: Call<TvResponse>, response: Response<TvResponse>) {
                    if (response.isSuccessful)
                    {
                        response.body()?.results.apply { this?.let { loadTvCallback.onTvRecevied(it) } }
                    } else{
                        Log.e("getTv", "responseFail: ${response.message()}")
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                    Log.e("getTv", "onFailure: ${t.message.toString()}")
                }
            })

        },SERVICE_LATENCY_IN_MILLIS)

    }

    fun getTvById(tvId: Int,loadTvByIdCallback: LoadTvByIdCallback ) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            apiConfig.getApiService().getTvById(tvId, API_KEY).enqueue(object :Callback<TvByIdResponse>{
                override fun onResponse(
                    call: Call<TvByIdResponse>,
                    response: Response<TvByIdResponse>
                ) {
                    if (response.isSuccessful)
                    {
                        response.body()?.apply { this?.let { loadTvByIdCallback.onTvByIdRecevied(it) } }
                        Log.e("getTvById", response.body().toString())
                    } else{
                        Log.e("getTvById", "responseFail: ${response.message()}")
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<TvByIdResponse>, t: Throwable) {
                    Log.e("getTvById", "onFailure: ${t.message.toString()}")
                }
            })

        },SERVICE_LATENCY_IN_MILLIS)

    }
}


