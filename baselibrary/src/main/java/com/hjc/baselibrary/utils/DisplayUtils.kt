package com.hjc.baselibrary.utils

import com.hjc.baselibrary.BaseApplication
import kotlin.properties.Delegates

/**
 * hjc
 * 界面相关Utils
 */
class DisplayUtils private constructor() {
    companion object {
        var screenWidth: Int by Delegates.notNull()
        var screenHeight: Int by Delegates.notNull()
        var screenDpi: Int by Delegates.notNull()
        var screenDensity: Float by Delegates.notNull()

        init {
            val displayMetrics = BaseApplication.context.resources.displayMetrics
            screenWidth = displayMetrics.widthPixels
            screenHeight = displayMetrics.heightPixels
            screenDpi = displayMetrics.densityDpi
            screenDensity = displayMetrics.density
        }

        fun dip2px(dipValue: Float): Int = (dipValue * screenDensity + 0.5f).toInt()

        fun px2dip(pxValue: Float): Int = (pxValue / screenDensity + 0.5f).toInt()
    }
}