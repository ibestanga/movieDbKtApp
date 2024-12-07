package com.ibra.dev.moviedbktapp.home.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ibra.dev.moviedbktapp.details.domain.models.DetailsMovieModel
import com.ibra.dev.moviedbktapp.home.data.api.HomeApi
import com.ibra.dev.moviedbktapp.home.data.database.dao.HomeDao
import com.ibra.dev.moviedbktapp.home.data.entities.MovieEntity
import com.ibra.dev.moviedbktapp.home.data.paging.MoviesPagingSource
import com.ibra.dev.moviedbktapp.home.domain.repositories.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepositoryImpl(
    private val remoteDataSource: HomeApi,
    private val localDataSource: HomeDao
) : HomeRepository {

    companion object {
        const val MAX_ITEMS = 10
    }

    override suspend fun getPopularMovies(): Flow<PagingData<MovieEntity>> = Pager(
        config = PagingConfig(
            pageSize = MAX_ITEMS,
            enablePlaceholders = false,
        ),
        pagingSourceFactory = {
            MoviesPagingSource(
                remoteLocalDataSource = remoteDataSource,
            )
        }
    ).flow

    override suspend fun getFavoriteMovies(): Flow<List<DetailsMovieModel>> {
        return flow {
            emit(localDataSource.getFavoritesMovies())
        }
    }
}