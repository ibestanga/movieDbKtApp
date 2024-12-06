package com.ibra.dev.moviedbktapp.commons.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun ShowLoading(modifier: Modifier) {
    Box(
        modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ShowResultMessage(
    modifier: Modifier,
    msg: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = msg)
        Button(
            onClick = onRetry
        ) {
            Text("Retry")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShowResultMessagePreview() {
    ShowResultMessage(Modifier.fillMaxSize(),"ocurrio un error") { }
}