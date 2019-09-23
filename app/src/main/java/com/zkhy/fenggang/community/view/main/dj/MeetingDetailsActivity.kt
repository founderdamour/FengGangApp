package com.zkhy.fenggang.community.view.main.dj

import android.os.Bundle
import android.text.Html
import android.view.View
import com.jiangyy.easydialog.CommonDialog
import com.jiangyy.easydialog.InputDialog
import com.sinothk.comm.utils.*
import com.sinothk.dialog.loading.LoadingDialog
import com.sinothk.image.selector.PhotoPreviewActivity
import com.sinothk.map.amap.location.MapLocationHelper
import com.sinothk.view.status.base.StatusViewBaseActivity
import com.sinothk.view.status.statusViews.StatusView
import com.zkhy.fenggang.comm.plugin.activity.WebPageActivity
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.api.BaseData
import com.zkhy.fenggang.community.model.bean.AttachmentEntity
import com.zkhy.fenggang.community.model.bean.DjThreeMeetInfoDTO
import com.zkhy.fenggang.community.model.bean.DjThreeMeetMemDTO
import com.zkhy.fenggang.community.model.bean.ExtDjThreeMeet
import com.zkhy.fenggang.community.presenter.MeetClassPresenter
import com.zkhy.fenggang.community.view.main.dj.adapters.AppNetNineGridAdapter
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.widget.TitleBarViewCreator
import kotlinx.android.synthetic.main.activity_dj_meeting_details.*

