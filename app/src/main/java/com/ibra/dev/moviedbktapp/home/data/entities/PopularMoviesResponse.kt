package com.ibra.dev.moviedbktapp.home.data.entities


import com.google.gson.annotations.SerializedName

data class PopularMoviesResponse(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val data: List<MovieEntity>? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null
)