package com.hjc.baselibrary.base


/**
 * @author hjc
 */
interface IPresenter<in V: IBaseView> {

    fun attachView(mRootView: V)

    fun detachView()

}
