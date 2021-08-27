package com.example.recycleview.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.login.data.repositories.ChantierRepository
import com.example.recycleview.Chantier
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChantierViewModel constructor(private val repository: ChantierRepository)  : ViewModel() {

    val chantierList = MutableLiveData<List<Chantier>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllChantier() {

        val response = repository.getAllChantier()
        response.enqueue(object : Callback<List<Chantier>> {
            override fun onResponse(call: Call<List<Chantier>>, response: Response<List<Chantier>>) {
                chantierList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Chantier>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}