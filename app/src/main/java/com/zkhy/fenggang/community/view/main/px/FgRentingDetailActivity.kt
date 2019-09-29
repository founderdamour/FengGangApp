package com.zkhy.fenggang.community.view.main.px

import android.annotation.SuppressLint
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
import com.zkhy.fenggang.community.model.bean.FgRentingEntity
import com.zkhy.library.base.activity.TitleBarBaseActivity
import kotlinx.android.synthetic.main.activity_fg_renting_detail.*
import java.net.URL

/**
 * 房屋租赁详情
 */
class FgRentingDetailActivity : TitleBarBaseActivity() {

    private var entity: FgRentingEntity? = null

    private var mHandler: Handler = Handler(Handler.Callback { msg ->
        if (msg.what == 183) {
            contentTv.text = msg.obj as CharSequence
            tipMsgTv.visibility = View.GONE
        }
        false
    })

    override fun getLayout(): Int {
        return R.layout.activity_fg_renting_detail
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("房屋租赁详情")
        initData()
        initView()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {

        personInfoTv.text = entity!!.deptName + "  " + entity!!.deptName + "  " + entity!!.phone
        houseInfoTv.text = entity!!.address + "(" + entity!!.areaSize + "㎡，" + entity!!.price + "元)"

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
                Html.fromHtml(entity!!.publishContent, imgGetter, null)
            }

            val msg: Message = Message.obtain()
            msg.what = 183
            msg.obj = text
            mHandler.sendMessage(msg)

        }).start()
    }

    private fun initData() {
        entity = intent.getSerializableExtra("entity") as FgRentingEntity?
    }
}