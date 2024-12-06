package com.ibra.dev.moviedbktapp.details.data.database.converter

import androidx.room.TypeConverter

class MovieConverters {
    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return value.joinToString(",")
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        return value.split(",").map { it.trim() }
    }
}