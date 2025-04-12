package com.example.saudeocaraapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.saudeocaraapp.ui.theme.ColorOffWhite
import com.example.saudeocaraapp.ui.theme.CorAzulForte

@Composable
fun ButtonComponent(
    onClick: () -> Unit,
    titulo:String,
    largura: Dp? = null,
    altura: Dp? = null
){

    ElevatedButton(
        modifier = Modifier.height( altura ?: 50.dp).width(largura ?: 150.dp),
        shape = RoundedCornerShape(10.dp ),
        colors = ButtonColors(
            containerColor = ColorOffWhite,
            contentColor = ColorOffWhite,
            disabledContentColor = ColorOffWhite,
            disabledContainerColor = ColorOffWhite,
        ),
        onClick = onClick) {
        Text(
            color = CorAzulForte,
            text = titulo,
            )

    }
}