package com.hbys.baselibrary.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.telephony.TelephonyManager
import com.hbys.baselibrary.BaseApplication
import java.io.IOException
import java.net.HttpURLConnection
import java.net.NetworkInterface
import java.net.SocketException
import java.net.URL

/**
 * @author hjc
 * @date 2018/12/29.
 * description：网络相关工具类
 */
class NetworkUtil private constructor() {

    companion object {
        private val TIMEOUT = 3000 // TIMEOUT

        /**
         * check NetworkAvailable
         * @param context
         * @return
         */
        @JvmStatic
        fun isNetworkAvailable(): Boolean {
            val manager = BaseApplication.context.getSystemService(
                Context.CONNECTIVITY_SERVICE
            ) as ConnectivityManager
            val info = manager.activeNetworkInfo
            return !(null == info || !info.isAvailable)
        }

        /**
         * 得到ip地址
         * @return
         */
        @JvmStatic
        fun getLocalIpAddress(): String {
            var ret = ""
            try {
                val en = NetworkInterface.getNetworkInterfaces()
                while (en.hasMoreElements()) {
                    val enumIpAddress = en.nextElement().inetAddresses
                    while (enumIpAddress.hasMoreElements()) {
                        val netAddress = enumIpAddress.nextElement()
                        if (!netAddress.isLoopbackAddress) {
                            ret = netAddress.hostAddress.toString()
                        }
                    }
                }
            } catch (ex: SocketException) {
                ex.printStackTrace()
            }

            return ret
        }


        /**
         * ping "http://www.baidu.com"
         */
        @JvmStatic
        private fun pingNetWork(): Boolean {
            var result = false
            var httpUrl: HttpURLConnection? = null
            try {
                httpUrl = URL("http://www.baidu.com")
                    .openConnection() as HttpURLConnection
                httpUrl.connectTimeout = TIMEOUT
                httpUrl.connect()
                result = true
            } catch (e: IOException) {
            } finally {
                if (null != httpUrl) {
                    httpUrl.disconnect()
                }
            }
            return result
        }

        /**
         * isWifi
         */
        @JvmStatic
        fun isWifi(): Boolean {
            val connectivityManager = BaseApplication.context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetInfo = connectivityManager.activeNetworkInfo
            return activeNetInfo != null && activeNetInfo.type == ConnectivityManager.TYPE_WIFI
        }

        /**
         * is wifi on
         */
        @JvmStatic
        fun isWifiEnabled(): Boolean {
            val mgrConn = BaseApplication.context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val mgrTel = BaseApplication.context
                .getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            return mgrConn.activeNetworkInfo != null && mgrConn
                .activeNetworkInfo.state == NetworkInfo.State.CONNECTED || mgrTel
                .networkType == TelephonyManager.NETWORK_TYPE_UMTS
        }

    }


}