package com.hjc.baselibrary.view.webview

/**
 * @author hjc
 * description：View的基本接口，可以定义View中共有的方法
 */
interface BaseView {

    fun showLoading(msg: String)

    fun showLoadSuccess(msg: String)

    fun showLoadFail(msg: String)
}
