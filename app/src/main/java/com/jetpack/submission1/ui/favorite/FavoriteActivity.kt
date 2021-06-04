package com.jetpack.submission1.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import androidx.annotation.StringRes
import com.google.android.material.tabs.TabLayoutMediator
import com.jetpack.submission1.R
import com.jetpack.submission1.databinding.ActivityFavoriteBinding
import com.jetpack.submission1.databinding.ActivityMovieBinding
import com.jetpack.submission1.ui.favorite.adapter.SectionsPagerAdapter

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var sectionsPagerAdapter: SectionsPagerAdapter


    companion object {

        @StringRes
        private  val TAB_TITLES= intArrayOf(
            R.string.movies,
            R.string.tv
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val upArrow =resources.getDrawable(R.drawable.ic_baseline_arrow_back_24)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(upArrow)
        supportActionBar?.title = Html.fromHtml("<font color=\"black\">" + getString(R.string.favorite) + "</font>")

        showTab()


    }
    private fun showTab(){
        sectionsPagerAdapter=SectionsPagerAdapter(this)
        val viewPager=binding.viewPager
        viewPager.adapter=sectionsPagerAdapter

        val tabs=binding.tabs
        TabLayoutMediator(tabs, viewPager){ tab, position->
            tab.text=resources.getString(TAB_TITLES[position])
        }.attach()
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