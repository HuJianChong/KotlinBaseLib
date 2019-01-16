package com.hjc.kotlintest.home

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hjc.baselibrary.base.BaseTitleActivity
import com.hjc.baselibrary.utils.StatusBarUtil
import com.hjc.kotlintest.R
import com.hjc.kotlintest.bean.ProjectBean
import com.hjc.kotlintest.common.WebViewActivity
import com.hjc.kotlintest.database.AppDatabase
import com.hjc.kotlintest.home.adapter.ProjectAdapter
import com.hjc.kotlintest.home.mvp.contract.HistoryContract
import com.hjc.kotlintest.home.mvp.presenter.HistoryPresenter
import com.hjc.kotlintest.utils.DividerUtils
import com.hjc.kotlintest.utils.MultiStatusViewUtils
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.main_activity.*

class HistoryActivity : BaseTitleActivity(), HistoryContract.View {
    private val mPresenter by lazy { HistoryPresenter() }
    private val mAdapter by lazy { ProjectAdapter(R.layout.main_project_item, ArrayList()) }
    private lateinit var multiStatusViewUtils: MultiStatusViewUtils

    override fun layoutId(): Int = R.layout.history_activity

    override fun initData() {
    }

    override fun initView() {
        mPresenter.attachView(this)
        setTitleText("浏览记录")

        mMultiStatusView = statusView
        multiStatusViewUtils = MultiStatusViewUtils(mMultiStatusView!!)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mAdapter
        recyclerView.addItemDecoration(DividerUtils.getDefaultDivider(this))
        mAdapter.setOnItemClickListener { baseQuickAdapter: BaseQuickAdapter<Any, BaseViewHolder>, view: View, i: Int ->
            launchActivityWithString(WebViewActivity::class.java, mAdapter.data[i].url)
            AppDatabase.getInstance(this).projectDao().insert(mAdapter.data[i])
            Logger.d(AppDatabase.getInstance(this).projectDao().count)
        }

        refreshLayout.setOnRefreshListener {
            start()
        }
        refreshLayout.setOnLoadMoreListener {
            start()
        }
    }

    override fun start() {
        mPresenter.getHistoryList()
    }

    override fun showHistoryList(datas: List<ProjectBean.Data>) {
        multiStatusViewUtils.handleResult(datas, true, refreshLayout)
        mAdapter.setNewData(datas)
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
