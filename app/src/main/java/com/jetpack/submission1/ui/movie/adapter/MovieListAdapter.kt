package com.jetpack.submission1.ui.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jetpack.submission1.BuildConfig
import com.jetpack.submission1.R
import com.jetpack.submission1.data.source.local.entity.MovieEntity
import com.jetpack.submission1.data.source.remote.response.MoviesResultsItem
import com.jetpack.submission1.databinding.ItemListBinding
import java.util.ArrayList

class MovieListAdapter: PagedListAdapter<MovieEntity, MovieListAdapter.MovieViewHolder>(DIFF_CALLBACK)  {
    companion object {
        private const val URL_IMAGE= BuildConfig.URL_IMAGE
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.idMovie == newItem.idMovie
            }
            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
    private var onItemClickCallback: MovieListAdapter.OnItemClickCallback? = null
    fun setOnItemClickCallback(onItemClickCallback: MovieListAdapter.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    private var listMovies = ArrayList<MovieEntity>()
    fun setMovies(movies: List<MovieEntity>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    inner class MovieViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(movieEntity: MovieEntity) {
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
        val movieEntity = getItem(position)
        if (movieEntity != null) {
            holder.bind(movieEntity)
        }
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: MovieEntity)
    }
}