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
import com.jetpack.submission1.BuildConfig
import com.jetpack.submission1.R
import com.jetpack.submission1.data.source.remote.response.MovieByIdResponse
import com.jetpack.submission1.data.source.remote.response.MoviePostersItem
import com.jetpack.submission1.data.source.remote.response.PostersItem
import com.jetpack.submission1.data.source.remote.response.TvByIdResponse
import com.jetpack.submission1.databinding.ActivityDetailBinding
import com.jetpack.submission1.detail.viewmodel.DetailViewModel
import com.jetpack.submission1.viewmodel.ViewModelFactory

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA_MOVIE = "extra_data_movie"
        const val EXTRA_DATA_TV = "extra_data_tv"
        private const val URL_IMAGE= BuildConfig.URL_IMAGE
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
        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        val idMovie=intent.extras?.getInt(EXTRA_DATA_MOVIE)
        val idTv=intent.extras?.getInt(EXTRA_DATA_TV)
        if(idMovie!=null){
            viewModel.setSelectedItemMovie(idMovie)
            viewModel.setSelectedImageMovie(idMovie)
            viewModel.getImageMovie().observe(this,{data->
                setImageMovie(data)
            })
            viewModel.getItemMovie().observe(this,{data->
                setContentMovie(data)
            })
        }
        if (idTv!=null){
            viewModel.setSelectedItemTv(idTv)
            viewModel.setSelectedImageTv(idTv)
            viewModel.getImageTv().observe(this,{data->
                setImageTv(data)
            })
            viewModel.getItemTv().observe(this,{data->
                setContentTv(data)
            })
        }
    }
    private fun setImageMovie(moviePostersItem: MoviePostersItem){
        val circularProgressDrawable = CircularProgressDrawable(this)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        Glide.with(applicationContext)
            .load(URL_IMAGE+moviePostersItem.filePath)
            .apply(
                RequestOptions.placeholderOf(circularProgressDrawable)
                    .error(R.drawable.ic_error)
            )
            .into(binding.imgPoster)
    }

    private fun setImageTv(postersItem: PostersItem){
        val circularProgressDrawable = CircularProgressDrawable(this)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        Glide.with(applicationContext)
            .load(URL_IMAGE+postersItem.filePath)
            .apply(
                RequestOptions.placeholderOf(circularProgressDrawable)
                    .error(R.drawable.ic_error))
            .into(binding.imgPoster)
    }

    private fun setContentMovie(movieEntity: MovieByIdResponse){
        supportActionBar?.title = Html.fromHtml("<font color=\"black\">" + movieEntity.title + "</font>")
        val circularProgressDrawable = CircularProgressDrawable(this)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        binding.run {
            overview.text=movieEntity.overview
        }

    }
    private fun setContentTv(tvEntity: TvByIdResponse){
        supportActionBar?.title = Html.fromHtml("<font color=\"black\">" + tvEntity.name + "</font>")
        val circularProgressDrawable = CircularProgressDrawable(this)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        binding.run {
            overview.text=tvEntity.overview
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