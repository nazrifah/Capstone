package com.dicoding.capstone.utils

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.dicoding.capstone.databinding.ActivitySplashScreenBinding
import com.dicoding.capstone.usersigin.ui.SigninActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    private lateinit var splashBinding: ActivitySplashScreenBinding
    private val handler = Handler(Looper.getMainLooper())
    private val valueDelay: Int = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)

        handler.postDelayed({
            val splashIntent = Intent(this, SigninActivity::class.java)
            startActivity(splashIntent)
            finish()
        }, valueDelay.toLong())
    }
}