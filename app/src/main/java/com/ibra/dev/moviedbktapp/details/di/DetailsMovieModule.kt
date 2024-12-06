package com.ibra.dev.moviedbktapp.details.di

import com.ibra.dev.moviedbktapp.details.data.api.DetailsMoviesApi
import com.ibra.dev.moviedbktapp.details.data.repositories.DetailsMovieRepositoryImpl
import com.ibra.dev.moviedbktapp.details.domain.repositories.DetailsMovieRepository
import com.ibra.dev.moviedbktapp.details.domain.usecase.GetDetailsUseCaseImpl
import com.ibra.dev.moviedbktapp.details.domain.usecase.MapDetailsEntityToDtoImpl
import com.ibra.dev.moviedbktapp.details.presentation.usecase.GetDetailsUseCase
import com.ibra.dev.moviedbktapp.details.presentation.usecase.MapDetailsEntityToDto
import com.ibra.dev.moviedbktapp.details.presentation.viewmodels.DetailsViewModel
import org.koin.dsl.module
import retrofit2.Retrofit

private fun providerDetailsMovieApi(retrofit: Retrofit) =
    retrofit.create(DetailsMoviesApi::class.java)

private val dataModule = module {
    single {
        providerDetailsMovieApi(get())
    }

    single<DetailsMovieRepository> {
        DetailsMovieRepositoryImpl(get())
    }
}

private val domainModule = module {
    single<MapDetailsEntityToDto> {
        MapDetailsEntityToDtoImpl()
    }

    single<GetDetailsUseCase> {
        GetDetailsUseCaseImpl(get(), get())
    }
}

private val presentationModule = module {
    factory {
        DetailsViewModel(get())
    }
}

val detailsModule = module {
    includes(
        dataModule,
        domainModule,
        presentationModule
    )
}