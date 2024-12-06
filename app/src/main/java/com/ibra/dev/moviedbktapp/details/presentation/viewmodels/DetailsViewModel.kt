package com.ibra.dev.moviedbktapp.details.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ibra.dev.moviedbktapp.commons.utils.launchWithIO
import com.ibra.dev.moviedbktapp.details.presentation.states.DetailsMovieActions
import com.ibra.dev.moviedbktapp.details.presentation.usecase.GetDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch

class DetailsViewModel(
    private val getDetailsUseCase: GetDetailsUseCase
) : ViewModel() {

    private val _detailsMovieEvents =
        MutableStateFlow<DetailsMovieActions>(DetailsMovieActions.Init)
    val detailsMovieEvents: StateFlow<DetailsMovieActions> = _detailsMovieEvents


    fun getDetailsMovie(movieId: Int) {
        launchWithIO {
            _detailsMovieEvents.value = DetailsMovieActions.Loading
            getDetailsUseCase.invoke(movieId)
                .catch { e ->
                    Log.e("DetailsViewModel", "error: ", e)
                    _detailsMovieEvents.value =
                        DetailsMovieActions.Error(errorMsg = e.message.orEmpty())
                }
                .collect { data ->
                    _detailsMovieEvents.value = DetailsMovieActions.GetDetailsSuccess(data)
                }
        }
    }
}