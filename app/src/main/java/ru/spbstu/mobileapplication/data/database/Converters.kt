package ru.spbstu.mobileapplication.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.spbstu.mobileapplication.domain.enums.ApartmentType


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
}

