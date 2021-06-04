package com.jetpack.submission1.ui.favorite.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jetpack.submission1.ui.favorite.fragment.FavMovieFragment
import com.jetpack.submission1.ui.favorite.fragment.FavTvFragment

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            // kirim data ke Fragment melalui new instance sesuai video intruksi dicoding
            0 -> fragment = FavMovieFragment()
            1 -> fragment = FavTvFragment()
        }
        return fragment as Fragment
    }
}