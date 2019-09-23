package com.zkhy.community.view.main.dj

import android.os.Bundle
import android.text.Html
import android.view.View
import com.sinothk.comm.utils.StringUtil
import com.zkhy.community.R
import com.zkhy.community.model.bean.WechatEntity
import com.zkhy.library.base.activity.StatusViewTitleBarActivity
import com.zkhy.library.widget.TitleBarViewCreator
import kotlinx.android.synthetic.main.activity_propaganda_sys_detail.*


/**
 * <pre>
 *  创建:  梁玉涛 2019/5/28 on 16:28
 *  项目: WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
class PropagandaSysDetailActivity : StatusViewTitleBarActivity() {

    private var wechatEntity: WechatEntity? = null

    override fun loadData() {
    }

    override fun getTitleBarView(): View = TitleBarViewCreator.createTitleLC(this, "详情")

    override fun getContentLayoutId(): Int = R.layout.activity_propaganda_sys_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        wechatEntity = intent.getSerializableExtra("data") as WechatEntity

        titleTv.text = StringUtil.getNotNullValue(wechatEntity?.title, "无题")

        val content = StringUtil.getNotNullValue(wechatEntity?.title, "暂无内容")
        val charSequence: CharSequence = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(content)
        }
        contentTv.text = charSequence
    }
}