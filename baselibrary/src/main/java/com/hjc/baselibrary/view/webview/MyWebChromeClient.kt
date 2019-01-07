package com.hjc.baselibrary.view.webview

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.support.v4.app.Fragment
import android.view.View
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView

import android.app.Activity.RESULT_OK

/**
 * @author hjc
 * date 2019/1/7.
 * description：
 */
class MyWebChromeClient(private val mView: WebViewInterface.View) : WebChromeClient() {
    private var mUploadMsg: ValueCallback<Uri>? = null
    private var mUploadMsgAboveAndroid5: ValueCallback<Array<Uri>>? = null
    private var mActivity: Activity? = null
    private val mProgress: View? = null
    private var mCustomView: View? = null
    private var mCustomViewCallback: WebChromeClient.CustomViewCallback? = null

    init {
        if (mView is Activity) {
            mActivity = mView
        } else if (mView is Fragment) {
            mActivity = (mView as Fragment).activity
        }
    }

    // 播放网络视频时全屏会被调用的方法
    override fun onShowCustomView(view: View, callback: WebChromeClient.CustomViewCallback) {
        mActivity!!.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        // 如果一个视图已经存在，那么立刻终止并新建一个
        if (mCustomView != null) {
            callback.onCustomViewHidden()
            return
        }

        mCustomView = view
        mCustomViewCallback = callback
    }

    // 视频播放退出全屏会被调用的
    override fun onHideCustomView() {
        if (mCustomView == null)
        // 不是全屏播放状态
            return
        mActivity!!.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        mCustomView!!.visibility = View.GONE
        mCustomView = null
        mCustomViewCallback!!.onCustomViewHidden()
    }

    // 视频加载时进程loading
    //    @SuppressLint("InflateParams")
    //    @Override
    //    public View getVideoLoadingProgressView() {
    //        if (mProgress == null) {
    //            LayoutInflater inflater = LayoutInflater.from(mActivity);
    //            mProgress = inflater.inflate(R.layout.view_loading, null);
    //        }
    //        return mProgress;
    //    }

    /**
     * 判断是否是全屏
     */
    fun inFullScreen(): Boolean {
        return mCustomView != null
    }

    override fun onReceivedTitle(view: WebView, title: String) {
        super.onReceivedTitle(view, title)
        // 设置title
        mActivity!!.title = title
    }

    //扩展浏览器上传文件
    //3.0++版本
    fun openFileChooser(uploadMsg: ValueCallback<Uri>, acceptType: String) {
        openFileChoose(uploadMsg)
    }

    //3.0--版本
    fun openFileChooser(uploadMsg: ValueCallback<Uri>) {
        openFileChoose(uploadMsg)
    }

    fun openFileChooser(uploadMsg: ValueCallback<Uri>, acceptType: String, capture: String) {
        openFileChoose(uploadMsg)
    }

    // For Android > 5.0
    override fun onShowFileChooser(
        webView: WebView,
        uploadMsg: ValueCallback<Array<Uri>>,
        fileChooserParams: WebChromeClient.FileChooserParams
    ): Boolean {
        openFileChooseForAndroid5(uploadMsg)
        return true
    }

    private fun openFileChoose(uploadMsg: ValueCallback<Uri>) {
        mUploadMsg = uploadMsg
        val i = Intent(Intent.ACTION_GET_CONTENT)
        i.addCategory(Intent.CATEGORY_OPENABLE)
        i.type = "image/*"
        mActivity!!.startActivityForResult(Intent.createChooser(i, "文件选择"), CODE_FILE_CHOOSE)
    }

    private fun openFileChooseForAndroid5(uploadMsg: ValueCallback<Array<Uri>>) {
        mUploadMsgAboveAndroid5 = uploadMsg
        val contentSelectionIntent = Intent(Intent.ACTION_GET_CONTENT)
        contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE)
        contentSelectionIntent.type = "image/*"

        val chooserIntent = Intent(Intent.ACTION_CHOOSER)
        chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent)
        chooserIntent.putExtra(Intent.EXTRA_TITLE, "图片选择")

        mActivity!!.startActivityForResult(chooserIntent, CODE_FILE_CHOOSE_5)
    }

    /**
     * 5.0以下 上传图片成功后的回调
     */
    fun uploadMsg(intent: Intent?, resultCode: Int) {
        if (null == mUploadMsg)
            return
        val result = if (intent == null || resultCode != RESULT_OK) null else intent.data
        mUploadMsg!!.onReceiveValue(result)
        mUploadMsg = null
    }

    /**
     * 5.0以上 上传图片成功后的回调
     */
    fun uploadMsgAboveAndroid5(intent: Intent?, resultCode: Int) {
        if (null == mUploadMsgAboveAndroid5)
            return
        val result = if (intent == null || resultCode != RESULT_OK) null else intent.data
        if (result != null) {
            mUploadMsgAboveAndroid5!!.onReceiveValue(arrayOf(result))
        } else {
            mUploadMsgAboveAndroid5!!.onReceiveValue(arrayOf())
        }
        mUploadMsgAboveAndroid5 = null
    }

    companion object {

        val CODE_FILE_CHOOSE = 1
        val CODE_FILE_CHOOSE_5 = 2
    }
}
