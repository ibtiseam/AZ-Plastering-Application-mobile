package com.example.login.ui.auth


import android.view.View
import androidx.lifecycle.ViewModel
import com.example.login.data.repositories.UserRepository

class AuthViewModel : ViewModel() {

    var username: String? = null
    var password: String? = null

    var authListener: AuthListener? = null


    fun onLoginButtonClick(view: View){
        authListener?.onStarted()
        if (username.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("username ou mot de passe est invalid")
            return
        }
      val loginResponse = UserRepository().userLogin(username!!, password!!)
            authListener?.onSuccess(loginResponse)


        }



}
