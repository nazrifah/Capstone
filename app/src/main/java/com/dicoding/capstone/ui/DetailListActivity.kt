package com.dicoding.capstone.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
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
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = FactoryViewModel.getInstance(this)
        val viewModel = ViewModelProvider(this, factory).get(ObatViewModel::class.java)

        setBinding(viewModel.getDetailObat(intent.getIntExtra(EXTRA_ID, 0)))
        binding.btnAdmin.setOnClickListener {
            val admin = "081261394158"
            val url = "https://api.whatsapp.com/send?phone=$admin"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }
    private fun setBinding(data: DataObat) {
        binding.tvNama.text = data.nama
        binding.tvDeskripsi.text = data.deskripsi
        binding.tvHarga.text = data.harga
        binding.tvDosis.text = data.dosis
        binding.tvAturan.text = data.aturan
        binding.tvEfek.text = data.efek
        Glide.with(this)
            .load(data.foto)
            .into(binding.ivPic)
    }
}