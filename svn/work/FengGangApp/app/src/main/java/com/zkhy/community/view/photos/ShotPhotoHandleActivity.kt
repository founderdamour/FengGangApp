package com.zkhy.community.view.photos

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import com.sinothk.comm.utils.NetUtil
import com.sinothk.comm.utils.StringUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.dialog.loading.LoadingDialog
import com.sinothk.image.selector.PhotoPreviewActivity
import com.zkhy.community.R
import com.zkhy.community.model.api.BaseData
import com.zkhy.community.model.bean.BizType
import com.zkhy.community.model.bean.ImgSelectEntity
import com.zkhy.community.model.bean.PhotosCreatorVo
import com.zkhy.community.presenter.PhotosCreatorPresenter
import com.zkhy.community.view.photos.adapter.AppImageSelectedShowAdapter
import com.zkhy.library.mvp.AndroidView
import kotlinx.android.synthetic.main.activity_photos_handle.*
import com.jiangyy.easydialog.CommonDialog


/**
 * <pre>
 *  创建:  梁玉涛 2019/3/24 on 14:27
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
open class ShotPhotoHandleActivity : PhotosCreatorBaseActivity(), AndroidView {
    var dealTaskId = ""
    private val pCreatorVo = PhotosCreatorVo()
    var presenter: PhotosCreatorPresenter? = null

    override fun getLayout(): Int = R.layout.activity_photos_handle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("提交处理情况", "发送") {
            submit()
        }

        bizId = ""
        dealTaskId = intent.getStringExtra("dealTaskId")

        presenter = PhotosCreatorPresenter(this)

        setImg()
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

        pCreatorVo.dealTaskId = dealTaskId.toLong()
        pCreatorVo.imgId = bizId.toLong()

        val doneDescStr: String = describeTv.text.toString()
        pCreatorVo.doneDesc = StringUtil.getNotNullValue(doneDescStr)

        presenter?.submitHandle(pCreatorVo)
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

    override fun loadComplete(resultData: Any?) {
        val resultData: BaseData<Boolean> = resultData as BaseData<Boolean>

        if (resultData.errcode == 0) {
            showTip("发送成功")

            finish()
        } else {
            showTip(resultData.errmsg)
        }
    }

    /**
     * 选图部分
     */
    private var mAlbumSelectedShowAdapter: AppImageSelectedShowAdapter? = null

    private fun setImg() {
        mAlbumSelectedShowAdapter = AppImageSelectedShowAdapter(this, 6)
        mRvAlbumSelected.layoutManager = GridLayoutManager(this, 4)
        mRvAlbumSelected.setHasFixedSize(true)
        mRvAlbumSelected.adapter = mAlbumSelectedShowAdapter

        mAlbumSelectedShowAdapter!!.setOnItemClickListener { _, position ->
            if (imgSelectList.size == position) {
                openSysCamera(BizType.WU_BM_SHOT_WORKSCENEDEAL)
            } else {
                //图片展示界面

                val imgList: ArrayList<String> = ArrayList()
                for (imgEntity: ImgSelectEntity in imgSelectList) {
                    imgList.add(imgEntity.filePath)
                }
                PhotoPreviewActivity.start(this@ShotPhotoHandleActivity, position, imgList)
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