package com.example.inventa_tu_tienda_2023.ui.profile.no_user.sign_up

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventa_tu_tienda_2023.R
import com.example.inventa_tu_tienda_2023.e_StatusResult
import com.example.inventa_tu_tienda_2023.models.Register.SignUpData
import com.example.inventa_tu_tienda_2023.models.User
import com.example.inventa_tu_tienda_2023.repositories.remote.RemoteRepository
import com.example.myanimeapp.models.errors.ErrorData
import com.example.myanimeapp.models.errors.e_Error
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {
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

    private fun areFieldsOk(signUpData: SignUpData): Boolean{
        if(signUpData.email.isEmpty()){
            errorMutableLiveData.value = ErrorData(
                errorEvent = e_Error.Email,
                errorMessageInt = R.string.signUp_emailEmpty_error
            )
        }else if(signUpData.password.isEmpty()){
            errorMutableLiveData.value = ErrorData(
                errorEvent = e_Error.Password,
                errorMessageInt = R.string.signUp_passwordEmpty_error
            )
        }else if(signUpData.repPassword.isEmpty()){
            errorMutableLiveData.value = ErrorData(
                errorEvent = e_Error.RepPassword,
                errorMessageInt = R.string.signUp_repPasswordEmpty_error
            )
        }else if(signUpData.companyName.isEmpty()){
            errorMutableLiveData.value = ErrorData(
                errorEvent = e_Error.CompanyName,
                errorMessageInt = R.string.signUp_companyNameEmpty_error
            )
        }
        else {
            if(!isUserNameValid(signUpData.email)){
                errorMutableLiveData.value = ErrorData(
                    errorEvent = e_Error.GenericInt,
                    errorMessageInt = R.string.signUp_wrongEmailFormat_error
                )
            }
            else if(!isPasswordValid(signUpData.password)){
                errorMutableLiveData.value = ErrorData(
                    errorEvent = e_Error.GenericInt,
                    errorMessageInt = R.string.signUp_passwordShort_error
                )
            }
            else if(signUpData.password != signUpData.repPassword){
                errorMutableLiveData.value = ErrorData(
                    errorEvent = e_Error.GenericInt,
                    errorMessageInt = R.string.signUp_differentPasswords_error
                )
            }
            else{
                return true
            }
        }
        return false
    }
    fun createUser(signUpData: SignUpData) {
        if(areFieldsOk(signUpData)){
            viewModelScope.launch {
                val result = remoteRepository.signUpUser(signUpData)
                if(result.status == e_StatusResult.success){
                    successMutableLiveData.postValue(result.data!!)
                }
                else
                    errorMutableLiveData.postValue(
                        ErrorData(
                            errorEvent = e_Error.GenericStr,
                            errorMessageStr = result.errorMessage
                        ))
            }
        }
    }

}