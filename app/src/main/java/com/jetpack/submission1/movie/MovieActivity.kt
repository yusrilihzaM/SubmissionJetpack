package com.jetpack.submission1.movie

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.jetpack.submission1.R
import com.jetpack.submission1.data.MovieEntity
import com.jetpack.submission1.databinding.ActivityMovieBinding
import com.jetpack.submission1.detail.DetailActivity
import com.jetpack.submission1.home.viewmodel.HomeViewModel
import com.jetpack.submission1.movie.adapter.MovieListAdapter

@Suppress("DEPRECATION")
class MovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieBinding
    private lateinit var movieListAdapter: MovieListAdapter
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val upArrow =resources.getDrawable(R.drawable.ic_baseline_arrow_back_24)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(upArrow)
        supportActionBar?.title = Html.fromHtml("<font color=\"black\">" + getString(R.string.movies) + "</font>")
        showMovies()
        movieListAdapter.setOnItemClickCallback(object : MovieListAdapter.OnItemClickCallback{
            override fun onItemClicked(data: MovieEntity) {
                Toast.makeText(this@MovieActivity, data.titleMovie, Toast.LENGTH_SHORT).show()
                val intent= Intent(this@MovieActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA_MOVIE,data)
                startActivity(intent)
            }
        })
    }
    private fun showMovies(){
        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[HomeViewModel::class.java]
        val movies=viewModel.getMovies()
        binding.rvMovie.setHasFixedSize(true)
        movieListAdapter= MovieListAdapter()
        movieListAdapter.setMovies(movies)
        binding.rvMovie.adapter=movieListAdapter
    }

    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            16908332->{
                this.finish()
                true
            }
            else -> true
        }
    }
}