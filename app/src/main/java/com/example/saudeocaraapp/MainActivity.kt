package com.example.saudeocaraapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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
import com.example.saudeocaraapp.views.HomeView
import com.example.saudeocaraapp.views.pacientes.PacienteHome
import com.example.saudeocaraapp.views.pacientes.PacienteLogin

class MainActivity : ComponentActivity() {

    private val apiService: ApiService by lazy { RetrofitInstance.getApiService() }
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val splashScreen = installSplashScreen()
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "paciente"
        ).build()

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
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    NavigationRotasApp(
                        apiService,
                        db.PacienteDAO()
                    )
                }
            }
        }
    }
}

@Composable
fun NavigationRotasApp(apiService: ApiService, pacienteDAO: PacienteDAO) {
    val navController = rememberNavController()

    val pacienteState = produceState<Pair<Boolean, LoginUsuario?>>(initialValue = true to null) {
        val paciente = pacienteDAO.getPaciente()
        value = false to paciente
    }
    val (isLoading, paciente) = pacienteState.value
    if (isLoading) {

        Column(
            modifier = Modifier.fillMaxSize().background(CorAzulForte),
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
                        apiService,
                        pacienteDAO
                    )
                }
            }

        } else {
            NavHost(navController = navController, startDestination = "paciente_home") {
                composable("paciente_home") {
                    PacienteHome(
                        navController,
                        apiService,
                        pacienteDAO
                    )
                }
            }

        }
    }


    }



