package com.dicoding.capstone.source.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "obatEntities")
data class ObatEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "nama")
    var nama: String,

    @ColumnInfo(name = "harga")
    var harga: String,

    @ColumnInfo(name = "deskripsi")
    var deskripsi: String,

    @ColumnInfo(name = "indikasi")
    var indikasi: String,

    @ColumnInfo(name = "komposisi")
    var komposisi: String,

    @ColumnInfo(name = "dosis")
    var dosis: String,

    @ColumnInfo(name = "aturan")
    var aturan: String,

    @ColumnInfo(name = "efek")
    var efek: String,

    @ColumnInfo(name = "foto")
    var foto: String,

    @ColumnInfo(name = "category")
    var category: String,
)