package com.ibra.dev.moviedbktapp.home.presentation.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.ibra.dev.moviedbktapp.home.presentation.viewmodels.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen() {
    val viewModel = koinViewModel<HomeViewModel>()

    val movies = viewModel.pagingMoviesStateFlow.collectAsLazyPagingItems()

    LaunchedEffect(null) {
        viewModel.getPopularMovies()
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(
            count = movies.itemCount
        ) { index: Int ->
            movies[index]?.let { item ->
                Text(item.name)
            }
        }
    }
}