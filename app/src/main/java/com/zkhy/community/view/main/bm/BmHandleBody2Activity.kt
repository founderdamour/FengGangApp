package com.zkhy.community.view.main.bm

import android.os.Bundle
import com.zkhy.community.R
import com.zkhy.community.model.bean.BizType
import com.zkhy.library.base.activity.TitleBarBaseActivity

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/12 on 9:23
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
class BmHandleBody2Activity : BmHandleBodyActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("二孩生育登记")

        setFlowId(BizType.FlowType.FLOW_3)
    }
}
