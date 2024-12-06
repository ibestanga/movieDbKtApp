package com.ibra.dev.moviedbktapp.commons.presentation.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ibra.dev.moviedbktapp.details.presentation.view.DetailsMovieScreen
import com.ibra.dev.moviedbktapp.home.presentation.view.HomeScreen

@Composable
fun AppNav() {
    val navController = rememberNavController()
    NavHost(
        navController,
        startDestination = HomeScreenDestination) {
        composable<HomeScreenDestination> {
            HomeScreen(navController)
        }

        composable<MovieDetailDestination> { backStackEntry ->
            val movieId: Int = backStackEntry.toRoute<MovieDetailDestination>().movieId
            DetailsMovieScreen(navController, movieId)
        }
    }
}