package com.hjc.baselibrary.net.exception

/**
 * Created by hjc on 2019/1/2.
 * desc:
 */
class ApiException : RuntimeException {

    private var code: Int? = null


    constructor(throwable: Throwable, code: Int) : super(throwable) {
        this.code = code
    }

    constructor(message: String) : super(Throwable(message))
}