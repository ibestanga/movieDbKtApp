package com.ibra.dev.moviedbktapp.details.presentation.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.ibra.dev.moviedbktapp.commons.presentation.component.ShowLoading
import com.ibra.dev.moviedbktapp.commons.presentation.component.ShowResultMessage
import com.ibra.dev.moviedbktapp.details.domain.models.DetailsMovieModel
import com.ibra.dev.moviedbktapp.details.presentation.states.DetailsMovieActions
import com.ibra.dev.moviedbktapp.details.presentation.viewmodels.DetailsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsMovieScreen(navController: NavHostController, movieId: Int) {

    val viewModel = koinViewModel<DetailsViewModel>()
    val states by viewModel.detailsMovieEvents.collectAsState()
    val isFavoriteMovie by viewModel.isFavoriteMovieStateFlow.collectAsState()

    LaunchedEffect(movieId) {
        viewModel.getDetailsMovie(movieId)
    }

    HandlerUiStates(
        states = states,
        showLoading = {
            ShowLoading(Modifier.fillMaxSize())
        },
        showErrorState = { msg ->
            ShowResultMessage(Modifier.fillMaxSize(), msg = msg) {
                viewModel.getDetailsMovie(movieId)
            }
        },
    ) { data ->
        DetailsMovieView(
            movie = data,
            isFavoriteMovie = isFavoriteMovie,
            onBackClick = {
                navController.popBackStack()
            },
            onSaveFavoriteMovie = { data: DetailsMovieModel ->
                if (isFavoriteMovie) {
                    viewModel.deleteMovieLikeFavorite(data)
                } else {
                    viewModel.saveMovieLikeFavorite(data)
                }
            }
        )
    }
}

@Composable
fun HandlerUiStates(
    states: DetailsMovieActions,
    showLoading: @Composable () -> Unit,
    showErrorState: @Composable (String) -> Unit,
    showMovieDetail: @Composable (DetailsMovieModel) -> Unit,
) {
    when (states) {
        DetailsMovieActions.Loading -> showLoading()
        is DetailsMovieActions.Error -> showErrorState(states.errorMsg)
        is DetailsMovieActions.GetDetailsSuccess -> showMovieDetail(states.data)
        else -> Unit
    }
}