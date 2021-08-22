package com.example.login.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.login.R
import com.example.login.data.network.responses.UserResponse
import com.example.login.databinding.ActivityLoginBinding
import com.example.login.sharedpreference.PreferenceHelper
import com.example.login.ui.auth.AuthListener
import com.example.login.ui.auth.AuthViewModel
import com.example.login.util.hide
import com.example.login.util.show
import com.example.login.util.toast
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(), AuthListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityLoginBinding =DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)



        binding.viewmodel= viewModel

        viewModel.authListener =this

    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(loginResponse: LiveData<UserResponse?>) {
       loginResponse.observe(this, Observer {
           it?.let { user ->
               PreferenceHelper.setUserConnected(this,user)
           }
           progress_bar.hide()
           val intent = Intent(this@LoginActivity, MainActivity::class.java)
           startActivity(intent)
       })
    }

    override fun onFailure(message: String) {
        toast(message)
    }
}