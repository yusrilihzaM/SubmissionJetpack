package com.jetpack.submission1.home.viewmodel

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

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