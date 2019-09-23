package com.zkhy.fenggang.community.view.main.bm

import android.os.Bundle
import android.view.View
import com.sinothk.comm.utils.ToastUtil
import com.zkhy.fenggang.community.R
import com.zkhy.library.base.activity.TitleBarBaseActivity
import kotlinx.android.synthetic.main.activity_bm_complain_home.*

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/13 on 10:13
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
class BmComplainHomeActivity : TitleBarBaseActivity(), View.OnClickListener {

    override fun getLayout(): Int = R.layout.activity_bm_complain_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("咨询投诉")

        item01.setOnClickListener(this)
        item02.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        ToastUtil.show("功能持续完善中...")
    }
}