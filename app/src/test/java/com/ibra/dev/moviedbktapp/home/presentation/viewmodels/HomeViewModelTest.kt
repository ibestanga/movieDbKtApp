package com.ibra.dev.moviedbktapp.home.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.ibra.dev.moviedbktapp.home.domain.models.MovieDto
import com.ibra.dev.moviedbktapp.home.presentation.usecases.GetFavoritesMoviesUseCase
import com.ibra.dev.moviedbktapp.home.presentation.usecases.GetPopularMoviesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase
    private lateinit var getFavoritesMoviesUseCase: GetFavoritesMoviesUseCase
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        getPopularMoviesUseCase = mockk()
        getFavoritesMoviesUseCase = mockk()
        viewModel = HomeViewModel(getPopularMoviesUseCase, getFavoritesMoviesUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getPopularMovies should update pagingMoviesStateFlow`() = runTest {
        // Arrange
        val mockPagingData = PagingData.empty<MovieDto>()
        coEvery { getPopularMoviesUseCase.invoke() } returns flowOf(mockPagingData)

        // Act
        viewModel.getPopularMovies()

        // Assert
        assertNotNull(viewModel.pagingMoviesStateFlow.value)
    }

    @Test
    fun `getFavoritesMovies should update favoriteMoviesStateFlow`() = runTest {
        val movie = MovieDto(
            id = 1,
            name = "Inception",
            poster = "https://example.com/inception-poster.jpg",
            overview = "A thief who steals corporate secrets through dream-sharing technology is given the task of planting an idea into the mind of a CEO.",
            releaseDate = "2010-07-16",
            voteAverage = 8.8
        )

        val anotherMovie = MovieDto(
            id = 2,
            name = "The Dark Knight",
            poster = "https://example.com/dark-knight-poster.jpg",
            overview = "When the menace known as the Joker emerges, he wreaks havoc on Gotham City, forcing Batman to confront one of his greatest psychological and physical tests.",
            releaseDate = "2008-07-18",
            voteAverage = 9.0
        )


        val mockFavoriteMovies = listOf(
            movie,
            anotherMovie
        )
        coEvery { getFavoritesMoviesUseCase.invoke() } returns flowOf(mockFavoriteMovies)

        viewModel.getFavoritesMovies()

        coEvery {
            getFavoritesMoviesUseCase.invoke()
        }
    }
}
