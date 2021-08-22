package com.example.login.data.repositories

import com.example.login.data.network.RetrofitService


class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllMovies() = retrofitService.getAllMovies()
}