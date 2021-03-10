package com.mdev.countriesapp.data.db

import androidx.room.TypeConverter
import java.util.*
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken




class Converters {
    @TypeConverter
    fun fromString(stringListString: String): List<String> {
        return stringListString.split(",").map { it }
    }

    @TypeConverter
    fun toString(stringList: List<String>): String {
        return stringList.joinToString(separator = ",")
    }
}