package com.jetpack.submission1.ui.home.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jetpack.submission1.data.AppRepostory
import com.jetpack.submission1.data.source.remote.response.MoviesResultsItem
import com.jetpack.submission1.data.source.remote.response.TvResultsItem
import com.jetpack.submission1.util.DataDummyMovie
import com.jetpack.submission1.util.DataDummyTv
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {
    private lateinit var viewModel: HomeViewModel

    @Mock
    private lateinit var appRepostory: AppRepostory

    @Mock
    private lateinit var observerMovie: Observer<List<MoviesResultsItem>>

    @Mock
    private lateinit var observerTv: Observer<List<TvResultsItem>>

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel= HomeViewModel(appRepostory)
    }

    @Test
    fun getMovies() {
        val movies= MutableLiveData<List<MoviesResultsItem>>()
        val dummyMovies = DataDummyMovie.getDummyRemoteMovie()
        movies.value=dummyMovies
        Mockito.`when`(appRepostory.getMovies()).thenReturn( movies)
        val moviesEntities=viewModel.getMovies().value
        verify(appRepostory).getMovies()
        assertNotNull(moviesEntities)
        assertEquals(20,moviesEntities?.size)
        viewModel.getMovies().observeForever(observerMovie)
        Mockito.verify(observerMovie).onChanged(dummyMovies)
    }

    @Test
    fun getTv() {
        val tv= MutableLiveData<List<TvResultsItem>>()
        val dummyTv = DataDummyTv.getDummyRemoteTv()
        tv.value=dummyTv
        Mockito.`when`(appRepostory.getTv()).thenReturn( tv)
        val tvEntities=viewModel.getTv().value
        verify(appRepostory).getTv()

        assertNotNull(tvEntities)
        assertEquals(20,tvEntities?.size)

        viewModel.getTv().observeForever(observerTv)
        Mockito.verify(observerTv).onChanged(dummyTv)
    }
}