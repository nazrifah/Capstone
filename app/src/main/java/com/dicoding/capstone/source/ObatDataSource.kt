package com.dicoding.capstone.source

import androidx.lifecycle.LiveData
import com.dicoding.capstone.source.entity.ObatEntity
import com.dicoding.capstone.source.room.ObatDao

class ObatDataSource private constructor(private val obatDao: ObatDao) {

    companion object {
        private var INSTANCE: ObatDataSource? = null

        fun getInstance(movieDao: ObatDao): ObatDataSource =
            INSTANCE ?: ObatDataSource(movieDao)
    }
    fun getObat(): LiveData<List<ObatEntity>> = obatDao.getObat()

    fun insertObat(obat : List<ObatEntity>) = obatDao.insertObat(obat)

    fun getCatBat(): LiveData<List<ObatEntity>> = obatDao.getCatBat()
}