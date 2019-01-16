package com.hjc.baselibrary.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.annotation.NonNull
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import com.classic.common.MultipleStatusView
import com.hjc.baselibrary.app.Const
import com.orhanobut.logger.Logger
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions


/**
 * @author hjc
 * created: 2019/1/2
 * desc:BaseActivity基类
 */
abstract class BaseActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {
    /**
     * 多种状态的 View 的切换
     */
    protected var mMultiStatusView: MultipleStatusView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        initData()
        initView()
        start()
        initListener()
    }

    private fun initListener() {
        mMultiStatusView?.setOnClickListener(mRetryClickListener)
    }

    open val mRetryClickListener: View.OnClickListener = View.OnClickListener {
        start()
    }


    /**
     *  加载布局
     */
    abstract fun layoutId(): Int

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 初始化 View
     */
    abstract fun initView()

    /**
     * 开始请求
     */
    abstract fun start()

    /**
     * 跳转
     */
    fun <T> launchActivity(cls: Class<T>) {
        val intent = Intent(this, cls)
        startActivity(intent)
    }

    fun <T> launchActivityWithString(cls: Class<T>, data: String?) {
        val intent = Intent(this, cls)
        if (data != null) {
            intent.putExtra(Const.EXTRA_DATA, data)
        }
        startActivity(intent)
    }

    fun <T> launchActivityWithData(cls: Class<T>, data: Parcelable) {
        val intent = Intent(this, cls)
        intent.putExtra(Const.EXTRA_DATA, data)
        startActivity(intent)
    }

    fun <T> launchActivityForResult(cls: Class<T>, requestCode: Int) {
        val intent = Intent(this, cls)
        startActivityForResult(intent, requestCode)
    }

    /**
     * 打卡软键盘
     */
    fun openKeyBord(mEditText: EditText, mContext: Context) {
        val imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN)
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    /**
     * 关闭软键盘
     */
    fun closeKeyBord(mEditText: EditText, mContext: Context) {
        val imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mEditText.windowToken, 0)
    }

    /**
     * 重写要申请权限的Activity或者Fragment的onRequestPermissionsResult()方法，
     * 在里面调用EasyPermissions.onRequestPermissionsResult()，实现回调。
     *
     * @param requestCode  权限请求的识别码
     * @param permissions  申请的权限
     * @param grantResults 授权结果
     */
    override fun onRequestPermissionsResult(requestCode: Int, @NonNull permissions: Array<String>, @NonNull grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    /**
     * 当权限被成功申请的时候执行回调
     *
     * @param requestCode 权限请求的识别码
     * @param perms       申请的权限的名字
     */
    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        Logger.i("EasyPermissions:%s", "获取成功的权限$perms")
    }

    /**
     * 当权限申请失败的时候执行的回调
     *
     * @param requestCode 权限请求的识别码
     * @param perms       申请的权限的名字
     */
    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        //处理权限名字字符串
        val sb = StringBuffer()
        for (str in perms) {
            sb.append(str)
            sb.append("\n")
        }
        sb.replace(sb.length - 2, sb.length, "")
        //用户点击拒绝并不在询问时候调用
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            Toast.makeText(this, "已拒绝权限" + sb + "并不再询问", Toast.LENGTH_SHORT).show()
            AppSettingsDialog.Builder(this)
                .setRationale("此功能需要" + sb + "权限，否则无法正常使用，是否打开设置")
                .setPositiveButton("好")
                .setNegativeButton("不行")
                .build()
                .show()
        }
    }

}


