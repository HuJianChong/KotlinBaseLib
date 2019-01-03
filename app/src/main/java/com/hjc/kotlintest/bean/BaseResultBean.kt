package com.hjc.kotlintest.bean

import com.google.gson.annotations.SerializedName

/**
 * @author hjc
 * date 2019/1/3 0003.
 * description：Base 网络请求结果Bean
 */
open class BaseResultBean<T> {
    val code: Int = 0
    val error: Boolean = false
    @SerializedName("results")
    val data: T? = null

    override fun toString(): String {
        return "BaseResultBean(code=$code, error=$error, data=$data)"
    }
}