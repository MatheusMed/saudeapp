package com.example.saudeocaraapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.saudeocaraapp.R
import com.example.saudeocaraapp.ui.theme.CorAzulForte
import com.example.saudeocaraapp.ui.theme.PurpleGrey80
import kotlinx.coroutines.launch


@Composable
fun ScaffoldCustom(
    tituloAppBar: String,
    navController: NavHostController,
    isBackButon: Boolean = false,
    isMenu: Boolean = false,
    content: @Composable () -> Unit
) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                onNavigate = { route ->
                    navController.navigate(route)
                    scope.launch { drawerState.close() }
                }
            )
        }
    ) {


        Scaffold(

            topBar = {
                TopBarComponent(
                    tituloAppBar,
                    navController,
                    isBackButon,
                    isMenu,
                    drawerState
                )
            },
            content = { paddingValues ->

                Column(
                    modifier = Modifier.padding(paddingValues)
                ) {
                    content()
                }
            }
        )
    }


}

@Composable
fun DrawerContent(onNavigate: (String) -> Unit) {



        Column(
          verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(CorAzulForte)
                .fillMaxHeight()
                .width(250.dp)
                .padding(top = 90.dp, bottom = 90.dp)

        ) {
            
            TextComponent(text = "Prefeitura Ocara Ceara", corText = PurpleGrey80)
            
            Spacer(modifier = Modifier.height(10.dp))

            Card(
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                colors = CardColors(
                    contentColor = PurpleGrey80,
                    containerColor = PurpleGrey80,
                    disabledContentColor = PurpleGrey80,
                    disabledContainerColor = PurpleGrey80
                )

            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextComponent(text = "Vagas")


                }
            }
            Card(
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                colors = CardColors(
                    contentColor = PurpleGrey80,
                    containerColor = PurpleGrey80,
                    disabledContentColor = PurpleGrey80,
                    disabledContainerColor = PurpleGrey80
                )

            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextComponent(text = "Prontuario")


                }
            }
            Spacer(modifier = Modifier.weight(1f)) 
            Card(
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                colors = CardColors(
                    contentColor = PurpleGrey80,
                    containerColor = PurpleGrey80,
                    disabledContentColor = PurpleGrey80,
                    disabledContainerColor = PurpleGrey80
                )

            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextComponent(text = "Sair")


                }
            }




        }





}