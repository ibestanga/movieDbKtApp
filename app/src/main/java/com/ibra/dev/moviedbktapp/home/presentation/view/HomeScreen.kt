package com.ibra.dev.moviedbktapp.home.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.ibra.dev.moviedbktapp.commons.presentation.navigations.MovieDetailDestination
import com.ibra.dev.moviedbktapp.commons.presentation.theme.padding_12dp
import com.ibra.dev.moviedbktapp.home.domain.models.MovieDto
import com.ibra.dev.moviedbktapp.home.presentation.viewmodels.HomeViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel = koinViewModel<HomeViewModel>()
    LaunchedEffect(null) {
        viewModel.getPopularMovies()
        viewModel.getFavoritesMovies()
    }
    val movies: LazyPagingItems<MovieDto> =
        viewModel.pagingMoviesStateFlow.collectAsLazyPagingItems()
    val favoritesMovies by viewModel.favoriteMoviesStateFlow.collectAsState()

    HomeViewPager(
        movies = movies,
        favoriteMovies = favoritesMovies,
        onTryAgainClick = { viewModel.getPopularMovies() }
    ) { id ->
        navController.navigate(MovieDetailDestination(id))
    }
}

@Composable
fun HomeViewPager(
    movies: LazyPagingItems<MovieDto>,
    favoriteMovies: List<MovieDto>,
    onTryAgainClick: () -> Unit,
    onDetailNavigate: (Int) -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { 2 })
    val coroutineScope = rememberCoroutineScope()

    val tabs = listOf("Popular Movies", "Favorite Movies")

    Scaffold(
        modifier = Modifier.padding(top = padding_12dp)
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                contentColor = Color.Black,
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                    ) {
                        Text(
                            text = title,
                            modifier = Modifier.padding(padding_12dp),
                            style = MaterialTheme.typography.titleMedium,
                        )
                    }
                }
            }

            HorizontalPager(
                state = pagerState,
            ) { page ->
                when (page) {
                    0 -> MoviesListLayout(
                        movies = movies,
                        onTryAgainClick = onTryAgainClick
                    ) {
                        onDetailNavigate(it)
                    }

                    1 -> FavoriteMoviesListLayout(favoriteMovies) {
                        onDetailNavigate(it)
                    }
                }
            }
        }
    }
}