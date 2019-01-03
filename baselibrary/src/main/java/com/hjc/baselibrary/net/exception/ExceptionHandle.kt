package com.hazz.kotlinmvp.net.exception

import com.google.gson.JsonParseException
import com.hjc.baselibrary.net.exception.ApiException
import com.hjc.baselibrary.net.exception.ErrorStatus
import com.orhanobut.logger.Logger

import org.json.JSONException
import java.lang.IllegalStateException

import java.net.ConnectException

import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

/**
 * Created by hjc on 2019/1/2.
 * desc: 异常处理类
 */
class ExceptionHandle {

    companion object {
        var errorCode = ErrorStatus.UNKNOWN_ERROR
        var errorMsg = "请求失败，请稍后重试"

        fun handleException(e: Throwable): String {
            e.printStackTrace()
            if (e is SocketTimeoutException) {//网络超时
                Logger.e("网络连接异常: " + e.message)
                errorMsg = "网络连接异常"
                errorCode = ErrorStatus.NETWORK_ERROR
            } else if (e is ConnectException) { //均视为网络错误
                Logger.e("网络连接异常: " + e.message)
                errorMsg = "网络连接异常"
                errorCode = ErrorStatus.NETWORK_ERROR
            } else if (e is JsonParseException
                || e is JSONException
                || e is ParseException
                || e is IllegalStateException
            ) {   //均视为解析错误
                Logger.e("数据解析异常: " + e.message)
                errorMsg = "数据解析异常"
                errorCode = ErrorStatus.SERVER_ERROR
            } else if (e is ApiException) {//服务器返回的错误信息
                errorMsg = e.message.toString()
                errorCode = ErrorStatus.SERVER_ERROR
            } else if (e is UnknownHostException) {
                Logger.e("网络连接异常: " + e.message)
                errorMsg = "网络连接异常"
                errorCode = ErrorStatus.NETWORK_ERROR
            } else if (e is IllegalArgumentException) {
                errorMsg = "参数错误"
                errorCode = ErrorStatus.SERVER_ERROR
            } else {//未知错误
                try {
                    Logger.e(e, "错误")
                } catch (e1: Exception) {
                    Logger.e("未知错误Debug调试 ")
                }

                errorMsg = "未知错误，可能抛锚了吧~"
                errorCode = ErrorStatus.UNKNOWN_ERROR
            }
            return errorMsg
        }

    }


}
