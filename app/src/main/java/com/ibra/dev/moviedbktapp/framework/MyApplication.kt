package com.ibra.dev.moviedbktapp.framework

import android.app.Application
import com.ibra.dev.moviedbktapp.framework.di.coreModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(coreModule))
        }
    }
}