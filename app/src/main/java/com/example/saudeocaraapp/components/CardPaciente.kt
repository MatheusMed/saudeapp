package com.example.saudeocaraapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.saudeocaraapp.ui.theme.CorAzulForte
import com.example.saudeocaraapp.ui.theme.PurpleGrey80

@Composable
fun CardPaciente(
    nome:String?,
    atendente:String?,
    sexo:String?,
    sus:String?,
    localidade:String?,
    posto:String?,
) {

    var sexoConvert = when(sexo){
        "M" -> "Masculino"
        "F" -> "Feminino"
        else -> "Feminino"
    }

    Card(
        modifier = Modifier
            .height(250.dp)

            .padding(top = 20.dp)
            .background(CorAzulForte),
        colors = CardColors(
           contentColor = PurpleGrey80,
            containerColor = PurpleGrey80,
            disabledContentColor = PurpleGrey80,
            disabledContainerColor = PurpleGrey80
        )

    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center


        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,

            ) {
                TextComponent(text = "Nome: ")
                if (nome != null) {
                    TextComponent(text = nome)
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,

            ) {
                TextComponent(text = "Agente: ")
                if (atendente != null) {
                    TextComponent(text = atendente)
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,

            ) {
                TextComponent(text = "Genero: ")
                TextComponent(text = sexoConvert)
            }
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,

            ) {
                TextComponent(text = "Cartao SUS: ")
                if (sus != null) {
                    TextComponent(text = sus)
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,

            ) {
                TextComponent(text = "Localidade: ")
                if (localidade != null) {
                    TextComponent(text = localidade)
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,

            ) {
                TextComponent(text = "Posto: ")
                if (posto != null) {
                    TextComponent(text = posto)
                }
            }
        }
    }
}