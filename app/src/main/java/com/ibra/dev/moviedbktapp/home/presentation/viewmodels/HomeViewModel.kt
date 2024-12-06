package com.ibra.dev.moviedbktapp.home.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ibra.dev.moviedbktapp.commons.utils.launchWithIO
import com.ibra.dev.moviedbktapp.home.domain.models.MovieDto
import com.ibra.dev.moviedbktapp.home.presentation.usecases.GetPopularMovies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    private val getPopularMovies: GetPopularMovies
) : ViewModel() {

    private val _pagingMoviesStateFlow = MutableStateFlow(PagingData.empty<MovieDto>())
    val pagingMoviesStateFlow: StateFlow<PagingData<MovieDto>> = _pagingMoviesStateFlow

    fun getPopularMovies() {
        launchWithIO {
            getPopularMovies.invoke()
                .cachedIn(viewModelScope)
                .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = PagingData.empty()
            ).collect {
                _pagingMoviesStateFlow.value = it
            }
        }
    }
}