package com.zkhy.community.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jiangyy.easydialog.CommonDialog
import com.sinothk.comm.utils.AppUtil
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.StringUtil
import com.sinothk.comm.utils.ToastUtil
import com.zkhy.community.MainActivity
import com.zkhy.community.R
import com.zkhy.community.model.bean.WmUser
import com.zkhy.community.model.cache.DataCache
import com.zkhy.community.model.cache.KeyWord
import com.zkhy.community.view.comm.LoginActivity
import com.zkhy.community.view.comm.SettingActivity
import com.zkhy.community.view.main.bm.BmHandleHistoryListActivity
import com.zkhy.community.view.main.zm.WishFromMeListActivity
import com.zkhy.community.view.mine.UserInfoSimpleEditActivity
import com.zkhy.community.view.photos.MyHandlePhotoListActivity
import com.zkhy.community.view.photos.MyShotPhotoListActivity
import com.zkhy.library.utils.ImageLoader
import kotlinx.android.synthetic.main.fragment_mine.*

class MineFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mine, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClickListener()
    }

    private fun setClickListener() {
        imageView.setOnClickListener(this)
        settingBtn.setOnClickListener(this)

        noticeItem.setOnClickListener(this)
        collectionItem.setOnClickListener(this)
        feedbackItem.setOnClickListener(this)
        myClaimItem.setOnClickListener(this)

        appUpdateItem.setOnClickListener(this)

        myHandleItem.setOnClickListener(this)
        myShortPhotoItem.setOnClickListener(this)

        logoutBtn.setOnClickListener(this)
        loginBtn.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        showView()
    }

    @SuppressLint("SetTextI18n")
    private fun showView() {

        if (DataCache.isAutoLogin()) {

            haveDataView.visibility = View.VISIBLE
            noDataView.visibility = View.GONE

            val userInfo: WmUser = DataCache.getUserInfo()
            userNameTv.text = if (StringUtil.isNotEmpty(userInfo.name)) {
                userInfo.name
            } else {
                userInfo.account
            }

            userLocationTv.text = StringUtil.getNotNullValue(userInfo.areaName, "本地")
            ImageLoader.show(context, userInfo.photo, R.drawable.mr_tx, imageView)

            // 版本更新提示
            newVersionTip.visibility = if (DataCache.getNewVersion()) {
                View.VISIBLE
            } else {
                View.GONE
            }

            val versionName: String = AppUtil.getAppVersionName()
            versionValueTv.text = "v $versionName"

            if (MainActivity.getRoles().contains(KeyWord.ROLE_SHOT_WORK_MAN)) {
                myHandleItem.visibility = View.VISIBLE
            } else {
                myHandleItem.visibility = View.GONE
            }
        } else {
            haveDataView.visibility = View.GONE
            noDataView.visibility = View.VISIBLE
        }
    }

    override fun onClick(v: View?) {

        when (v) {
            imageView -> {
                IntentUtil.openActivity(activity, UserInfoSimpleEditActivity::class.java)
                        .putIntExtra("fromType", 1)
                        .start()
            }

            noticeItem -> {
                ToastUtil.show("功能完善中")
            }

            myClaimItem -> {
                if (!DataCache.isAutoLogin()) {
                    CommonDialog.Builder(activity)
                            .setTitle("登录提示")
                            .setCanceledOnTouchOutside(false)
                            .setMessage(resources.getString(R.string.login_tip))
                            .setPositiveButton("登录") {
                                // 前往登录
                                IntentUtil.openActivity(activity, LoginActivity::class.java).start()
                            }.setNegativeButton("取消") {
                            }.show()
                    return
                }

                IntentUtil.openActivity(activity, WishFromMeListActivity::class.java)
                        .start()
            }

            collectionItem -> {
                if (!DataCache.isAutoLogin()) {
                    CommonDialog.Builder(activity)
                            .setTitle("登录提示")
                            .setCanceledOnTouchOutside(false)
                            .setMessage(resources.getString(R.string.login_tip))
                            .setPositiveButton("登录") {
                                // 前往登录
                                IntentUtil.openActivity(activity, LoginActivity::class.java).start()
                            }.setNegativeButton("取消") {
                            }.show()
                    return
                }

                IntentUtil.openActivity(activity, BmHandleHistoryListActivity::class.java)
                        .start()
            }
            feedbackItem -> {
                ToastUtil.show("功能完善中")
            }
            appUpdateItem -> {
                (activity as MainActivity).checkNewVersion(activity, true)
            }
            settingBtn -> {
                IntentUtil.openActivity(activity, SettingActivity::class.java).startInFragment(this)
            }

            myHandleItem -> {
                IntentUtil.openActivity(activity, MyHandlePhotoListActivity::class.java).startInFragment(this)
            }

            myShortPhotoItem -> {
                IntentUtil.openActivity(activity, MyShotPhotoListActivity::class.java).startInFragment(this)
            }

            loginBtn -> { // 游客点击登录
//                ActivityUtil.finishAll() // 关闭程序
//                DataCache.setAutoLogin(false) // 取消自动登录
                // 重新登录
                IntentUtil.openActivity(activity, LoginActivity::class.java).start()
            }

            logoutBtn -> {
                DataCache.setAutoLogin(false)

                DataCache.clearAll()

                showView()
            }
        }
    }
}
