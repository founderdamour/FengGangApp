package com.zkhy.fenggang.community.view.mine

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.StringUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.comm.utils.ViewUtil
import com.sinothk.dialog.loading.LoadingDialog
import com.sinothk.helper.image.compress.ImageCompress
import com.sinothk.image.selector.PhotoPickerActivity
import com.sinothk.image.selector.SelectModel
import com.sinothk.image.selector.intent.PhotoPickerIntent
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.api.BaseData
import com.zkhy.fenggang.community.model.bean.AttachmentEntity
import com.zkhy.fenggang.community.model.bean.WmUser
import com.zkhy.fenggang.community.model.cache.DataCache
import com.zkhy.fenggang.community.presenter.UserPresenter
import com.zkhy.fenggang.community.view.comm.AddressStreetListActivity
import com.zkhy.library.base.activity.TitleBarBaseActivity
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.utils.ImageLoader
import com.zkhy.library.utils.StringUtilExt
import kotlinx.android.synthetic.main.activity_user_info_simple_edit.*

/**
 * 用户信息完善
 */
open class UserInfoSimpleEditActivity : TitleBarBaseActivity(), AndroidExt2View, View.OnClickListener {
//    private var fromType = 1

    private var presenter: UserPresenter? = null

    private val REQUEST_CODE_ADDRESS = 102
    private val REQUEST_SINGLE_CODE = 104

    private var wmUser: WmUser? = null

    override fun getLayout(): Int = R.layout.activity_user_info_simple_edit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTitleBar("完善信息") {
            finish()
//            if (fromType == 0) {// 提示完善时进入时,退出则退出App
//                ActivityUtil.finishAll() // 关闭程序
//                DataCache.setAutoLogin(false) // 取消自动登录
//                // 重新登录
//                IntentUtil.openActivity(this, LoginActivity::class.java).start()
//            }
        }

