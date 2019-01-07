package com.hjc.kotlintest.net

import com.hjc.baselibrary.net.BaseRetrofitManager
import com.hjc.kotlintest.app.Const

/**
 * @author hjc
 * @date 2019/1/2 0002.
 * description：Retrofit 网络请求 Manager
 */
object RetrofitManager : BaseRetrofitManager() {
    val service: ApiService by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        getRetrofit().create(ApiService::class.java)
    }

    override fun getBaseUrl(): String = Const.BASE_URL
}