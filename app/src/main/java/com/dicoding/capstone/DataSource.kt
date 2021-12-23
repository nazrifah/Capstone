package com.dicoding.capstone

import androidx.lifecycle.LiveData
import com.dicoding.capstone.adapter.DataObat
import com.dicoding.capstone.source.entity.ObatEntity
import com.dicoding.capstone.utils.Resource

interface DataSource {
    fun getObat() : LiveData<Resource<List<ObatEntity>>>

    fun getDetailObat(id: Int) : DataObat

    fun insertObat(obat: List<ObatEntity>)

    fun getCatBat() : LiveData<Resource<List<ObatEntity>>>

    fun getCatDem() : LiveData<Resource<List<ObatEntity>>>

    fun getCatKul() : LiveData<Resource<List<ObatEntity>>>

    fun getCatDia() : LiveData<Resource<List<ObatEntity>>>
}