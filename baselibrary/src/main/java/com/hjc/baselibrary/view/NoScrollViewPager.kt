package com.hjc.baselibrary.view

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * 不可以滑动，但是可以setCurrentItem的ViewPager。
 */
class NoScrollViewPager : ViewPager {
    private var noScroll = true

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    fun setNoScroll(noScroll: Boolean) {
        this.noScroll = noScroll
    }

    override fun onTouchEvent(arg0: MotionEvent): Boolean {
        return if (noScroll)
            false
        else
            super.onTouchEvent(arg0)
    }

    override fun onInterceptTouchEvent(arg0: MotionEvent): Boolean {
        return if (noScroll)
            false
        else
            super.onInterceptTouchEvent(arg0)
    }

    //去除页面切换时的滑动翻页效果
    override fun setCurrentItem(item: Int) {
        super.setCurrentItem(item, false)
    }
}
