package com.dicoding.capstone.source.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dicoding.capstone.source.entity.ObatEntity

@Dao
interface ObatDao {
    @Query("SELECT * FROM obatEntities")
    fun getObat(): LiveData<List<ObatEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertObat(obat : List<ObatEntity>)

    @Query("SELECT * FROM obatEntities where category='batuk'")
    fun getCatBat(): LiveData<List<ObatEntity>>

    @Query("SELECT * FROM obatEntities where category='demam'")
    fun getCatDem(): LiveData<List<ObatEntity>>

    @Query("SELECT * FROM obatEntities where category='kulit'")
    fun getCatKul(): LiveData<List<ObatEntity>>

    @Query("SELECT * FROM obatEntities where category='diabetes'")
    fun getCatDia(): LiveData<List<ObatEntity>>
}