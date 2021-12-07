package com.dicoding.capstone.model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.capstone.AppRepository
import com.dicoding.capstone.Injection

class FactoryViewModel private constructor(private val appRepository: AppRepository) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var  instance : FactoryViewModel?= null

        fun getInstance(context: Context) : FactoryViewModel = instance ?: synchronized(this) {
            instance ?: FactoryViewModel(Injection.provideRepository(context)).apply {
                instance = this
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(ObatViewModel::class.java) -> {
                return ObatViewModel(appRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel Class : " + modelClass.name)
        }
    }

}