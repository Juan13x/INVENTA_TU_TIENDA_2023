package com.example.inventa_tu_tienda_2023.repositories.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.inventa_tu_tienda_2023.models.User

@Dao
interface UserDAO {
    @Insert
    suspend fun saveUserInfo(user: User)

    @Delete
    suspend fun deleteUserInfo(user: User)

    @Query("SELECT * FROM user WHERE email = :email")
    suspend fun getUserInfo(email: String): User?
}