package com.hjc.kotlintest.common

import com.classic.common.MultipleStatusView

/**
 * @author hjc
 * @date 2019/1/2.
 * description：扩展函数
 */
fun MultipleStatusView.isShow(): Boolean {
    return this.viewStatus == MultipleStatusView.STATUS_CONTENT
}