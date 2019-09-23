package com.zkhy.community.view.photos

import android.os.Bundle
import android.view.View
import com.dds.webrtclib.WebRTCManager
import com.dds.webrtclib.bean.*
import com.dds.webrtclib.ui.ChatRoomActivity
import com.dds.webrtclib.ws.IConnectEvent
import com.sinothk.comm.utils.DateUtil
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.StringUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.image.selector.PhotoPreviewActivity
import com.sinothk.view.status.statusViews.StatusView
import com.zkhy.community.R
import com.zkhy.community.model.api.BaseData
import com.zkhy.community.model.api.UrlConfig
import com.zkhy.community.model.bean.*
import com.zkhy.community.model.cache.DataCache
import com.zkhy.community.presenter.PhotosCreatorPresenter
import com.zkhy.community.view.photos.adapter.WuMinAppNineGridAdapter
import com.zkhy.library.base.activity.StatusViewTitleBarActivity
import com.zkhy.library.mvp.AndroidView
import com.zkhy.library.utils.ImageLoader
import com.zkhy.library.widget.TitleBarViewCreator
import kotlinx.android.synthetic.main.activity_shot_photo_handle_detail.*

/**
 * <pre>
 *  创建:  梁玉涛 2019/3/24 on 22:07
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
class ShotPhotoHandleDetailActivity : StatusViewTitleBarActivity(), AndroidView {

    var presenter: PhotosCreatorPresenter? = null

    //    override fun getTitleBarView(): View = TitleBarViewCreator.createTitleLC(this, "处理详情")
    override fun getTitleBarView(): View = TitleBarViewCreator.createTitleLCR(this, "处理详情", "视频") {
        presenter?.loadingVideoUrl(this)
    }

    override fun getContentLayoutId(): Int = R.layout.activity_shot_photo_handle_detail

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
                contentImgItem.visibility = View.VISIBLE

                val imgList: ArrayList<String> = ArrayList()
                for (imgEntity: AttachmentEntity in contentImgList) {
                    imgList.add(imgEntity.url)
                }

                val nineGridAdapter = WuMinAppNineGridAdapter(this, imgList)
                nineGridView.nineGridAdapter = nineGridAdapter

                nineGridAdapter.setOnItemClickListener { position, urlOrFilePathList ->
                    PhotoPreviewActivity.start(this, position, urlOrFilePathList)
                }
            } else {
                contentImgItem.visibility = View.GONE
            }
        }

        // 指派单位信息
        if (data.shotTasks != null && data.shotTasks.isNotEmpty()) {

            for (shotTaskInfo: BmShotTaskInfoDTO in data.shotTasks) {

                if (shotTaskInfo.id == data.dealTaskId) { // 找到自己处理数据

                    // 显示处理部门
                    if (StringUtil.isEmpty(data.questionCode)) {
                        zpItem.visibility = View.GONE
                    } else {
                        zpItem.visibility = View.VISIBLE
                        zpContentTv.text = "请${StringUtil.getNotNullValue(shotTaskInfo.dealDeptName, "相关部门")}及时处理本次事项！"

                        if (data.createTime != null) {
                            zpTimeTv.text = DateUtil.getDateStrByDate(data.createTime, "yyyy-MM-dd HH:mm")
                            zpTimeTv.visibility = View.VISIBLE
                        } else {
                            zpTimeTv.visibility = View.GONE
                        }
                    }

                    //====================================================== 显示分配任务
                    taskView.visibility = View.VISIBLE // 属于自己处理
                    // 设置上一级信息
                    zpItem.setBackgroundResource(R.color.tc_list_tip)
                    zpPoint.setBackgroundResource(R.drawable.shape_point_flow_done)

                    // 指派信息
                    doingContentTv.text = StringUtil.getNotNullValue(shotTaskInfo.taskDesc)
                    if (shotTaskInfo.createTime != null) {
                        doingTimeTv.text = DateUtil.getDateStrByDate(shotTaskInfo.createTime, "yyyy-MM-dd HH:mm")
                        doingTimeTv.visibility = View.VISIBLE
                    } else {
                        doingTimeTv.visibility = View.GONE
                    }
                    // 紧急程度
                    when (shotTaskInfo.worryLevel) { // (0普通1紧急2特急)
                        0 -> {
                            taskLevelTv.text = "一般"
                            taskLevelTv.setBackgroundResource(R.color.level_3)
                        }
                        1 -> {
                            taskLevelTv.text = "紧急"
                            taskLevelTv.setBackgroundResource(R.color.level_2)
                        }
                        2 -> {
                            taskLevelTv.text = "特急"
                            taskLevelTv.setBackgroundResource(R.color.level_1)
                        }
                    }

                    // 完成期限
                    if (shotTaskInfo.requireDoneTime != null) {
                        val timeStr: String =
                            DateUtil.getDateStrByDate(shotTaskInfo.requireDoneTime, "yyyy-MM-dd HH:mm:ss")
                        requireDoneTimeTv.text = "完成期限：$timeStr"
                        requireDoneTimeTv.visibility = View.VISIBLE
                    } else {
                        requireDoneTimeTv.visibility = View.GONE
                    }

                    // =============================================完成部分==================================================
                    // 处理按钮控制
                    if (shotTaskInfo.status == 0) {
                        bottomView.visibility = View.VISIBLE
                        submitBtn.setOnClickListener {
                            IntentUtil.openActivity(
                                this@ShotPhotoHandleDetailActivity,
                                ShotPhotoHandleActivity::class.java
                            )
                                .putStringExtra("dealTaskId", data.dealTaskId.toString())
                                .start()
                        }

                    } else {
                        bottomView.visibility = View.GONE
                    }

                    // 任务完成情况
                    if (shotTaskInfo.taskDeals == null || shotTaskInfo.taskDeals.size == 0) {
                        // 设置上一级
                        doingPoint.setBackgroundResource(R.drawable.shape_point_flow_doing)

                        doneResultView.visibility = View.GONE
                    } else {
                        doingPoint.setBackgroundResource(R.drawable.shape_point_flow_done)
                        doingLine.setBackgroundResource(R.color.tc_list_tip)

                        // 处理完成数据展示
                        doneResultView.visibility = View.VISIBLE
                        doneResultView.setData(this, shotTaskInfo.taskDeals, null)
                    }
                }
            }
        } else {
            taskView.visibility = View.GONE
        }
    }

    fun loadingVideoUrlEnd(roomNum: String) {
//        val intent = Intent()
//        intent.action = "android.intent.action.VIEW"
//        val uri = Uri.parse(url)
//        intent.data = uri
//        startActivity(intent)

        // 默契加入房间
        Constant.url = UrlConfig.LIVE_URL

        val wss: String = Constant.url

        Constant.iceServers = arrayOf(
            // new MyIceServer("stun:stun.l.google.com:19302"),
            MyIceServer("turn:112.74.58.200:3478", "wz", "123456")
        )

        WebRTCManager.getInstance().init(wss, Constant.iceServers, object : IConnectEvent {
            override fun onSuccess() {
                initUserInfo()

                ChatRoomActivity.openActivity(this@ShotPhotoHandleDetailActivity)
            }

            override fun onFailed(msg: String?) {
                if (msg == null) {
                }
                ToastUtil.show("连接视频服务器失败，请稍后重试")
            }
        })

        WebRTCManager.getInstance().connect(MediaType.TYPE_MEETING, roomNum) //roomNum
    }

    private fun initUserInfo() {
        val userInfo: WmUser = DataCache.getUserInfo()
        Constant.userId = userInfo.userId

        val meetingMsg = MeetingMsg()
        meetingMsg.eventName = "__joinUser"

        val meetingContent = MeetingContent()
        meetingContent.id = Constant.userId
        meetingContent.name = userInfo.name
        meetingContent.photo = userInfo.photo

        meetingMsg.data = meetingContent

        WebRTCManager.getInstance().sendMsg(meetingMsg)

    }
}