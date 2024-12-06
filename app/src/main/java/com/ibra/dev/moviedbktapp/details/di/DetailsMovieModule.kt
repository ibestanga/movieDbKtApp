package com.ibra.dev.moviedbktapp.details.di

import com.ibra.dev.moviedbktapp.details.data.api.DetailsMoviesApi
import com.ibra.dev.moviedbktapp.details.data.repositories.DetailsMovieRepositoryImpl
import com.ibra.dev.moviedbktapp.details.domain.repositories.DetailsMovieRepository
import com.ibra.dev.moviedbktapp.details.domain.usecase.DeleteMovieFromFavoritesImpl
import com.ibra.dev.moviedbktapp.details.domain.usecase.GetDetailsUseCaseImpl
import com.ibra.dev.moviedbktapp.details.domain.usecase.IsFavoriteMovieUseCaseImpl
import com.ibra.dev.moviedbktapp.details.domain.usecase.MapDetailsEntityToDtoImpl
import com.ibra.dev.moviedbktapp.details.domain.usecase.SaveMovieLikeFavoriteUseCaseImpl
import com.ibra.dev.moviedbktapp.details.presentation.usecase.DeleteMovieFromFavorites
import com.ibra.dev.moviedbktapp.details.presentation.usecase.GetDetailsUseCase
import com.ibra.dev.moviedbktapp.details.presentation.usecase.IsFavoriteMovieUseCase
import com.ibra.dev.moviedbktapp.details.presentation.usecase.MapDetailsEntityToDto
import com.ibra.dev.moviedbktapp.details.presentation.usecase.SaveMovieLikeFavoriteUseCase
import com.ibra.dev.moviedbktapp.details.presentation.viewmodels.DetailsViewModel
import com.ibra.dev.moviedbktapp.framework.database.AppDatabase
import org.koin.dsl.module
import retrofit2.Retrofit

private fun providerDetailsMovieApi(retrofit: Retrofit) =
    retrofit.create(DetailsMoviesApi::class.java)

private fun providerDetailMovieDao(database: AppDatabase) = database.detailsDao()

private val dataModule = module {
    single {
        providerDetailMovieDao(get())
    }

    single {
        providerDetailsMovieApi(get())
    }

    single<DetailsMovieRepository> {
        DetailsMovieRepositoryImpl(get(), get())
    }
}

private val domainModule = module {
    single<MapDetailsEntityToDto> {
        MapDetailsEntityToDtoImpl()
    }

    single<IsFavoriteMovieUseCase> {
        IsFavoriteMovieUseCaseImpl(get())
    }

    single<GetDetailsUseCase> {
        GetDetailsUseCaseImpl(get(), get())
    }

    single<SaveMovieLikeFavoriteUseCase> {
        SaveMovieLikeFavoriteUseCaseImpl(get())
    }

    single<DeleteMovieFromFavorites> {
        DeleteMovieFromFavoritesImpl(get())
    }
}

private val presentationModule = module {
    factory {
        DetailsViewModel(get(), get(), get(), get())
    }
}

val detailsModule = module {
    includes(
        dataModule,
        domainModule,
        presentationModule
    )
}