package com.ibra.dev.moviedbktapp.commons.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ibra.dev.moviedbktapp.commons.presentation.theme.MovieDbKtAppTheme
import com.ibra.dev.moviedbktapp.details.presentation.view.DetailsMovieScreen
import com.ibra.dev.moviedbktapp.home.presentation.view.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieDbKtAppTheme {
                DetailsMovieScreen(1241982)
            }
        }
    }
}