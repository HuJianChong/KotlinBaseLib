package com.hjc.kotlintest.utils

import com.classic.common.MultipleStatusView
import com.scwang.smartrefresh.layout.SmartRefreshLayout

/**
 * @author hjc
 * date 2019/1/4 0004.
 * description：multiStatusView 工具类
 */
class MultiStatusViewUtils(private val multipleStatusView: MultipleStatusView) {

    var isShownContent = false

    fun <T> handleResult(data: ArrayList<T>?, isRefresh: Boolean, refreshLayout: SmartRefreshLayout) {
        if (isRefresh) {
            refreshLayout.finishRefresh()
        } else {
            refreshLayout.finishLoadMore()
        }

        if (data == null || data.isEmpty()) {   //服务器返回数据为空
            if (isRefresh) {  //刷新数据为空
                multipleStatusView.showEmpty()
                isShownContent = false
            } else {  //加载数据为空
                refreshLayout.finishLoadMoreWithNoMoreData()
            }
        } else if (!isShownContent) {    //数据不为空，且不是内容状态
            multipleStatusView.showContent()
            isShownContent = true
        }
    }

    fun showLoading() {
        if (!isShownContent) {
            multipleStatusView.showLoading()
        }
    }

    fun showError() {
        if (!isShownContent) {
            multipleStatusView.showError()
        }
    }
}