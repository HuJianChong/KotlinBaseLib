package com.hjc.kotlintest.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import com.hjc.baselibrary.base.BaseActivity
import com.hjc.baselibrary.utils.AppUtils
import com.hjc.kotlintest.R
import kotlinx.android.synthetic.main.splash_activity.*
import pub.devrel.easypermissions.EasyPermissions


/**
 * @author hjc
 * date ${DATE}.
 * description：闪屏页
 */
class SplashActivity : BaseActivity() {

    private var alphaAnimation: AlphaAnimation? = null

    override fun layoutId(): Int = R.layout.splash_activity

    override fun initData() {
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        versionNameTv.text = "v${AppUtils.versionName}"

        //渐变展示启动屏
        alphaAnimation = AlphaAnimation(0.3f, 1.0f)
        alphaAnimation?.duration = 2000
        alphaAnimation?.setAnimationListener(object : AnimationListener {
            override fun onAnimationEnd(arg0: Animation) {
                redirectTo()
            }

            override fun onAnimationRepeat(animation: Animation) {}

            override fun onAnimationStart(animation: Animation) {}

        })

        checkPermission()
    }

    override fun start() {
    }


    fun redirectTo() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun checkPermission() {
        val perms = arrayOf(Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        EasyPermissions.requestPermissions(this, "KotlinMvp应用需要以下权限，请允许", 0, *perms)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        if (requestCode == 0 && perms.isNotEmpty()) {
            if (perms.contains(Manifest.permission.READ_PHONE_STATE)
                && perms.contains(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            ) {
                if (alphaAnimation != null) {
                    iconImg.startAnimation(alphaAnimation)
                }
            }
        }
    }


}