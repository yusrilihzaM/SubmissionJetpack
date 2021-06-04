package com.jetpack.submission1.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jetpack.submission1.data.AppDataSource
import com.jetpack.submission1.data.source.remote.RemoteDataSource
import com.jetpack.submission1.data.source.remote.response.*

class FakeAppRepostory (private val remoteDataSource: RemoteDataSource) : AppDataSource {
  
    override fun getMovies(): LiveData<List<MoviesResultsItem>> {
        val movieResults = MutableLiveData<List<MoviesResultsItem>>()
        remoteDataSource.getMovies(object :RemoteDataSource.LoadMoviesCallback{
            override fun onMoviesRecevied(movieResponse: List<MoviesResultsItem>) {
                movieResults.postValue(movieResponse)
            }
        })
        return movieResults
    }


    override fun getTv(): LiveData<List<TvResultsItem>> {
        val tvResults = MutableLiveData<List<TvResultsItem>>()
        remoteDataSource.getTv(object :RemoteDataSource.LoadTvCallback{
            override fun onTvRecevied(tvResponse: List<TvResultsItem>) {
                tvResults.postValue(tvResponse)
            }
        })
        return tvResults
    }




}