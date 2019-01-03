package com.hjc.kotlintest.home.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hjc.baselibrary.glide.GlideApp
import com.hjc.kotlintest.R
import com.hjc.kotlintest.bean.ProjectBean
import com.hjc.kotlintest.utils.ImageLoaderUtils

/**
 * @author hjc
 * date 2019/1/3 0003.
 * description：项目Adapter
 */
class ProjectAdapter(layoutResId: Int, data: MutableList<ProjectBean.Data>?) :
    BaseQuickAdapter<ProjectBean.Data, BaseViewHolder>(layoutResId, data) {

    override fun convert(helper: BaseViewHolder?, item: ProjectBean.Data?) {
        item?.apply {
            helper?.setText(R.id.titleTv, desc)
            helper?.setText(R.id.timeTv, publishedAt)
            helper?.setText(R.id.authorTv, who)

            if (images != null && images.size > 0) {
                GlideApp.with(helper?.itemView!!).applyDefaultRequestOptions(ImageLoaderUtils.defaultOptions)
                    .load(images[0]).into(helper.getView(R.id.imageView) as ImageView)
            }
        }
    }

}