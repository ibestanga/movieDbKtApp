package com.ibra.dev.moviedbktapp.home.domain.models

data class MovieDto(
    val id: Int,
    val name: String,
    val poster: String,
    val overview: String,
    val releaseDate: String
)
