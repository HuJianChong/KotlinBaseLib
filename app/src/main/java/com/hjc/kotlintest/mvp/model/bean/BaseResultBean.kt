package com.hjc.kotlintest.mvp.model.bean

/**
 * @author hjc
 * date 2019/1/3 0003.
 * description：Base 网络请求结果Bean
 */
open class BaseResultBean<T> {
    val code: Int = 0
    val error: Boolean = false
    val results: T? = null

    override fun toString(): String {
        return "BaseResultBean(code=$code, error=$error, results=$results)"
    }
}