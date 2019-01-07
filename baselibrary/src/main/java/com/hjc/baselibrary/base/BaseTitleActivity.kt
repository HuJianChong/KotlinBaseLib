package com.hjc.baselibrary.base

import android.annotation.SuppressLint
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import com.hjc.baselibrary.R
import kotlinx.android.synthetic.main.layout_title.*

/**
 * @author hjc
 * date 2019/1/7 0007.
 * description：Base 带标题的Activity
 */
abstract class BaseTitleActivity : BaseActivity() {

    @SuppressLint("InflateParams")
    override fun setContentView(layoutResID: Int) {
        val view = layoutInflater.inflate(R.layout.activity_base_title, null) as ViewGroup
        layoutInflater.inflate(layoutResID, view, true)
        setContentView(view)
    }

    override fun initView() {
        leftImg.setOnClickListener {
            onBackPressed()
        }
    }

    protected fun setTitleText(title: String) {
        titleTv.text = title
    }

    protected fun setRightText(title: String) {
        setRightText(title, null)
    }

    protected fun setRightText(title: String, onClickListener: View.OnClickListener?) {
        if (TextUtils.isEmpty(title)) {
            return
        }
        rightTv.visibility = View.VISIBLE
        rightTv.text = title
        if (onClickListener != null) {
            rightTv.setOnClickListener(onClickListener)
        }
    }

    protected fun setRightDrawableResource(resid: Int, onClickListener: View.OnClickListener) {
        rightImg.visibility = View.VISIBLE
        rightImg.setImageResource(resid)
        rightImg.setOnClickListener(onClickListener)
    }

    protected fun setRightDrawableResource2(resid: Int, onClickListener: View.OnClickListener) {
        rightImg2.visibility = View.VISIBLE
        rightImg2.setImageResource(resid)
        rightImg2.setOnClickListener(onClickListener)
    }
}