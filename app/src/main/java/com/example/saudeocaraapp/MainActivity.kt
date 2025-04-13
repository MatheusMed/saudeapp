package com.example.saudeocaraapp

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.saudeocaraapp.database.AppDatabase
import com.example.saudeocaraapp.database.PacienteDAO
import com.example.saudeocaraapp.models.LoginUsuario
import com.example.saudeocaraapp.network.RetrofitInstance
import com.example.saudeocaraapp.service.ApiService
import com.example.saudeocaraapp.ui.theme.BackgroundColor
import com.example.saudeocaraapp.ui.theme.ColorBranco
import com.example.saudeocaraapp.ui.theme.CorAzulForte
import com.example.saudeocaraapp.ui.theme.SaudeocaraappTheme
import com.example.saudeocaraapp.viewmodel.PacienteViewModel
import com.example.saudeocaraapp.views.HomeView
import com.example.saudeocaraapp.views.pacientes.ConsultaView
import com.example.saudeocaraapp.views.pacientes.PacienteHome
import com.example.saudeocaraapp.views.pacientes.PacienteLogin
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.koinViewModel
import org.koin.core.context.GlobalContext.get
import org.koin.java.KoinJavaComponent.inject

class MainActivity : ComponentActivity() {


    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val splashScreen = installSplashScreen()


        setContent {
            SaudeocaraappTheme(
                darkTheme = true
            ) {
                window.statusBarColor = BackgroundColor.toArgb()
                window.navigationBarColor = BackgroundColor.toArgb()


                val insetsController = WindowInsetsControllerCompat(window, window.decorView)
                insetsController.isAppearanceLightStatusBars = false
                insetsController.isAppearanceLightNavigationBars = false

                WindowCompat.setDecorFitsSystemWindows(window, false)
                 val apiService: ApiService by inject()
                 val pacienteDAO: PacienteDAO by inject()
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    NavigationRotasApp(
                        apiService,
                        pacienteDAO
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationRotasApp(
    apiService: ApiService,
    pacienteDAO: PacienteDAO
) {
    val navController = rememberNavController()

    val pacienteViewModel = koinViewModel<PacienteViewModel>()

    LaunchedEffect(Unit){
        pacienteViewModel.trazerPaciente()
    }

    val paciente by pacienteViewModel.paciente.collectAsState()

    val loading by pacienteViewModel.isLoading.collectAsState()

    if (loading) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(CorAzulForte),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,


        ) {
            CircularProgressIndicator(
                color = ColorBranco
            )
        }
    } else {

        if (paciente == null) {
            NavHost(navController = navController, startDestination = "initial") {
                composable("initial") {
                    HomeView(
                        navController
                    )
                }
                composable("paciente_login") {
                    PacienteLogin(
                        navController,
                        apiService,
                        pacienteDAO
                    )
                }
                composable("paciente_home") {
                    PacienteHome(
                        navController,

                    )
                }
            }

        } else {
            NavHost(navController = navController, startDestination = "paciente_home") {
                composable("initial") {
                    HomeView(
                        navController
                    )
                }
                composable("paciente_home") {
                    PacienteHome(
                        navController,

                    )

                }
                composable("vagas_paciente") {
                    ConsultaView(
                        navController,
                        apiService,
                        pacienteDAO
                    )

                }
                composable("paciente_login") {
                    PacienteLogin(
                        navController,
                        apiService,
                        pacienteDAO
                    )
                }


            }

        }
    }


    }



