package com.hjc.kotlintest.app

import android.content.Context
import com.hjc.baselibrary.BaseApplication
import com.hjc.baselibrary.utils.StringUtils
import com.hjc.kotlintest.R
import com.orhanobut.logger.Logger
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader

class App : BaseApplication() {

    override fun initConfig() {
        super.initConfig()

        //配置初始化
        Logger.d("初始化%s", StringUtils.dataFormat(1024 * 1024))

        //初始化刷新控件
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context: Context, layout: RefreshLayout ->
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white)//全局设置主题颜色
            ClassicsHeader(context)//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header
        }
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context: Context, layout: RefreshLayout ->
            ClassicsFooter(context).setDrawableSize(20f)
        }
    }
}