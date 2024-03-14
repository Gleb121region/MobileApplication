package ru.spbstu.mobileapplication.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.spbstu.mobileapplication.domain.enums.interview.ApartmentType


object Converters {
    private val gson = Gson()

    @TypeConverter
    fun stringToSet(data: String?): Set<ApartmentType> {
        if (data == null) {
            return emptySet()
        }
        val listType = object : TypeToken<Set<ApartmentType?>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun setToString(someObjects: Set<ApartmentType?>?): String {
        return gson.toJson(someObjects)
    }
}

