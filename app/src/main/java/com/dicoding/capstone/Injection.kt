package com.dicoding.capstone

import android.content.Context
import com.dicoding.capstone.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): AppRepository {

        val remoteDataSource = RemoteData.getInstance(JsonHelper(context))

        return AppRepository.getInstance(remoteDataSource)
    }
}