package com.jetpack.submission1.ui.tv

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
import com.jetpack.submission1.data.source.local.entity.TvEntity
import com.jetpack.submission1.data.source.remote.response.TvResultsItem
import com.jetpack.submission1.databinding.ActivityTvBinding
import com.jetpack.submission1.ui.detail.DetailActivity
import com.jetpack.submission1.ui.home.viewmodel.HomeViewModel
import com.jetpack.submission1.ui.tv.adapter.TvListAdapter
import com.jetpack.submission1.viewmodel.ViewModelFactory

@Suppress("DEPRECATION")
class TvActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTvBinding
    private lateinit var tvListAdapter: TvListAdapter
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv)
        binding = ActivityTvBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val upArrow =resources.getDrawable(R.drawable.ic_baseline_arrow_back_24)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(upArrow)
        supportActionBar?.title = Html.fromHtml("<font color=\"black\">" + getString(R.string.tv) + "</font>")
        showTvShow()

    }
    private fun showTvShow(){
        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
        viewModel.getTv().observe(this,{tv->
            showLoading(false)
            binding.rvMovie.setHasFixedSize(true)
            tvListAdapter= TvListAdapter()
            tvListAdapter.setTv(tv.data)
            binding.rvMovie.adapter=tvListAdapter
            tvListAdapter.setOnItemClickCallback(object : TvListAdapter.OnItemClickCallback{
                override fun onItemClicked(data: TvEntity) {
                    Toast.makeText(this@TvActivity, data.originalName, Toast.LENGTH_SHORT).show()
                    val intent= Intent(this@TvActivity, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_DATA_TV,data.id)
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