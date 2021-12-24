package com.dicoding.capstone.usersigin.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.capstone.usersigin.data.User
import com.dicoding.capstone.usersigin.data.UserRepository

class SigninViewModel(private val repository: UserRepository) : ViewModel() {
    fun insert(data: User) {
        repository.insertUser(data)
    }
    fun getUser(): LiveData<List<User>> {
        return repository.getUser()
    }
}