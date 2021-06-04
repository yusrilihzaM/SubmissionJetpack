package com.jetpack.submission1.ui.favorite.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jetpack.submission1.R
import com.jetpack.submission1.databinding.FragmentFavMovieBinding
import com.jetpack.submission1.databinding.FragmentFavTvBinding


class FavTvFragment : Fragment() {
    private var fragmentFavTvBinding: FragmentFavTvBinding? = null
    private val binding get() = fragmentFavTvBinding

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
}