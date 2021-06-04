package com.jetpack.submission1.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jetpack.submission1.data.source.remote.RemoteDataSource
import com.jetpack.submission1.util.DataDummyMovie
import com.jetpack.submission1.util.DataDummyTv
import com.jetpack.submission1.util.LiveDataTestUtil
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito

class AppRepostoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)

    private val appRepository = FakeAppRepostory(remote)

    private val movieResponses = DataDummyMovie.getDummyRemoteMovie()

    private val tvResponses = DataDummyTv.getDummyRemoteTv()

    @Test
    fun getMovies() {
        com.nhaarman.mockitokotlin2.doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onMoviesRecevied(movieResponses)
            null
        }.`when`(remote).getMovies(com.nhaarman.mockitokotlin2.any())
        val movieEntities = LiveDataTestUtil.getValue(appRepository.getMovies())
        com.nhaarman.mockitokotlin2.verify(remote).getMovies(com.nhaarman.mockitokotlin2.any())
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getTv() {
        com.nhaarman.mockitokotlin2.doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvCallback)
                .onTvRecevied(tvResponses)
            null
        }.`when`(remote).getTv(com.nhaarman.mockitokotlin2.any())
        val tvEntities = LiveDataTestUtil.getValue(appRepository.getTv())
        com.nhaarman.mockitokotlin2.verify(remote).getTv(com.nhaarman.mockitokotlin2.any())
        assertNotNull(tvEntities)
        assertEquals(tvResponses.size.toLong(), tvEntities.size.toLong())
    }




}