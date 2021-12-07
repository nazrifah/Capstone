package com.dicoding.capstone

import com.dicoding.capstone.adapter.DataObat

interface DataSource {
    fun getObat() : List<DataObat>

    fun getDetailObat(id: Int) : DataObat
}