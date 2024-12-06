package com.ibra.dev.moviedbktapp.home.presentation.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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

    LaunchedEffect(null) {
        viewModel.getPopularMovies()
    }
    
    HandlerPageStates(
        movies = movies,
        showLoading = {
            ShowLoading(Modifier.fillMaxSize())
        },
        showErrorState = {
            ShowResultMessage(modifier = Modifier.fillMaxSize(),"Something wrong ...") { }
        },
        showEmptyList = {
            ShowResultMessage(modifier = Modifier.fillMaxSize(),"Not elements found") { }
        }
    ) {
        MoviesListLayout(movies) { id ->
            navController.navigate(MovieDetailDestination(id))
        }
    }
}

@Composable
fun HandlerPageStates(
    movies: LazyPagingItems<MovieDto>,
    showLoading: @Composable () -> Unit,
    showErrorState: @Composable () -> Unit,
    showEmptyList: @Composable () -> Unit,
    showCitiesList: @Composable () -> Unit,
) {
    val loadingState: Boolean =
        movies.loadState.refresh is LoadState.Loading && movies.itemCount == 0
    val emptyState: Boolean =
        movies.loadState.refresh is LoadState.NotLoading && movies.itemCount == 0
    val errorState: Boolean = movies.loadState.hasError
    when {
        loadingState -> showLoading()
        emptyState -> showEmptyList()
        errorState -> showErrorState()
        else -> showCitiesList()
    }
}