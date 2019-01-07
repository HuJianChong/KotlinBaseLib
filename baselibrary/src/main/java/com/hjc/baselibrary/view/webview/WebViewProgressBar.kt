package com.hjc.baselibrary.view.webview

import android.content.Context
import android.os.Handler
import android.os.Message
import android.util.AttributeSet
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.ProgressBar

class WebViewProgressBar : ProgressBar {

    private var schedule = 0// web加载时进度条当前进度
    private var isFinishSchedule = 100000// web加载完成时进度条当前进度
    private var isFinish = false//web是否加载完成
    private var myThread: MyThread? = null

    internal var mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            schedule = msg.what
            progress = msg.what
            if (msg.what > isFinishSchedule) {
                val animation = AlphaAnimation(1.0f, 0.3f)
                animation.duration = 400
                startAnimation(animation)
            }
            if (msg.what >= 10000) {
                visibility = View.GONE
            }
        }

    }

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    /**
     * 进度条开始加载，刷新可重复调用
     */
    fun startProgress() {
        visibility = View.VISIBLE
        max = 10000
        progress = 0
        isFinish = false
        if (myThread != null) {
            myThread!!.interrupt()
        }
        myThread = MyThread()
        myThread!!.start()
    }

    /**
     * 加载完成结束进度条
     */
    fun finishProgress() {
        isFinish = true
    }

    inner class MyThread : Thread() {
        override fun run() {

            try {
                var time = 0
                val n = (TIME_SECOND - TIME_FIRST) / TIME_SLEEP//中间慢速段等差数列次数
                val d = (CHANGE_SECOND - CHANGE_FIRST) / (n - 1)//中间慢速段每次前进长度等差数列公差
                while (true) {
                    if (isFinish) {
                        finishProgressSchedule()
                        return
                    }
                    val message = Message()
                    if (time <= TIME_FIRST) {//前部分快速速
                        Thread.sleep(TIME_SLEEP.toLong())
                        time = time + TIME_SLEEP
                        message.what = time / TIME_SLEEP * (CHANGE_FIRST * TIME_SLEEP) / TIME_FIRST
                        mHandler.sendMessage(message)
                    } else if (time > TIME_FIRST && time <= TIME_SECOND) {//中间慢慢减速
                        Thread.sleep(TIME_SLEEP.toLong())
                        time = time + TIME_SLEEP
                        val thisN = (time - TIME_FIRST) / TIME_SLEEP
                        val An = CHANGE_FIRST + (thisN - 1) * d//计算当前该走到位置
                        message.what = An
                        mHandler.sendMessage(message)
                    } else if (time > TIME_SECOND) {
                        for (a in 1..1000) {//60秒未加载完，进度条直接结束
                            Thread.sleep(60)
                            if (a == 1000) {//当访问超时快速结束
                                finishProgress()
                            }
                            if (isFinish) {
                                finishProgressSchedule()
                                return
                            }
                        }
                    }
                }

            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }

    }

    /**
     * 子线程中ProgressBar加载完成快速走完剩余进度
     */
    @Throws(InterruptedException::class)
    private fun finishProgressSchedule() {
        isFinishSchedule = schedule
        var i = schedule
        do {
            val message = Message()
            Thread.sleep(TIME_SLEEP.toLong())
            i = i + 100
            message.what = i
            mHandler.sendMessage(message)
        } while (i <= 10000)
    }

    companion object {
       const val CHANGE_FIRST = 3000//起始快速所走长度
       const val CHANGE_SECOND = 9000//中间减速段长度和起始长度总和
       const val TIME_FIRST = 1000//起始快速所需时间
       const val TIME_SECOND = 5000//中间减速段时间和起始时间总和
       const val TIME_SLEEP = 10//ProgressBar每次更新进度时间
    }
}