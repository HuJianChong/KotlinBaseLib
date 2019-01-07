package com.hjc.kotlintest.home

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hjc.baselibrary.base.BaseActivity
import com.hjc.baselibrary.utils.StatusBarUtil
import com.hjc.kotlintest.R
import com.hjc.kotlintest.bean.ProjectBean
import com.hjc.kotlintest.common.WebViewActivity
import com.hjc.kotlintest.home.adapter.ProjectAdapter
import com.hjc.kotlintest.home.mvp.contract.ProjectContract
import com.hjc.kotlintest.home.mvp.presenter.ProjectPresenter
import com.hjc.kotlintest.utils.DividerUtils
import com.hjc.kotlintest.utils.MultiStatusViewUtils
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : BaseActivity(), ProjectContract.View {
    private val mPresenter by lazy { ProjectPresenter() }
    private val mAdapter by lazy { ProjectAdapter(R.layout.main_project_item, ArrayList()) }
    private var mIsRefresh = true
    private lateinit var multiStatusViewUtils: MultiStatusViewUtils

    override fun layoutId(): Int = R.layout.main_activity

    override fun initData() {
    }

    override fun initView() {
        mPresenter.attachView(this)
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, statusView)

        mMultiStatusView = statusView
        multiStatusViewUtils = MultiStatusViewUtils(mMultiStatusView!!)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mAdapter
        recyclerView.addItemDecoration(DividerUtils.getDefaultDivider(this))
        mAdapter.setOnItemClickListener { baseQuickAdapter: BaseQuickAdapter<Any, BaseViewHolder>, view: View, i: Int ->
            launchActivityWithString(WebViewActivity::class.java, mAdapter.data[i].url)
        }

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
        multiStatusViewUtils.handleResult(projectBean.data, mIsRefresh, refreshLayout)
        if (mIsRefresh) {
            mAdapter.setNewData(projectBean.data)
        } else if (projectBean.data != null) {
            mAdapter.addData(projectBean.data)
        }
    }

    override fun showError(errorMsg: String, errorCode: Int) {
        multiStatusViewUtils.showError()
    }

    override fun showLoading() {
        multiStatusViewUtils.showLoading()
    }

    override fun dismissLoading() {
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}
