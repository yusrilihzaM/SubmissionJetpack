package com.jetpack.submission1.tv.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jetpack.submission1.BuildConfig
import com.jetpack.submission1.R
import com.jetpack.submission1.data.source.remote.response.TvResultsItem
import com.jetpack.submission1.databinding.ItemListBinding
import java.util.*

class TvListAdapter : RecyclerView.Adapter<TvListAdapter.MovieViewHolder>()  {
    companion object {
        private const val URL_IMAGE= BuildConfig.URL_IMAGE
    }
    private var onItemClickCallback: OnItemClickCallback? = null
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    private var listTv = ArrayList<TvResultsItem>()
    fun setTv(tv: List<TvResultsItem>?) {
        if (tv == null) return
        this.listTv.clear()
        this.listTv.addAll(tv)
    }

    inner class MovieViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(tvEntity: TvResultsItem) {
            val circularProgressDrawable = CircularProgressDrawable(itemView.context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()
            with(binding) {
                Glide.with(itemView.context)
                    .load(URL_IMAGE+tvEntity.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(circularProgressDrawable)
                            .error(R.drawable.ic_error))
                    .into(imgPosterView)
                titleView.text=tvEntity.name
                overviewView.text=tvEntity.overview
            }
            itemView.setOnClickListener { onItemClickCallback?.onItemClicked(tvEntity) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listTv[position])
    }
    override fun getItemCount(): Int = listTv.size
    interface OnItemClickCallback {
        fun onItemClicked(data: TvResultsItem)
    }
}