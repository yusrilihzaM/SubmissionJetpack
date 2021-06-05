package com.jetpack.submission1.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jetpack.submission1.data.AppDataSource
import com.jetpack.submission1.data.source.local.LocalDataSource
import com.jetpack.submission1.data.source.local.entity.MovieEntity
import com.jetpack.submission1.data.source.local.entity.TvEntity
import com.jetpack.submission1.data.source.remote.ApiResponse
import com.jetpack.submission1.data.source.remote.RemoteDataSource
import com.jetpack.submission1.data.source.remote.response.*
import com.jetpack.submission1.util.AppExecutors
import com.jetpack.submission1.vo.Resource

class FakeAppRepostory constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    AppDataSource {

    override fun getMovies(): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, List<MoviesResultsItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<MovieEntity>> {
                return localDataSource.getMovies()
            }

            override fun shouldFetch(data: List<MovieEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<MoviesResultsItem>>> {
                return remoteDataSource.getMovies()
            }

            override fun saveCallResult(data: List<MoviesResultsItem>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                        response.id,
                        response.overview,
                        response.originalTitle,
                        response.title,
                        response.posterPath,
                        response.id,
                        false
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovie(movieList)
            }
        }.asLiveData()
    }

    override fun getFavMovie(): LiveData<List<MovieEntity>> {
        return localDataSource.getFavMovies()
    }

    override fun getMovieFavId(id: Int): LiveData<List<MovieEntity>>{
        return localDataSource.getMovieFavId(id)
    }

    override fun setMovieFav(movieEntity: MovieEntity, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setFavMovie(movieEntity, state) }

    }

    override fun getTv(): LiveData<Resource<List<TvEntity>>> {
        return object : NetworkBoundResource<List<TvEntity>, List<TvResultsItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<TvEntity>> {
                return localDataSource.getTv()
            }

            override fun shouldFetch(data: List<TvEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<TvResultsItem>>> {
                return remoteDataSource.getTv()
            }

            override fun saveCallResult(data: List<TvResultsItem>) {
                val tvList = ArrayList<TvEntity>()
                for (response in data) {
                    val tv = TvEntity(
                        response.id,
                        response.overview,
                        response.posterPath,
                        response.originalName,
                        response.name,
                        response.id,
                        false
                    )
                    tvList.add(tv)
                }
                localDataSource.insertTv(tvList)
            }
        }.asLiveData()
    }

    override fun getFavTv(): LiveData<List<TvEntity>> {
        return localDataSource.getFavTv()
    }

    override fun getTvFavId(id: Int): LiveData<List<TvEntity>> {
        return localDataSource.getFavFavId(id)
    }

    override fun setTvFav(tvEntity: TvEntity, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setFavTv(tvEntity, state) }
    }


}