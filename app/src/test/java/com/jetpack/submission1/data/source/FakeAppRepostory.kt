package com.jetpack.submission1.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    override fun getMovieById(movieId: Int): LiveData<MovieByIdResponse> {
        val movieResults = MutableLiveData<MovieByIdResponse>()
        remoteDataSource.getMovieById(movieId,object :RemoteDataSource.LoadMovieByIdCallback{
            override fun onMovieByIdRecevied(movieByIdResponse: MovieByIdResponse) {
                movieResults.postValue(movieByIdResponse)
            }
        })
        return movieResults
    }

    override fun getImageMovies(movieId: Int): LiveData<MoviePostersItem> {
        val movieResults = MutableLiveData<MoviePostersItem>()
        remoteDataSource.getImageMovie(movieId,object :RemoteDataSource.LoadMovieImageCallback{
            override fun onMovieImageRecevied(movieImageResponse: MoviePostersItem) {
                movieResults.postValue(movieImageResponse)
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

    override fun getTvById(tvId: Int): LiveData<TvByIdResponse> {
        val tvResults = MutableLiveData<TvByIdResponse>()
        remoteDataSource.getTvById(tvId,object :RemoteDataSource.LoadTvByIdCallback{
            override fun onTvByIdRecevied(tvByIdResponse: TvByIdResponse) {
                tvResults.postValue(tvByIdResponse)
            }
        })
        return tvResults
    }

    override fun getImageTv(tvId: Int): LiveData<PostersItem> {
        val tvResults = MutableLiveData<PostersItem>()
        remoteDataSource.getImageTv(tvId,object :RemoteDataSource.LoadTvImageCallback{
            override fun onMovieTvRecevied(tvImageResponse: PostersItem) {
                tvResults.postValue(tvImageResponse)
            }
        })
        return tvResults
    }


}