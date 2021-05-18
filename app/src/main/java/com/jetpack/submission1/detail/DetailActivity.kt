package com.jetpack.submission1.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jetpack.submission1.R
import com.jetpack.submission1.data.MovieEntity
import com.jetpack.submission1.data.TvEntity
import com.jetpack.submission1.databinding.ActivityDetailBinding
import com.jetpack.submission1.detail.viewmodel.DetailViewModel

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA_MOVIE = "extra_data_movie"
        const val EXTRA_DATA_TV = "extra_data_tv"
    }
    private lateinit var binding: ActivityDetailBinding
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val upArrow =resources.getDrawable(R.drawable.ic_baseline_arrow_back_24)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(upArrow)

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailViewModel::class.java]
        val dataMovie=intent.getParcelableExtra<MovieEntity>(EXTRA_DATA_MOVIE)
        val dataTv=intent.getParcelableExtra<TvEntity>(EXTRA_DATA_TV)
        if(dataMovie!=null){
            val title=dataMovie.titleMovie
            viewModel.setSelectedItemMovie(title)
            val data=viewModel.getItemMovie()
            setContentMovie(data)
        }
        if (dataTv!=null){
            val title=dataTv.titleTv
            viewModel.setSelectedItemTv(title)
            val data=viewModel.getItemTv()
            setContentTv(data)
        }
    }
    private fun setContentMovie(movieEntity: MovieEntity){
        supportActionBar?.title = Html.fromHtml("<font color=\"black\">" + movieEntity.titleMovie + "</font>")
        val circularProgressDrawable = CircularProgressDrawable(this)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        binding.run {
            Glide.with(applicationContext)
                .load(movieEntity.imgMovie)
                .apply(
                    RequestOptions.placeholderOf(circularProgressDrawable)
                        .error(R.drawable.ic_error)
                )
                .into(imgPoster)
            overview.text=movieEntity.overviewMovie
        }

    }
    private fun setContentTv(tvEntity: TvEntity){
        supportActionBar?.title = Html.fromHtml("<font color=\"black\">" + tvEntity.titleTv + "</font>")
        val circularProgressDrawable = CircularProgressDrawable(this)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        binding.run {
            Glide.with(applicationContext)
                .load(tvEntity.imgTv)
                .apply(
                    RequestOptions.placeholderOf(circularProgressDrawable)
                        .error(R.drawable.ic_error))
                .into(imgPoster)
            overview.text=tvEntity.overviewTv
        }

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