package com.example.saudeocaraapp.models

data class ConsultaModel(
    val crm: String,
    val data: String,
    val especialidade: String,
    val id: String,
    val medico: String,
    val posto: String,
    val turno: String,
    val vagas: Int
)