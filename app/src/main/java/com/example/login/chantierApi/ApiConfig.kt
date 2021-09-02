package com.example.login.chantierApi

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiConfig {
    @Multipart
    @POST("/addCh")
    fun uploadMulFile(
        @Part file: MultipartBody.Part?,
        @Part file2: MultipartBody.Part?,
        @Part file3: MultipartBody.Part?,
        @Part file4: MultipartBody.Part?,
        @Part file5: MultipartBody.Part?,
        @Part("pname") pname: RequestBody?,
        @Part("client") client: RequestBody?,
        @Part("region") region: RequestBody?,
        @Part("type_travaux") type_travaux: RequestBody?,
        @Part("type_projet") type_projet: RequestBody?
    ): Call<ResponseBody?>?
}