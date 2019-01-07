package com.hjc.kotlintest.common

import com.hjc.baselibrary.app.Const
import com.hjc.baselibrary.base.BaseWebViewActivity

/**
 * @author hjc
 * date 2019/1/7 0007.
 * description：通用webView Activity
 */
class WebViewActivity : BaseWebViewActivity() {
    var mUrl: String? = null

    override fun initData() {
        mUrl = intent.getStringExtra(Const.EXTRA_DATA)
    }

    override fun initView() {
        super.initView()
        setTitleText("详情")
    }

    override fun start() {
        mUrl?.let {
            webView.loadUrl(mUrl)
        }
    }

}