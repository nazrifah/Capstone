package com.dicoding.capstone.usersigin.viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.capstone.usersigin.data.UserRepository

class ViewModelFactory(private val repository: UserRepository?): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        try {
            return modelClass.getConstructor(UserRepository::class.java).newInstance(repository)
        } catch (e: InstantiationException) {
            throw RuntimeException("Cannot create an instance of $modelClass", e)
        }
    }
    companion object {
        fun createFactory(activity: Activity): ViewModelFactory {
            val context = activity.applicationContext
                ?: throw IllegalStateException("Not yet attached to Application")

            return ViewModelFactory(UserRepository.getInstance(context))
        }
    }
}