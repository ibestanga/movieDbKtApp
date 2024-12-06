package com.ibra.dev.moviedbktapp.commons.presentation.navigations

import kotlinx.serialization.Serializable

@Serializable
object HomeScreenDestination

@Serializable
data class MovieDetailDestination(val movieId: Int)
