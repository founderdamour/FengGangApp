package com.zkhy.community.view.main.dw

import android.os.Build
import android.os.Bundle
import android.text.Html
import com.sinothk.comm.utils.DateUtil
import com.zkhy.community.R
import com.zkhy.community.model.bean.DWOpenListEntity
import com.zkhy.library.base.activity.TitleBarBaseActivity
import kotlinx.android.synthetic.main.activity_dw_open_detail.*

class DWOpenDetailActivity : TitleBarBaseActivity() {

    private var entity: DWOpenListEntity? = null

    override fun getLayout(): Int {
        return R.layout.activity_dw_open_detail
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("党务公开详情")
        initData()
        initView()
    }

    private fun initView() {
        dwTitleTv.text = entity!!.title
        dwTimeTv.text = DateUtil.getDateStrByDate(entity!!.createTime, "yyyy-MM-dd")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            dwContentTv.text = Html.fromHtml(entity!!.content, Html.FROM_HTML_MODE_LEGACY)
        } else {
            dwContentTv.text = Html.fromHtml(entity!!.content)
        }
    }

    private fun initData() {
        entity = intent.getSerializableExtra("entity") as DWOpenListEntity?
    }
}