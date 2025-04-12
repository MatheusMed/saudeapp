package com.example.saudeocaraapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "paciente")
data class LoginUsuario (
    @PrimaryKey val id: String,
    val genero: String,
    val nome: String,
    val carteira: String,
    val idade: String,
    val posto: String,
    val agente: String,
    val matricula: String,
    val localidade: String,
    val token: String
)




//data class LoginUsuario (
//    @SerializedName("id") val id: String,
//    @SerializedName("genero")  val genero: String,
//    @SerializedName("nome") val nome: String,
//    @SerializedName("carteira") val carteira: String,
//    @SerializedName("idade") val idade: String,
//    @SerializedName("posto") val posto: String,
//    @SerializedName("agente") val agente: String,
//    @SerializedName("matricula") val matricula: String,
//    @SerializedName("localidade") val localidade: String,
//    @SerializedName("token")val token: String
//)