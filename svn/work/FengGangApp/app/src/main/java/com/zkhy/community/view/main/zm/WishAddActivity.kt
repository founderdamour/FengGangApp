package com.zkhy.community.view.main.zm

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.StringUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.dialog.loading.LoadingDialog
import com.sinothk.helper.image.compress.ImageCompress
import com.sinothk.image.selector.PhotoPickerActivity
import com.sinothk.image.selector.SelectModel
import com.sinothk.image.selector.intent.PhotoPickerIntent
import com.zkhy.community.R
import com.zkhy.community.model.api.BaseData
import com.zkhy.community.model.api.CommReq
import com.zkhy.community.model.bean.AttachmentEntity
import com.zkhy.community.model.cache.DataCache
import com.zkhy.community.presenter.ZMPresenter
import com.zkhy.community.view.comm.AddressStreetListActivity
import com.zkhy.library.base.activity.TitleBarBaseActivity
import com.zkhy.library.base.commInfoEdit.InfoEditActivity
import com.zkhy.library.base.commInfoEdit.KeyValueEntity
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.utils.CommUtil
import kotlinx.android.synthetic.main.activity_wish_add.*
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/11 on 16:26
 *  项目:  WuMinAndroid
 *  描述:  创建心愿
 *  更新:
 * <pre>
 */
class WishAddActivity : TitleBarBaseActivity(), View.OnClickListener, AndroidExt2View {

    private var id: String? = ""
    private var photoUrl: String? = ""

    private val REQUEST_CODE_NAME = 100
    private val REQUEST_CODE_PHONE = 101
    private val REQUEST_CODE_ADDRESS = 102
    private val REQUEST_CODE_CONTENT = 103
    private val REQUEST_SINGLE_CODE = 104

    private var presenter: ZMPresenter? = null

    override fun getLayout(): Int = R.layout.activity_wish_add

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("发布微心愿", "提交") {
            submit()
        }

        presenter = ZMPresenter(this)

        imgItem.setOnClickListener(this)
        userNameItem.setOnClickListener(this)
        userPhoneItem.setOnClickListener(this)
        userAddressItem.setOnClickListener(this)
        contentItem.setOnClickListener(this)

        val currUser = DataCache.getUserInfo()

        userNameTv.text = StringUtil.getNotNullValue(currUser.name, "")
        userPhoneTv.text = StringUtil.getNotNullValue(currUser.phone, "")

