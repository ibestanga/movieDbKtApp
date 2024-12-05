package com.ibra.dev.moviedbktapp.framework.remote

import android.content.Context

import com.ibra.dev.moviedbktapp.R
import com.ibra.dev.moviedbktapp.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

private const val TIME_OUT = 15L

val apiKey = BuildConfig.API_KEY

val networkModule = module {
    single {
        providerInterceptor()
    }
    single {
        providerOkHttpClient(get(),get())
    }
    single {
        provideRetrofit(get(), get())
    }
}

fun provideRetrofit(okHttpClient: OkHttpClient, context: Context): Retrofit {
    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(context.getString(R.string.base_url))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun providerInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    return interceptor
}

fun providerOkHttpClient(
    httpLoggingInterceptor: HttpLoggingInterceptor,
    context: Context
): OkHttpClient {
    val cacheSize = 10 * 1024 * 1024L // 10 MB
    val cacheDirectory = File(context.cacheDir, "http_cache")

    return OkHttpClient.Builder()
        .cache(Cache(cacheDirectory, cacheSize))
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $apiKey")
                .build()
            chain.proceed(request)
        }
        .addInterceptor(httpLoggingInterceptor).build()
}