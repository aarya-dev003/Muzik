package com.example.muzik.activities

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.muzik.R
import com.example.muzik.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fromTopAnimation =AnimationUtils.loadAnimation(this, R.anim.from_top)
        val fromBottomAnimation =AnimationUtils.loadAnimation(this, R.anim.from_bottom)
        val fadeAnimation = AnimationUtils.loadAnimation(this, R.anim.fade)
        val muziktv = binding.muzictv
        val stL= binding.straigthL
        val invL = binding.invertedL

        invL.startAnimation(fromTopAnimation)
        stL.startAnimation(fromBottomAnimation)
        muziktv.startAnimation(fadeAnimation)

        android.os.Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        },2000)
    }
}