package com.hjc.baselibrary.utils

/**
 * @author hjc
 * @date 2019/1/2 0002.
 * description：字符串工具类
 */
class StringUtils private constructor() {

    companion object {

        /**
         * 数据流量格式化
         */
        @JvmStatic
        fun dataFormat(total: Long): String {
            var result: String
            var speedReal: Int = (total / (1024)).toInt()
            result = if (speedReal < 512) {
                speedReal.toString() + " KB"
            } else {
                val mSpeed = speedReal / 1024.0
                (Math.round(mSpeed * 100) / 100.0).toString() + " MB"
            }
            return result
        }
    }

}