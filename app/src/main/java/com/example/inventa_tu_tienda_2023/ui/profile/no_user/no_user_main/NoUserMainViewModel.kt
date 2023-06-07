package com.example.inventa_tu_tienda_2023.ui.profile.no_user.no_user_main

import androidx.lifecycle.ViewModel
import com.example.inventa_tu_tienda_2023.repositories.remote.RemoteRepository
import kotlinx.coroutines.runBlocking

class NoUserMainViewModel : ViewModel() {
    private val remoteRepository = RemoteRepository()

    fun isThereCurrentUser(): Boolean = runBlocking { remoteRepository.isThereCurrentUser() }
}