package com.jetpack.submission1.tv

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.jetpack.submission1.R
import com.jetpack.submission1.data.TvEntity
import com.jetpack.submission1.databinding.ActivityTvBinding
import com.jetpack.submission1.detail.DetailActivity
import com.jetpack.submission1.home.viewmodel.HomeViewModel
import com.jetpack.submission1.tv.adapter.TvListAdapter

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
        tvListAdapter.setOnItemClickCallback(object : TvListAdapter.OnItemClickCallback{
            override fun onItemClicked(data: TvEntity) {
                Toast.makeText(this@TvActivity, data.titleTv, Toast.LENGTH_SHORT).show()
                val intent= Intent(this@TvActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA_TV,data)
                startActivity(intent)
            }
        })
    }
    private fun showTvShow(){
        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[HomeViewModel::class.java]
        val tv=viewModel.getTv()
        binding.rvMovie.setHasFixedSize(true)
        tvListAdapter= TvListAdapter()
        tvListAdapter.setTv(tv)
        binding.rvMovie.adapter=tvListAdapter
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