package com.ibra.dev.moviedbktapp.home.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ibra.dev.moviedbktapp.commons.utils.orAlternative
import com.ibra.dev.moviedbktapp.home.data.api.HomeApi
import com.ibra.dev.moviedbktapp.home.data.entities.MovieEntity
import retrofit2.HttpException
import java.io.IOException
import kotlin.properties.Delegates

class MoviesPagingSource(
    private val remoteLocalDataSource: HomeApi,
) : PagingSource<Int, MovieEntity>() {
    private var page by Delegates.notNull<Int>()

    override fun getRefreshKey(state: PagingState<Int, MovieEntity>): Int? {
        return state.anchorPosition?.let { position ->
            val closestPage = state.closestPageToPosition(position)
            closestPage?.prevKey?.plus(1) ?: closestPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieEntity> {
        return try {
            page = params.key.orAlternative(1)

            val movies = fetchRemote(page)

            LoadResult.Page(
                data = movies,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (movies.isEmpty()) null else page + 1
            )
        } catch (e: IllegalArgumentException) {
            Log.e(MoviesPagingSource::class.java.simpleName, "load: data movies is null", e)
            LoadResult.Error(e)
        } catch (e: HttpException) {
            Log.e(
                MoviesPagingSource::class.java.simpleName,
                "load: ${e.message()} with code : ${e.code()}",
                e
            )
            LoadResult.Error(e)
        } catch (e: IOException) {
            Log.e(MoviesPagingSource::class.java.simpleName, "load: ", e)
            LoadResult.Error(e)
        } catch (e: Exception) {
            Log.e(MoviesPagingSource::class.java.simpleName, "load: ", e)
            LoadResult.Error(Exception("Unknown Error"))
        }
    }

    private suspend fun fetchRemote(page: Int): List<MovieEntity> =
        remoteLocalDataSource.getPopularMovie(page = page).let { response ->
            if (response.isSuccessful) {
                response.body()?.data ?: throw IllegalArgumentException("Movies Data not Available")
            } else {
                throw HttpException(response)
            }
        }
}