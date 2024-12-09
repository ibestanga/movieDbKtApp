package com.ibra.dev.moviedbktapp.home.di

import com.ibra.dev.moviedbktapp.framework.database.AppDatabase
import com.ibra.dev.moviedbktapp.home.data.api.HomeApi
import com.ibra.dev.moviedbktapp.home.data.repositories.HomeRepositoryImpl
import com.ibra.dev.moviedbktapp.home.domain.repositories.HomeRepository
import com.ibra.dev.moviedbktapp.home.domain.usecase.GetFavoritesMoviesUseCaseImpl
import com.ibra.dev.moviedbktapp.home.domain.usecase.GetPopularMoviesUseCaseImpl
import com.ibra.dev.moviedbktapp.home.domain.usecase.MapMovieEntityToDomainModelUseCaseImpl
import com.ibra.dev.moviedbktapp.home.presentation.usecases.GetFavoritesMoviesUseCase
import com.ibra.dev.moviedbktapp.home.presentation.usecases.GetPopularMoviesUseCase
import com.ibra.dev.moviedbktapp.home.domain.usecase.MapMovieEntityToDomainModelUseCase
import com.ibra.dev.moviedbktapp.home.presentation.viewmodels.HomeViewModel
import org.koin.dsl.module
import retrofit2.Retrofit

private val dataModule = module {
    single {
        providerHomeApi(get())
    }
    single {
        providerHomeDao(get())
    }
    single<HomeRepository> {
        HomeRepositoryImpl(get(), get())
    }
}

private val domainModule = module {
    single<MapMovieEntityToDomainModelUseCase> {
        MapMovieEntityToDomainModelUseCaseImpl()
    }
    single<GetFavoritesMoviesUseCase> {
        GetFavoritesMoviesUseCaseImpl(get(), get())
    }
    single<GetPopularMoviesUseCase> {
        GetPopularMoviesUseCaseImpl(get(), get())
    }
}

private val presentationModule = module {
    factory {
        HomeViewModel(get(), get())
    }
}

val homeModule = module {
    includes(dataModule, domainModule, presentationModule)
}

private fun providerHomeApi(retrofit: Retrofit) = retrofit.create(HomeApi::class.java)

private fun providerHomeDao(database: AppDatabase) = database.homeDao()