package com.example.saudeocaraapp.views.pacientes

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavHostController
import com.example.saudeocaraapp.components.CardPaciente
import com.example.saudeocaraapp.components.ScaffoldCustom
import com.example.saudeocaraapp.database.PacienteDAO
import com.example.saudeocaraapp.models.LoginUsuario
import com.example.saudeocaraapp.service.ApiService
import com.example.saudeocaraapp.ui.theme.BackgroundColor
import com.example.saudeocaraapp.ui.theme.ColorBranco
import com.example.saudeocaraapp.viewmodel.PacienteViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun PacienteHome(navController: NavHostController) {
    val pacienteViewModel = koinViewModel<PacienteViewModel>()

    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                pacienteViewModel.trazerPaciente()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    val paciente by pacienteViewModel.paciente.collectAsState()


    ScaffoldCustom(
        tituloAppBar = "Paciente",
        navController = navController,
        isMenu = true,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColor)
        ) {
            paciente?.let {
                CardPaciente(
                    nome = it.nome,
                    atendente = it.agente,
                    sexo = it.genero,
                    sus = it.carteira,
                    localidade = it.localidade,
                    posto = it.posto
                )
            } ?: CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }



}