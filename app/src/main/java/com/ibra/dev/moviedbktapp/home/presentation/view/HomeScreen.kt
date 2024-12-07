package com.ibra.dev.moviedbktapp.home.presentation.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.ibra.dev.moviedbktapp.commons.presentation.component.ShowLoading
import com.ibra.dev.moviedbktapp.commons.presentation.component.ShowResultMessage
import com.ibra.dev.moviedbktapp.commons.presentation.navigations.MovieDetailDestination
import com.ibra.dev.moviedbktapp.home.domain.models.MovieDto
import com.ibra.dev.moviedbktapp.home.presentation.viewmodels.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel = koinViewModel<HomeViewModel>()

    val movies: LazyPagingItems<MovieDto> =
        viewModel.pagingMoviesStateFlow.collectAsLazyPagingItems()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(null) {
        viewModel.getPopularMovies()
    }

    HandlerPageStates(
        movies = movies,
        isLoading = isLoading,
        showLoading = {
            ShowLoading(Modifier.fillMaxSize())
        },
        showErrorState = {
            ShowResultMessage(modifier = Modifier.fillMaxSize(), "Something wrong ...") { }
        },
    ) {
        MoviesListLayout(movies) { id ->
            navController.navigate(MovieDetailDestination(id))
        }
    }
}

@Composable
fun HandlerPageStates(
    movies: LazyPagingItems<MovieDto>,
    isLoading: Boolean,
    showLoading: @Composable () -> Unit,
    showErrorState: @Composable () -> Unit,
    showCitiesList: @Composable () -> Unit,
) {
    val errorState: Boolean = movies.loadState.hasError
    when {
        isLoading -> showLoading()
        errorState -> showErrorState()
        else -> showCitiesList()
    }
}