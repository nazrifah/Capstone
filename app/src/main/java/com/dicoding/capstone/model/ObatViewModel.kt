package com.dicoding.capstone.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.capstone.AppRepository
import com.dicoding.capstone.adapter.DataObat
import com.dicoding.capstone.source.entity.ObatEntity
import com.dicoding.capstone.utils.Resource

class ObatViewModel(private val appRepository: AppRepository):ViewModel() {
    fun getObat(): LiveData<Resource<List<ObatEntity>>> = appRepository.getObat()

    fun getDetailObat(id: Int): DataObat = appRepository.getDetailObat(id)

    fun getCatBat(id:Int): LiveData<Resource<List<ObatEntity>>> = appRepository.getCatBat()

    fun getCatDem(id:Int): LiveData<Resource<List<ObatEntity>>> = appRepository.getCatDem()

    fun getCatKul(id:Int): LiveData<Resource<List<ObatEntity>>> = appRepository.getCatKul()

    fun getCatDia(id:Int): LiveData<Resource<List<ObatEntity>>> = appRepository.getCatDia()
}