package com.jetpack.submission1.ui.detail.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.jetpack.submission1.data.AppRepostory
import com.jetpack.submission1.data.source.local.entity.MovieEntity
import com.jetpack.submission1.data.source.local.entity.TvEntity
import com.jetpack.submission1.vo.Resource

class DetailViewModel(private val appRepostory: AppRepostory): ViewModel() {


    private val getFavoriteMovie= MutableLiveData<Boolean>()
    private val getFavoriteTv= MutableLiveData<Boolean>()

    fun setFavoriteMovie(movieEntity: MovieEntity) {
        val fav=movieEntity.favorite
        var ket:Boolean
        if(fav){
            ket=false
            appRepostory.setMovieFav(movieEntity,ket)
        }else{
            ket=true
            appRepostory.setMovieFav(movieEntity,ket)
        }
        getFavoriteMovie.postValue(ket)
    }

    fun setFavoriteTv(tvEntity: TvEntity) {
        val fav=tvEntity.favorite
        var ket:Boolean
        if(fav){
            ket=false
            appRepostory.setTvFav(tvEntity,ket)
        }else{
            ket=true
            appRepostory.setTvFav(tvEntity,ket)
        }
        getFavoriteTv.postValue(ket)
    }
}
