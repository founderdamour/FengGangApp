package com.zkhy.community.view.photos

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.sinothk.comm.utils.DateUtil
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.StringUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.dialog.loading.LoadingDialog
import com.sinothk.image.selector.PhotoPreviewActivity
import com.sinothk.view.status.statusViews.StatusView
import com.zkhy.comm.plugin.activity.WebPageActivity
import com.zkhy.comm.plugin.activity.WebPageTempActivity
import com.zkhy.community.R
import com.zkhy.community.model.api.BaseData
import com.zkhy.community.model.bean.AttachmentEntity
import com.zkhy.community.model.bean.BizType
import com.zkhy.community.model.bean.BmShotQuestionInfoDTO
import com.zkhy.community.presenter.PhotosCreatorPresenter
import com.zkhy.community.view.photos.adapter.WuMinAppNineGridAdapter
import com.zkhy.library.base.activity.StatusViewTitleBarActivity
import com.zkhy.library.mvp.AndroidView
import com.zkhy.library.utils.ImageLoader
import com.zkhy.library.widget.TitleBarViewCreator
import kotlinx.android.synthetic.main.activity_shot_photo_detail.*

/**
 * <pre>
 *  创建:  梁玉涛 2019/3/24 on 22:07
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
class ShotPhotoDetailActivity : StatusViewTitleBarActivity(), AndroidView {

    var presenter: PhotosCreatorPresenter? = null

    override fun getTitleBarView(): View = TitleBarViewCreator.createTitleLC(this, "处理详情")

//    override fun getTitleBarView(): View = TitleBarViewCreator.createTitleLCR(this, "处理详情", "视频") {
////        IntentUtil.openActivity(this@ShotPhotoDetailActivity, WebPageTempActivity::class.java).start()
//
//        val intent = Intent()
//        intent.action = "android.intent.action.VIEW"
//        val uri = Uri.parse("https://47.106.221.149/test/?2")
//        intent.data = uri
//        startActivity(intent)
//    }

    override fun getContentLayoutId(): Int = R.layout.activity_shot_photo_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = PhotosCreatorPresenter(this)
        loadData()
    }

    override fun loadData() {
        val id = intent.getStringExtra("id")
        presenter?.loadShotPhotoDetailData(id)
    }

    override fun loadingShow() {
        StatusView.showLoading("加载中")
    }

    override fun loadingDismiss() {
    }

    override fun showTip(msg: String?) {
        StatusView.showError(msg)
    }

    override fun loadComplete(resultData: Any?) {
        val result: BaseData<BmShotQuestionInfoDTO> = resultData as BaseData<BmShotQuestionInfoDTO>
        if (result.errcode != 0 || result.data == null) {
            showTip(result.errmsg)
        } else {
            StatusView.showContent()
            showView(result.data)
        }
    }

    private fun showView(data: BmShotQuestionInfoDTO) {
        ImageLoader.show(this, data.photo, R.drawable.mr_tx, userAvatarIv)
        userNameTv.text = StringUtil.getNotNullValue(data.userName, "匿名用户")
        timeTv.text = StringUtil.getNotNullValue(data.phone)

        contentTv.text = StringUtil.getNotNullValue(data.questionDesc)

        if (data.createTime != null) {
            val timeStr: String = DateUtil.getDateStrByDate(data.createTime, "yyyy-MM-dd HH:mm")
            submitTimeTv.text = timeStr
        }

        submitLocTv.text = StringUtil.getNotNullValue(data.questionAddress, "无位置信息 ...")

        if (data.attachments != null) {

            val contentImgList: List<AttachmentEntity>? = data.attachments[BizType.WU_BM_SHOT_QUESTION]

            if (contentImgList != null && contentImgList.isNotEmpty()) {

                val imgList: ArrayList<String> = ArrayList()
                for (imgEntity: AttachmentEntity in contentImgList) {
                    imgList.add(imgEntity.url)
                }

                val nineGridAdapter =
                    WuMinAppNineGridAdapter(this@ShotPhotoDetailActivity, imgList)
                nineGridView.nineGridAdapter = nineGridAdapter

                nineGridAdapter.setOnItemClickListener { position, urlOrFilePathList ->
                    PhotoPreviewActivity.start(this, position, urlOrFilePathList)
                }

                submitImgItem.visibility = View.VISIBLE
            } else {
                submitImgItem.visibility = View.GONE
            }
        }

        // 指派信息
        if (StringUtil.isEmpty(data.questionCode)) {
            zpItem.visibility = View.GONE
        } else {
            zpItem.visibility = View.VISIBLE
            zpContentTv.text = "您的问题反馈单编号 ${data.questionCode} ,已反馈到相关部门，请耐心等待 ..."

            if (data.createTime != null) {
                zpTimeTv.text = DateUtil.getDateStrByDate(data.createTime, "yyyy-MM-dd HH:mm")
                zpTimeTv.visibility = View.VISIBLE
            } else {
                zpTimeTv.visibility = View.GONE
            }

        }

        // 处理完成：处理状态(0待分派1进行中2已完成)
        if (data.status == 2) {
            zpItem.setBackgroundResource(R.color.tc_list_tip)
            zpPoint.setBackgroundResource(R.drawable.shape_point_flow_done)

            wcItem.visibility = View.VISIBLE

            wcContentTv.text = StringUtil.getNotNullValue(data.summary)
            if (data.doneTime != null) {
                wcTimeTv.text = DateUtil.getDateStrByDate(data.doneTime, "yyyy-MM-dd HH:mm")
                wcTimeTv.visibility = View.VISIBLE
            } else {
                wcTimeTv.visibility = View.GONE
            }

            if (data.attachments != null) {
                val wcContentImgList: List<AttachmentEntity>? = data.attachments[BizType.WU_BM_SHOT_REPLY]
                if (wcContentImgList != null && wcContentImgList.isNotEmpty()) {

                    val imgList: ArrayList<String> = ArrayList()
                    for (imgEntity: AttachmentEntity in wcContentImgList) {
                        imgList.add(imgEntity.url)
                    }

                    val done9GridAdapter =
                        WuMinAppNineGridAdapter(this@ShotPhotoDetailActivity, imgList)
                    done9GridView.nineGridAdapter = done9GridAdapter

                    done9GridAdapter.setOnItemClickListener { position, urlOrFilePathList ->
                        PhotoPreviewActivity.start(this, position, urlOrFilePathList)
                    }

                    doneImgItem.visibility = View.VISIBLE
                } else {
                    doneImgItem.visibility = View.GONE
                }
            }

            // 完事评分
            if (data.evaluateStar == null || data.evaluateStar == 0) {// 评论过不显示
                starAddView.visibility = View.VISIBLE
                starAddBtn.setOnClickListener {

                    StarViewDialog.create(this@ShotPhotoDetailActivity).setContent({ stars ->
                        StarViewDialog.closeDialog()
                        presenter?.evaluateService(this@ShotPhotoDetailActivity, data.id, stars)
                    }, {
                        StarViewDialog.closeDialog()
                    })
                }
            } else {
                starAddView.visibility = View.GONE
            }

        } else {
            wcItem.visibility = View.GONE
            zpItem.setBackgroundColor(Color.WHITE)
            zpPoint.setBackgroundResource(R.drawable.shape_point_flow_doing)

            starAddView.visibility = View.GONE
        }
    }

    fun loadingEvaluateShow() {
        LoadingDialog.show(this, "正在提交")
    }

    fun loadingEvaluateDismiss() {
        LoadingDialog.hidden()
    }

    fun loadEvaluateComplete(resultData: BaseData<Boolean>) {
        if (resultData.errcode == 0) {
            ToastUtil.show("评价成功")
            starAddView.visibility = View.GONE
        } else {
            ToastUtil.show(resultData.errmsg)
        }
    }
}