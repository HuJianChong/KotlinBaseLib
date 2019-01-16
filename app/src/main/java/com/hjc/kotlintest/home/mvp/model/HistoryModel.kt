package com.hjc.kotlintest.home.mvp.model

import com.hjc.baselibrary.BaseApplication
import com.hjc.baselibrary.scheduler.SchedulerUtils
import com.hjc.kotlintest.bean.ProjectBean
import com.hjc.kotlintest.database.AppDatabase
import io.reactivex.Observable

/**
 * Created by hjc
 * desc: 项目数据模型
 */
class HistoryModel {

    /**
     * 获取项目信息
     */
    fun getHistoryData(): Observable<List<ProjectBean.Data>> {
        return Observable.create<List<ProjectBean.Data>> {
            it.onNext(AppDatabase.getInstance(BaseApplication.context).projectDao().all)
        }.compose(SchedulerUtils.ioToMain())
    }
}