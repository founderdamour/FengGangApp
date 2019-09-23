//package com.zkhy.community.view.photos
//
//import android.os.Bundle
//import android.view.View
//import com.sinothk.comm.utils.DateUtil
//import com.sinothk.comm.utils.IntentUtil
//import com.sinothk.comm.utils.StringUtil
//import com.sinothk.image.selector.PhotoPreviewActivity
//import com.sinothk.view.status.statusViews.StatusView
//import com.zkhy.community.R
//import com.zkhy.community.model.api.BaseData
//import com.zkhy.community.model.bean.AttachmentEntity
//import com.zkhy.community.model.bean.BizType
//import com.zkhy.community.model.bean.BmShotQuestionInfoDTO
//import com.zkhy.community.model.bean.BmShotTaskInfoDTO
//import com.zkhy.community.presenter.PhotosCreatorPresenter
//import com.zkhy.community.view.main.dj.AppPhotoPreviewActivity
//import com.zkhy.community.view.photos.adapter.WuMinAppNineGridAdapter
//import com.zkhy.library.base.activity.StatusViewTitleBarActivity
//import com.zkhy.library.mvp.AndroidView
//import com.zkhy.library.utils.ImageLoader
//import com.zkhy.library.widget.TitleBarViewCreator
//import kotlinx.android.synthetic.main.activity_shot_photo_handle_detail.*
//
///**
// * <pre>
// *  创建:  梁玉涛 2019/3/24 on 22:07
// *  项目:  WuMinAndroid
// *  描述:
// *  更新:
// * <pre>
// */
//class ShotPhotoHandleDetailActivityB : StatusViewTitleBarActivity(), AndroidView {
//
//    var presenter: PhotosCreatorPresenter? = null
//
//    override fun getTitleBarView(): View = TitleBarViewCreator.createTitleLC(this, "处理详情")
//
//    override fun getContentLayoutId(): Int = R.layout.activity_shot_photo_handle_detail
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        presenter = PhotosCreatorPresenter(this)
//    }
//
//    override fun onResume() {
//        super.onResume()
//        loadData()
//    }
//
//    override fun loadData() {
//        val id = intent.getStringExtra("id")
//        presenter?.loadShotPhotoDetailData(id)
//    }
//
//    override fun loadingShow() {
//        StatusView.showLoading("加载中")
//    }
//
//    override fun loadingDismiss() {
//    }
//
//    override fun showTip(msg: String?) {
//        StatusView.showError(msg)
//    }
//
//    override fun loadComplete(resultData: Any?) {
//        val result: BaseData<BmShotQuestionInfoDTO> = resultData as BaseData<BmShotQuestionInfoDTO>
//        if (result.errcode != 0 || result.data == null) {
//            showTip(result.errmsg)
//        } else {
//            StatusView.showContent()
//            showView(result.data)
//        }
//    }
//
//    private fun showView(data: BmShotQuestionInfoDTO) {
//        ImageLoader.show(this, data.photo, R.drawable.mr_tx, userAvatarIv)
//        userNameTv.text = StringUtil.getNotNullValue(data.userName, "匿名用户")
//        timeTv.text = StringUtil.getNotNullValue(data.phone)
//
//        contentTv.text = StringUtil.getNotNullValue(data.questionDesc)
//
//        if (data.createTime != null) {
//            val timeStr: String = DateUtil.getDateStrByDate(data.createTime, "yyyy-MM-dd HH:mm")
//            submitTimeTv.text = timeStr
//        }
//
//        submitLocTv.text = StringUtil.getNotNullValue(data.questionAddress, "无位置信息 ...")
//
//        if (data.attachments != null) {
//
//            val contentImgList: List<AttachmentEntity>? = data.attachments[BizType.WU_BM_SHOT_QUESTION]
//
//            if (contentImgList != null && contentImgList.isNotEmpty()) {
//                contentImgItem.visibility = View.VISIBLE
//
//                val imgList: ArrayList<String> = ArrayList()
//                for (imgEntity: AttachmentEntity in contentImgList) {
//                    imgList.add(imgEntity.url)
//                }
//
//                val nineGridAdapter = WuMinAppNineGridAdapter(this, imgList)
//                nineGridView.nineGridAdapter = nineGridAdapter
//
//                nineGridAdapter.setOnItemClickListener { position, urlOrFilePathList ->
//                    PhotoPreviewActivity.start(this, position, urlOrFilePathList)
//                }
//            } else {
//                contentImgItem.visibility = View.GONE
//            }
//        }
//
//        // 指派单位信息
//        if (data.shotTasks != null && data.shotTasks.isNotEmpty()) {
//
//            for (shotTaskInfo: BmShotTaskInfoDTO in data.shotTasks) {
//
//                if (shotTaskInfo.id == data.dealTaskId) {
//
//                    // 显示处理部门
//                    if (StringUtil.isEmpty(data.questionCode)) {
//                        zpItem.visibility = View.GONE
//                    } else {
//                        zpItem.visibility = View.VISIBLE
//                        zpContentTv.text = "请${StringUtil.getNotNullValue(shotTaskInfo.dealDeptName, "相关部门")}及时处理本次事项！"
//
//                        if (data.createTime != null) {
//                            zpTimeTv.text = DateUtil.getDateStrByDate(data.createTime, "yyyy-MM-dd HH:mm")
//                            zpTimeTv.visibility = View.VISIBLE
//                        } else {
//                            zpTimeTv.visibility = View.GONE
//                        }
//                    }
//
//                    // 显示分配任务
//                    taskView.visibility = View.VISIBLE // 属于自己处理
//                    // 设置上一级信息
//                    zpItem.setBackgroundResource(R.color.tc_list_tip)
//                    zpPoint.setBackgroundResource(R.drawable.shape_point_flow_done)
//
//                    // 指派信息
//                    doingContentTv.text = StringUtil.getNotNullValue(shotTaskInfo.taskDesc)
//                    if (shotTaskInfo.createTime != null) {
//                        doingTimeTv.text = DateUtil.getDateStrByDate(shotTaskInfo.createTime, "yyyy-MM-dd HH:mm")
//                        doingTimeTv.visibility = View.VISIBLE
//                    } else {
//                        doingTimeTv.visibility = View.GONE
//                    }
//
//
//                    // 完成部分
//                    if (shotTaskInfo.status != 1) {
//                        // 设置上一级信息
//                        doingPoint.setBackgroundResource(R.drawable.shape_point_flow_doing)
//
//                        bottomView.visibility = View.VISIBLE
//                        doneItem.visibility = View.GONE
//
//                        submitBtn.setOnClickListener {
//                            IntentUtil.openActivity(
//                                this@ShotPhotoHandleDetailActivityB,
//                                ShotPhotoHandleActivity::class.java
//                            )
//                                .putStringExtra("dealTaskId", data.dealTaskId.toString())
//                                .start()
//                        }
//                    } else {
//                        bottomView.visibility = View.GONE
//                        doneItem.visibility = View.VISIBLE
//
//                        doingPoint.setBackgroundResource(R.drawable.shape_point_flow_done)
//                        doingLine.setBackgroundResource(R.color.tc_list_tip)
//
//                        // 展示完成数据
//                        doneContentTv.text = StringUtil.getNotNullValue(shotTaskInfo.doneDesc)
//                        if (shotTaskInfo.doneTime != null) {
//                            doneTimeTv.text = DateUtil.getDateStrByDate(
//                                shotTaskInfo.doneTime,
//                                "yyyy-MM-dd HH:mm"
//                            )
//                        } else {
//                            doneTimeTv.text = "近期"
//                        }
//
//                        if (shotTaskInfo.attachments != null) {
//                            val contentImgList: List<AttachmentEntity>? =
//                                shotTaskInfo.attachments[BizType.WU_BM_SHOT_WORKSCENEDEAL]
//
//                            if (contentImgList != null && !contentImgList.isEmpty()) {
//
//
//                                val imgList: ArrayList<String> = ArrayList()
//                                for (imgEntity: AttachmentEntity in contentImgList) {
//                                    imgList.add(imgEntity.url)
//                                }
//
//                                val done9GridAdapter = WuMinAppNineGridAdapter(this, imgList)
//                                done9GridView.nineGridAdapter = done9GridAdapter
//
//                                done9GridAdapter.setOnItemClickListener { position, urlOrFilePathList ->
//                                    PhotoPreviewActivity.start(this, position, urlOrFilePathList)
//                                }
//
//                                doneImgItem.visibility = View.VISIBLE
//                            } else {
//                                doneImgItem.visibility = View.GONE
//                            }
//                        }
//                    }
//                }
//            }
//        } else {
//            taskView.visibility = View.GONE
//        }
//
////        // 全部处理完成：处理状态(0待分派1进行中2已完成)
////        if (data.status == 2) {
////            zpItem.setBackgroundResource(R.color.tc_list_tip)
////            zpPoint.setBackgroundResource(R.drawable.shape_point_flow_done)
////
////            wcItem.visibility = View.VISIBLE
////
////            wcContentTv.text = StringUtil.getNotNullValue(data.summary)
////            if (data.doneTime != null) {
////                wcTimeTv.text = DateUtil.getDateStrByDate(data.doneTime, "yyyy-MM-dd HH:mm")
////                wcTimeTv.visibility = View.VISIBLE
////            } else {
////                wcTimeTv.visibility = View.GONE
////            }
////
////            if (data.attachments != null) {
////                val wcContentImgList: List<AttachmentEntity>? = data.attachments[BizType.WU_BM_SHOT_REPLY]
////                if (wcContentImgList != null && wcContentImgList.isNotEmpty()) {
////                    ImageLoader.show(this, wcContentImgList[0].url, R.drawable.img_loading_default, doneIv)
////                    doneImgItem.visibility = View.VISIBLE
////
////                    doneIv.setOnClickListener {
////                        val list = ArrayList<String>()
////                        list.add(wcContentImgList[0].url)
////                        AppPhotoPreviewActivity.start(
////                            this@ShotPhotoHandleDetailActivity,
////                            0,
////                            list
////                        )
////                    }
////                } else {
////                    doneImgItem.visibility = View.GONE
////                }
////            }
////
////        } else {
////            wcItem.visibility = View.GONE
////            zpItem.setBackgroundColor(Color.WHITE)
////            zpPoint.setBackgroundResource(R.drawable.shape_point_flow_doing)
////        }
//    }
//}