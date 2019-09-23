package com.zkhy.fenggang.community.view.main.bm

import android.os.Bundle
import android.view.View
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.ToastUtil
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.cache.KeyWord
import com.zkhy.library.base.activity.TitleBarBaseActivity
import kotlinx.android.synthetic.main.activity_biz_order_list.*

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/11 on 16:26
 *  项目:  WuMinAndroid
 *  描述:  预约项目
 *  更新:
 * <pre>
 */
class BmOrderHomeActivity : TitleBarBaseActivity(), View.OnClickListener {

    override fun getLayout(): Int = R.layout.activity_biz_order_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("预约事项", "预约记录") {
            IntentUtil.openActivity(this@BmOrderHomeActivity, OrderHistoryListActivity::class.java).start()
        }

        item0Layout.setOnClickListener(this)
        item1Layout.setOnClickListener(this)
        item2Layout.setOnClickListener(this)
        item3Layout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            item0Layout -> {
                IntentUtil.openActivity(this@BmOrderHomeActivity, BmOrderDateActivity::class.java)
                    .putStringExtra("typeCode", KeyWord.wm_con_or_marry)//wm_con_or_marry
                    .start()
            }

            item1Layout -> {
                IntentUtil.openActivity(this@BmOrderHomeActivity, BmOrderDateActivity::class.java)
                    .putStringExtra("typeCode", KeyWord.wm_con_or_disa)//wm_con_or_disa
                    .start()
            }

            item3Layout -> {
                IntentUtil.openActivity(this@BmOrderHomeActivity, BmOrderTakeFileListActivity::class.java)
                    .start()
            }

            item2Layout -> {
                ToastUtil.show("暂未开放")
            }
        }
    }
}
