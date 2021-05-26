package com.jetpack.submission1.detail.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jetpack.submission1.data.source.AppRepostory
import com.jetpack.submission1.data.source.remote.response.*
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
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    @Mock
    private lateinit var observerMovie: Observer<MovieByIdResponse>
    @Mock
    private lateinit var observerImageMovie: Observer<MoviePostersItem>
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @Mock
    private lateinit var observerTv: Observer<TvByIdResponse>
    @Mock
    private lateinit var observerImageTv: Observer<PostersItem>
    @Mock
    private lateinit var appRepostory: AppRepostory
    @Before
    fun setUp() {
        viewModel = DetailViewModel(appRepostory)
    }
    // konten
    @Test
    fun getItemMovie() {
        val movies = MutableLiveData<MovieByIdResponse>()
        val dummyMovies = DataDummyMovie.getDummyMovieById()
        movies.value=dummyMovies

        Mockito.`when`(dummyMovies.id?.let { appRepostory.getMovieById(it) }).thenReturn(movies)
        dummyMovies.id?.let { viewModel.setSelectedItemMovie(it) }
        val moviesEntity = viewModel.getItemMovie().value as MovieByIdResponse
        print(moviesEntity)

        dummyMovies.id?.let { Mockito.verify(appRepostory).getMovieById(it) }

        assertNotNull(moviesEntity)

        assertEquals(dummyMovies.title,moviesEntity.title)
        assertEquals(dummyMovies.overview,moviesEntity.overview)

        viewModel.getItemMovie().observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyMovies)
    }
    @Test
    fun getItemTv() {
        val tv = MutableLiveData<TvByIdResponse>()
        val dummyTv = DataDummyTv.getDummyTvById()
        tv.value=dummyTv

        Mockito.`when`(dummyTv.id.let { appRepostory.getTvById(it) }).thenReturn(tv)
        dummyTv.id.let { viewModel.setSelectedItemTv(it) }
        val tvEntity = viewModel.getItemTv().value as TvByIdResponse
        print(tvEntity)

        dummyTv.id.let { Mockito.verify(appRepostory).getTvById(it) }
        assertNotNull(tvEntity)

        assertEquals(dummyTv.name,tvEntity.name)
        assertEquals(dummyTv.overview,tvEntity.overview)

        viewModel.getItemTv().observeForever(observerTv)
        verify(observerTv).onChanged(dummyTv)
    }
    // gambar
    @Test
    fun getMovieImage(){
        val movies = MutableLiveData<MoviePostersItem>()
        val dummyImageMovies = DataDummyMovie.getDummyMovieImage()
        val dummyMovies=DataDummyMovie.getDummyRemoteMovie()[0]
        movies.value=dummyImageMovies

        Mockito.`when`(dummyMovies.id?.let { appRepostory.getImageMovies(it) }).thenReturn(movies)
        dummyMovies.id?.let { viewModel.setSelectedImageMovie(it) }
        val moviesEntity = viewModel.getImageMovie().value as MoviePostersItem
        print(moviesEntity)

        dummyMovies.id?.let { Mockito.verify(appRepostory).getImageMovies(it) }

        assertNotNull(moviesEntity)

        assertEquals(dummyMovies.posterPath,moviesEntity.filePath)

        viewModel.getImageMovie().observeForever(observerImageMovie)
        verify(observerImageMovie).onChanged(dummyImageMovies)
    }

    @Test
    fun getTvImage(){
        val tv = MutableLiveData<PostersItem>()
        val dummyImageTv = DataDummyTv.getDummyTvImage()
        val dummyTv=DataDummyTv.getDummyRemoteTv()[0]
        tv.value=dummyImageTv

        Mockito.`when`(dummyTv.id?.let { appRepostory.getImageTv(it) }).thenReturn(tv)
        dummyTv.id?.let { viewModel.setSelectedImageTv(it) }
        val tvEntity = viewModel.getImageTv().value as PostersItem
        print(tvEntity)

        dummyTv.id?.let { Mockito.verify(appRepostory).getImageTv(it) }

        assertNotNull(tvEntity)

        assertEquals(dummyTv.posterPath,tvEntity.filePath)

        viewModel.getImageTv().observeForever(observerImageTv)
        verify(observerImageTv).onChanged(dummyImageTv)
    }
}