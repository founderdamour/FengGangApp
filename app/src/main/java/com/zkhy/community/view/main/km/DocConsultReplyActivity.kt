package com.zkhy.community.view.main.km

import android.os.Bundle
import android.text.Html
import android.text.TextUtils
import android.view.MotionEvent
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.sinothk.comm.utils.NetUtil
import com.sinothk.comm.utils.StringUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.dialog.loading.LoadingDialog
import com.zkhy.community.R
import com.zkhy.community.model.api.BaseData
import com.zkhy.community.model.api.CommReq
import com.zkhy.community.model.bean.DocQAReplyBean
import com.zkhy.community.presenter.KMPresenter
import com.zkhy.library.base.activity.EditViewAdapterBaseActivity
import com.zkhy.library.base.activity.TitleBarBaseActivity
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.mvp.AndroidView
import com.zkhy.library.utils.ImageLoader
import kotlinx.android.synthetic.main.activity_am_law_consult_reply.*

/**
 * 回复咨询
 */
class DocConsultReplyActivity : EditViewAdapterBaseActivity(), AndroidExt2View {
    override fun load2Complete(resultData: Any?) {
    }

    private var consId = ""
    private var item: DocQAReplyBean? = null

    private var presenter: KMPresenter? = null

    override fun getLayout(): Int = R.layout.activity_am_law_consult_reply

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTitleBar("回复评论")
        // 适配输入框被遮挡问题
        initKeyBoardListener(scrollView)

        item = intent.getSerializableExtra("item") as DocQAReplyBean?
        consId = intent.getStringExtra("consId")

        initView()
        presenter = KMPresenter(this)
    }

    private fun initView() {
        if (item != null) {
            userNameTv.text = item!!.answerUserName
            timeTv.text = item!!.quesTime

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                contentTv.text = Html.fromHtml(
                    StringUtil.getNotNullValue(item!!.question),
                    Html.FROM_HTML_MODE_LEGACY
                )
            } else {
                contentTv.text = Html.fromHtml(StringUtil.getNotNullValue(item!!.question))
            }

            ImageLoader.show(this, item!!.answerUserPhoto, R.drawable.mr_tx, userAvatarIv)
        }

//        // 设置Activity Window的softInputMode属性---确保输入法弹出不会触发window大小改变
//        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
//
//        contentEt.setOnTouchListener { v, event ->
//            when {
//                event.action == MotionEvent.ACTION_DOWN -> v.parent.requestDisallowInterceptTouchEvent(true)
//                event.action == MotionEvent.ACTION_UP -> v.parent.requestDisallowInterceptTouchEvent(false)
//                event.action == MotionEvent.ACTION_CANCEL -> v.parent.requestDisallowInterceptTouchEvent(false)
//            }
//            false
//        }



        submitBtn.setOnClickListener {
            submit()
        }
    }

    private fun submit() {
        if (TextUtils.isEmpty(consId)) {
            ToastUtil.show("数据异常,请返回后重试")
            return
        }

        val content = contentEt.text.toString()

        if (!NetUtil.isAvailable(this)) {
            ToastUtil.show(R.string.net_error)
            return
        }

        if (StringUtil.isEmpty(content)) {
            ToastUtil.show("请输入回复内容 ...")
            return
        }

        if (content.length > 1000) {
            ToastUtil.show("回复内容字数应在1000字符以内...")
            return
        }

        val commReq = CommReq()
        commReq.consId = consId
        commReq.answer = content
        commReq.isFromWeb = false

        presenter?.addDocQuestionReply(commReq)
    }

    override fun loadComplete(result: Any?) {
        val baseData = result as BaseData<Boolean>
        if (baseData.errcode == 0) {
            if (baseData.data) {
                showTip("新增成功")
                finish()
            } else {
                showTip(baseData.errmsg)
            }
        } else {
            showTip(baseData.errmsg)
        }
    }

    override fun loadingShow() {
        LoadingDialog.show(this, "正在提交")
    }

    override fun loadingDismiss() {
        LoadingDialog.hidden()
    }

    override fun showTip(msg: String?) {
        ToastUtil.show(msg)
    }
}
