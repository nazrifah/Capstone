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

    companion object {
        const val ID_BATUK = 1
        const val ID_DEMAM = 2
        const val ID_KULIT = 3
        const val ID_DIABETES = 4
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            card1.setOnClickListener {
                Intent(this@MainActivity,OptionActivity::class.java).also {
                    it.putExtra(OptionActivity.EXTRA_ID, ID_BATUK)
                    startActivity(it)
                }
            }
            card2.setOnClickListener {
                Intent(this@MainActivity,OptionActivity::class.java).also {
                    it.putExtra(OptionActivity.EXTRA_ID, ID_DEMAM)
                    startActivity(it)
                }
            }
            card3.setOnClickListener {
                Intent(this@MainActivity,OptionActivity::class.java).also {
                    it.putExtra(OptionActivity.EXTRA_ID, ID_KULIT)
                    startActivity(it)
                }
            }
            card4.setOnClickListener {
                Intent(this@MainActivity,OptionActivity::class.java).also {
                    it.putExtra(OptionActivity.EXTRA_ID, ID_DIABETES)
                    startActivity(it)
                }
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

            binding.menuHomeText.visibility = View.VISIBLE
            binding.menuListText.visibility = View.VISIBLE
        }
        else {
            binding.menuList.visibility = View.INVISIBLE
            binding.menuHome.visibility = View.INVISIBLE

            binding.menuHomeText.visibility = View.INVISIBLE
            binding.menuListText.visibility = View.INVISIBLE
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