package com.example.mychatapp.core

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.mychatapp.R

@Composable
fun TextView(
    value: String,
    fonWeight: FontWeight = FontWeight.Normal,
    fonSize: Int=18,
    color: Color= R.color.black.getColor(),
    modifier: Modifier = Modifier
){
    Text(
        text = value,
        fontWeight = fonWeight,
        fontSize = fonSize.sp,
        color = color,
        modifier = modifier
    )

}