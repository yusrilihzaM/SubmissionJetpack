package com.jetpack.submission1.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jetpack.submission1.BuildConfig
import com.jetpack.submission1.R
import com.jetpack.submission1.data.MovieEntity
import com.jetpack.submission1.data.source.remote.response.MoviesResultsItem
import com.jetpack.submission1.databinding.ItemCardBinding
import com.jetpack.submission1.databinding.ItemListBinding
import com.jetpack.submission1.home.adapter.MovieCardAdapter
import java.util.ArrayList

class MovieListAdapter: RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>()  {
    companion object {
        private const val URL_IMAGE= BuildConfig.URL_IMAGE
    }
    private var onItemClickCallback: MovieListAdapter.OnItemClickCallback? = null
    fun setOnItemClickCallback(onItemClickCallback: MovieListAdapter.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    private var listMovies = ArrayList<MoviesResultsItem>()
    fun setMovies(movies: List<MoviesResultsItem>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    inner class MovieViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(movieEntity: MoviesResultsItem) {
            val circularProgressDrawable = CircularProgressDrawable(itemView.context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()
            with(binding) {
                Glide.with(itemView.context)
                    .load(URL_IMAGE+movieEntity.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(circularProgressDrawable)
                            .error(R.drawable.ic_error))
                    .into(imgPosterView)
                titleView.text=movieEntity.title
                overviewView.text=movieEntity.overview
            }
            itemView.setOnClickListener { onItemClickCallback?.onItemClicked(movieEntity) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovies[position])
    }
    override fun getItemCount(): Int = listMovies.size
    interface OnItemClickCallback {
        fun onItemClicked(data: MoviesResultsItem)
    }
}