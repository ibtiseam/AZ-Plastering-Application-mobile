package com.example.login.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.login.R
import com.example.login.sharedpreference.PreferenceHelper
import com.example.login.ui.activities.LoginActivity
import com.example.login.ui.activities.MainActivity

class Splash_Screen : AppCompatActivity() {
    private val SPLASH_TIME: Long =3000

    override fun onCreate(savedInstanceState: Bundle?) {

       val user = PreferenceHelper.getUserConnected(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            if (user != null){
                startActivity(Intent(this, MainActivity::class.java))
                }else{
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }, SPLASH_TIME)
    }
}