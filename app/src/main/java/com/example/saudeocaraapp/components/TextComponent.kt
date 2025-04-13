package com.example.saudeocaraapp.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.saudeocaraapp.ui.theme.CorAzulForte

@Composable
fun TextComponent(
    text:String,
    corText: Color? = null
) {
    Text(
        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
        text = text, fontSize = 20.sp, color = corText ?: CorAzulForte)
}