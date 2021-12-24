package com.dicoding.capstone.usersigin.data

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "user")
@Parcelize
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @NonNull
    var id: Int = 0,

    @ColumnInfo(name = "name")
    @NonNull
    var name: String,

    @ColumnInfo(name = "no_telephone")
    @NonNull
    var no_telephone: String,

    @ColumnInfo(name = "address")
    @NonNull
    var address: String,

    @ColumnInfo(name = "email")
    @NonNull
    var email: String,

    @ColumnInfo(name = "password")
    @NonNull
    var password: String
):Parcelable