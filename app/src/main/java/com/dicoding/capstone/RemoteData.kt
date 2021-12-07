package com.dicoding.capstone

import com.dicoding.capstone.adapter.DataObat
import com.dicoding.capstone.utils.JsonHelper

class RemoteData private constructor(private val jsonHelper: JsonHelper) {

    companion object {
        @Volatile
        private var instance: RemoteData? = null

        fun getInstance(helper: JsonHelper): RemoteData =
            instance ?: synchronized(this) {
                instance ?: RemoteData(helper).apply { instance = this }
            }
    }
    fun getAllObat(): List<DataObat> = jsonHelper.loadObat()

    fun getDetailObat(): List<DataObat> = jsonHelper.loadObat()
}