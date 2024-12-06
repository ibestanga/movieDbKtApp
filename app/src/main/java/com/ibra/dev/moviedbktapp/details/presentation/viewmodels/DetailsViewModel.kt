package com.ibra.dev.moviedbktapp.details.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibra.dev.moviedbktapp.commons.utils.launchWithIO
import com.ibra.dev.moviedbktapp.details.domain.models.DetailsMovieModel
import com.ibra.dev.moviedbktapp.details.presentation.states.DetailsMovieActions
import com.ibra.dev.moviedbktapp.details.presentation.usecase.DeleteMovieFromFavorites
import com.ibra.dev.moviedbktapp.details.presentation.usecase.GetDetailsUseCase
import com.ibra.dev.moviedbktapp.details.presentation.usecase.IsFavoriteMovieUseCase
import com.ibra.dev.moviedbktapp.details.presentation.usecase.SaveMovieLikeFavoriteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion

class DetailsViewModel(
    private val getDetailsUseCase: GetDetailsUseCase,
    private val isFavoriteMovieUseCase: IsFavoriteMovieUseCase,
    private val saveMovieLikeFavoriteUseCase: SaveMovieLikeFavoriteUseCase,
    private val deleteMovieFromFavoriteUseCase: DeleteMovieFromFavorites
) : ViewModel() {

    private val _detailsMovieEvents =
        MutableStateFlow<DetailsMovieActions>(DetailsMovieActions.Init)
    val detailsMovieEvents: StateFlow<DetailsMovieActions> = _detailsMovieEvents

    private val _isFavoriteMovieStateFlow = MutableStateFlow(false)
    val isFavoriteMovieStateFlow: StateFlow<Boolean> = _isFavoriteMovieStateFlow

    fun getDetailsMovie(movieId: Int) {
        launchWithIO {
            _detailsMovieEvents.value = DetailsMovieActions.Loading
            getDetailsUseCase.invoke(movieId)
                .catch { e ->
                    Log.e("DetailsViewModel", "error: ", e)
                    _detailsMovieEvents.value =
                        DetailsMovieActions.Error(errorMsg = e.message.orEmpty())
                }.onCompletion {
                    _isFavoriteMovieStateFlow.value = isFavoriteMovieUseCase.invoke(movieId)
                }.collect { data ->
                    _detailsMovieEvents.value = DetailsMovieActions.GetDetailsSuccess(data)
                }
        }
    }

    fun saveMovieLikeFavorite(movie: DetailsMovieModel) {
        launchWithIO {
            val result = viewModelScope.async(Dispatchers.IO) {
                saveMovieLikeFavoriteUseCase.invoke(movie)
            }
            result.await()
            _isFavoriteMovieStateFlow.value = isFavoriteMovieUseCase.invoke(movie.id)
        }
    }

    fun deleteMovieLikeFavorite(movie: DetailsMovieModel) {
        launchWithIO {
            val result = viewModelScope.async(Dispatchers.IO) {
                deleteMovieFromFavoriteUseCase.invoke(movie.id)
            }
            result.await()
            _isFavoriteMovieStateFlow.value = isFavoriteMovieUseCase.invoke(movie.id)
        }
    }
}