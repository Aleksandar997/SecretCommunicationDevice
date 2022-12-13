package com.example.secretcommunicationdevice.loader

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Loader() {
    CircularProgressIndicator(modifier = Modifier.then(Modifier.size(32.dp)), color = Color.White)
}