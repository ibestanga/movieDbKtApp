package com.ibra.dev.moviedbktapp.commons.utils

fun Int?.orAlternative(alternative: Int = 0) = this ?: alternative
fun Double?.orAlternative(alternative: Double = 0.0) = this ?: alternative

