package com.hjc.kotlintest.home

import android.support.v7.widget.LinearLayoutManager
import com.hjc.baselibrary.base.BaseActivity
import com.hjc.baselibrary.utils.StatusBarUtil
import com.hjc.kotlintest.R
import com.hjc.kotlintest.bean.ProjectBean
import com.hjc.kotlintest.common.isShow
import com.hjc.kotlintest.home.adapter.ProjectAdapter
import com.hjc.kotlintest.home.mvp.contract.ProjectContract
import com.hjc.kotlintest.home.mvp.presenter.ProjectPresenter
import com.hjc.kotlintest.utils.DividerUtils
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : BaseActivity(), ProjectContract.View {
    private val mPresenter by lazy { ProjectPresenter() }
    private val mAdapter by lazy { ProjectAdapter(R.layout.main_project_item, ArrayList()) }
    private var mIsRefresh = true

    override fun layoutId(): Int = R.layout.main_activity

    override fun initData() {
    }

    override fun initView() {
        mPresenter.attachView(this)
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, statusView)

        mLayoutStatusView = statusView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mAdapter
        recyclerView.addItemDecoration(DividerUtils.getDefaultDivider(this))

        refreshLayout.setOnRefreshListener {
            mIsRefresh = true
            start()
        }
        refreshLayout.setOnLoadMoreListener {
            mIsRefresh = false
            start()
        }
    }

    override fun start() {
        mPresenter.getProjectList()
    }

    override fun showProjectList(projectBean: ProjectBean) {
        if (projectBean.data == null) {
            return
        }
        if (mIsRefresh) {
            mAdapter.setNewData(projectBean.data)
        } else {
            mAdapter.addData(projectBean.data)
        }
    }

    override fun showError(errorMsg: String, errorCode: Int) {
        Logger.d(errorMsg)
        mLayoutStatusView?.showError()
    }

    override fun showLoading() {
        mLayoutStatusView?.apply {
            if (!isShow()) {
                showLoading()
            }
        }
    }

    override fun dismissLoading() {
        mLayoutStatusView?.apply {
            if (!isShow()) {
                showContent()
            }
        }

        if (mIsRefresh) {
            refreshLayout.finishRefresh()
        } else {
            refreshLayout.finishLoadMore()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}
