package com.hjc.baselibrary

import android.content.Context
import android.support.v4.app.Fragment
import android.view.View
import android.widget.Toast

/**
 * @author hjc
 * @date 2019/1/2.
 * description：扩展函数
 */
fun Fragment.showToast(content: String): Toast {
    val toast = Toast.makeText(this.activity?.applicationContext, content, Toast.LENGTH_SHORT)
    toast.show()
    return toast
}

fun Context.showToast(content: String): Toast {
    val toast = Toast.makeText(BaseApplication.context, content, Toast.LENGTH_SHORT)
    toast.show()
    return toast
}