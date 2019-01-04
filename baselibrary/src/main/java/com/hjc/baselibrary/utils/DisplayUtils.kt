package com.hjc.baselibrary.utils

import android.content.Context
import android.util.DisplayMetrics
import kotlin.properties.Delegates

/**
 * hjc
 * 界面相关Utils
 */
class DisplayUtils private constructor() {
    companion object {
        private lateinit var displayMetrics: DisplayMetrics
        var screenWidth: Int by Delegates.notNull()
        var screenHeight: Int by Delegates.notNull()
        var screenDpi: Int by Delegates.notNull()
        var screenDensity: Float by Delegates.notNull()

        fun init(context: Context) {
            displayMetrics = context.resources.displayMetrics
            screenWidth = displayMetrics.widthPixels
            screenHeight = displayMetrics.heightPixels
            screenDpi = displayMetrics.densityDpi
            screenDensity = displayMetrics.density
        }

        fun dip2px(dipValue: Float): Int = (dipValue * screenDensity + 0.5f).toInt()

        fun px2dip(pxValue: Float): Int = (pxValue / screenDensity + 0.5f).toInt()
    }
}