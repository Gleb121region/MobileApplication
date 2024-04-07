package ru.spbstu.mobileapplication.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.spbstu.mobileapplication.domain.enums.ApartmentType
import java.lang.reflect.Type
import java.util.Date


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

    @TypeConverter
    fun stringSetToString(data: Set<String>?): String {
        return gson.toJson(data)
    }

    @TypeConverter
    fun stringToStringSet(data: String?): Set<String>? {
        val listType: Type = object : TypeToken<Set<String>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }
}

