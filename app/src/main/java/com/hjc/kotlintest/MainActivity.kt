package com.hjc.kotlintest

import com.hjc.baselibrary.base.BaseActivity
import com.hjc.baselibrary.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun layoutId(): Int = R.layout.activity_main

    override fun initData() {
    }

    override fun initView() {
        StatusBarUtil.immersive(this)
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPadding(this, window.decorView)

        mLayoutStatusView = statusView
    }

    override fun start() {
    }

}
