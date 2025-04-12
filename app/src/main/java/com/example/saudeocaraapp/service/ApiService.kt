package com.example.saudeocaraapp.service

import com.example.saudeocaraapp.models.LoginUsuario
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/pacientes/login")
    suspend fun login(@Query("cns") cns:String): LoginUsuario
}