package com.zkhy.community.view.main.zm

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.jiangyy.easydialog.CommonDialog
import com.jiangyy.easydialog.InputDialog
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.StatusBarUtil
import com.sinothk.comm.utils.StringUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.dialog.loading.LoadingDialog
import com.sinothk.view.status.base.StatusViewBaseActivity
import com.sinothk.view.status.statusViews.StatusView
import com.zkhy.community.MainActivity
import com.zkhy.community.R
import com.zkhy.community.model.api.BaseData
import com.zkhy.community.model.api.CommReq
import com.zkhy.community.model.bean.WishEntity
import com.zkhy.community.model.bean.WmUser
import com.zkhy.community.model.cache.DataCache
import com.zkhy.community.model.cache.KeyWord
import com.zkhy.community.presenter.ZMExtPresenter
import com.zkhy.community.view.comm.LoginActivity
import com.zkhy.library.mvp.AndroidExt3View
import com.zkhy.library.utils.ImageLoader
import com.zkhy.library.widget.TitleBarViewCreator
import kotlinx.android.synthetic.main.activity_wish_details.*

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/11 on 16:26
 *  项目:  WuMinAndroid
 *  描述:  预约项目
 *  更新:
 * <pre>
 */
class WishDetailsActivity : StatusViewBaseActivity(), AndroidExt3View {

    var currUser: WmUser? = null

    override fun getContentRetryListener(): View.OnClickListener {
        return View.OnClickListener {
            StatusView.showLoading("记载中...")
            refreshView()
        }
    }

    override fun getTitleBarView(): View = TitleBarViewCreator.createTitleLC(this, "心愿详情")

    override fun getContentLayoutId(): Int = R.layout.activity_wish_details

    var presenter: ZMExtPresenter? = null

    var flag: Int = 0
    var id = ""
    var wishStatus = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.transparencyBar(this)

        flag = intent.getIntExtra("flag", 0)
        id = intent.getStringExtra("id")
        wishStatus = intent.getIntExtra("wishStatus", 0)

        currUser = DataCache.getUserInfo()

        presenter = ZMExtPresenter(this)

