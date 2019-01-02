package com.hbys.kotlintest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.hazz.kotlinmvp.glide.GlideApp
import com.hazz.kotlinmvp.glide.ImageLoaderUtils
import com.hbys.baselibrary.utils.StatusBarUtil

class MainActivity : AppCompatActivity() {

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        StatusBarUtil.immersive(this)
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPadding(this, window.decorView)

        val textView = findViewById<TextView>(R.id.textView)
        val button = findViewById<Button>(R.id.button)
        val imageView: ImageView = findViewById(R.id.imageView)
        button.setOnClickListener {
            count++
            textView.text = count.toString()
        }

        Glide.with(this).applyDefaultRequestOptions(ImageLoaderUtils.defaultOptions)
        GlideApp.with(this).apply { ImageLoaderUtils.defaultOptions }.load("http://wx4.sinaimg.cn/large/bf9800dely1fk9qwicfjjj20dw0dwtan.jpg").into(imageView)
//        GlideApp.with(this).load("http://wx4.sinaimg.cn/large/bf9800dely1fk9qwicfjjj20dw0dwtan.jpg")
//            .fitCenter().placeholder(R.mipmap.ic_launcher).into(imageView)
    }
}
