package com.dicoding.capstone.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.capstone.R
import com.dicoding.capstone.adapter.DataAdapter
import com.dicoding.capstone.databinding.ActivityOptionBinding
import com.dicoding.capstone.model.FactoryViewModel
import com.dicoding.capstone.model.ObatViewModel
import com.dicoding.capstone.source.entity.ObatEntity

class OptionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOptionBinding
    private lateinit var adapter: DataAdapter
    private lateinit var viewModel: ObatViewModel

    companion object{
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = DataAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            rvIdobat.setHasFixedSize(true)
            rvIdobat.layoutManager = LinearLayoutManager(this@OptionActivity)
            rvIdobat.adapter = adapter
        }

        val factory = FactoryViewModel.getInstance(this)
        viewModel = ViewModelProvider(this, factory).get(ObatViewModel::class.java)

        val intentExtra = intent.extras
        if (intentExtra != null){
            val idExtra = intentExtra.getInt(EXTRA_ID)
            if (idExtra == 1){
                viewModel.getCatBat(idExtra).observe(this, { obat ->
                    adapter.listData(obat.data)
                    adapter.notifyDataSetChanged()
                })
            }else if (idExtra == 2){
                viewModel.getCatDem(idExtra).observe(this, { obat ->
                    adapter.listData(obat.data)
                    adapter.notifyDataSetChanged()
                })
            }else if (idExtra == 3){
                viewModel.getCatKul(idExtra).observe(this, { obat ->
                    adapter.listData(obat.data)
                    adapter.notifyDataSetChanged()
                })
            }else if (idExtra == 4){
                viewModel.getCatDia(idExtra).observe(this, { obat ->
                    adapter.listData(obat.data)
                    adapter.notifyDataSetChanged()
                })
            }
        }

        adapter.setOnItemClickCallback(object : DataAdapter.OnItemClickCallback{
            override fun onItemClicked(data: ObatEntity) {
                Intent(this@OptionActivity, DetailListActivity::class.java).also {
                    it.putExtra(DetailListActivity.EXTRA_ID, data.id)
                    startActivity(it)
                }
            }
        })
    }
}