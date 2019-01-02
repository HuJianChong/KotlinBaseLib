package com.hbys.baselibrary.utils

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

        fun init(context: Context) {
            displayMetrics = context.resources.displayMetrics
            screenWidth = displayMetrics.widthPixels
            screenHeight = displayMetrics.heightPixels
            screenDpi = displayMetrics.densityDpi
        }
    }
}