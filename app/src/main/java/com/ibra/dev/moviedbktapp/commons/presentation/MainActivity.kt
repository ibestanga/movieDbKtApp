package com.ibra.dev.moviedbktapp.commons.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ibra.dev.moviedbktapp.commons.presentation.navigations.AppNav
import com.ibra.dev.moviedbktapp.commons.presentation.theme.MovieDbKtAppTheme
import com.ibra.dev.moviedbktapp.details.presentation.view.DetailsMovieScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieDbKtAppTheme {
                AppNav()
            }
        }
    }
}