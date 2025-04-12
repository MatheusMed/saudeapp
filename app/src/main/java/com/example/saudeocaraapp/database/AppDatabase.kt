package com.example.saudeocaraapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.saudeocaraapp.models.LoginUsuario


@Database(entities = [LoginUsuario::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun PacienteDAO(): PacienteDAO
}