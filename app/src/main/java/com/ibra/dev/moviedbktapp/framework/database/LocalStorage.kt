package com.ibra.dev.moviedbktapp.framework.database

import android.content.Context
import androidx.room.Room
import org.koin.dsl.module

private val databaseModule = module {
    single {
        getDatabase(get())
    }
}

val localStorageModule = module {
    includes(databaseModule)
}

fun getDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "AppDatabase"
    ).fallbackToDestructiveMigration().build()
}