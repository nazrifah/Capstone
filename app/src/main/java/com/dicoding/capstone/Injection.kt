package com.dicoding.capstone

import android.content.Context
import com.dicoding.capstone.source.ObatDataSource
import com.dicoding.capstone.source.room.ObatDatabase
import com.dicoding.capstone.utils.AppExecutors
import com.dicoding.capstone.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): AppRepository {

        val obatDatabase = ObatDatabase.getInstance(context)
        val remoteDataSource = RemoteData.getInstance(JsonHelper(context))
        val localDataSource = ObatDataSource.getInstance(obatDatabase.appDao())
        val appExecutors = AppExecutors()
        return AppRepository.getInstance(remoteDataSource,localDataSource, appExecutors)
    }
}