        StatusView.showLoading("记载中...")
        refreshView()
    }

    private fun refreshView() {
        val vo = CommReq()
        vo.id = id
        vo.status = wishStatus.toString()
        presenter?.loadWishDetails(vo)
    }

    @SuppressLint("SetTextI18n")
    private fun initView(wishEntity: WishEntity) {

        userNameTv.text = StringUtil.getNotNullValue(wishEntity.name, "")
        createTimeTv.text = StringUtil.getNotNullValue(wishEntity.createTime, "")
        phoneNumTv.text = StringUtil.getNotNullValue(wishEntity.phone, "")

        addressTv.text = StringUtil.getNotNullValue(wishEntity.street) +
                StringUtil.getNotNullValue(wishEntity.area)

        wishContentTv.text = StringUtil.getNotNullValue(wishEntity.wishContent, "")

//        val url = ServerConfig.baseUrl + "api/admin/file/find/zm_wish_publish/" + wishEntity.id
//        ImageLoader.show(this, url, R.drawable.wish_banner22, bannerIv)
        ImageLoader.show(this, wishEntity.photo, R.drawable.mr_tx, bannerIv)

        checkContentTv.text = StringUtil.getNotNullValue(wishEntity.apprSuggest, "同意")
        checkContentLayout.visibility = View.VISIBLE
        checkContentLine.visibility = View.VISIBLE

        completeContentTv.text = StringUtil.getNotNullValue(wishEntity.finshMessage, "祝一切顺利")
        completeContentLine.visibility = View.GONE
        completeContentLayout.visibility = View.GONE

        //flag 状态标识：0、心愿清单 1、我的认领 2、我的发布
        //wishStatus 状态： 0、待审核 1、待认领 2、进行中 3、已完成 4、已退回
        val wishStatus = wishEntity.wishStatus
        when (wishStatus) {
            0 -> {
                statusTv.text = "待审核"
                bottomView.visibility = View.GONE

                checkContentLayout.visibility = View.GONE
                checkContentLine.visibility = View.GONE
            }
            1 -> {
                statusTv.text = "待认领"
                bottomView.visibility = View.VISIBLE

                operationBtn1.visibility = View.VISIBLE
                operationBtn2.visibility = View.GONE
                operationBtn3.visibility = View.GONE

                if (MainActivity.getRoles().contains(KeyWord.ROLE_PARTY_MEMBER)) {
                    bottomView.visibility = View.VISIBLE
                } else {
                    bottomView.visibility = View.GONE
                }
            }
            2 -> {
                statusTv.text = "已认领"

                bottomView.visibility = View.VISIBLE
                operationBtn1.visibility = View.GONE
                operationBtn2.visibility = View.VISIBLE
                operationBtn3.visibility = View.GONE

                if (currUser?.userId.equals(wishEntity.claimUserId)) {// 我认领的
                    operationBtn3.visibility = View.VISIBLE
                    phoneNumTv.text = wishEntity.phone
                } else {
                    operationBtn3.visibility = View.GONE

                    if (!TextUtils.isEmpty(wishEntity.phone) && wishEntity.phone.length == 11) {
                        phoneNumTv.text = "${wishEntity.phone.substring(0, 7)}****"
                    } else {
                        phoneNumTv.text = "手机号有误"
                    }
                }
            }
            3 -> {
                statusTv.text = "已完成"

                bottomView.visibility = View.VISIBLE
                operationBtn1.visibility = View.GONE
                operationBtn2.visibility = View.VISIBLE
                operationBtn3.visibility = View.GONE

                completeContentLine.visibility = View.VISIBLE
                completeContentLayout.visibility = View.VISIBLE
            }
            4 -> {
                statusTv.text = "已退回"
                statusTv.setTextColor(Color.RED)

                bottomView.visibility = View.GONE

                checkContentTv.text = StringUtil.getNotNullValue(wishEntity.apprSuggest, "退回")
                checkContentTv.setTextColor(Color.RED)

                checkContentLayout.visibility = View.VISIBLE
                checkContentLine.visibility = View.VISIBLE
            }
        }

        operationBtn1.setOnClickListener {

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
                return@setOnClickListener
            }

            CommonDialog.Builder(this)
                .setTitle("重要提示")
                .setMessage("确认要认领实现这个心愿吗？")
                .setPositiveButton("确定") {
                    // status传给我1，完成传2
                    val vo = CommReq()
                    vo.id = wishEntity.id
                    vo.wishStatus = "1"
                    presenter?.claimWish(vo)
                }
                .setNegativeButton("取消", null).show()

        }

        operationBtn3.setOnClickListener {
            InputDialog.Builder(this)
                .setTitle("完成寄语")
                .setHint("有什么想说的？")
                .setLines(5)
                .setPositiveButton("确定") { view ->
                    // status传给我1，完成传2
                    val vo = CommReq()
                    vo.id = wishEntity.id
                    vo.wishStatus = "2"
                    vo.finshMessage = view.tag.toString()
                    presenter?.claimWish(vo)

                }.setNegativeButton("取消", null).show()
        }

        operationBtn2.setOnClickListener {
            // ToastUtil.show("认领人信息")
            IntentUtil.openActivity(this, ClaimUserInfoActivity::class.java)
                .putSerializableExtra("entity", wishEntity)
                .start()
        }
    }

    override fun loadingDismiss() {
        LoadingDialog.hidden()
    }

    override fun loadingShow() {
        LoadingDialog.show(this, "正在加载")
    }

    override fun showTip(msg: String?) {
        loadingDismiss()
        ToastUtil.show(msg)
    }

    override fun loadComplete(resultData: Any?) {
        // 获取详情
        val result = resultData as BaseData<WishEntity>
        if (result.errcode == 0) {
            if (result.data != null) {
                initView(result.data)
                StatusView.showContent()
            } else {
                StatusView.showEmptyData("暂无数据")
            }
        } else {
            StatusView.showError(result.errmsg)
        }
    }

    override fun load2Complete(resultData: Any?) {
        // 认领/完成返回
        val result = resultData as BaseData<Boolean>
        if (result.errcode == 0) {
            if (result.data) {
                showTip("操作成功")
                refreshView()
            } else {
                showTip(result.errmsg)
            }
        } else {
            showTip(result.errmsg)
        }
    }

    override fun load3Complete(resultData: Any?) {

    }
}
