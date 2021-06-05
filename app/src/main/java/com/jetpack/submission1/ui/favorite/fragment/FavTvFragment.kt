package com.jetpack.submission1.ui.favorite.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.jetpack.submission1.R
import com.jetpack.submission1.data.source.local.entity.TvEntity
import com.jetpack.submission1.databinding.FragmentFavMovieBinding
import com.jetpack.submission1.databinding.FragmentFavTvBinding
import com.jetpack.submission1.ui.detail.DetailActivity
import com.jetpack.submission1.ui.favorite.viewmodel.FavoriteViewModel
import com.jetpack.submission1.ui.home.viewmodel.HomeViewModel
import com.jetpack.submission1.ui.movie.adapter.MovieListAdapter
import com.jetpack.submission1.ui.tv.adapter.TvListAdapter
import com.jetpack.submission1.viewmodel.ViewModelFactory


class FavTvFragment : Fragment() {
    private var fragmentFavTvBinding: FragmentFavTvBinding? = null
    private val binding get() = fragmentFavTvBinding
    private lateinit var tvListAdapter: TvListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        fragmentFavTvBinding = FragmentFavTvBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]
            tvListAdapter= TvListAdapter()
            viewModel.getFavTv().observe(viewLifecycleOwner,{data->
                if(data.isEmpty()){
                    showLoading(false)
                    showNodata(true)
                }else{
                    showLoading(false)
                    showNodata(false)
                    binding?.icNodata?.visibility = View.GONE
                    binding?.rvTv?.setHasFixedSize(true)
                    tvListAdapter= TvListAdapter()
                    tvListAdapter.submitList(data)
                    binding?.rvTv?.adapter=tvListAdapter
                    tvListAdapter.setOnItemClickCallback(object : TvListAdapter.OnItemClickCallback{
                        override fun onItemClicked(data: TvEntity) {
                            Toast.makeText(context, data.originalName, Toast.LENGTH_SHORT).show()
                            val intent= Intent(context, DetailActivity::class.java)
                            intent.putExtra(DetailActivity.EXTRA_DATA_TV,data)
                            startActivity(intent)
                        }
                    })
                }

            })
        }
    }
    private fun showNodata(state:Boolean){
        if (state) {
            binding?.icNodata?.visibility = View.VISIBLE
        } else {
            binding?.icNodata?.visibility = View.GONE
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        fragmentFavTvBinding = null
    }
    private fun showLoading(state: Boolean) {
        if (state) {
            binding?.progressBar?.visibility = View.VISIBLE
        } else {
            binding?.progressBar?.visibility = View.GONE
        }
    }
}