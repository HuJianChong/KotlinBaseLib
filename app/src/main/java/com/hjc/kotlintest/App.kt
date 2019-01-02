package com.hjc.kotlintest

import com.hjc.baselibrary.BaseApplication
import com.hjc.baselibrary.utils.StringUtils
import com.orhanobut.logger.Logger

class App : BaseApplication() {

    override fun initConfig() {
        super.initConfig()

        //配置初始化
        Logger.d("初始化%s", StringUtils.dataFormat(1024 * 1024))
    }

}