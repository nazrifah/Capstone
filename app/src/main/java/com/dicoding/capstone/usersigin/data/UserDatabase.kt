package com.dicoding.capstone.usersigin.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var instance: UserDatabase? = null
        fun getInstance(context: Context): UserDatabase {
            return synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context, UserDatabase::class.java, "user.db"
                ).allowMainThreadQueries().fallbackToDestructiveMigration().build()

            }

        }
    }
}