package com.ibra.dev.moviedbktapp.framework.di

import com.ibra.dev.moviedbktapp.framework.database.localStorageModule
import com.ibra.dev.moviedbktapp.framework.remote.networkModule
import org.koin.dsl.module

val coreModule = module {
    includes(
        networkModule,
        localStorageModule,
    )
}