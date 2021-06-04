package com.jetpack.submission1.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jetpack.submission1.BuildConfig
import com.jetpack.submission1.R
import com.jetpack.submission1.data.source.local.entity.TvEntity
import com.jetpack.submission1.data.source.remote.response.TvResultsItem
import com.jetpack.submission1.databinding.ItemCardBinding
import java.util.*

class TvCardAdapter: RecyclerView.Adapter<TvCardAdapter.TvViewHolder>() {

    private var onItemClickCallback: TvCardAdapter.OnItemClickCallback? = null
    fun setOnItemClickCallback(onItemClickCallback: TvCardAdapter.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    private var listTvs = ArrayList<TvEntity>()
    fun setTv(tv: List<TvEntity>) {
        if (tv == null) return
        this.listTvs.clear()
        this.listTvs.addAll(tv)
    }

    inner class TvViewHolder(private val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(tvEntity: TvEntity) {
            val circularProgressDrawable = CircularProgressDrawable(itemView.context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()

            with(binding) {
                Glide.with(itemView.context)
                    .load(URL_IMAGE+tvEntity.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(circularProgressDrawable)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgPoster)
            }
            itemView.setOnClickListener { onItemClickCallback?.onItemClicked(tvEntity) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val itemsBinding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvViewHolder(itemsBinding)
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        holder.bind(listTvs[position])
    }
    override fun getItemCount(): Int = listTvs.size
    interface OnItemClickCallback {
        fun onItemClicked(data: TvEntity)
    }
    companion object {
        private const val URL_IMAGE= BuildConfig.URL_IMAGE
    }
}