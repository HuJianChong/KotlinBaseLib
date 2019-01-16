package com.hjc.kotlintest.home.mvp.contract

import com.hjc.baselibrary.base.IBaseView
import com.hjc.baselibrary.base.IPresenter
import com.hjc.kotlintest.bean.ProjectBean


/**
 * Created by hjc
 * desc: 历史记录 契约类
 */
interface HistoryContract {

    interface View : IBaseView {
        /**
         * 显示历史记录列表
         */
        fun showHistoryList(datas: List<ProjectBean.Data>)

        /**
         * 显示错误信息
         */
        fun showError(errorMsg: String, errorCode: Int)
    }

    interface Presenter : IPresenter<View> {
        /**
         * 获取分类的信息
         */
        fun getHistoryList()
    }
}