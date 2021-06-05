package com.jetpack.submission1.ui.favorite.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jetpack.submission1.data.AppRepostory
import com.jetpack.submission1.data.source.local.entity.MovieEntity
import com.jetpack.submission1.data.source.local.entity.TvEntity
import com.jetpack.submission1.ui.home.viewmodel.HomeViewModel
import com.jetpack.submission1.util.DataDummyMovie
import com.jetpack.submission1.util.DataDummyTv
import com.jetpack.submission1.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest{
    private lateinit var viewModel: FavoriteViewModel
    @Mock
    private lateinit var appRepostory: AppRepostory

    @Mock
    private lateinit var observerMovie: Observer<List<MovieEntity>>

    @Mock
    private lateinit var observerTv: Observer<List<TvEntity>>

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel= FavoriteViewModel(appRepostory)
    }

    @Test
    fun getFavMovies() {
        val movies= MutableLiveData<List<MovieEntity>>()
        val dummyMovies = DataDummyMovie.getDummyRemoteMovie()
        movies.value=dummyMovies
        Mockito.`when`(appRepostory.getFavMovie()).thenReturn( movies)
        val moviesEntities=viewModel.getFavMovies().value
        verify(appRepostory).getFavMovie()

        assertNotNull(moviesEntities)
        assertEquals(20,moviesEntities?.size)

        viewModel.getFavMovies().observeForever(observerMovie)
        Mockito.verify(observerMovie).onChanged(dummyMovies)
    }

    @Test
    fun getFavTv() {
        val tv= MutableLiveData<List<TvEntity>>()
        val dummyTv = DataDummyTv.getDummyRemoteTv()
        tv.value=dummyTv
        Mockito.`when`(appRepostory.getFavTv()).thenReturn(tv)
        val tvEntities=viewModel.getFavTv().value
        verify(appRepostory).getFavTv()

        assertNotNull(tvEntities)
        assertEquals(20,tvEntities?.size)

        viewModel.getFavTv().observeForever(observerTv)
        Mockito.verify(observerTv).onChanged(dummyTv)
    }
}