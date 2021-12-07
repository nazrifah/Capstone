package com.dicoding.capstone.model

import androidx.lifecycle.ViewModel
import com.dicoding.capstone.AppRepository
import com.dicoding.capstone.adapter.DataObat

class ObatViewModel(private val appRepository: AppRepository):ViewModel() {
    fun getObat(): List<DataObat> = appRepository.getObat()

    fun getDetailObat(id: Int): DataObat = appRepository.getDetailObat(id)
}