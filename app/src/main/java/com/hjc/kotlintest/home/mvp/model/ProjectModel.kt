package com.hjc.kotlintest.home.mvp.model

import com.hjc.baselibrary.scheduler.SchedulerUtils
import com.hjc.kotlintest.bean.ProjectBean
import com.hjc.kotlintest.net.RetrofitManager
import io.reactivex.Observable

/**
 * Created by hjc
 * desc: 项目数据模型
 */
class ProjectModel {

    /**
     * 获取项目信息
     */
    fun getProjectData(): Observable<ProjectBean> {
        return RetrofitManager.service.getProjectList(10)
                .compose(SchedulerUtils.ioToMain())
    }
}