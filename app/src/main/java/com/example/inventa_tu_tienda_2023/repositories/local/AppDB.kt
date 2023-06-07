package com.example.inventa_tu_tienda_2023.repositories.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.inventa_tu_tienda_2023.models.User

@Database(entities = [User::class], version = 1)
abstract class AppDB: RoomDatabase() {

    abstract fun UserDao(): UserDAO
}