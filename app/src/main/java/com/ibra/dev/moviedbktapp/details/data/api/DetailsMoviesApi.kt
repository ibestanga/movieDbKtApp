package com.ibra.dev.moviedbktapp.details.data.api

import com.ibra.dev.moviedbktapp.details.data.entities.response.DetailsMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailsMoviesApi {

    companion object {
        const val DETAIL_MOVIE_PATH = "movie/{movieId}"
    }

    @GET(DETAIL_MOVIE_PATH)
    suspend fun getDetailMovie(@Path("movieId") movieId: Int, @Query("language") language: String = "en-US") : Response<DetailsMovieResponse>
}