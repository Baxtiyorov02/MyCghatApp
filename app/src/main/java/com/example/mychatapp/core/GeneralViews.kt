package com.example.mychatapp.core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.mychatapp.ui.theme.MainColor

@Composable
fun HorizontalSpace(
    db: Int = 10,
) {
    Spacer(Modifier.width(db.dp))
}

@Composable
fun VerticalSpace(
    db: Int = 10
) {
    Spacer(Modifier.width(db.dp))

}


@Composable
fun LoadingBox() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        CircularProgressIndicator(color = MainColor)

    }
}

@Composable
fun Int.getColor() = colorResource(this)