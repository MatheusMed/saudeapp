package com.example.saudeocaraapp.views

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.saudeocaraapp.ui.theme.BackgroundColor


@Composable
fun HomeView(navController: NavHostController) {
    val context = LocalContext.current
    ScaffoldCustom(
        tituloAppBar = "Saude Ocara",
        navController
    )
    {
        Column(
//            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            modifier = Modifier
                .fillMaxSize()
                .background(
                    BackgroundColor
                )

        ) {

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(200.dp),
                contentScale = ContentScale.Fit
            )


           Row(
               verticalAlignment = Alignment.CenterVertically,
               horizontalArrangement = Arrangement.SpaceEvenly,
               modifier = Modifier.fillMaxWidth()

           ) {
               ButtonComponent(
                   onClick = {
//                       Toast.makeText(context,"em desenvolvimento..",Toast.LENGTH_SHORT).show()
                       navController.navigate("paciente_login")
                   },
                   "Paciente"
               )
               ButtonComponent(
                   onClick = {
                       Toast.makeText(context,"em desenvolvimento..",Toast.LENGTH_SHORT).show()
                   },
                   "Atendente"
               )

           }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth().padding(vertical = 14.dp)

            ) {
                ButtonComponent(
                    onClick = {
                        Toast.makeText(context,"em desenvolvimento..",Toast.LENGTH_SHORT).show()
                    },
                    "Medicos"
                )
                ButtonComponent(
                    onClick = {
                        Toast.makeText(context,"em desenvolvimento..",Toast.LENGTH_SHORT).show()
                    },
                    "Novidades"
                )

            }
        }
    }

}