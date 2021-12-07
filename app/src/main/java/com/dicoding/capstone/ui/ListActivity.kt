package com.dicoding.capstone.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.capstone.adapter.DataObat
import com.dicoding.capstone.model.FactoryViewModel
import com.dicoding.capstone.model.ObatViewModel
import com.dicoding.capstone.adapter.DataAdapter
import com.dicoding.capstone.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding
    private lateinit var adapter: DataAdapter
    private lateinit var viewModel: ObatViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = DataAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            rvObat.setHasFixedSize(true)
            rvObat.layoutManager = LinearLayoutManager(this@ListActivity)
            rvObat.adapter = adapter
        }

        val factory = FactoryViewModel.getInstance(this)
        viewModel = ViewModelProvider(this, factory).get(ObatViewModel::class.java)
        adapter.listData(viewModel.getObat())

        adapter.setOnItemClickCallback(object : DataAdapter.OnItemClickCallback{
            override fun onItemClicked(data: DataObat) {
                Intent(this@ListActivity, DetailListActivity::class.java).also {
                    it.putExtra(DetailListActivity.EXTRA_ID, data.id)
                    startActivity(it)
                }
            }
        })
    }
}