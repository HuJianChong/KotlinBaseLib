package com.hjc.kotlintest.home

import com.hjc.baselibrary.base.BaseTitleActivity
import com.hjc.kotlintest.R

/**
 * @author hjc
 * date 2019/1/7 0007.
 * description：
 */
class DetailActivity : BaseTitleActivity() {

    override fun layoutId(): Int = R.layout.detail_activity

    override fun initData() {
    }

    override fun initView() {
        setTitleText("详情")
    }

    override fun start() {
    }

}