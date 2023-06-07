package com.example.inventa_tu_tienda_2023.repositories.remote

import com.example.inventa_tu_tienda_2023.RemoteResult
import com.example.inventa_tu_tienda_2023.e_StatusResult
import com.example.inventa_tu_tienda_2023.models.Register.SignUpData
import com.example.inventa_tu_tienda_2023.models.User
import com.example.inventa_tu_tienda_2023.models.UserRemote
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class RemoteRepository {
    private val auth = Firebase.auth
    private val db = Firebase.firestore
    private val collUsers = "users"
    private val subCollStores = "stores"

    suspend fun isThereCurrentUser(): Boolean {
        return auth.currentUser != null
    }
    suspend fun getCurrentUserEmail(): String? {
        return if(isThereCurrentUser())
            auth.currentUser?.email
        else
            null
    }
    suspend fun signUpUser(userData: SignUpData): RemoteResult<User> {
        try {
            val resultAuth = auth.createUserWithEmailAndPassword(userData.email, userData.password).await()
            val user = User(
                email = userData.email,
                companyName = userData.companyName,
                number = userData.number,
                userName = userData.userName
            )
            val resultFireStore = createUserFireStore(user)
            return if (resultFireStore.status == e_StatusResult.success) {
                RemoteResult.Success(user)
            } else {
                RemoteResult.Error(
                    exception = resultFireStore.errorException,
                    errorMessage = resultFireStore.errorMessage
                )
            }
        }catch (exception: Exception) {
            return RemoteResult.Error(
                exception = exception,
                errorMessage = exception.message
            )
        }
    }
    private suspend fun createUserFireStore(userLocal: User) : RemoteResult<Boolean> {
        return try {
            val user = UserRemote(
                userName = userLocal.userName,
                email = userLocal.email,
                companyName = userLocal.companyName,
                number = userLocal.number
            )
            val resultFireStore = db.collection(collUsers).document(user.email!!).set(user).await()
            RemoteResult.Success(true)
        } catch (exception: Exception) {
            RemoteResult.Error(
                exception = exception,
                errorMessage = exception.message
            )
        }
    }

    suspend fun getUserFireStore(email: String) : RemoteResult<User>{
        return try {
            val result = db.collection(collUsers).document(email).get().await()
            if(result.exists()){
                val userRemote = result.toObject<UserRemote>()!!
                val userLocal= User(
                    userName = userRemote.userName!!,
                    email = userRemote.email!!,
                    companyName = userRemote.companyName!!,
                    number = userRemote.number!!
                )
                RemoteResult.Success(userLocal)
            }else{
                val exception = Exception("The Document doesn't exist")
                RemoteResult.Error(
                    exception = exception,
                    errorMessage = exception.message
                )
            }

        }catch (exception: Exception){
            RemoteResult.Error(
                exception = exception,
                errorMessage = exception.message
            )
        }
    }
    suspend fun loginUser(email: String, password: String): RemoteResult<User> {
        return try{
            val resultAuth = auth.signInWithEmailAndPassword(email, password).await()
            val resultFirestore = getUserFireStore(email)
            if(resultFirestore.status == e_StatusResult.success){
                val user = resultFirestore.data!!
                RemoteResult.Success(user)
            }else{
                val exception = resultFirestore.errorException!!
                RemoteResult.Error(
                    exception = exception,
                    errorMessage = exception.message
                )
            }

        }catch(exception: Exception){
            RemoteResult.Error(
                exception = exception,
                errorMessage = exception.message
            )
        }
    }
    suspend fun logout(){
        auth.signOut()
    }

}