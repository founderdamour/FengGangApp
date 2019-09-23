package com.zkhy.community.view.main.zm

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.sinothk.comm.utils.StatusBarUtil
import com.sinothk.comm.utils.StringUtil
import com.sinothk.view.status.base.StatusViewBaseActivity
import com.sinothk.view.status.statusViews.StatusView
import com.zkhy.community.R
import com.zkhy.community.model.api.ServerConfig
import com.zkhy.community.model.bean.WishEntity
import com.zkhy.community.model.cache.DataCache
import com.zkhy.library.utils.ImageLoader
import com.zkhy.library.widget.TitleBarViewCreator
import kotlinx.android.synthetic.main.activity_wish_claim_user_info.*

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/11 on 16:26
 *  项目:  WuMinAndroid
 *  描述:  认领人信息
 *  更新:
 * <pre>
 */
class ClaimUserInfoActivity : StatusViewBaseActivity() {

    private var wishEntity: WishEntity? = null

    override fun getContentRetryListener(): View.OnClickListener {
        return View.OnClickListener {
            StatusView.showLoading("记载中...")
            refreshView()
        }
    }

    override fun getTitleBarView(): View = TitleBarViewCreator.createTitleLC(this, "认领好心人")

    override fun getContentLayoutId(): Int = R.layout.activity_wish_claim_user_info

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.transparencyBar(this)

        StatusView.showLoading("记载中...")
        refreshView()
    }

    private fun refreshView() {
        wishEntity = intent.getSerializableExtra("entity") as WishEntity?
        if (wishEntity != null) {
            initView(wishEntity!!)
        } else {
            StatusView.showEmptyData("暂无数据")
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initView(wishEntity: WishEntity) {
        StatusView.showContent()

        ImageLoader.show(this, wishEntity.claimPhoto, R.drawable.mr_tx, imageView)

        userNameTv.text = StringUtil.getNotNullValue(wishEntity.claimUserName, "")
        userAgeTv.text = "${wishEntity.age}岁"

        partyDeptTv.text = StringUtil.getNotNullValue(wishEntity.orgName, "")
        claimTimeTv.text = StringUtil.getNotNullValue(wishEntity.claimTime, "")
        loveNumTv.text = "${wishEntity.loCount}次"

        val phoneNUm = wishEntity.claimPhone
        if (!TextUtils.isEmpty(phoneNUm) && phoneNUm.length == 11) {

            val currUser = DataCache.getUserInfo()
            if (currUser.userId == wishEntity.applyUserId || currUser.userId == wishEntity.claimUserId) {
                userPhoneTv.text = phoneNUm
            } else {
                userPhoneTv.text = "${phoneNUm.substring(0, 7)}****"
            }
        } else {
            userPhoneTv.text = "手机号有误"
        }
    }
}
