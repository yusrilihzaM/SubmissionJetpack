package com.jetpack.submission1.ui.detail

import android.annotation.SuppressLint
import android.graphics.Movie
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jetpack.submission1.BuildConfig
import com.jetpack.submission1.R
import com.jetpack.submission1.data.source.local.entity.MovieEntity
import com.jetpack.submission1.data.source.local.entity.TvEntity
import com.jetpack.submission1.data.source.remote.response.*
import com.jetpack.submission1.databinding.ActivityDetailBinding
import com.jetpack.submission1.ui.detail.viewmodel.DetailViewModel
import com.jetpack.submission1.viewmodel.ViewModelFactory
import com.jetpack.submission1.vo.Status
import com.jetpack.submission1.vo.Status.*
import com.sackcentury.shinebuttonlib.ShineButton

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA_MOVIE = "extra_data_movie"
        const val EXTRA_DATA_TV = "extra_data_tv"
        private const val URL_IMAGE= BuildConfig.URL_IMAGE
    }
    private lateinit var binding: ActivityDetailBinding
    private lateinit var circularProgressDrawable : CircularProgressDrawable

    private lateinit var viewModel: DetailViewModel
    private var idMovie:Int=0
    private var idTv:Int=0
    private lateinit var dataMovie:MovieEntity
    private lateinit var dataTv:TvEntity
    private lateinit var loveBtn: ShineButton
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val upArrow =resources.getDrawable(R.drawable.ic_baseline_arrow_back_24)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(upArrow)

        circularProgressDrawable= CircularProgressDrawable(this)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        loveBtn=binding.love
        loveBtn.init(this)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]


        if(intent.getParcelableExtra<MovieEntity>(EXTRA_DATA_MOVIE)!=null){
            dataMovie=intent.getParcelableExtra<MovieEntity>(EXTRA_DATA_MOVIE) as MovieEntity
            setContentMovie(dataMovie)
            idMovie=dataMovie.idMovie
            loveBtn.isChecked = dataMovie.favorite
            loveBtn.setOnClickListener {
                viewModel.setFavoriteMovie(dataMovie)
            }
        }
        else {
            dataTv=intent.getParcelableExtra<TvEntity>(EXTRA_DATA_TV) as TvEntity
            setContentTv(dataTv)
            idTv=dataTv.tvId
            loveBtn.isChecked = dataTv.favorite
            loveBtn.setOnClickListener {
                viewModel.setFavoriteTv(dataTv)
            }
        }
    }

    private fun setContentMovie(movieEntity: MovieEntity){
        supportActionBar?.title = Html.fromHtml("<font color=\"black\">" + movieEntity.title + "</font>")
        binding.run {
            overview.text=movieEntity.overview
            Glide.with(applicationContext)
                .load(URL_IMAGE+movieEntity.posterPath)
                .apply(
                    RequestOptions.placeholderOf(circularProgressDrawable)
                        .error(R.drawable.ic_error)
                )
                .into(binding.imgPoster)
        }

    }
    private fun setContentTv(tvEntity: TvEntity){
        supportActionBar?.title = Html.fromHtml("<font color=\"black\">" + tvEntity.name + "</font>")
        binding.run {
            overview.text=tvEntity.overview
            Glide.with(applicationContext)
                .load(URL_IMAGE+tvEntity.posterPath)
                .apply(
                    RequestOptions.placeholderOf(circularProgressDrawable)
                        .error(R.drawable.ic_error))
                .into(binding.imgPoster)
        }

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