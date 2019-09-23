package com.zkhy.fenggang.community.view.main.km

import android.os.Bundle
import android.view.View
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.ToastUtil
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.comm.plugin.activity.WebPageActivity
import com.zkhy.library.base.activity.TitleBarBaseActivity
import kotlinx.android.synthetic.main.activity_doctor_order_list.*

class DoctorOrderListActivity : TitleBarBaseActivity(), View.OnClickListener {

    override fun getLayout(): Int = R.layout.activity_doctor_order_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("预约挂号")

        item0Layout.setOnClickListener(this)
        item1Layout.setOnClickListener(this)

        item2Layout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            item0Layout -> {
                IntentUtil.openActivity(this, WebPageActivity::class.java)
                    .putStringExtra("webUrl", "http://yxy.zy20.com/hosreg.php?orgid=1")
                    .putStringExtra("webTitle", "遵义医学院附属医院")
                    .start()
            }

            item1Layout -> {
                IntentUtil.openActivity(this, WebPageActivity::class.java)
                    .putStringExtra("webUrl", "http://mp.gzhc365.com/dept_list_yy.xhtml?_route=f286&viewcode=html&HIS_CD=286&SCE_ID=")
                    .putStringExtra("webTitle", "遵义市第一人民医院")
                    .start()
            }

            item2Layout -> {
                ToastUtil.show("暂未开放")
            }
        }
    }

}
