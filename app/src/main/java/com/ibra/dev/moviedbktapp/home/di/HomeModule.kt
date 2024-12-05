package com.ibra.dev.moviedbktapp.home.di

import com.ibra.dev.moviedbktapp.framework.database.AppDatabase
import com.ibra.dev.moviedbktapp.home.data.api.HomeApi
import com.ibra.dev.moviedbktapp.home.data.repositories.HomeRepositoryImpl
import com.ibra.dev.moviedbktapp.home.domain.repositories.HomeRepository
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {
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

private fun providerHomeApi(retrofit: Retrofit) = retrofit.create(HomeApi::class.java)

private fun providerHomeDao(database: AppDatabase) = database.homeDao()