package com.dicoding.capstone.source.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.capstone.source.entity.ObatEntity

@Database(entities = [ObatEntity::class], version = 1, exportSchema = false )
abstract class ObatDatabase : RoomDatabase() {
    abstract fun appDao(): ObatDao

    companion object {
        @Volatile
        private var INSTANCE : ObatDatabase?= null

        fun getInstance(context: Context): ObatDatabase = INSTANCE ?: synchronized(this) {
            Room.databaseBuilder(
                context.applicationContext,
                ObatDatabase::class.java,
                "Obat.db"
            ).build().apply {
                INSTANCE = this
            }
        }
    }
}