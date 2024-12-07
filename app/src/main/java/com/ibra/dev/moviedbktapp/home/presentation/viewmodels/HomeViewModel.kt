package com.ibra.dev.moviedbktapp.home.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ibra.dev.moviedbktapp.commons.utils.launchWithIO
import com.ibra.dev.moviedbktapp.home.domain.models.MovieDto
import com.ibra.dev.moviedbktapp.home.presentation.usecases.GetFavoritesMovies
import com.ibra.dev.moviedbktapp.home.presentation.usecases.GetPopularMovies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getPopularMovies: GetPopularMovies,
    private val getFavoritesMovies: GetFavoritesMovies
) : ViewModel() {

    private val _pagingMoviesStateFlow = MutableStateFlow(PagingData.empty<MovieDto>())
    val pagingMoviesStateFlow: StateFlow<PagingData<MovieDto>> = _pagingMoviesStateFlow

    private val _favoriteMoviesStateFlow = MutableStateFlow<List<MovieDto>>(emptyList())
    val favoriteMoviesStateFlow: StateFlow<List<MovieDto>> = _favoriteMoviesStateFlow

    fun getPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
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

    fun getFavoritesMovies() {
        launchWithIO {
            getFavoritesMovies.invoke().collect { data ->
                _favoriteMoviesStateFlow.value = data
            }
        }
    }
}