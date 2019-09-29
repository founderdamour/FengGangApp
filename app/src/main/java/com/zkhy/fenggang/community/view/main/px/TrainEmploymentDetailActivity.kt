package com.zkhy.fenggang.community.view.main.px

import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.content.ContextCompat
import android.text.Html
import android.view.View
import com.sinothk.comm.utils.DateUtil
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.bean.TrainEmploymentEntity
import com.zkhy.library.base.activity.TitleBarBaseActivity
import kotlinx.android.synthetic.main.activity_train_employment_detail.*
import java.net.URL

/**
 * 培训就业详情
 */
class TrainEmploymentDetailActivity : TitleBarBaseActivity() {

    private var entity: TrainEmploymentEntity? = null
    private var px: Int? = 0

    private var mHandler: Handler = Handler(Handler.Callback { msg ->
        if (msg.what == 183) {
            contentTv.text = msg.obj as CharSequence
            tipMsgTv.visibility = View.GONE
        }
        false
    })

    override fun getLayout(): Int {
        return R.layout.activity_train_employment_detail
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        if (px == 0) {
            setTitleBar("培训详情")
        } else {
            setTitleBar("就业详情")
        }
        initView()
    }

    private fun initView() {
        titleTv.text = entity!!.title
        timeTv.text = DateUtil.getDateStrByDate(entity!!.publishTime, "yyyy-MM-dd")

        Thread(Runnable {
            val imgGetter = Html.ImageGetter { source ->
                var drawable: Drawable
                val url: URL
                try {
                    url = URL(source)
                    drawable = Drawable.createFromStream(url.openStream(), "")  //获取网路图片
                } catch (e: Exception) {
                    drawable = ContextCompat.getDrawable(this.baseContext, R.mipmap.preview3)!!
                }
                drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                drawable
            }
            val text: CharSequence
            text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(entity!!.publishContent, Html.FROM_HTML_MODE_LEGACY, imgGetter, null)
            } else {
                Html.fromHtml(entity!!.publishContent,imgGetter,null)
            }

            val msg: Message = Message.obtain()
            msg.what = 183
            msg.obj = text
            mHandler.sendMessage(msg)

        }).start()
    }

    private fun initData() {
        px = intent.getIntExtra("px", 0)
        entity = intent.getSerializableExtra("entity") as TrainEmploymentEntity?
    }
}