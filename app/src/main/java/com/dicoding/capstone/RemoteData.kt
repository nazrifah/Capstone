package com.dicoding.capstone

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.capstone.adapter.DataObat
import com.dicoding.capstone.utils.ApiResponse
import com.dicoding.capstone.utils.EspressoIdlingResource
import com.dicoding.capstone.utils.JsonHelper

class RemoteData private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler(Looper.getMainLooper())

    companion object {

        private const val SERVICE_LATENCY_IN_MILLIS: Long = 500

        @Volatile
        private var instance: RemoteData? = null

        fun getInstance(helper: JsonHelper): RemoteData =
            instance ?: synchronized(this) {
                instance ?: RemoteData(helper).apply { instance = this }
            }
    }
    fun getAllObat(): LiveData<ApiResponse<List<DataObat>>> {
        EspressoIdlingResource.increment()
        val obatResult = MutableLiveData<ApiResponse<List<DataObat>>>()
        handler.postDelayed({
            obatResult.value = ApiResponse.success(jsonHelper.loadObat())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return obatResult
    }

    fun getDetailObat(): List<DataObat> = jsonHelper.loadObat()
}