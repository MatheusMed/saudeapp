package com.example.saudeocaraapp.views.pacientes

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.saudeocaraapp.components.ScaffoldCustom
import com.example.saudeocaraapp.components.TextComponent
import com.example.saudeocaraapp.database.PacienteDAO
import com.example.saudeocaraapp.models.ConsultaModel
import com.example.saudeocaraapp.models.LoginUsuario
import com.example.saudeocaraapp.service.ApiService
import com.example.saudeocaraapp.ui.theme.CorAzulForte
import com.example.saudeocaraapp.ui.theme.PurpleGrey80
import com.example.saudeocaraapp.viewmodel.PacienteViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.viewmodel.factory.KoinViewModelFactory
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ConsultaView(
    navController: NavHostController,
    apiService: ApiService,
    pacienteDAO: PacienteDAO
) {

    val pacienteViewModel = koinViewModel<PacienteViewModel>()

    LaunchedEffect(Unit) {
        pacienteViewModel.trazerConsulta()
    }

    val listaConsultas by pacienteViewModel.stateListaConsultas.collectAsState()
    val loading by pacienteViewModel.isLoading.collectAsState()


    ScaffoldCustom(
        tituloAppBar = "Vagas do posto",
        navController = navController,
                isBackButon = true,
    ) {
        if (loading) {
            Column(
                modifier = Modifier.fillMaxSize().background(CorAzulForte),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(CorAzulForte)
            ) {
                items(listaConsultas) { consulta ->
                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .background(CorAzulForte),
                        colors = CardColors(
                            contentColor = PurpleGrey80,
                            containerColor = PurpleGrey80,
                            disabledContentColor = PurpleGrey80,
                            disabledContainerColor = PurpleGrey80
                        )

                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 20.dp),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Center

                        ) {
                            TextComponent(text = "Medico: ${consulta.medico}")
                            TextComponent(text = "Data: ${consulta.data}")
                            TextComponent(text = "Posto: ${consulta.posto}")
                            TextComponent(text = "Turno: ${consulta.turno}")
                            TextComponent(text = "Total de vagas: ${consulta.vagas}")
                            TextComponent(text = "Especialidade: ${consulta.especialidade}")

                        }
                    }
                }
            }
        }
    }

    }






