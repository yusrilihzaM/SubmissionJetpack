package com.jetpack.submission1.splashscreen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.bumptech.glide.Glide
import com.jetpack.submission1.R
import com.jetpack.submission1.databinding.ActivitySplashBinding
import com.jetpack.submission1.home.HomeActivity

class SplashActivity : Activity() {
    companion object{
        const val TIME:Long=3000
    }
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Glide.with(this)
            .load(R.drawable.ic_movie)
            .into(binding.icLogo)
        Handler(Looper.getMainLooper()).postDelayed({ moveActivity() },TIME)

    }
    private fun moveActivity() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}