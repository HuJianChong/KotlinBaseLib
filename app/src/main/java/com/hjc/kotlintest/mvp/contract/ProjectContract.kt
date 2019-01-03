package com.hjc.kotlintest.mvp.contract

import com.hjc.baselibrary.base.IBaseView
import com.hjc.baselibrary.base.IPresenter
import com.hjc.kotlintest.mvp.model.bean.ProjectBean


/**
 * Created by hjc
 * desc: 项目 契约类
 */
interface ProjectContract {

    interface View : IBaseView {
        /**
         * 显示项目的信息
         */
        fun showProjectList(projectList: ArrayList<ProjectBean>)

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