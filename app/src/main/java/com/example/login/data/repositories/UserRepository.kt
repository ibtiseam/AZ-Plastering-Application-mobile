package com.example.login.data.repositories


import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.login.data.network.MyApi
import com.example.login.data.network.responses.UserResponse
import com.example.login.ui.activities.MainActivity
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserRepository {


    fun userLogin(username: String, password: String): LiveData<UserResponse?>{

        val loginResponse = MutableLiveData<UserResponse?>()
        MyApi().userLogin(username, password)
            .enqueue(object  : Callback<UserResponse?>{
                override fun onResponse(
                    call: Call<UserResponse?>,
                    response: Response<UserResponse?>
                ) {
                    if(response.isSuccessful){
                        loginResponse.value=response.body()
                    }else {
                        loginResponse.value=null
                    }

                }

                override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                    loginResponse.value=null

                }

            })
        return loginResponse
    }
}