        presenter = UserPresenter(this)
        wmUser = DataCache.getUserInfo()
        initView(wmUser)
        setOnClickListener()
    }

    private fun setOnClickListener() {
        avatarIv.setOnClickListener(this)
        userAreaItem.setOnClickListener(this)

        sexFemaleItem.setOnClickListener(this)
        sexMaleItem.setOnClickListener(this)

        submitBtn.setOnClickListener(this)
    }

    private fun initView(wmUser: WmUser?) {
        if (wmUser == null) {
            return
        }

        userNameEt.setText(StringUtil.getNotNullValue(wmUser.name))
        ViewUtil.focusMoveToEnd(userNameEt)
        userIDCardEt.setText(StringUtil.getNotNullValue(wmUser.idcard))
        ViewUtil.focusMoveToEnd(userIDCardEt)

        userAreaTv.text = StringUtil.getNotNullValue(wmUser.areaFullName)
        userAddressTv.setText(StringUtil.getNotNullValue(wmUser.addrDetail))
        ViewUtil.focusMoveToEnd(userAddressTv)

        ImageLoader.show(this, wmUser.photo, R.drawable.mr_tx, avatarIv)

        if (wmUser.sex == null || "0" == wmUser.sex) {
            changeSex(0)
        } else {
            changeSex(1)
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            avatarIv -> { // 图片选择
                val intent = PhotoPickerIntent(this@UserInfoSimpleEditActivity)
                intent.setSelectModel(SelectModel.SINGLE)
                intent.setShowCamera(true, "com.zkhy.fenggang.community") // 是否显示拍照， 默认false
                startActivityForResult(intent, REQUEST_SINGLE_CODE)
            }

            userAreaItem -> {
                IntentUtil.openActivity(this, AddressStreetListActivity::class.java)
                    .putStringExtra("id", "525629318659833921")
                    .requestCode(REQUEST_CODE_ADDRESS)
                    .start()
            }

            sexFemaleItem -> {
                changeSex(0)
            }

            sexMaleItem -> {
                changeSex(1)
            }

            submitBtn -> {
                submitUserInfo()
            }
        }
    }

    private fun changeSex(sex: Int) {
        when (sex) {
            0 -> {
                sexFemaleImage.setImageResource(R.drawable.mr033)
                sexMaleImage.setImageResource(R.drawable.mr01)
                sexFemaleTv.setTextColor(resources.getColor(R.color.colorAccent))
                sexMaleTv.setTextColor(resources.getColor(R.color.tc_list_tip))
                wmUser!!.sex = "0"
            }
            1 -> {
                sexFemaleImage.setImageResource(R.drawable.mr01)
                sexMaleImage.setImageResource(R.drawable.mr033)
                sexFemaleTv.setTextColor(resources.getColor(R.color.tc_list_tip))
                sexMaleTv.setTextColor(resources.getColor(R.color.colorAccent))
                wmUser!!.sex = "1"
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // 信息编辑
        infoEdit(requestCode, resultCode, data)

        // 图片选择
        if (requestCode == REQUEST_SINGLE_CODE && resultCode == Activity.RESULT_OK && data != null) {
            //图片选择返回
            val pathList = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT)
            submitImg(pathList)
        }
    }

    private fun infoEdit(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != 200 || data == null) {
            return
        }

        when (requestCode) {
            REQUEST_CODE_ADDRESS -> {
                val streetName = data.getStringExtra("streetName")
                val areaName = data.getStringExtra("areaName")
                userAreaTv.text = "$streetName$areaName"

//                val streetCode = data.getStringExtra("streetCode")
                val areaCode = data.getStringExtra("areaCode")
                if (StringUtil.isNotEmpty(areaCode)) {
                    wmUser!!.areaCode = areaCode
                }
            }
        }
    }

    private fun submitImg(pathList: ArrayList<String>?) {
        if (pathList == null || pathList.size == 0) {
            ToastUtil.show("图片选择失败，请重试")
            return
        }

        val path = pathList[0]
        try {
            ImageCompress.execute(path) { obj ->
                val newPath = if (obj != null) {
                    obj as String
                } else {
                    path
                }

                val bitmap = BitmapFactory.decodeFile(newPath)
                avatarIv.setImageBitmap(bitmap)

                presenter?.uploadFile("", "zm_wish_publish", newPath)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            ToastUtil.show("图片读取失败，请重试")
        }
    }

    override fun loadingShow() {
        LoadingDialog.show(this, "提交中")
    }

    override fun loadingDismiss() {
        LoadingDialog.hidden()
    }

    override fun showTip(msg: String?) {
        ToastUtil.show(msg)
        loadingDismiss()
    }

    override fun loadComplete(resultData: Any) {
        val newUser: WmUser? = resultData as WmUser
        if (newUser != null) {

            DataCache.setNeedTipInputUserInfo(false)

            DataCache.saveLoginUserInfo(newUser)
            finish()
        } else {
            showTip("用户信息返回有误")
        }
    }

    override fun load2Complete(resultData: Any?) {
        val result: BaseData<AttachmentEntity> = resultData as BaseData<AttachmentEntity>
        if (result.data !== null) {
            wmUser!!.photo = result.data.url
        }
    }

    private fun submitUserInfo() {
        val userName = userNameEt.text.toString()
        val userIDCard = userIDCardEt.text.toString()

        val userArea = userAreaTv.text.toString()
        val userAddress = userAddressTv.text.toString()

        if (StringUtil.isEmpty(userName)) {
            ToastUtil.show("姓名不能为空")
            return
        }

        if (StringUtil.isEmpty(userIDCard)) {
            ToastUtil.show("身份证号不能为空")
            return
        }

        if (!StringUtilExt.isIDNumber(userIDCard)) {
            ToastUtil.show("身份证号格式不对")
            return
        }

        if (StringUtil.isEmpty(userArea) || StringUtil.isEmpty(wmUser!!.areaCode)) {
            ToastUtil.show("社区及街道不能为空")
            return
        }

        wmUser!!.name = userName
        wmUser!!.idcard = userIDCard

        wmUser!!.areaFullName = userArea
        wmUser!!.addrDetail = userAddress
        wmUser!!.isAppIs = true

        presenter!!.updateUserInfo(wmUser!!)
    }
}
