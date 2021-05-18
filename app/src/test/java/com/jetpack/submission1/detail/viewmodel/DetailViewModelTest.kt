package com.jetpack.submission1.detail.viewmodel

import com.jetpack.submission1.util.DataDummyMovie
import com.jetpack.submission1.util.DataDummyTv
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummyMovie.generateDummyMovie()[0]
    private val movieTitle = dummyMovie.titleMovie
    private val dummyTv = DataDummyTv.generateDummyTv()[0]
    private val tvTitle = dummyTv.titleTv
    @Before
    fun setUp() {
        viewModel = DetailViewModel()
        viewModel.setSelectedItemMovie(movieTitle)
        viewModel.setSelectedItemTv(tvTitle)
    }
    @Test
    fun getItemMovie() {
        viewModel.setSelectedItemMovie(dummyMovie.titleMovie)
        val movieEntity = viewModel.getItemMovie()
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.titleMovie,movieEntity.titleMovie)
        assertEquals(dummyMovie.imgMovie,movieEntity.imgMovie)
        assertEquals(dummyMovie.overviewMovie,movieEntity.overviewMovie)
    }
    @Test
    fun getItemTv() {
        viewModel.setSelectedItemTv(dummyTv.titleTv)
        val tvEntity = viewModel.getItemTv()
        assertNotNull(tvEntity)
        assertEquals(tvEntity.titleTv,tvEntity.titleTv)
        assertEquals(tvEntity.imgTv,tvEntity.imgTv)
        assertEquals(tvEntity.overviewTv,tvEntity.overviewTv)
    }
}