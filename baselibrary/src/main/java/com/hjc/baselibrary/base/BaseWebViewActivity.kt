package com.hjc.baselibrary.base

import android.annotation.SuppressLint
import android.os.Build
import android.webkit.WebSettings
import android.webkit.WebView
import com.hjc.baselibrary.R
import com.hjc.baselibrary.view.webview.MyWebChromeClient
import com.hjc.baselibrary.view.webview.MyWebViewClient
import com.hjc.baselibrary.view.webview.WebViewInterface
import com.hjc.baselibrary.view.webview.WebViewProgressBar
import kotlinx.android.synthetic.main.activity_web_view.*

/**
 * @author hjc
 * date 2019/1/7 0007.
 * description：Base WebView Activity
 */
abstract class BaseWebViewActivity : BaseTitleActivity(), WebViewInterface.View {
    protected lateinit var mWebView: WebView
    protected lateinit var mProgressbar: WebViewProgressBar

    override fun layoutId(): Int = R.layout.activity_web_view

    override fun initView() {
        super.initView()
        mWebView = webView
        initWebView(mWebView, this)
        mProgressbar = progressbar
        mProgressbar.startProgress()
    }

    override fun onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack()// 返回前一个页面
        } else {
            super.onBackPressed()
        }
    }

    override fun showLoading(msg: String) {}

    override fun showLoadSuccess(msg: String) {
        mProgressbar.finishProgress()
    }

    override fun showLoadFail(msg: String) {}

    override fun showNoNetwork() {}

    companion object {

        @SuppressLint("SetJavaScriptEnabled", "AddJavascriptInterface")
        fun initWebView(webView: WebView, view: WebViewInterface.View) {
            val ws = webView.settings
            //网页内容的宽度是否可以大于WebView控件的宽度
            ws.loadWithOverviewMode = false
            //保存表单数据
            ws.saveFormData = true
            //是否应该支持使用其屏幕缩放控件和手势缩放
            ws.setSupportZoom(true)
            ws.builtInZoomControls = true
            ws.displayZoomControls = false
            //启动应用缓存
            ws.setAppCacheEnabled(true)
            //设置缓存模式
            ws.cacheMode = WebSettings.LOAD_DEFAULT
            //启用JavaScript执行，默认的是false
            ws.javaScriptEnabled = true
            //页面加载后之后再放开图片
            ws.blockNetworkImage = false
            //使用localStorage则必须打开
            ws.domStorageEnabled = true
            //设置文字的格式
            ws.defaultTextEncodingName = "UTF-8"

            //WebView5.0开始默认不允许混合模式，https中不能加载http资源，需要设置开启
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ws.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            }

            //设置字体默认缩放大小(改变网页字体大小，setTextSize API14被弃用)
            ws.textZoom = 100

            webView.webChromeClient = MyWebChromeClient(view)
            webView.webViewClient = MyWebViewClient(view)
        }
    }

}