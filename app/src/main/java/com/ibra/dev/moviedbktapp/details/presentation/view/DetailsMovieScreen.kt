package com.ibra.dev.moviedbktapp.details.presentation.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.ibra.dev.moviedbktapp.commons.presentation.component.ShowLoading
import com.ibra.dev.moviedbktapp.commons.presentation.component.ShowResultMessage
import com.ibra.dev.moviedbktapp.details.domain.models.DetailsMovieDto
import com.ibra.dev.moviedbktapp.details.presentation.states.DetailsMovieActions
import com.ibra.dev.moviedbktapp.details.presentation.viewmodels.DetailsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsMovieScreen(movieId: Int) {
    val viewModel = koinViewModel<DetailsViewModel>()
    val states by viewModel.detailsMovieEvents.collectAsState()
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
        DetailsMovieView(data)
    }
}

@Composable
fun HandlerUiStates(
    states: DetailsMovieActions,
    showLoading: @Composable () -> Unit,
    showErrorState: @Composable (String) -> Unit,
    showMovieDetail: @Composable (DetailsMovieDto) -> Unit,
) {
    when (states) {
        DetailsMovieActions.Loading -> showLoading()
        is DetailsMovieActions.Error -> showErrorState(states.errorMsg)
        is DetailsMovieActions.GetDetailsSuccess -> showMovieDetail(states.data)
        else -> Unit
    }
}