package com.example.inventa_tu_tienda_2023.ui.profile.with_user.profile_main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventa_tu_tienda_2023.models.User
import com.example.inventa_tu_tienda_2023.repositories.local.LocalRepository
import com.example.inventa_tu_tienda_2023.repositories.remote.RemoteRepository
import com.example.inventa_tu_tienda_2023.ui.shared.SharedViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ProfileMainViewModel : ViewModel() {
    val userToPrintMutableLiveData: MutableLiveData<String> = MutableLiveData()
    val userToPrintLiveData: LiveData<String> = userToPrintMutableLiveData

    private val remoteRepository = RemoteRepository()
    private val localRepository = LocalRepository()
    fun getUser(sharedModel: SharedViewModel) {
        viewModelScope.launch {
            val currentUser = runBlocking { remoteRepository.getCurrentUserEmail() }
            val userInfo = localRepository.getUserInfo(currentUser!!)
            if(userInfo == null){
                localRepository.saveUserInfo(sharedModel.user)
                printUser(sharedModel.user)
            }
            else {
                printUser(userInfo)
            }
        }
    }

    private fun printUser(userInfo: User) {
        if (userInfo.userName != "") {
            if (userInfo.userName.isNotBlank())
                userToPrintMutableLiveData.value = userInfo.userName
            else
                userToPrintMutableLiveData.value = userInfo.email
        }
    }

    fun logout(sharedModel: SharedViewModel) {
        viewModelScope.launch {
            localRepository.deleteUserInfo(sharedModel.user)
        }
        runBlocking { remoteRepository.logout() }
    }
}