package com.dicoding.capstone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.capstone.databinding.ItemContentBinding
import com.dicoding.capstone.source.entity.ObatEntity

class DataAdapter(): RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    private var list = ArrayList<ObatEntity>()

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    fun listData(arrayUser: List<ObatEntity>?) {
        if (arrayUser == null) return
        this.list.clear()
        this.list.addAll(arrayUser)
        notifyDataSetChanged()
    }

    inner class DataViewHolder(private val binding: ItemContentBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: ObatEntity) {
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(data)
            }
            binding.apply {
                binding.viewNama.text = data.nama
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = ItemContentBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickCallback{
        fun onItemClicked(data: ObatEntity)
    }
}