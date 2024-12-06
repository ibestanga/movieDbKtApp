package com.ibra.dev.moviedbktapp.details.domain.models

data class DetailsMovieDto(
    val title: String,
    val releaseDate: String,
    val genres: List<String>,
    val voteAverage: Double,
    val tagLine: String,
    val overview: String,
    val popularity: Double,
    val language: List<String>,
    val backdropPoster: String,
    val poster: String
)
