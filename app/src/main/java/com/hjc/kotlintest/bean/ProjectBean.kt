package com.hjc.kotlintest.bean

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.hjc.kotlintest.database.Converters

/**
 * @author hjc
 * @date 2019/1/3 0003.
 * description：项目Bean
 */
class ProjectBean : BaseResultBean<ArrayList<ProjectBean.Data>>() {

    @Entity(tableName = "project")
    @TypeConverters(Converters::class)
    data class Data(
        @PrimaryKey
        val _id: String,
        val createdAt: String?,
        val desc: String?,
        val images: ArrayList<String>?,
        val publishedAt: String?,
        val source: String?,
        val type: String?,
        val url: String?,
        val used: Boolean?,
        val who: String?
    ) {
        @Ignore
        constructor() : this(
            "", null, null, null, null,
            null, null, null, false, null
        )
        //    "_id": "5965ad43421aa90ca3bb6ae9",
        //    "createdAt": "2017-07-12T13:01:55.875Z",
        //    "desc": "Android 上最便捷的第三方 Keyboard 集合。",
        //    "images": [
        //    "http://img.gank.io/6f9816fd-bada-4912-a133-6a7194d35292"
        //    ],
        //    "publishedAt": "2017-07-12T13:05:59.766Z",
        //    "source": "chrome",
        //    "type": "Android",
        //    "url": "https://github.com/hoanganhtuan95ptit/AwesomeKeyboard",
        //    "used": true,
        //    "who": "代码家"
    }
}