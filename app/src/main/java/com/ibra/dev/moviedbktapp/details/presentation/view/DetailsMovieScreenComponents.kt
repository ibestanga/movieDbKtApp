package com.ibra.dev.moviedbktapp.details.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.ibra.dev.moviedbktapp.R
import com.ibra.dev.moviedbktapp.commons.presentation.component.MyTopBar
import com.ibra.dev.moviedbktapp.commons.presentation.theme.padding_12dp
import com.ibra.dev.moviedbktapp.commons.presentation.theme.padding_4dp
import com.ibra.dev.moviedbktapp.commons.presentation.theme.padding_8dp
import com.ibra.dev.moviedbktapp.details.domain.models.DetailsMovieModel

@Composable
fun DetailsMovieView(
    movie: DetailsMovieModel,
    isFavoriteMovie: Boolean,
    onBackClick: () -> Unit,
    onSaveFavoriteMovie: (DetailsMovieModel) -> Unit
) {
    Scaffold(
        topBar = {
            MyTopBar(
                title = "Detalle",
                needBackNavigation = true,
                actionIcon = if (isFavoriteMovie) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                onBackPressClick = onBackClick,
                actionClick = {
                    onSaveFavoriteMovie(movie)
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            AsyncImage(
                model = movie.poster,
                contentDescription = movie.poster,
                placeholder = painterResource(R.drawable.ic_place_holder),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.35f)
                    .padding(start = padding_4dp, end = padding_4dp)
                    .clip(RoundedCornerShape(padding_8dp)),
                contentScale = ContentScale.FillBounds
            )

            Text(
                movie.title,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(padding_12dp))
            Text(
                "${movie.releaseDate} - ${movie.genres.joinToString(", ")}",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(Modifier.height(padding_12dp))
            Text(
                movie.tagLine,
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(Modifier.height(padding_12dp))
            Text(
                "Overview",
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(Modifier.height(padding_4dp))
            Text(
                movie.overview,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(showBackground = true, heightDp = 500, widthDp = 300)
@Composable
private fun DetailsMovieScreenPreview() {
    val movie = DetailsMovieModel(
        id = 1,
        title = "movie test",
        releaseDate = "dd/mm/YYYY",
        genres = listOf("Adventure", "Comedian"),
        voteAverage = 7.838,
        tagLine = "Tag line test",
        overview = """Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.""",
        popularity = 34.33,
        language = listOf("en", "es"),
        backdropPoster = "https://image.tmdb.org/t/p/w800/4OgaftFNqtE1UvfDDb2Eov7A5Rz.jpg",
        poster = "https://image.tmdb.org/t/p/w500/4OgaftFNqtE1UvfDDb2Eov7A5Rz.jpg",
        productionCompanyNames = listOf("warner")
    )
    DetailsMovieView(movie = movie, isFavoriteMovie = false ,onBackClick = {}, onSaveFavoriteMovie = {})
}