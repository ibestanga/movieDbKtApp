package com.ibra.dev.moviedbktapp.home.data.database.converter

import androidx.room.TypeConverter

class GenreIdsConverter {
    @TypeConverter
    fun fromList(list: List<Int>?): String? {
        return list?.joinToString(",")
    }

    @TypeConverter
    fun toList(data: String?): List<Int>? {
        return data?.split(",")?.map { it.toInt() }
    }
}