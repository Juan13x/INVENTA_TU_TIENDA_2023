package com.example.inventa_tu_tienda_2023.repositories.local

import com.example.inventa_tu_tienda_2023.LocalDatabaseGlobal
import com.example.inventa_tu_tienda_2023.models.User

class LocalRepository {

    suspend fun saveUserInfo(user: User){
        val userDB = LocalDatabaseGlobal.database.UserDao()
        userDB.saveUserInfo(user)
    }

    suspend fun deleteUserInfo(user: User){
        val userDB = LocalDatabaseGlobal.database.UserDao()
        userDB.deleteUserInfo(user)
    }
    suspend fun getUserInfo(email: String): User? {
        val userDB = LocalDatabaseGlobal.database.UserDao()
        return userDB.getUserInfo(email)
    }
}