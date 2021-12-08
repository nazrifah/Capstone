package com.dicoding.capstone.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.dicoding.capstone.R
import com.dicoding.capstone.model.ObatViewModel
import com.dicoding.capstone.adapter.DataAdapter
import com.dicoding.capstone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: DataAdapter
    private lateinit var viewModel: ObatViewModel
    private val openingAnimation : Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.opening_anim) }
    private val closingAnimation : Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.closing_anim) }
    private var clickedFab = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            card1.setOnClickListener {
                startActivity(
                    Intent(this@MainActivity, OptionActivity::class.java)
                )
            }
            card2.setOnClickListener {
                startActivity(
                        Intent(this@MainActivity, OptionActivity::class.java)
                )
            }

            fab.setOnClickListener{
                settingFloatingButton()
            }
            menuList.setOnClickListener {
                startActivity(
                    Intent(this@MainActivity, ListActivity::class.java)
                )
            }
            menuHome.setOnClickListener {
                startActivity(
                    Intent(this@MainActivity, AboutUsActivity::class.java)
                )
            }
        }
    }
    private fun settingFloatingButton(){
        visibility(clickedFab)
        clickableFab(clickedFab)
        animationFab(clickedFab)

        clickedFab = !clickedFab
    }
    private fun visibility(clickedFAB : Boolean){
        if (!clickedFAB){
            binding.menuList.visibility = View.VISIBLE
            binding.menuHome.visibility = View.VISIBLE
        }
        else {
            binding.menuList.visibility = View.INVISIBLE
            binding.menuHome.visibility = View.INVISIBLE
        }
    }
    private fun animationFab(clickedFAB : Boolean){
        if (!clickedFAB){
            binding.menuList.startAnimation(openingAnimation)
            binding.menuHome.startAnimation(openingAnimation)
        }
        else {
            binding.menuList.startAnimation(closingAnimation)
            binding.menuHome.startAnimation(closingAnimation)
        }
    }

    private fun clickableFab(clickedFAB : Boolean){
        if (!clickedFAB){
            binding.menuList.isClickable = true
            binding.menuHome.isClickable = true
        }
        else {
            binding.menuList.isClickable = false
            binding.menuHome.isClickable = false
        }
    }
}