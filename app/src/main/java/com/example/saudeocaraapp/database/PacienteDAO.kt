package com.example.saudeocaraapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.saudeocaraapp.models.LoginUsuario


@Dao
interface PacienteDAO {

    @Insert
    suspend fun inserir(paciente: LoginUsuario)

    @Query("SELECT * FROM paciente LIMIT 1")
    suspend fun getPaciente(): LoginUsuario?

//    @Delete
//    suspend fun deletar(tarefa: LoginUsuario)
//
    @Query("DELETE FROM paciente WHERE id = :id")
    suspend fun deletarPorId(id: Int)
}