package com.jetpack.submission1.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.jetpack.submission1.data.source.local.LocalDataSource
import com.jetpack.submission1.data.source.local.entity.MovieEntity
import com.jetpack.submission1.data.source.local.entity.TvEntity
import com.jetpack.submission1.data.source.remote.RemoteDataSource
import com.jetpack.submission1.util.AppExecutors
import com.jetpack.submission1.util.DataDummyMovie
import com.jetpack.submission1.util.DataDummyTv
import com.jetpack.submission1.util.LiveDataTestUtil
import com.jetpack.submission1.utils.PagedListUtil
import com.jetpack.submission1.vo.Resource

import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito
import org.mockito.Mockito.*

class AppRepostoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val appRepository = FakeAppRepostory(remote,local, appExecutors)

    private val movieResponses = DataDummyMovie.getDummyRemoteMovie()

    private val tvResponses = DataDummyTv.getDummyRemoteTv()

    @Test
    fun getMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getMovies()).thenReturn(dataSourceFactory)
        appRepository.getMovies()

        verify(local).getMovies()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummyMovie.getDummyRemoteMovie()))
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getTv() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvEntity>
        `when`(local.getTv()).thenReturn(dataSourceFactory)
        appRepository.getTv()

        verify(local).getTv()

        val tvEntities = Resource.success(PagedListUtil.mockPagedList(DataDummyTv.getDummyRemoteTv()))
        assertNotNull(tvEntities)
        assertEquals(tvResponses.size.toLong(), tvEntities.data?.size?.toLong())
    }

    @Test
    fun getFavTv() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvEntity>
        `when`(local.getFavTv()).thenReturn(dataSourceFactory)
        appRepository.getFavTv()

        verify(local).getFavTv()

        val tvEntities = Resource.success(PagedListUtil.mockPagedList(DataDummyTv.getDummyRemoteTv()))
        assertNotNull(tvEntities)
        assertEquals(tvResponses.size.toLong(), tvEntities.data?.size?.toLong())
    }

    @Test
    fun getFavMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavMovies()).thenReturn(dataSourceFactory)
        appRepository.getFavMovie()

        verify(local).getFavMovies()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummyMovie.getDummyRemoteMovie()))
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }
}