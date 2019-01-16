package com.hjc.baselibrary.utils

import android.annotation.SuppressLint
import android.text.TextUtils
import android.widget.Toast
import com.hjc.baselibrary.BaseApplication

/**
 * ToastUtils
 */
class ToastUtils {
    companion object {
        private val context
            get() = BaseApplication.context
        private var toast: Toast? = null

        fun show(resId: Int) {
            show(context.resources.getText(resId), Toast.LENGTH_SHORT)
        }

        fun show(resId: Int, duration: Int) {
            show(context.resources.getText(resId), duration)
        }

        fun show(text: CharSequence) {
            show(text, Toast.LENGTH_SHORT)
        }

        /*public static void showDebug(CharSequence text) {
            if (BuildConfig.DEBUG) {
                show(text, Toast.LENGTH_SHORT);
            }
        }*/

        @SuppressLint("ShowToast")
        fun show(text: CharSequence?, duration: Int) {
            var text = text
            text = if (TextUtils.isEmpty(text?.toString() ?: "")) "请检查您的网络！" else text
            if (toast == null) {
                toast = Toast.makeText(context, text, duration)
            } else {
                toast!!.setText(text)
            }
            toast!!.show()
        }
    }
}