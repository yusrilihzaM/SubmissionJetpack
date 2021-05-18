package com.jetpack.submission1.home.viewmodel

import com.jetpack.submission1.detail.viewmodel.DetailViewModel
import com.jetpack.submission1.util.DataDummyMovie
import com.jetpack.submission1.util.DataDummyTv
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class HomeViewModelTest {
    private lateinit var viewModel: HomeViewModel
    private val sizeMovie=19
    private val sizeTv=20
    @Before
    fun setUp() {
        viewModel = HomeViewModel()
    }
    @Test
    fun getMovies() {
        val movieEntities = viewModel.getMovies()
        assertNotNull(movieEntities)
        assertEquals(sizeMovie, movieEntities.size)
    }

    @Test
    fun getTv() {
        val tvEntities = viewModel.getTv()
        assertNotNull(tvEntities)
        assertEquals(sizeTv, tvEntities.size)
    }
}