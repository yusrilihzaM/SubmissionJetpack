package com.jetpack.submission1.ui.home.viewmodel

import android.graphics.Movie
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.jetpack.submission1.data.AppRepostory
import com.jetpack.submission1.data.source.local.entity.MovieEntity
import com.jetpack.submission1.data.source.local.entity.TvEntity
import com.jetpack.submission1.data.source.remote.response.MoviesResultsItem
import com.jetpack.submission1.data.source.remote.response.TvResultsItem
import com.jetpack.submission1.util.DataDummyMovie
import com.jetpack.submission1.util.DataDummyTv
import com.jetpack.submission1.vo.Resource
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
    private lateinit var observerMovie: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var observerTv: Observer<Resource<PagedList<TvEntity>>>

    @Mock
    private lateinit var pagedListMovie: PagedList<MovieEntity>
    @Mock
    private lateinit var pagedListTv: PagedList<TvEntity>
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel= HomeViewModel(appRepostory)
    }

    @Test
    fun getMovies() {
        val movies= MutableLiveData<Resource<PagedList<MovieEntity>>>()
        val dummyMovies =  Resource.success(pagedListMovie)
        movies.value=dummyMovies
        Mockito.`when`(dummyMovies.data?.size).thenReturn(20)
        Mockito.`when`(appRepostory.getMovies()).thenReturn( movies)
        val moviesEntities=viewModel.getMovies().value?.data
        verify(appRepostory).getMovies()
        assertNotNull(moviesEntities)
        assertEquals(20,moviesEntities?.size)
        viewModel.getMovies().observeForever(observerMovie)
        Mockito.verify(observerMovie).onChanged(dummyMovies)
    }

    @Test
    fun getTv() {
        val tv= MutableLiveData<Resource<PagedList<TvEntity>>>()
        val dummyTv = Resource.success(pagedListTv)
        tv.value=dummyTv
        Mockito.`when`(dummyTv.data?.size).thenReturn(20)
        Mockito.`when`(appRepostory.getTv()).thenReturn( tv)
        val tvEntities=viewModel.getTv().value?.data
        verify(appRepostory).getTv()

        assertNotNull(tvEntities)
        assertEquals(20,tvEntities?.size)

        viewModel.getTv().observeForever(observerTv)
        Mockito.verify(observerTv).onChanged(dummyTv)
    }
}