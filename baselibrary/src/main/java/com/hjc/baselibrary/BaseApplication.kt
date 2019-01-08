package com.hjc.baselibrary

import android.app.Application
import android.content.Context
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import kotlin.properties.Delegates


/**
 * Created by hjc on 2019/1/2.
 * Base Application
 */
open class BaseApplication : Application() {

    companion object {
        var context: Context by Delegates.notNull()
            private set
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        initConfig()
    }

    /**
     * 初始化配置
     */
    protected open fun initConfig() {
        //初始化Logger日志打印
        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false)  // 隐藏线程信息 默认：显示
            .methodCount(1)         // 决定打印多少行（每一行代表一个方法）默认：2
            .methodOffset(0)        // (Optional) Hides internal method calls up to offset. Default 0
            .tag("hjc")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
            .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }
}
