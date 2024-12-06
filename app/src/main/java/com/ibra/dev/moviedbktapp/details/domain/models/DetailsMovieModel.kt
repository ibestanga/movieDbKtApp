package com.ibra.dev.moviedbktapp.details.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites_movies")
data class DetailsMovieModel(
    @PrimaryKey
    val id: Int,
    val title: String,
    val releaseDate: String,
    val genres: List<String>,
    val voteAverage: Double,
    val tagLine: String,
    val overview: String,
    val popularity: Double,
    val language: List<String>,
    val backdropPoster: String,
    val poster: String,
    val productionCompanyNames: List<String>
)
