package com.jetpack.submission1.ui.movie

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.jetpack.submission1.R
import com.jetpack.submission1.data.source.local.entity.MovieEntity
import com.jetpack.submission1.data.source.remote.response.MoviesResultsItem
import com.jetpack.submission1.databinding.ActivityMovieBinding
import com.jetpack.submission1.ui.detail.DetailActivity
import com.jetpack.submission1.ui.home.viewmodel.HomeViewModel
import com.jetpack.submission1.ui.movie.adapter.MovieListAdapter
import com.jetpack.submission1.viewmodel.ViewModelFactory

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

    }
    private fun showMovies(){
        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
        viewModel.getMovies().observe(this,{movies->
            showLoading(false)
            binding.rvMovie.setHasFixedSize(true)
            movieListAdapter= MovieListAdapter()
//            movieListAdapter.setMovies(movies.data)
            movieListAdapter.submitList(movies.data)
            binding.rvMovie.adapter=movieListAdapter
            movieListAdapter.setOnItemClickCallback(object : MovieListAdapter.OnItemClickCallback{
                override fun onItemClicked(data: MovieEntity) {
                    Toast.makeText(this@MovieActivity, data.originalTitle, Toast.LENGTH_SHORT).show()
                    val intent= Intent(this@MovieActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA_MOVIE,data)
                startActivity(intent)
                }
            })
        })

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
    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}