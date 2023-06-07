package com.example.inventa_tu_tienda_2023.models

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class UserRemote(
    val email: String ?= null,
    val number: String ?= null,
    val companyName: String ?= null,
    val userName: String  ?= null
)
