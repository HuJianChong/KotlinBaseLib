package com.hjc.kotlintest.home.mvp.presenter

import com.hazz.kotlinmvp.net.exception.ExceptionHandle
import com.hjc.baselibrary.base.BasePresenter
import com.hjc.kotlintest.home.mvp.contract.HistoryContract
import com.hjc.kotlintest.home.mvp.contract.ProjectContract
import com.hjc.kotlintest.home.mvp.model.HistoryModel
import com.hjc.kotlintest.home.mvp.model.ProjectModel

/**
 * Created by hjc
 * desc:history Presenter
 */
class HistoryPresenter : BasePresenter<HistoryContract.View>(), HistoryContract.Presenter {

    private val historyModel: HistoryModel by lazy {
        HistoryModel()
    }

    /**
     * 获取项目
     */
    override fun getHistoryList() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = historyModel.getHistoryData()
                .subscribe({ historyList ->
                    mRootView?.apply {
                        dismissLoading()
                        showHistoryList(historyList)
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