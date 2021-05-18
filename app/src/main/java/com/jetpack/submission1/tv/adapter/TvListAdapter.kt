package com.jetpack.submission1.tv.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jetpack.submission1.R
import com.jetpack.submission1.data.MovieEntity
import com.jetpack.submission1.data.TvEntity
import com.jetpack.submission1.databinding.ItemListBinding
import com.jetpack.submission1.movie.adapter.MovieListAdapter
import java.util.ArrayList

class TvListAdapter : RecyclerView.Adapter<TvListAdapter.MovieViewHolder>()  {
    private var onItemClickCallback: OnItemClickCallback? = null
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    private var listTv = ArrayList<TvEntity>()
    fun setTv(tv: List<TvEntity>?) {
        if (tv == null) return
        this.listTv.clear()
        this.listTv.addAll(tv)
    }

    inner class MovieViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(tvEntity: TvEntity) {
            val circularProgressDrawable = CircularProgressDrawable(itemView.context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()
            with(binding) {
                Glide.with(itemView.context)
                    .load(tvEntity.imgTv)
                    .apply(
                        RequestOptions.placeholderOf(circularProgressDrawable)
                            .error(R.drawable.ic_error))
                    .into(imgPoster)
                title.text=tvEntity.titleTv
                overview.text=tvEntity.overviewTv
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
        fun onItemClicked(data: TvEntity)
    }
}