package com.example.saudeocaraapp.views.pacientes

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.saudeocaraapp.R
import com.example.saudeocaraapp.components.ButtonComponent
import com.example.saudeocaraapp.components.ScaffoldCustom
import com.example.saudeocaraapp.database.PacienteDAO

import com.example.saudeocaraapp.service.ApiService
import com.example.saudeocaraapp.ui.theme.BackgroundColor
import com.example.saudeocaraapp.viewmodel.PacienteViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers


import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel


@Composable
fun PacienteLogin(
    navController: NavHostController,
    apiService: ApiService,
    pacienteDAO: PacienteDAO
) {

    var cartaoSus by remember {
        mutableStateOf<String>("")
    }
//
    val coroutineScope = rememberCoroutineScope()


    val context = LocalContext.current

    val pacienteViewModel = koinViewModel<PacienteViewModel>()

    val loading by pacienteViewModel.isLoading.collectAsState()

    ScaffoldCustom(
        "Login Paciente",
        navController,
        isBackButon = true

    ){



        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColor),
//            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(200.dp),
                contentScale = ContentScale.Fit
            )
            
            Text(text = "Saude Ocara", fontSize = 40.sp)






            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                label = {
                    Text(text = "Cartao do sus")
                },
                
                value = cartaoSus, onValueChange = {
                cartaoSus = it
            })
            ButtonComponent(
                isLoading = loading,
                onClick = {
                    pacienteViewModel.login(cartaoSus)
                    navController.navigate("paciente_home")


                }, titulo = "Logar", largura = 300.dp,
                )

        }
    }
}