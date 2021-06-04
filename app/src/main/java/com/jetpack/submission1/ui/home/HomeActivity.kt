package com.jetpack.submission1.ui.home

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.jetpack.submission1.R
import com.jetpack.submission1.data.source.local.entity.MovieEntity
import com.jetpack.submission1.data.source.local.entity.TvEntity
import com.jetpack.submission1.data.source.remote.response.MoviesResultsItem
import com.jetpack.submission1.data.source.remote.response.TvResultsItem
import com.jetpack.submission1.databinding.ActivityHomeBinding
import com.jetpack.submission1.ui.detail.DetailActivity
import com.jetpack.submission1.ui.favorite.FavoriteActivity
import com.jetpack.submission1.ui.home.adapter.MovieCardAdapter
import com.jetpack.submission1.ui.home.adapter.TvCardAdapter
import com.jetpack.submission1.ui.home.viewmodel.HomeViewModel
import com.jetpack.submission1.ui.movie.MovieActivity
import com.jetpack.submission1.ui.tv.TvActivity
import com.jetpack.submission1.viewmodel.ViewModelFactory

@Suppress("DEPRECATION")
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var movieCardAdapter: MovieCardAdapter
    private lateinit var tvCardAdapter: TvCardAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = Html.fromHtml("<font color=\"black\">" + getString(R.string.app_name) + "</font>")
        showMovies()
        showTvShow()
        binding.viewMovie.setOnClickListener {
            startActivity(Intent(this, MovieActivity::class.java))
        }
        binding.viewTv.setOnClickListener {
            startActivity(Intent(this, TvActivity::class.java))
        }

    }

    private fun showMovies(){
        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
        viewModel.getMovies().observe(this,{movies->
            showLoading(false)
            binding.rvMovie.setHasFixedSize(true)
            movieCardAdapter= MovieCardAdapter()
            movies.data?.let { movieCardAdapter.setMovies(it) }
            binding.rvMovie.adapter=movieCardAdapter
            movieCardAdapter.setOnItemClickCallback(object :MovieCardAdapter.OnItemClickCallback{
                override fun onItemClicked(data: MovieEntity) {
                    Toast.makeText(this@HomeActivity, data.posterPath, Toast.LENGTH_SHORT).show()
                    val intent=Intent(this@HomeActivity, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_DATA_MOVIE,data)
                    startActivity(intent)
                }
            })
        })
    }
    private fun showTvShow(){
        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
        viewModel.getTv().observe(this,{tv->
            showLoading(false)
            binding.rvTv.setHasFixedSize(true)
            tvCardAdapter= TvCardAdapter()
            tv.data?.let { tvCardAdapter.setTv(it) }
            binding.rvTv.adapter=tvCardAdapter
            tvCardAdapter.setOnItemClickCallback(object :TvCardAdapter.OnItemClickCallback{
                override fun onItemClicked(data: TvEntity) {
                    Toast.makeText(this@HomeActivity, data.name, Toast.LENGTH_SHORT).show()
                    val intent=Intent(this@HomeActivity, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_DATA_TV,data)
                    startActivity(intent)
                }
            })
        })

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater=menuInflater
        inflater.inflate(R.menu.main_menu,menu)

        val searchManager=getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = Html.fromHtml("<font color = #0000>" + resources.getString(R.string.search_hint) + "</font>")

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(this@HomeActivity, query, Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        }
        )
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings -> {
                true
            }
            R.id.favorite -> {
                startActivity(Intent(this@HomeActivity,FavoriteActivity::class.java))
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