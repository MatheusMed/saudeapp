package com.example.saudeocaraapp.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import com.example.saudeocaraapp.ui.theme.BackgroundColor
import com.example.saudeocaraapp.ui.theme.ColorBranco
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarComponent(
    titulo: String,
    navController: NavHostController,
    isBack: Boolean = false,
    isMenu: Boolean,
    drawerState: DrawerState,
){

    val scope = rememberCoroutineScope()
    CenterAlignedTopAppBar(

        navigationIcon = {
            if (isBack) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Voltar"
                    )
                }
            }else if(isMenu){
                IconButton(onClick = {
                    scope.launch { drawerState.open() }
                }) {
                    Icon(Icons.Default.Menu, contentDescription = "Menu")
                }

            }
        },


        colors = TopAppBarColors(
            containerColor = BackgroundColor,
            actionIconContentColor = ColorBranco,
            titleContentColor = ColorBranco,
            navigationIconContentColor = ColorBranco,
            scrolledContainerColor = ColorBranco,
        ),
        title = {
        Text(text = titulo)
    })

}
