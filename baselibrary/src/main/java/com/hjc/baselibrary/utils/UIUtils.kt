package com.hjc.baselibrary.utils

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import com.hjc.baselibrary.BaseApplication


/**
 * 有关UI的工具类，如获取资源(颜色，字符串，drawable等)，
 * 屏幕宽高，dp与px转换
 */
object UIUtils {

    private val context: Context
        get() = BaseApplication.context

    private val resources: Resources
        get() = context.resources

    val displayMetrics: DisplayMetrics
        get() {

            val displayMetrics = DisplayMetrics()
            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            windowManager?.defaultDisplay?.getMetrics(displayMetrics)

            return displayMetrics
        }

    /**
     * 获取屏幕宽度 像素值
     *
     * @return 屏幕宽度
     */
    val screenWidth: Int
        get() = displayMetrics.widthPixels

    /**
     * 获取屏幕高度 像素值
     *
     * @return 屏幕高度
     */
    val screenHeight: Int
        get() = displayMetrics.heightPixels

    /**
     * 获取颜色值
     *
     * @param resId 颜色资源id
     * @return 颜色值
     */
    fun getColor(resId: Int): Int {
        return ContextCompat.getColor(context, resId)
    }

    fun getColor(color: String): Int {
        return Color.parseColor(color)
    }

    fun getColorStateList(@ColorRes resId: Int): ColorStateList? {
        return ContextCompat.getColorStateList(context, resId)
    }

    /**
     * 获取Drawable
     *
     * @param resTd Drawable资源id
     * @return Drawable
     */
    fun getDrawable(resTd: Int): Drawable {
        return resources.getDrawable(resTd)
    }

    /**
     * 获取字符串
     *
     * @param resId 字符串资源id
     * @return 字符串
     */
    fun getString(resId: Int): String {
        return resources.getString(resId)
    }

    /**
     * 获取字符串数组
     *
     * @param resId 数组资源id
     * @return 字符串数组
     */
    fun getStringArray(resId: Int): Array<String> {
        return resources.getStringArray(resId)
    }

    fun getDimen(resId: Int): Int {
        return resources.getDimensionPixelSize(resId)
    }

    /**
     * 获取View findViewById
     */
    fun <E : View> findView(activity: Activity, id: Int): E {
        return activity.findViewById<View>(id) as E
    }

    /**
     * 获取View findViewById
     */
    fun <E : View> findView(view: View, id: Int): E {
        return view.findViewById<View>(id) as E
    }

    /**
     * 设置view展示状态
     */
    fun setViewVisibleState(view: View, isVisible: Boolean) {
        view.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    /**
     * 将dp值转换为px值
     *
     * @param dp 需要转换的dp值
     * @return px值
     */
    fun dp2px(dp: Float): Int {
        return (resources.displayMetrics.density * dp + 0.5f).toInt()
    }

    /**
     * 将px值转换为dp值
     *
     * @param px 需要转换的px值
     * @return dp值
     */
    fun px2dp(px: Float): Int {
        return (px / resources.displayMetrics.density + 0.5f).toInt()
    }

    /**
     * 获取v在屏幕中的坐标
     */
    fun getScreenPosition(v: View): IntArray {
        val location = IntArray(2)
        v.getLocationOnScreen(location)
        //        int x = location[0];
        //        int y = location[1];
        return location
    }
}
