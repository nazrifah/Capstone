package com.dicoding.capstone.usersigin.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = User::class)
    fun insertUser(user: User)

    @Query("SELECT * FROM user")
    fun getUser(): LiveData<List<User>>
}