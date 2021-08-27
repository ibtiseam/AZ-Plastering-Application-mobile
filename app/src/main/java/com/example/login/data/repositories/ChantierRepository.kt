package com.example.login.data.repositories

import com.example.login.data.network.RetrofitService


class ChantierRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllChantier() = retrofitService.getAllChantier()
}