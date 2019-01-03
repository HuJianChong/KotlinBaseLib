package com.hjc.baselibrary.scheduler

/**
 * Created by hjc
 * desc:线程调度器工具类
 */
object SchedulerUtils {

    fun <T> ioToMain(): IoMainScheduler<T> {
        return IoMainScheduler()
    }
}
