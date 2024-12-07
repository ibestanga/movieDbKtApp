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

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading


    fun getPopularMovies() {
        _isLoading.value = true
        launchWithIO {
            getPopularMovies.invoke()
                .cachedIn(viewModelScope)
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.Lazily,
                    initialValue = PagingData.empty()
                ).collect {
                    _isLoading.value = false
                    _pagingMoviesStateFlow.value = it
                }
        }
    }
}