        streetCode = currUser.streetCode
        areaCode = currUser.areaCode
        userAddressValue.text = StringUtil.getNotNullValue(currUser.areaFullName, "")
    }

    override fun onClick(v: View?) {

        when (v) {
            userNameItem -> {
                val keyValue = KeyValueEntity()
                keyValue.title = "许愿人姓名"
                keyValue.content = userNameTv.text.toString()
                keyValue.inputHint = "请输入许愿人姓名"
                keyValue.minSize = 2
                keyValue.maxSize = 10
                keyValue.requestCode = REQUEST_CODE_NAME
                InfoEditActivity.start(this, keyValue)
            }

            userPhoneItem -> {
                val keyValue = KeyValueEntity()
                keyValue.title = "许愿人电话"
                keyValue.content = userPhoneTv.text.toString()
                keyValue.inputHint = "请输入许愿人电话"
                keyValue.minSize = 0
                keyValue.maxSize = 11
                keyValue.requestCode = REQUEST_CODE_PHONE
                InfoEditActivity.start(this, keyValue)
            }
            userAddressItem -> {
                IntentUtil.openActivity(this, AddressStreetListActivity::class.java)
                    .putStringExtra("id", "525629318659833921")
                    .requestCode(REQUEST_CODE_ADDRESS)
                    .start()
            }

            contentItem -> {
                val keyValue = KeyValueEntity()
                keyValue.title = "许愿内容"
                keyValue.content = contentValue.text.toString()
                keyValue.inputHint = "请输入许愿内容描述"
                keyValue.minSize = 2
                keyValue.maxSize = 500
                keyValue.requestCode = REQUEST_CODE_CONTENT
                InfoEditActivity.start(this, keyValue)
            }

            imgItem -> { // 图片选择
                val intent = PhotoPickerIntent(this@WishAddActivity)
                intent.setSelectModel(SelectModel.SINGLE)
                intent.setShowCamera(true, "com.zkhy.community") // 是否显示拍照， 默认false
                startActivityForResult(intent, REQUEST_SINGLE_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 信息编辑
        infoEdit(requestCode, resultCode, data)

        // 图片选择
        if (resultCode == Activity.RESULT_OK && data != null) {
            //图片选择返回
            if (requestCode == REQUEST_SINGLE_CODE) {
                val pathList = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT)
                submitImg(pathList)
            }
        }
    }

    private fun infoEdit(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != 200 || data == null) {
            return
        }

        when (requestCode) {
            REQUEST_CODE_NAME -> {
                val valueStr = data.getStringExtra("value")
                userNameTv.text = valueStr
            }
            REQUEST_CODE_PHONE -> {
                val valueStr = data.getStringExtra("value")
                userPhoneTv.text = valueStr
            }
            REQUEST_CODE_ADDRESS -> {
                val streetName = data.getStringExtra("streetName")
                val areaName = data.getStringExtra("areaName")

                userAddressValue.text = "$streetName$areaName"

                areaCode = data.getStringExtra("areaCode")
                streetCode = data.getStringExtra("streetCode")
            }
            REQUEST_CODE_CONTENT -> {
                val valueStr = data.getStringExtra("value")
                contentValue.text = valueStr
            }
        }
    }

    private var streetCode = ""
    private var areaCode = ""

    private fun submit() {
        val name = userNameTv.text.toString()
        val phoneNum = userPhoneTv.text.toString()
        val content = contentValue.text.toString()

        if (StringUtil.isEmpty(name) || name.length < 2 || name.length > 30) {
            ToastUtil.show("请正确许愿人姓名")
            return
        }

        if (StringUtil.isEmpty(phoneNum) || phoneNum.length != 11 || !CommUtil.checkPhoneNum(phoneNum)) {
            ToastUtil.show("请正确许愿人手机号")
            return
        }

        if (StringUtil.isEmpty(areaCode)) {
            ToastUtil.show("请选择许愿人地址")
            return
        }

        if (StringUtil.isEmpty(content.trim())) {
            ToastUtil.show("请填写许愿内容")
            return
        }

        if (!isHanNumLetterPunctuationChar(content.trim())) {
            ToastUtil.show("许愿内容不能含有特殊符号")
            return
        }

        if (StringUtil.isEmpty(id)) {
            ToastUtil.show("请上传封面图片")
            return
        }

        val vo = CommReq()
        vo.id = id
        vo.photo = photoUrl

        vo.applyName = name
        vo.applyPhone = phoneNum
        vo.wishContent = content

        vo.streetCode = streetCode
        vo.areaCode = areaCode

        presenter?.publicWish(vo)
    }

    override fun loadingShow() {
        LoadingDialog.show(this, "正在提交资料")
    }

    override fun loadingDismiss() {
        LoadingDialog.hidden()
    }

    override fun showTip(msg: String?) {
        ToastUtil.show(msg)
        LoadingDialog.hidden()
    }

    override fun loadComplete(resultData: Any?) {
        val result = resultData as BaseData<Boolean>
        if (result.errcode == 0) {
            if (result.data) {
                showTip("发布成功")
                finish()
            } else {
                showTip(result.errmsg)
            }
        } else {
            showTip(result.errmsg)
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
                imgItem.setImageBitmap(bitmap)

                presenter?.uploadFile("", "zm_wish_publish", newPath)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            ToastUtil.show("图片读取失败，请重试")
        }
    }

    override fun load2Complete(resultData: Any?) {
        val result: BaseData<AttachmentEntity> = resultData as BaseData<AttachmentEntity>
        if (result.data !== null) {
            id = result.data.bizId
            photoUrl = result.data.url
        }
    }

    private fun isHanNumLetterPunctuationChar(str: String): Boolean {// 数字，字母，汉字，标点
        val pas = "^[\\u4E00-\\u9FA5A-Za-z0-9_，。？,.?!！、`~@<>《》｛｝{}()\\[\\]【】'‘’“”;；：:]+\$"
        val p: Pattern = Pattern.compile(pas)
        val m: Matcher = p.matcher(str)
        return m.matches()
    }
}
