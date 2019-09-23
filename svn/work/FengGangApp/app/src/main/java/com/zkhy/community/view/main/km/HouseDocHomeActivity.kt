package com.zkhy.community.view.main.km

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.jiangyy.easydialog.CommonDialog
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.ToastUtil
import com.zkhy.community.R
import com.zkhy.community.model.api.BaseData
import com.zkhy.community.model.bean.BizUserEntity
import com.zkhy.community.model.bean.OnlineUserEntity
import com.zkhy.community.model.cache.DataCache
import com.zkhy.community.presenter.KMPresenter
import com.zkhy.community.view.comm.LoginActivity
import com.zkhy.library.base.activity.TitleBarBaseActivity
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.mvp.AndroidView
import kotlinx.android.synthetic.main.activity_km_house_doc_home.*
import java.lang.Exception
import java.util.ArrayList

/**
 * 家庭医生
 */
open class HouseDocHomeActivity : TitleBarBaseActivity(), View.OnClickListener, AndroidExt2View {

    private var presenter: KMPresenter? = null

    override fun getLayout(): Int = R.layout.activity_km_house_doc_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("家庭医生")

        item0Layout.setOnClickListener(this)
        item1Layout.setOnClickListener(this)

        presenter = KMPresenter(this)
    }

    override fun onResume() {
        super.onResume()
        presenter?.getOnlineDocList()
    }

    override fun onClick(v: View?) {
        when (v) {
            item0Layout -> {
                IntentUtil.openActivity(this@HouseDocHomeActivity, HouseDocQAListActivity::class.java).start()
            }

            item1Layout -> {
                if (!DataCache.isAutoLogin()) {
                    CommonDialog.Builder(this)
                        .setTitle("登录提示")
                        .setCanceledOnTouchOutside(false)
                        .setMessage(resources.getString(R.string.login_tip))
                        .setPositiveButton("登录") {
                            // 前往登录
                            IntentUtil.openActivity(this, LoginActivity::class.java).start()
                        }.setNegativeButton("取消") {
                        }.show()
                    return
                }
                IntentUtil.openActivity(this@HouseDocHomeActivity, HouseDocQAHistoryActivity::class.java).start()
            }

//            doc0Item -> {
//                callPhone(0)
//            }
//
//            doc1Item -> {
//                callPhone(1)
//            }
//
//            doc2Item -> {
//                callPhone(2)
//            }
        }
    }

    private fun callPhone(i: Int) {
        try {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:18208425833"))
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
            ToastUtil.show("拨号功能不可用")
        }
    }

    override fun loadingShow() {
    }

    override fun loadingDismiss() {
    }

    override fun showTip(msg: String?) {
    }

    override fun loadComplete(result: Any?) {

        if (result == null) {
            return
        }

        val resultData: BaseData<ArrayList<BizUserEntity>>? = result as BaseData<ArrayList<BizUserEntity>>

        if (resultData == null || resultData.data == null || resultData.data.size == 0) {
            lawyerListItemView.visibility = View.GONE

            return
        } else {
            lawyerListItemView.visibility = View.VISIBLE

            val subList: List<BizUserEntity>?
            if (resultData.data.size > 3) {
                subList = resultData.data.subList(0, 3)
            } else {
                subList = resultData.data
            }

            lawyerListItemView.setData(this, subList)
            lawyerListItemView.setOnItemClickListener { id ->
                IntentUtil.openActivity(this@HouseDocHomeActivity, DocDetailActivity::class.java)
                    .putStringExtra("id",id)
                    .start()
            }
        }
    }

    override fun load2Complete(resultData: Any?) {
    }
}
