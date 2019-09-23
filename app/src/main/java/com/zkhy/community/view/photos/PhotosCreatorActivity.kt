package com.zkhy.community.view.photos

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.jiangyy.easydialog.CommonDialog
import com.sinothk.comm.utils.NetUtil
import com.sinothk.comm.utils.StringUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.dialog.loading.LoadingDialog
import com.sinothk.image.selector.PhotoPreviewActivity
import com.sinothk.map.amap.location.AMapLocationEntity
import com.sinothk.map.amap.location.MapLocationHelper
import com.zkhy.community.R
import com.zkhy.community.model.api.BaseData
import com.zkhy.community.model.bean.BizType
import com.zkhy.community.model.bean.ImgSelectEntity
import com.zkhy.community.model.bean.PhotosCreatorVo
import com.zkhy.community.presenter.PhotosCreatorPresenter
import com.zkhy.community.view.photos.adapter.AppImageSelectedShowAdapter
import com.zkhy.library.mvp.AndroidView
import kotlinx.android.synthetic.main.activity_photos_creator.*

/**
 * <pre>
 *  创建:  梁玉涛 2019/3/24 on 14:27
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
open class PhotosCreatorActivity : PhotosCreatorBaseActivity(), AndroidView {

    private var no1ImgPath = ""

    private val pCreatorVo = PhotosCreatorVo()
    private var locationInfo: AMapLocationEntity? = null

    var presenter: PhotosCreatorPresenter? = null

    override fun getLayout(): Int = R.layout.activity_photos_creator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("发布随手拍", "发送") {
            submit()
        }

        no1ImgPath = intent.getStringExtra("no1ImgPath")
        bizId = ""
        upLoad(bizId, BizType.WU_BM_SHOT_QUESTION, no1ImgPath)

        presenter = PhotosCreatorPresenter(this)

        locRefresh.setOnClickListener {
            refreshLoc(true)
        }

        refreshLoc(false)

        setImg()
    }

    private fun refreshLoc(tip: Boolean) {
        if (tip) {
            LoadingDialog.show(this, "获取位置中...")
        }

        MapLocationHelper.with(this).location { locInfo ->
            locationInfo = locInfo
            if (locInfo.code == 0) {
                locationInfoTv.text = StringUtil.getNotNullValue(locInfo.address, "无位置信息")
            } else {
                locationInfoTv.text = StringUtil.getNotNullValue("获取失败")
            }

            if (tip) {
                LoadingDialog.hidden()
            }
        }
    }

    private fun submit() {
        if (!NetUtil.isConnected(this)) {
            ToastUtil.show(R.string.net_error)
            return
        }

        if (StringUtil.isEmpty(bizId)) {
            ToastUtil.show("请上传文件")
            return
        }

        val addressStr: String = describeTv.text.toString()
        if (StringUtil.isEmpty(addressStr)) {
            ToastUtil.show("请输入描述内容")
            return
        }

        if (locationInfo == null) {
            ToastUtil.show("请先刷新位置信息后提交")
            return
        }

        pCreatorVo.longitude = locationInfo!!.longitude
        pCreatorVo.latitude = locationInfo!!.latitude
        pCreatorVo.questionAddress = locationInfo!!.address

        pCreatorVo.id = bizId.toLong()

        pCreatorVo.questionDesc = addressStr
        pCreatorVo.areaCode = "shljd_wjsq"

        presenter?.submit(pCreatorVo)
    }

    override fun loadingShow() {
        LoadingDialog.show(this, "数据提交中...")
    }

    override fun loadingDismiss() {
        LoadingDialog.hidden()
    }

    override fun showTip(msg: String?) {
        ToastUtil.show(msg)
    }

    override fun loadComplete(result: Any?) {
        val resultData: BaseData<Boolean> = result as BaseData<Boolean>

        if (resultData.errcode == 0) {
            showTip("发送成功")

            finish()
        } else {
            showTip(resultData.errmsg)
        }
    }

    private var mAlbumSelectedShowAdapter: AppImageSelectedShowAdapter? = null

    private fun setImg() {
        mAlbumSelectedShowAdapter = AppImageSelectedShowAdapter(this, 6)
        mRvAlbumSelected.layoutManager = GridLayoutManager(this, 4)
        mRvAlbumSelected.setHasFixedSize(true)
        mRvAlbumSelected.adapter = mAlbumSelectedShowAdapter

        mAlbumSelectedShowAdapter!!.setOnItemClickListener { _, position ->
            if (imgSelectList.size == position) {
                openSysCamera(BizType.WU_BM_SHOT_QUESTION)
            } else {
                //图片展示界面
                val imgList: ArrayList<String> = ArrayList()
                for (imgEntity: ImgSelectEntity in imgSelectList) {
                    imgList.add(imgEntity.filePath)
                }
                PhotoPreviewActivity.start(this@PhotosCreatorActivity, position, imgList)
            }
        }

        mAlbumSelectedShowAdapter!!.setItemDelClick { position ->
            dialog(position)
        }
    }

    override fun refreshImgView() {
        mAlbumSelectedShowAdapter?.setData(imgSelectList)
    }

    /*
     * Dialog对话框提示用户删除操作
     * position为删除图片位置
     */
    protected fun dialog(position: Int) {
        CommonDialog.Builder(this)
            .setTitle("提示")
            .setMessage("确认移除已添加的图片吗？")
            .setPositiveButton("确认") {
                if (mAlbumSelectedShowAdapter!!.itemCount > 0) {
                    // 删除服务器文件
                    delFileById(imgSelectList[position].id)
                    // 移除展示数据
                    imgSelectList.removeAt(position)
                    // 刷新界面
                    mAlbumSelectedShowAdapter?.setData(imgSelectList)
                    mAlbumSelectedShowAdapter?.notifyDataSetChanged()
                }
            }.setNegativeButton("取消") {

            }.show()
    }
}