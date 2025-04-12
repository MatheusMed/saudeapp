package com.example.saudeocaraapp.network

import com.example.saudeocaraapp.service.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://agentesaudeocara-1.onrender.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getApiService():ApiService{
        return getInstance().create(ApiService::class.java)
    }
}