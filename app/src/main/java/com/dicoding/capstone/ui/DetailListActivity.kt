package com.dicoding.capstone.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.dicoding.capstone.adapter.DataObat
import com.dicoding.capstone.model.FactoryViewModel
import com.dicoding.capstone.model.ObatViewModel
import com.dicoding.capstone.databinding.ActivityDetailListBinding

class DetailListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailListBinding

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = FactoryViewModel.getInstance(this)
        val viewModel = ViewModelProvider(this, factory).get(ObatViewModel::class.java)

        setBinding(viewModel.getDetailObat(intent.getIntExtra(EXTRA_ID, 0)))
    }
    private fun setBinding(data: DataObat) {

        binding.tvNama.text = data.nama
        binding.tvDeskripsi.text = data.deskripsi
    }
}