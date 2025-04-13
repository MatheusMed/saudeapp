package com.example.saudeocaraapp.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saudeocaraapp.database.PacienteDAO
import com.example.saudeocaraapp.models.ConsultaModel
import com.example.saudeocaraapp.models.LoginUsuario
import com.example.saudeocaraapp.service.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class PacienteViewModel(
    private val pacienteDAO: PacienteDAO,
    private val apiService: ApiService
): ViewModel() {

    private val _stateListaConsultas = MutableStateFlow<List<ConsultaModel>>(emptyList())
    val stateListaConsultas = _stateListaConsultas.asStateFlow()

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading = _isLoading.asStateFlow()

    private val _paciente = MutableStateFlow<LoginUsuario?>(null)
    val paciente = _paciente.asStateFlow()



    fun trazerPaciente(){
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val pacienteSalvo = pacienteDAO.getPaciente();
                _paciente.value = pacienteSalvo
                _isLoading.value= false
            }catch (_: Exception){
                _isLoading.value= false
            }finally {
                _isLoading.value= false
            }

        }

    }



    @RequiresApi(Build.VERSION_CODES.O)
    fun trazerConsulta(){
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val paciente = pacienteDAO.getPaciente()
                val dataFormatada = LocalDate.now().format(DateTimeFormatter.ISO_DATE)
                if (paciente == null) {
                    Log.w("API", "Paciente não encontrado no banco local")
                    return@launch
                }
                val response = apiService.consultaVagas(
                    data = dataFormatada,
                    posto = paciente.posto ?: "",
                    turno = ""
                )
                Log.d("API", "Consultas feitas com sucesso: $response")
                _stateListaConsultas.value = response

                _isLoading.value = false

            } catch (e: Exception) {
                Log.e("API", "Erro ao fazer consultas", e)
                _isLoading.value = false
            } finally {
                _isLoading.value = false
            }
        }
    }


    fun login(cns:String){
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = apiService.login(cns)
                Log.d("API", "Login sucesso: $response")
                pacienteDAO.inserir(response)
                _isLoading.value = false

            } catch (e: Exception) {
                Log.e("API", "Erro no login", e)
            }finally {
                _isLoading.value = false
            }
        }
    }

    fun deletarPaciente() {
        viewModelScope.launch {
            pacienteDAO.deletarDadosBanco()
        }
    }


}