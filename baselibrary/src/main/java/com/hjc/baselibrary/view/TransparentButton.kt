package com.hjc.baselibrary.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * 点击会变透明的Button
 * Created by hjc on 2017/5/19.
 */
class TransparentButton : android.support.v7.widget.AppCompatTextView {
    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (isClickable && isEnabled) {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> alpha = 0.9f
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> alpha = 1f
            }
        }
        return super.onTouchEvent(event)
    }
}
