package com.ibra.dev.moviedbktapp.commons.utils

inline fun <reified T : Number> T?.orAlternative(alternative: T = 0 as T): T = when {
    this == null -> alternative
    this is Double -> 0.0 as T
    this is Float -> 0f as T
    this is Long -> 0L as T
    this is Int -> 0 as T
    this is Short -> 0.toShort() as T
    this is Byte -> 0.toByte() as T
    else -> alternative
}