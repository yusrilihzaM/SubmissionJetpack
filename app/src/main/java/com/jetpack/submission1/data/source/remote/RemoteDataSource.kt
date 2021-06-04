package com.jetpack.submission1.data.source.remote

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    interface LoadTvCallback{
        fun onTvRecevied(tvResponse: List<TvResultsItem>)
    }


    fun getMovies(): LiveData<ApiResponse<List<MoviesResultsItem>>> {
        EspressoIdlingResource.increment()
        val dataItem = MutableLiveData<ApiResponse<List<MoviesResultsItem>>>()
        handler.postDelayed({
            apiConfig.getApiService().getMovies(API_KEY).enqueue(object : Callback<MoviesResponse>{
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                if (response.isSuccessful)
                {
                    val data= response.body()?.results
                    dataItem.value = data?.let { ApiResponse.success(it) }!!
//                    response.body()?.results.apply { this?.let { loadMovieCallback.onMoviesRecevied(it) } }
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
        return dataItem
    }


    fun getTv(): LiveData<ApiResponse<List<TvResultsItem>>> {
        EspressoIdlingResource.increment()
        val dataItem = MutableLiveData<ApiResponse<List<TvResultsItem>>>()
        handler.postDelayed({
            apiConfig.getApiService().getTvs(API_KEY).enqueue(object :Callback<TvResponse>{
                override fun onResponse(call: Call<TvResponse>, response: Response<TvResponse>) {
                    if (response.isSuccessful)
                    {
                        val data= response.body()?.results
                        dataItem.value = data?.let { ApiResponse.success(it) }!!
//                        response.body()?.results.apply { this?.let { loadTvCallback.onTvRecevied(it) } }
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
        return dataItem
    }


}


