package com.ibra.dev.moviedbktapp.home.data.api

import com.ibra.dev.moviedbktapp.home.data.entities.PopularMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApi {

    companion object {
        const val MOVIE_POPULAR_PATH = "movie/popular"
    }

    @GET(MOVIE_POPULAR_PATH)
    suspend fun getPopularMovie(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int,
    ): Response<PopularMoviesResponse>
}