/**
 * <pre>
 *  创建:  梁玉涛 2019/2/15 on 16:24
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
class MeetingDetailsActivity : StatusViewBaseActivity(), AndroidExt2View {

    var id = ""
    private var address = ""

    companion object {
        var receivedList: ArrayList<DjThreeMeetMemDTO>? = null
        var unreceivedList: ArrayList<DjThreeMeetMemDTO>? = null

        var joinsMeetMemDTO: ArrayList<DjThreeMeetMemDTO>? = null
        var leaveMeetMemDTO: ArrayList<DjThreeMeetMemDTO>? = null
    }

    private var presenter: MeetClassPresenter? = null

    override fun getContentRetryListener(): View.OnClickListener = View.OnClickListener { loadData(id) }

    override fun getTitleBarView(): View = TitleBarViewCreator.createTitleLC(this, "会议详情")

    override fun getContentLayoutId(): Int = R.layout.activity_dj_meeting_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.transparencyBar(this)

        id = intent.getStringExtra("id")

        presenter = MeetClassPresenter(this)

        MapLocationHelper.with(this).location { locEntity ->
            address = locEntity.address
        }

        loadData(id)
    }

    private fun loadData(id: String) {
        presenter?.loadMeetDetails(id)
    }

    override fun loadComplete(resultData: Any?) {
        try {
            val result: DjThreeMeetInfoDTO = resultData as DjThreeMeetInfoDTO
            showMeetContent(result)
        } catch (e: Exception) {
        }
    }

    private fun showMeetContent(djThreeMeetInfoDTO: DjThreeMeetInfoDTO?) {

        if (djThreeMeetInfoDTO != null) {

            StatusView.showContent()

            // 内容部分
            if (djThreeMeetInfoDTO.meetDTO != null) {
                val meetDTO: ExtDjThreeMeet = djThreeMeetInfoDTO.meetDTO
                meetTitleTv.text = StringUtil.getNotNullValue(meetDTO.meetName)
                meetOrgTv.text = StringUtil.getNotNullValue(meetDTO.partDeptName)
                hostValueTv.text = StringUtil.getNotNullValue(meetDTO.compere)
                meetAddressValueTv.text = StringUtil.getNotNullValue(meetDTO.meetAddr)
                meetTimeValueTv.text = DateUtil.getDateStrByDate(meetDTO.planStartDate, "yyyy年MM月dd日 HH:mm")

                // 内容
                if (StringUtil.isNotEmpty(meetDTO.meetContent)) {
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        meetContentTv.text = Html.fromHtml(meetDTO.meetContent, Html.FROM_HTML_MODE_LEGACY)
                    } else {
                        meetContentTv.text = (Html.fromHtml(meetDTO.meetContent))
                    }
                } else {
                    meetContentTv.text = "暂无描述"
                }

                showBottomView(meetDTO)

                showMeetFile(djThreeMeetInfoDTO.meetDTO)
            }

            // 接收情况
            receivedBtn.text = "已读 (${djThreeMeetInfoDTO.redNum}人)"
            unreceivedBtn.text = "未读 (${djThreeMeetInfoDTO.notRednum}人)"

            receivedList = if (djThreeMeetInfoDTO.redMeetMemDTO != null) {
                djThreeMeetInfoDTO.redMeetMemDTO
            } else {
                ArrayList()
            }

            unreceivedList = if (djThreeMeetInfoDTO.notRedMeetMemDTO != null) {
                djThreeMeetInfoDTO.notRedMeetMemDTO
            } else {
                ArrayList()
            }

            unreceivedBtn.setOnClickListener {
                IntentUtil.openActivity(this@MeetingDetailsActivity, MeetReadStatusActivity::class.java)
                    .putIntExtra("index", 0).start()
            }

            receivedBtn.setOnClickListener {
                IntentUtil.openActivity(this@MeetingDetailsActivity, MeetReadStatusActivity::class.java)
                    .putIntExtra("index", 1)
                    .start()
            }

            // 参会情况
            attendNumBtn.text = "实到 (${djThreeMeetInfoDTO.joinsNum}人)"
            leavedNumBtn.text = "请假 (${djThreeMeetInfoDTO.leaveNum}人)"

            joinsMeetMemDTO = if (djThreeMeetInfoDTO.joinsMeetMemDTO != null) {
                djThreeMeetInfoDTO.joinsMeetMemDTO
            } else {
                ArrayList()
            }

            leaveMeetMemDTO = if (djThreeMeetInfoDTO.leaveMeetMemDTO != null) {
                djThreeMeetInfoDTO.leaveMeetMemDTO
            } else {
                ArrayList()
            }

            attendNumBtn.setOnClickListener {
                IntentUtil.openActivity(this@MeetingDetailsActivity, MeetAttendInfoActivity::class.java)
                    .putIntExtra("index", 0)
                    .start()
            }

            leavedNumBtn.setOnClickListener {
                IntentUtil.openActivity(this@MeetingDetailsActivity, MeetAttendInfoActivity::class.java)
                    .putIntExtra("index", 1).start()
            }
        } else {
            StatusView.showEmptyData("无会议数据")
        }
    }

    private fun showBottomView(meetDTO: ExtDjThreeMeet) {
        // 参加状态: 登录人参加状态（0：未读 1：待参加，会议没有开始也没有请假 2：已请假，
        // 3：未参加，结束了未签到未请假，4：已参加，已签到
        val joinStatus: Int = meetDTO.joinStatus

        // 会议状态
        when (meetDTO.meetStatus) { // 会议状态（0：未启动，1：未开始，2：进行中，3：已结束，4：已取消，5：已归档）
            0 -> {
                meetStatusValueTv.text = "未启动"
                receiveInfoView.visibility = View.VISIBLE
                attendanceInfoView.visibility = View.GONE

                bottomView.visibility = View.VISIBLE

                // 底部View
                bottomView.visibility = View.VISIBLE

                attendanceBtn.visibility = View.VISIBLE
                if (joinStatus == 2) {
                    lineView.visibility = View.VISIBLE
                    leaveBtn.visibility = View.VISIBLE
                    leaveBtn.isEnabled = false
                    leaveBtn.text = "已请假"
                } else {
                    lineView.visibility = View.VISIBLE
                    leaveBtn.visibility = View.VISIBLE
                    leaveBtn.isEnabled = true
                    leaveBtn.text = "请假"
                }
            }
            1 -> {
                meetStatusValueTv.text = "未开始"
                receiveInfoView.visibility = View.VISIBLE
                attendanceInfoView.visibility = View.GONE

                // 底部View
                bottomView.visibility = View.VISIBLE

                attendanceBtn.visibility = View.VISIBLE
                if (joinStatus == 2) {
                    lineView.visibility = View.VISIBLE
                    leaveBtn.visibility = View.VISIBLE
                    leaveBtn.isEnabled = false
                } else {
                    lineView.visibility = View.VISIBLE
                    leaveBtn.visibility = View.VISIBLE
                    leaveBtn.isEnabled = true
                }
            }
            2 -> {
                meetStatusValueTv.text = "进行中"
                receiveInfoView.visibility = View.VISIBLE
                attendanceInfoView.visibility = View.GONE

                // 底部View
                bottomView.visibility = View.VISIBLE
                attendanceBtn.visibility = View.VISIBLE

                lineView.visibility = View.GONE
                leaveBtn.visibility = View.GONE
                leaveBtn.isEnabled = false

                // 已签到，设置签到不可用
                if (joinStatus != 4) {
                    attendanceBtn.isEnabled = true
                    attendanceBtn.text = "会议签到"
                } else {
                    attendanceBtn.isEnabled = false
                    attendanceBtn.text = "会议已签到"
                }
            }

            4 -> {
                meetStatusValueTv.text = "已取消"
                receiveInfoView.visibility = View.VISIBLE
                attendanceInfoView.visibility = View.GONE

                bottomView.visibility = View.GONE
            }

            3 -> {
                meetStatusValueTv.text = "已结束"
                receiveInfoView.visibility = View.GONE
                attendanceInfoView.visibility = View.VISIBLE

                bottomView.visibility = View.GONE
            }

            5 -> {
                meetStatusValueTv.text = "已归档"
                receiveInfoView.visibility = View.GONE
                attendanceInfoView.visibility = View.VISIBLE

                bottomView.visibility = View.GONE
            }
        }

        // 事件
        attendanceBtn.setOnClickListener {

            if (meetDTO.meetStatus != 2) {
                CommonDialog.Builder(this)
                    .setTitle("签到提醒", R.color.colorAccent)
                    .setMessage("会议未开始，不能签到！", R.color.red)
                    .setPositiveButton("知道了", null, R.color.colorAccent)
                    .setNegativeButton("取消", null, R.color.colorAccent).show()
                return@setOnClickListener
            }

            if (StringUtil.isEmpty(address)) {
                showMeetAttendanceLocate()
                return@setOnClickListener

            } else {
                showMeetAttendance(address)
                return@setOnClickListener
            }
        }

        leaveBtn.setOnClickListener {
            InputDialog.Builder(this)
                .setTitle("请假事由")
                .setHint("请输入请假事由")
                .setLines(3)
                .setPositiveButton(
                    "确定", { view ->
                        val leaveReason: String = view.tag.toString()
                        if (StringUtil.isEmpty(leaveReason)) {
                            ToastUtil.show("请输入请假事由")
                            return@setPositiveButton
                        }

                        presenter?.leaveMeet(this@MeetingDetailsActivity, id, leaveReason)

                    }, R.color.colorAccent
                )
                .setNegativeButton("取消", null, R.color.colorAccent).show()
        }
    }

    private fun showMeetAttendanceLocate() {
        CommonDialog.Builder(this)
            .setTitle("定位失败", R.color.colorAccent)
            .setMessage("无法获取签到位置,请检查后重试...", R.color.red)
            .setPositiveButton("重新定位", {

                MapLocationHelper.with(this).location { locEntity ->
                    if (locEntity.code == 0) {
                        address = locEntity.address
                        showMeetAttendance(address)
                    } else {
                        ToastUtil.show("定位失败,请检查后重试...")
                    }
                }
            }, R.color.colorAccent)
            .setNegativeButton("取消", null, R.color.colorAccent).show()
    }

    private fun showMeetAttendance(address: String) {
        CommonDialog.Builder(this)
            .setTitle("会议签到", R.color.colorAccent)
            .setMessage("当前位于：\n$address", R.color.tc)
            .setPositiveButton("确定", {

                presenter?.attendMeet(this@MeetingDetailsActivity, id, address)

            }, R.color.colorAccent)
            .setNegativeButton("取消", null, R.color.colorAccent).show()
    }

    override fun load2Complete(resultData: Any?) {
    }

    override fun loadingDismiss() {
    }

    override fun loadingShow() {
        StatusView.showLoading("正在加载数据")
    }

    override fun showTip(msg: String?) {
        StatusView.showLoading(msg)
    }

    fun leaveMeetEnd(resultData: BaseData<Boolean>) {
        if (resultData.data) {
            ToastUtil.show("请假成功")
            loadData(id)
        } else {
            ToastUtil.show("请假失败")
        }
    }

    fun showLoading() {
        LoadingDialog.show(this, "正在提交")
    }

    fun dismissLoading() {
        LoadingDialog.hidden()
    }

    fun showLeaveMeetTip(msg: String?) {
        ToastUtil.show(msg)
    }

    fun attendMeetEnd(resultData: BaseData<Boolean>) {
        if (resultData.data) {
            ToastUtil.show("会议签到成功")
            loadData(id)
        } else {
            ToastUtil.show("会议签到失败")
        }
    }

    private fun showMeetFile(djThreeMeetInfoDTO: ExtDjThreeMeet) {
        if (djThreeMeetInfoDTO.files == null || djThreeMeetInfoDTO.files.isEmpty()) {
            fileInfoView.visibility = View.GONE
            return
        } else {
            fileInfoView.visibility = View.VISIBLE
        }

        try { // "bm_meeting_material";//会议资料
            val meetingMaterialFiles: List<AttachmentEntity>? = djThreeMeetInfoDTO.files["bm_meeting_material"]
            if (meetingMaterialFiles == null || meetingMaterialFiles.isEmpty()) {
                meetingMaterialView.visibility = View.GONE
            } else {
                meetingMaterialView.visibility = View.VISIBLE
                meetingMaterialFileList.setData(meetingMaterialFiles) { entity ->
                    WebPageActivity.start(this@MeetingDetailsActivity, "会议资料", getFileView(entity.url))
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

        try { //  "bm_meeting_abstract";//会议纪要
            val meetingAbstractFiles: List<AttachmentEntity>? = djThreeMeetInfoDTO.files["bm_meeting_abstract"]
            if (meetingAbstractFiles == null || meetingAbstractFiles.isEmpty()) {
                meetingAbstractView.visibility = View.GONE
            } else {
                meetingAbstractView.visibility = View.VISIBLE
                meetingAbstractFileList.setData(meetingAbstractFiles) { entity ->
                    WebPageActivity.start(this@MeetingDetailsActivity, "会议纪要", getFileView(entity.url))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        try { //  "bm_meeting_decide";//会议决议
            val meetingDecideFiles: List<AttachmentEntity>? = djThreeMeetInfoDTO.files["bm_meeting_decide"]

            if (meetingDecideFiles == null || meetingDecideFiles.isEmpty()) {
                meetingDecideView.visibility = View.GONE
            } else {
                meetingDecideView.visibility = View.VISIBLE
                meetingDecideFileList.setData(meetingDecideFiles) { entity ->
                    WebPageActivity.start(this@MeetingDetailsActivity, "会议决议", getFileView(entity.url))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        try { //  "bm_meeting_image";//会议图片
            val meetingImageFiles: List<AttachmentEntity>? = djThreeMeetInfoDTO.files["bm_meeting_image"]

            if (meetingImageFiles == null || meetingImageFiles.isEmpty()) {
                meetingFilesView.visibility = View.GONE
            } else {
                meetingFilesView.visibility = View.VISIBLE

                val imgUrlList = ArrayList<String>()
                for (attachment: AttachmentEntity in meetingImageFiles) {
                    imgUrlList.add(attachment.url)
                }

//                val meetImagesAdapter = MeetImagesAdapter(this, imgUrlList) { position, urlList ->
//
//                    AppPhotoPreviewActivity.start(
//                        this@MeetingDetailsActivity,
//                        position,
//                        urlList
//                    )
//                }
//                gridView.adapter = meetImagesAdapter

                val nineGridAdapter = AppNetNineGridAdapter(this@MeetingDetailsActivity, imgUrlList)
                nineGridView.nineGridAdapter = nineGridAdapter

                nineGridAdapter.setOnItemClickListener { position, urlOrFilePathList ->
                    PhotoPreviewActivity.start(
                        this@MeetingDetailsActivity,
                        position,
                        urlOrFilePathList
                    )
                }

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getFileView(url: String?): String {
        return "http://www.xdocin.com/xdoc?_func=to&amp;_format=html&_cache=1&_xdoc=$url"
    }
}