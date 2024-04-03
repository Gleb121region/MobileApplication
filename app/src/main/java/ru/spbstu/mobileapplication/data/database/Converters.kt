package ru.spbstu.mobileapplication.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.spbstu.mobileapplication.domain.enums.ApartmentType
import java.lang.reflect.Type


object Converters {
    private val gson = Gson()

    @TypeConverter
    fun stringToList(data: String?): List<ApartmentType> {
        if (data == null) {
            return emptyList()
        }
        val listType = object : TypeToken<List<ApartmentType?>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: List<ApartmentType?>?): String {
        return gson.toJson(someObjects)
    }

    @TypeConverter
    fun fromString(value: String): List<String>? {
        val listType: Type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<String>?): String {
        return gson.toJson(list)
    }
}

