package com.hjc.kotlintest.utils

import android.content.Context
import com.hjc.kotlintest.R
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration

/**
 * @author hjc
 * date 2019/1/4 0004.
 * description：RecyclerView 分割线
 */
class DividerUtils private constructor() {

    companion object {
        fun getDefaultDivider(context: Context): HorizontalDividerItemDecoration {
            return HorizontalDividerItemDecoration.Builder(context)
                .colorResId(R.color.division_line).sizeResId(R.dimen.division_line).build()
        }

        fun getDefaultMarginDivider(context: Context): HorizontalDividerItemDecoration {
            return HorizontalDividerItemDecoration.Builder(context)
                .colorResId(R.color.division_line).sizeResId(R.dimen.division_line).marginResId(R.dimen.padding_common)
                .build()
        }
    }

}