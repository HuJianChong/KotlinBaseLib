package com.hjc.baselibrary.net

/**
 * Created by hjc on 2019/1/2.
 * 封装返回的数据
 */
class BaseResponse<T>(val code :Int,
                      val msg:String,
                      val data:T)