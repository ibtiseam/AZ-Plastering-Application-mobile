package com.example.login.ui.auth

import androidx.lifecycle.LiveData
import com.example.login.data.network.responses.UserResponse

interface AuthListener {

    fun onStarted()
    fun onFailure(message: String)
    fun onSuccess(loginResponse: LiveData<UserResponse?>)
}