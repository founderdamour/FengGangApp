package com.zkhy.community.view.main.zm

import android.os.Bundle
import android.view.View
import com.jiangyy.easydialog.CommonDialog
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.dialog.loading.LoadingDialog
import com.zkhy.community.MainActivity
import com.zkhy.community.R
import com.zkhy.community.model.api.BaseData
import com.zkhy.community.model.api.CommReq
import com.zkhy.community.model.api.CommResult
import com.zkhy.community.model.cache.DataCache
import com.zkhy.community.model.cache.KeyWord
import com.zkhy.community.presenter.ZMPresenter
import com.zkhy.community.view.comm.LoginActivity
import com.zkhy.community.view.mine.UserInfoSimpleEditActivity
import com.zkhy.library.base.activity.TitleBarBaseActivity
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.mvp.AndroidView
import kotlinx.android.synthetic.main.activity_zm_wish_home.*

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/11 on 16:26
 *  项目:  WuMinAndroid
 *  描述:  预约项目
 *  更新:
 * <pre>
 */
class WishHomeActivity : TitleBarBaseActivity(), View.OnClickListener, AndroidExt2View {

    private var presenter: ZMPresenter? = null

    override fun getLayout(): Int = R.layout.activity_zm_wish_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("微心愿", "发布") {

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
                return@setTitleBar
            } else {
                val needTip: Boolean = DataCache.getNeedTipInputUserInfo()

                if (needTip) {
                    CommonDialog.Builder(this)
                        .setTitle("重要提示")
                        .setCanceledOnTouchOutside(false)
                        .setMessage("\n继续使用应用前，需要完善\n\n用户的基本信息...\n")
                        .setPositiveButton("确定") {

                            IntentUtil.openActivity(this, UserInfoSimpleEditActivity::class.java)
                                .putIntExtra("fromType", 0)
                                .start()

                        }.setNegativeButton("取消") {
                        }.show()
                } else {
                    IntentUtil.openActivity(this, WishAddActivity::class.java).start()
                }
            }
        }

        if (MainActivity.getRoles().contains(KeyWord.ROLE_PARTY_MEMBER)) {
            item1Layout.visibility = View.VISIBLE
        } else {
            item1Layout.visibility = View.GONE
        }

        item0Layout.setOnClickListener(this)
        item1Layout.setOnClickListener(this)
        item2Layout.setOnClickListener(this)

        presenter = ZMPresenter(this)
    }

    override fun onResume() {
        super.onResume()
        val vo = CommReq()
        vo.userId = "0"
        presenter!!.loadWishTotal(vo)
    }

    override fun onClick(v: View?) {

        when (v) {
            item0Layout -> {
                IntentUtil.openActivity(this@WishHomeActivity, WishAllListActivity::class.java)
                    .start()
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

                IntentUtil.openActivity(this@WishHomeActivity, WishFromMeListActivity::class.java)
                    .start()
            }
            item2Layout -> {
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

                IntentUtil.openActivity(this@WishHomeActivity, WishCreateByMeListActivity::class.java)
                    .start()
            }
        }
    }

    override fun loadComplete(result: Any?) {
        val baseData = result as BaseData<CommResult>
        if (baseData.errcode == 0) {

            item0NumTv.text = baseData.data.wishCountApp.toString()
            item1NumTv.text = baseData.data.myClaim.toString()
            item2NumTv.text = baseData.data.myPublish.toString()
        } else {
            showTip(baseData.errmsg)
        }
    }

    override fun loadingShow() {
        LoadingDialog.show(this, "正在加载")
    }

    override fun loadingDismiss() {
        LoadingDialog.hidden()
    }

    override fun showTip(msg: String?) {
        ToastUtil.show(msg)
    }

    override fun load2Complete(resultData: Any?) {

    }
}
