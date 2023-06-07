package com.example.inventa_tu_tienda_2023.ui.profile.no_user.log_in

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventa_tu_tienda_2023.R
import com.example.inventa_tu_tienda_2023.e_StatusResult
import com.example.inventa_tu_tienda_2023.models.User
import com.example.inventa_tu_tienda_2023.repositories.remote.RemoteRepository
import com.example.myanimeapp.models.errors.ErrorData
import com.example.myanimeapp.models.errors.e_Error
import kotlinx.coroutines.launch

class LogInViewModel : ViewModel() {
    private val errorMutableLiveData: MutableLiveData<ErrorData> = MutableLiveData()
    val errorLiveData: LiveData<ErrorData> = errorMutableLiveData

    private val successMutableLiveData: MutableLiveData<User> = MutableLiveData()
    val successLiveData: LiveData<User> = successMutableLiveData

    private val remoteRepository = RemoteRepository()
    private fun isUserNameValid(userEmail: String): Boolean {
        val containEmail = userEmail.contains('@')
        return if (!containEmail)
            false
        else
            Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    private fun areFieldsOk(email: String, password: String): Boolean{
        if(email.isEmpty()){
            errorMutableLiveData.value = ErrorData(
                errorEvent = e_Error.Email,
                errorMessageInt = R.string.login_emailEmpty_error
            )
        }
        else if(password.isEmpty()){
            errorMutableLiveData.value = ErrorData(
                errorEvent = e_Error.Password,
                errorMessageInt = R.string.login_passwordEmpty_error
            )
        }
        else {
            if (!isUserNameValid(email)) {
                errorMutableLiveData.value = ErrorData(
                    errorEvent = e_Error.GenericInt,
                    errorMessageInt = R.string.login_wrongEmailFormat_error
                )
            } else if (!isPasswordValid(password)) {
                errorMutableLiveData.value = ErrorData(
                    errorEvent = e_Error.GenericInt,
                    errorMessageInt = R.string.login_passwordShort_error
                )
            } else
                return true
        }
        return false
    }

    fun login(email: String, password: String){
        if(areFieldsOk(email, password)){
            viewModelScope.launch {
                val result = remoteRepository.loginUser(email, password)
                if(result.status == e_StatusResult.success){
                    //TODO: Save User Info with persistent assistant
                    successMutableLiveData.postValue(result.data!!)
                }
                else{
                    errorMutableLiveData.postValue(
                        ErrorData(
                            errorEvent = e_Error.GenericStr,
                            errorMessageStr = result.errorMessage
                        )
                    )
                }
            }
        }
    }
}