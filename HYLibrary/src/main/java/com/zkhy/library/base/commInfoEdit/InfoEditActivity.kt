package com.zkhy.library.base.commInfoEdit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.sinothk.comm.utils.StringUtil
import com.sinothk.comm.utils.ViewUtil
import com.zkhy.library.R
import com.zkhy.library.base.activity.TitleBarBaseActivity
import com.zkhy.library.utils.AViewUtil
import com.zkhy.library.utils.StringUtilExt
import kotlinx.android.synthetic.main.activity_info_edit.*

class InfoEditActivity : TitleBarBaseActivity() {

    private var oldValue = ""

    companion object {
        fun start(mActivity: Activity, keyValue: KeyValueEntity) {
            val intent = Intent(mActivity, InfoEditActivity::class.java)

            val bundle = Bundle()
            bundle.putSerializable("keyValue", keyValue)
            intent.putExtras(bundle)

            mActivity.startActivityForResult(intent, keyValue.requestCode)
        }
    }

    override fun getLayout(): Int = R.layout.activity_info_edit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 获得传入值
        val keyValue: KeyValueEntity = intent.getSerializableExtra("keyValue") as KeyValueEntity

        setTitleBar(StringUtil.getNotNullValue(keyValue.title, "编辑"), "保存") {

            val value: String = inputBoxEt.text.toString()
            if (oldValue != value) {
                val intent = intent
                intent.putExtra("value", value)
                setResult(200, intent)
            }
            finish()
        }

        // 内容
        oldValue = StringUtil.getNotNullValue(keyValue.content)
        if (StringUtil.isNotEmpty(oldValue)) {
            inputBoxEt.setText(oldValue)
            ViewUtil.focusMoveToEnd(inputBoxEt)
        }

        StringUtilExt.setEditTextInhibitInputSpaChat(inputBoxEt)

        // 为空提示输入
        inputBoxEt.hint = keyValue.inputHint

        // 输入提示
        if (StringUtil.isNotEmpty(keyValue.inputTip)) {
            inputTipTv.text = keyValue.inputTip
            inputTipTv.visibility = View.VISIBLE
        } else {
            inputTipTv.visibility = View.GONE
        }

        // 输入字数控制
        if (keyValue.maxSize > 0) {
            inputMaxTipTv.visibility = View.VISIBLE

            val currSize: Int = inputBoxEt.text.length
            val maxSize: Int = keyValue.maxSize

            inputMaxTipTv.text = "$currSize/$maxSize"

            AViewUtil.inputMaxTip(this, inputBoxEt, inputMaxTipTv, keyValue.maxSize, "最多输入" + keyValue.maxSize + "字符")
        } else {
            inputMaxTipTv.visibility = View.GONE
        }
    }
}
