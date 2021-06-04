package com.jetpack.submission1.ui.favorite.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.jetpack.submission1.R
import com.jetpack.submission1.data.source.local.entity.MovieEntity
import com.jetpack.submission1.databinding.FragmentFavMovieBinding
import com.jetpack.submission1.ui.detail.DetailActivity
import com.jetpack.submission1.ui.favorite.viewmodel.FavoriteViewModel
import com.jetpack.submission1.ui.movie.adapter.MovieListAdapter
import com.jetpack.submission1.viewmodel.ViewModelFactory


class FavMovieFragment : Fragment() {

    private var fragmentFavMovieBinding: FragmentFavMovieBinding? = null
    private val binding get() = fragmentFavMovieBinding
    private lateinit var movieListAdapter: MovieListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentFavMovieBinding = FragmentFavMovieBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]
            movieListAdapter= MovieListAdapter()
            viewModel.getFavMovies().observe(viewLifecycleOwner,{data->
                if(data.isEmpty()){
                    showLoading(false)
                    showNodata(true)
                }else{
                    showLoading(false)
                    showNodata(false)
                    binding?.icNodata?.visibility = View.GONE
                    binding?.rvMovie?.setHasFixedSize(true)
                    movieListAdapter= MovieListAdapter()
                    movieListAdapter.setMovies(data)
                    binding?.rvMovie?.adapter=movieListAdapter
                    movieListAdapter.setOnItemClickCallback(object : MovieListAdapter.OnItemClickCallback{
                        override fun onItemClicked(data: MovieEntity) {
                            Toast.makeText(context, data.originalTitle, Toast.LENGTH_SHORT).show()
                            val intent= Intent(context, DetailActivity::class.java)
                            intent.putExtra(DetailActivity.EXTRA_DATA_MOVIE,data)
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
        fragmentFavMovieBinding = null
    }
    private fun showLoading(state: Boolean) {
        if (state) {
            binding?.progressBar?.visibility = View.VISIBLE
        } else {
            binding?.progressBar?.visibility = View.GONE
        }
    }
}