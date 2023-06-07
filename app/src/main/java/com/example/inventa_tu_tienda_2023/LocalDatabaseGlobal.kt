package com.example.inventa_tu_tienda_2023

import android.app.Application
import androidx.room.Room
import com.example.inventa_tu_tienda_2023.repositories.local.AppDB

class LocalDatabaseGlobal: Application() {

    companion object{
        lateinit var database: AppDB
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AppDB::class.java,
            "my_store_db"
        ).build()
    }
}