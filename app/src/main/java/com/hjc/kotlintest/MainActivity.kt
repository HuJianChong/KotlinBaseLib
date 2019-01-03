package com.hjc.kotlintest

import com.hjc.baselibrary.base.BaseActivity
import com.hjc.baselibrary.utils.StatusBarUtil
import com.hjc.kotlintest.mvp.contract.ProjectContract
import com.hjc.kotlintest.mvp.model.bean.ProjectBean
import com.hjc.kotlintest.mvp.presenter.ProjectPresenter
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), ProjectContract.View {
    private val mPresenter by lazy { ProjectPresenter() }

    override fun layoutId(): Int = R.layout.activity_main

    override fun initData() {
    }

    override fun initView() {
        mPresenter.attachView(this)
        StatusBarUtil.darkMode(this)

        mLayoutStatusView = statusView
    }

    override fun start() {
        mPresenter.getProjectList()
    }

    override fun showProjectList(projectBean: ProjectBean) {
        Logger.d(projectBean)
    }

    override fun showError(errorMsg: String, errorCode: Int) {
        Logger.d(errorMsg)
        mLayoutStatusView?.showError()
    }

    override fun showLoading() {
        mLayoutStatusView?.showLoading()
    }

    override fun dismissLoading() {
        mLayoutStatusView?.showContent()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}
