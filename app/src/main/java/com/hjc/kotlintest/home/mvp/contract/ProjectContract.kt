package com.hjc.kotlintest.home.mvp.contract

import com.hjc.baselibrary.base.IBaseView
import com.hjc.baselibrary.base.IPresenter
import com.hjc.kotlintest.bean.ProjectBean


/**
 * Created by hjc
 * desc: 项目 契约类
 */
interface ProjectContract {

    interface View : IBaseView {
        /**
         * 显示项目的信息
         */
        fun showProjectList(projectBean: ProjectBean)

        /**
         * 显示错误信息
         */
        fun showError(errorMsg: String, errorCode: Int)
    }

    interface Presenter : IPresenter<View> {
        /**
         * 获取分类的信息
         */
        fun getProjectList()
    }
}