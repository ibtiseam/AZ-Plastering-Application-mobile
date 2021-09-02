package com.example.login.data.network

import com.example.login.data.network.responses.UserResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MyApi {

    @FormUrlEncoded
    @POST("api/auth/sign")
    fun userLogin(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<UserResponse?>

    companion object{
        operator fun invoke() :MyApi{
            return  Retrofit.Builder()
                .baseUrl("https://az-platre.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}