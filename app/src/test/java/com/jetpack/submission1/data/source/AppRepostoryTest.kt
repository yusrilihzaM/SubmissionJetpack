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
    private val movieByIdResponses = DataDummyMovie.getDummyMovieById()
    private val movieImageResponses = DataDummyMovie.getDummyMovieImage()
    private val movieId = movieResponses[0].id
    private val tvResponses = DataDummyTv.getDummyRemoteTv()
    private val tvId = tvResponses[0].id
    private val tvByIdResponses = DataDummyTv.getDummyTvById()
    private val tvImageResponses = DataDummyTv.getDummyTvImage()
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
    fun getMovieById() {
        com.nhaarman.mockitokotlin2.doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadMovieByIdCallback)
                .onMovieByIdRecevied(movieByIdResponses)
            null
        }.`when`(remote).getMovieById(
            com.nhaarman.mockitokotlin2.eq(movieId),
            com.nhaarman.mockitokotlin2.any()
        )

        val moviesByIdEntities = LiveDataTestUtil.getValue(appRepository.getMovieById(movieId))

        com.nhaarman.mockitokotlin2.verify(remote)
            .getMovieById(com.nhaarman.mockitokotlin2.eq(movieId), com.nhaarman.mockitokotlin2.any())

        assertNotNull(moviesByIdEntities)
        assertEquals(moviesByIdEntities.id, movieByIdResponses.id)
        assertEquals(moviesByIdEntities.title, movieByIdResponses.title)
        assertEquals(moviesByIdEntities.overview, movieByIdResponses.overview)
        assertEquals(moviesByIdEntities.posterPath, movieByIdResponses.posterPath)
    }

    @Test
    fun getImageMovies() {
        com.nhaarman.mockitokotlin2.doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadMovieImageCallback)
                .onMovieImageRecevied(movieImageResponses)
            null
        }.`when`(remote).getImageMovie(
            com.nhaarman.mockitokotlin2.eq(movieId),
            com.nhaarman.mockitokotlin2.any()
        )

        val moviesImageEntities = LiveDataTestUtil.getValue(appRepository.getImageMovies(movieId))

        com.nhaarman.mockitokotlin2.verify(remote)
            .getImageMovie(com.nhaarman.mockitokotlin2.eq(movieId), com.nhaarman.mockitokotlin2.any())
        assertNotNull(moviesImageEntities)
        assertEquals(moviesImageEntities.filePath, movieImageResponses.filePath)
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

    @Test
    fun getTvById() {
        com.nhaarman.mockitokotlin2.doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadTvByIdCallback)
                .onTvByIdRecevied(tvByIdResponses)
            null
        }.`when`(remote).getTvById(
            com.nhaarman.mockitokotlin2.eq(tvId),
            com.nhaarman.mockitokotlin2.any()
        )

        val tvByIdEntities = LiveDataTestUtil.getValue(appRepository.getTvById(tvId))

        com.nhaarman.mockitokotlin2.verify(remote)
            .getTvById(com.nhaarman.mockitokotlin2.eq(tvId), com.nhaarman.mockitokotlin2.any())

        assertNotNull(tvByIdEntities)
        assertEquals(tvByIdEntities.id, tvByIdResponses.id)
        assertEquals(tvByIdEntities.name, tvByIdResponses.name)
        assertEquals(tvByIdEntities.overview, tvByIdResponses.overview)
        assertEquals(tvByIdEntities.posterPath, tvByIdResponses.posterPath)
    }

    @Test
    fun getImageTv() {
        com.nhaarman.mockitokotlin2.doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadTvImageCallback)
                .onMovieTvRecevied(tvImageResponses)
            null
        }.`when`(remote).getImageTv(
            com.nhaarman.mockitokotlin2.eq(tvId),
            com.nhaarman.mockitokotlin2.any()
        )

        val tvImageEntities = LiveDataTestUtil.getValue(appRepository.getImageTv(tvId))

        com.nhaarman.mockitokotlin2.verify(remote)
            .getImageTv(com.nhaarman.mockitokotlin2.eq(tvId), com.nhaarman.mockitokotlin2.any())
        assertNotNull(tvImageEntities)
        assertEquals(tvImageEntities.filePath, tvImageResponses.filePath)
    }
}