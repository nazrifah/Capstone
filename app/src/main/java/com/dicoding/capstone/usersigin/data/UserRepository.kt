package com.dicoding.capstone.usersigin.data

import android.content.Context
import androidx.lifecycle.LiveData
import java.util.concurrent.Executors

class UserRepository(private val dao: UserDao) {
    private val singleExecutors = Executors.newSingleThreadExecutor()

    fun getUser(): LiveData<List<User>> {
        return dao.getUser()
    }

    fun insertUser(data: User) = executeThread{
        dao.insertUser(data)
    }

    private fun executeThread(f: () -> Unit) {
        singleExecutors.execute(f)
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(context: Context): UserRepository? {
            return instance ?: synchronized(UserRepository::class.java) {
                if (instance == null) {
                    val database = UserDatabase.getInstance(context)
                    instance = UserRepository(database.userDao())
                }
                return instance
            }
        }
    }
}