package com.jetpack.submission1.home

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.jetpack.submission1.R
import com.jetpack.submission1.data.MovieEntity
import com.jetpack.submission1.data.TvEntity
import com.jetpack.submission1.databinding.ActivityHomeBinding
import com.jetpack.submission1.detail.DetailActivity
import com.jetpack.submission1.home.adapter.MovieCardAdapter
import com.jetpack.submission1.home.adapter.TvCardAdapter
import com.jetpack.submission1.home.viewmodel.HomeViewModel
import com.jetpack.submission1.movie.MovieActivity
import com.jetpack.submission1.tv.TvActivity

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
        movieCardAdapter.setOnItemClickCallback(object :MovieCardAdapter.OnItemClickCallback{
            override fun onItemClicked(data: MovieEntity) {
                Toast.makeText(this@HomeActivity, data.titleMovie, Toast.LENGTH_SHORT).show()
                val intent=Intent(this@HomeActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA_MOVIE,data)
                startActivity(intent)
            }
        })
        tvCardAdapter.setOnItemClickCallback(object :TvCardAdapter.OnItemClickCallback{
            override fun onItemClicked(data: TvEntity) {
                Toast.makeText(this@HomeActivity, data.titleTv, Toast.LENGTH_SHORT).show()
                val intent=Intent(this@HomeActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA_TV,data)
                startActivity(intent)
            }
        })
    }

    private fun showMovies(){
        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[HomeViewModel::class.java]
        val movies=viewModel.getMovies()
        binding.rvMovie.setHasFixedSize(true)
        movieCardAdapter= MovieCardAdapter()
        movieCardAdapter.setMovies(movies)
        binding.rvMovie.adapter=movieCardAdapter
    }
    private fun showTvShow(){
        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[HomeViewModel::class.java]
        val tv=viewModel.getTv()
        binding.rvTv.setHasFixedSize(true)
        tvCardAdapter= TvCardAdapter()
        tvCardAdapter.setTv(tv)
        binding.rvTv.adapter=tvCardAdapter
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
//                startActivity(Intent(this, SettingsActivity::class.java))
                true
            }
            R.id.favorite -> {
//                startActivity(Intent(this, FavoriteActivity::class.java))
                true
            }
            else -> true
        }
    }
}