package com.zkhy.community.view.comm

import android.annotation.SuppressLint
import android.os.Bundle
import com.sinothk.comm.utils.DateUtil
import com.sinothk.comm.utils.StringUtil
import com.zkhy.community.R
import com.zkhy.community.model.bean.NoticeMsgEntity
import com.zkhy.community.presenter.CommPresenter
import com.zkhy.library.base.activity.TitleBarBaseActivity
import com.zkhy.library.mvp.AndroidExt2View
import kotlinx.android.synthetic.main.activity_notice_msg_detail.*

class NoticeMsgDetailActivity : TitleBarBaseActivity(), AndroidExt2View {


    private var presenter: CommPresenter? = null
    var entity: NoticeMsgEntity? = null

    override fun getLayout(): Int = R.layout.activity_notice_msg_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("通知详情")
        entity = intent.getSerializableExtra("entity") as NoticeMsgEntity?

        if (entity != null) {
            showView(entity!!)

            try {
                val ids = ArrayList<String>()
                ids.add(entity!!.receiveId.toString())

                presenter = CommPresenter(this)
                presenter?.loadNoticeMsgStatus(ids)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun loadingShow() {
    }

    override fun loadingDismiss() {
    }

    override fun showTip(msg: String?) {
    }

    override fun loadComplete(resultData: Any) {
    }

    @SuppressLint("SetTextI18n")
    private fun showView(entity: NoticeMsgEntity) {
        titleTv.text = StringUtil.getNotNullValue(entity.title)
        contentTv.text = "     " + StringUtil.getNotNullValue(entity.content)

        if (entity.createTime != null) {
            timeTv.text = DateUtil.getDateStrByDate(entity.createTime, "yyyy年MM月dd日")
        } else {
            timeTv.text = "暂无时间"
        }
    }

    override fun load2Complete(resultData: Any?) {
    }
}
