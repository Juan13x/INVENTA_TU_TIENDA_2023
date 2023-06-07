package com.example.inventa_tu_tienda_2023.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "number") val number: String = "",
    @ColumnInfo(name = "company_name") val companyName: String,
    @ColumnInfo(name = "user_name") val userName: String  = ""
)