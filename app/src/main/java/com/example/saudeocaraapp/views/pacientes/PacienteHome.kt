package com.example.saudeocaraapp.views.pacientes

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.saudeocaraapp.components.CardPaciente
import com.example.saudeocaraapp.components.ScaffoldCustom
import com.example.saudeocaraapp.database.PacienteDAO
import com.example.saudeocaraapp.models.LoginUsuario
import com.example.saudeocaraapp.service.ApiService
import com.example.saudeocaraapp.ui.theme.BackgroundColor
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun PacienteHome(navController: NavHostController, apiService: ApiService, pacienteDAO: PacienteDAO) {

    ScaffoldCustom(
        tituloAppBar = "Paciente",
        navController =navController,
        isMenu = true
    ) {
        Column(
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(
                    BackgroundColor
                )
        ) {
            var paciente by remember {
                mutableStateOf<LoginUsuario?>(null)
            }



            LaunchedEffect(Unit){
                paciente = pacienteDAO.getPaciente()
            }
            CardPaciente(
                nome = paciente?.nome,
                atendente = paciente?.agente,
                sexo = paciente?.genero, sus = paciente?.carteira,
                localidade = paciente?.localidade,
                posto =paciente?.posto
            )
        }
    }


}