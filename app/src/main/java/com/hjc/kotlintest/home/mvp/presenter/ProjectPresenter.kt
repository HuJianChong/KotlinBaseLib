package com.hjc.kotlintest.home.mvp.presenter

import com.hazz.kotlinmvp.net.exception.ExceptionHandle
import com.hjc.baselibrary.base.BasePresenter
import com.hjc.kotlintest.home.mvp.contract.ProjectContract
import com.hjc.kotlintest.home.mvp.model.ProjectModel

/**
 * Created by hjc
 * desc:项目的 Presenter
 */
class ProjectPresenter : BasePresenter<ProjectContract.View>(), ProjectContract.Presenter {

    private val projectModel: ProjectModel by lazy {
        ProjectModel()
    }

    /**
     * 获取项目
     */
    override fun getProjectList() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = projectModel.getProjectData()
                .subscribe({ projectList ->
                    mRootView?.apply {
                        dismissLoading()
                        showProjectList(projectList)
                    }
                }, { t ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(t),ExceptionHandle.errorCode)
                    }

                })

        addSubscription(disposable)
    }
}