package com.hjc.kotlintest.database

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*


/**
 * 数据库时间转化器
 */
class Converters {
    companion object {
        @JvmStatic
        @TypeConverter
        fun fromTimestamp(value: Long?): Date? {
            return if (value == null) null else Date(value)
        }

        @JvmStatic
        @TypeConverter
        fun dateToTimestamp(date: Date?): Long? {
            return date?.time
        }

        @JvmStatic
        @TypeConverter
        fun fromString(data: String?): ArrayList<String>? {
            val listType = object : TypeToken<ArrayList<String>>() {
            }.type
            return Gson().fromJson(data, listType)
        }

        @JvmStatic
        @TypeConverter
        fun fromArrayList(list: ArrayList<String>?): String? {
            val gson = Gson()
            return gson.toJson(list)
        }
    }
}