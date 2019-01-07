package com.hjc.baselibrary.view.webview

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v4.app.Fragment
import android.webkit.WebView
import android.webkit.WebViewClient
import com.hjc.baselibrary.R
import com.hjc.baselibrary.utils.NetworkUtil
import com.hjc.baselibrary.utils.UIUtils


/**
 * Created by hjc
 * 在WebViewClient中会监听网页连接
 */
class MyWebViewClient(private val mView: WebViewInterface.View) : WebViewClient() {

    private var mActivity: Activity? = null

    init {
        if (mView is Activity) {
            mActivity = mView
        } else if (mView is Fragment) {
            mActivity = (mView as Fragment).activity
        }
    }

    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        //优酷视频跳转到浏览器播放
        if (url.startsWith("http://v.youku.com/")) {
            val intent = Intent()
            intent.action = "android.intent.action.VIEW"
            intent.addCategory("android.intent.category.DEFAULT")
            intent.addCategory("android.intent.category.BROWSABLE")
            val content_url = Uri.parse(url)
            intent.data = content_url
            mActivity!!.startActivity(intent)
            return true

            //电话，短信，邮箱
        } else if (url.startsWith(WebView.SCHEME_TEL) || url.startsWith("sms:") || url.startsWith(WebView.SCHEME_MAILTO)) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            mActivity!!.startActivity(intent)
            return true
        }
        mView.showLoading(UIUtils.getString(R.string.loading))
        view.loadUrl(url)
        return false
    }

    override fun onPageFinished(view: WebView, url: String) {

        if (NetworkUtil.isNetworkAvailable()) {
            mView.showLoadSuccess(UIUtils.getString(R.string.load_success))
        } else {
            mView.showNoNetwork()
        }

        super.onPageFinished(view, url)
    }

    // 解决视频全屏播放按返回页面被放大的问题
    override fun onScaleChanged(view: WebView, oldScale: Float, newScale: Float) {
        super.onScaleChanged(view, oldScale, newScale)
        if (newScale - oldScale > 7) {
            view.setInitialScale((oldScale / newScale * 100).toInt()) //异常放大，缩回去。
        }
    }
}
