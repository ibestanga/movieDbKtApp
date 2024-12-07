package com.ibra.dev.moviedbktapp.home.presentation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.ibra.dev.moviedbktapp.R
import com.ibra.dev.moviedbktapp.commons.presentation.component.ShowLoading
import com.ibra.dev.moviedbktapp.commons.presentation.component.ShowResultMessage
import com.ibra.dev.moviedbktapp.commons.presentation.theme.padding_16dp
import com.ibra.dev.moviedbktapp.commons.presentation.theme.padding_24dp
import com.ibra.dev.moviedbktapp.commons.presentation.theme.padding_4dp
import com.ibra.dev.moviedbktapp.commons.presentation.theme.padding_8dp
import com.ibra.dev.moviedbktapp.home.domain.models.MovieDto

@Composable
fun MoviesListLayout(
    movies: LazyPagingItems<MovieDto>,
    onTryAgainClick: () -> Unit,
    onDetailNavigate: (Int) -> Unit
) {

    val loadingState: Boolean =
        movies.loadState.refresh is LoadState.Loading && movies.itemCount == 0
    val emptyState: Boolean =
        movies.loadState.refresh is LoadState.NotLoading && movies.itemCount == 0
    val errorState: Boolean = movies.loadState.hasError
    when {
        loadingState -> ShowLoading(Modifier.fillMaxSize())
        emptyState -> ShowResultMessage(
            Modifier.fillMaxSize(),
            "Not found movies"
        ) {}
        errorState -> ShowResultMessage(
            Modifier.fillMaxSize(),
            "something wrong you can try again",
            showButton = true
        ) {
            onTryAgainClick()
        }
        else -> LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(
                count = movies.itemCount
            ) { index: Int ->
                movies[index]?.let { item ->
                    MovieItem(item) {
                        onDetailNavigate(it.id)
                    }
                }
            }
        }
    }
}

@Composable
fun FavoriteMoviesListLayout(
    movies: List<MovieDto>,
    onDetailNavigate: (Int) -> Unit
) {

    when {
        movies.isEmpty() -> {
            ShowResultMessage(Modifier.fillMaxSize(), "you haven't movies favorites yet") { }
        }

        else -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(movies) { item: MovieDto ->
                    MovieItem(item) {
                        onDetailNavigate(it.id)
                    }
                }
            }
        }
    }
}

@Composable
fun MovieItem(
    movie: MovieDto,
    onItemClick: (MovieDto) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(padding_8dp)
            .clickable { onItemClick(movie) },
        elevation = CardDefaults.cardElevation(padding_4dp)
    ) {
        Row(
            modifier = Modifier.padding(padding_16dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500${movie.poster}",
                contentDescription = movie.name,
                placeholder = painterResource(R.drawable.ic_place_holder),
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.4f)
                    .clip(RoundedCornerShape(padding_8dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(padding_16dp))

            Column(
                modifier = Modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = movie.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(padding_8dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.ic_user_rate_average),
                        contentDescription = "Rating",
                        modifier = Modifier.size(padding_24dp)
                    )

                    Spacer(modifier = Modifier.width(padding_4dp))

                    Text(
                        text = "${((movie.voteAverage / 10) * 100).toInt()}%",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Spacer(modifier = Modifier.height(padding_4dp))

                Text(
                    text = "Release: ${movie.releaseDate}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(padding_4dp))

                Text(
                    text = "Release: ${movie.overview}",
                    style = MaterialTheme.typography.bodySmall,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Gray
                )
            }
        }
    }
}