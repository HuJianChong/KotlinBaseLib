package com.hjc.kotlintest.net

import com.hjc.kotlintest.mvp.model.bean.ProjectBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by hjc
 * Api 接口
 */
interface ApiService {

    /**
     * 随机数据
     */
    @GET("random/data/Android/{num}")
    fun getProjectList(@Path("num") num: Int): Observable<ProjectBean>

}