package com.zkhy.fenggang.community.view.main.am

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.amap.api.maps.AMap
import com.amap.api.maps.CameraUpdateFactory
import com.amap.api.maps.model.LatLng
import com.amap.api.maps.model.MultiPointItem
import com.sinothk.comm.utils.DateUtil
import com.sinothk.comm.utils.NetUtil
import com.sinothk.comm.utils.StringUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.dialog.loading.LoadingDialog
import com.sinothk.map.amap.location.AMapLocationEntity
import com.sinothk.map.amap.location.MapLocationHelper
import com.sinothk.view.status.statusViews.StatusView
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.comm.base.MapViewBaseActivity
import com.zkhy.fenggang.community.model.api.BaseData
import com.zkhy.fenggang.community.model.api.CommReq
import com.zkhy.fenggang.community.model.api.PageData
import com.zkhy.fenggang.community.model.api.PageReq
import com.zkhy.fenggang.community.model.bean.LatLngEntity
import com.zkhy.fenggang.community.model.bean.PoliceStationEntity
import com.zkhy.fenggang.community.model.bean.PoliceStationRecordEntity
import com.zkhy.fenggang.community.model.cache.DataCache
import com.zkhy.fenggang.community.presenter.AMPresenter
import com.zkhy.library.mvp.AndroidExt2View
import kotlinx.android.synthetic.main.activity_am_police_list_map.*
import java.util.*

class AmPoliceStationMapActivity : MapViewBaseActivity(), AndroidExt2View {

    var policeStationName = ""
    var policeStationPhone = ""

    override fun load2Complete(resultData: Any?) {
    }

    private var presenter: AMPresenter? = null
    override fun getLayout(): Int = R.layout.activity_am_police_list_map

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("警务管理")

        presenter = AMPresenter(this)
        refreshData()

        initView()
    }

    private fun initView() {

        MapLocationHelper.with(this).location { locEntity ->
            mapLocEntity = locEntity
        }

        callPoliceBtn.setOnClickListener {
            doCall(policeStationName, policeStationPhone)
        }

    }

    private fun refreshData() {
        val latLon: LatLngEntity = DataCache.findLatLng()
        if (latLon.latitude == 0.0 || latLon.longitude == 0.0) {
            showTip("位置获取失败")
            val latLng = LatLng(27.7220800000, 106.9245300000)//构造一个位置
            aMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
            return
        }

        if (!NetUtil.isConnected(this)) {
            StatusView.showNetWorkError("网络已断开")
            return
        }

        val pageParam = PageReq<CommReq>()
        pageParam.pageNo = 1
        pageParam.pageSize = 100
        val comm = CommReq()
        comm.addr = ""
        comm.officeName = ""
        comm.officeType = ""
        comm.isFromApp = true

        comm.officeLon = latLon.longitude
        comm.officeLat = latLon.latitude

        pageParam.data = comm
        presenter!!.loadPoliceStationList(pageParam)
    }


    override fun loadingShow() {
        LoadingDialog.show(this, "加载中...")
    }

    override fun loadingDismiss() {
        LoadingDialog.hidden()
    }

    override fun showTip(msg: String?) {
        ToastUtil.show(msg)
    }

    override fun loadComplete(resultData: Any?) {
        val result: BaseData<PageData<PoliceStationEntity>> = resultData as BaseData<PageData<PoliceStationEntity>>

        if (result.errcode == 0) {

            if (result.data == null || result.data.list == null || result.data.list.size == 0) {
                showTip("暂无数据")
            } else {
                val pageData = result.data
                val listData = pageData.list as ArrayList<PoliceStationEntity>
                getMapData(listData)
                StatusView.showContent()
            }
        } else {
            StatusView.showError(result.errmsg)
        }
    }

    private fun getMapData(listData: ArrayList<PoliceStationEntity>) {
        val mapList: ArrayList<MultiPointItem> = ArrayList()
        for (entity: PoliceStationEntity in listData.iterator()) {

            if (entity.officeLat > 0 && entity.officeLon > 0) {
                val multiPoint = MultiPointItem(LatLng(entity.officeLat, entity.officeLon))
                multiPoint.title = entity.officeName
                multiPoint.snippet = entity.addr
                multiPoint.`object` = "警员"

                if (StringUtil.isNotEmpty(entity.telephone) || StringUtil.isNotEmpty(entity.phone)) {

                    var phoneStr = ""
                    if (StringUtil.isNotEmpty(entity.telephone)) {
                        phoneStr += "|" + entity.telephone
                    }
                    if (StringUtil.isNotEmpty(entity.phone)) {
                        phoneStr += "|" + entity.phone
                    }

                    if (phoneStr.isNotEmpty()) {
                        multiPoint.customerId = phoneStr.substring(1)
                    } else {
                        multiPoint.customerId = ""
                    }
                }

                mapList.add(multiPoint)
            } else {
                continue
            }
        }
        if (mapList.size > 0) {
            setMultiPointOverlay(mapList)
        }

        if (listData.size > 0) {

            val name: String = listData[0].officeName
            val phone1: String = listData[0].telephone
            val phone2: String = listData[0].phone

            val phone: String = when {
                StringUtil.isNotEmpty(phone2) -> phone2
                StringUtil.isNotEmpty(phone1) -> phone1
                else -> ""
            }

            policeStationName = name
            policeStationPhone = phone
        }
    }

    private fun doCall(name: String, phoneNum: String) {

        if (StringUtil.isEmpty(phoneNum)) {
            ToastUtil.show("号码不可用")
            return
        }

        if (StringUtil.isNotEmpty(phoneNum)) {
            try {
                Thread {
                    submitCallRecord(name, phoneNum)
                }.start()

                val phone = phoneNum.replace("-", "")
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
                ToastUtil.show("拨号功能不可用")
            }
        }
    }

    override fun setMapMultiPointClickListener(): AMap.OnMultiPointClickListener {

        // 定义海量点点击事件
        return AMap.OnMultiPointClickListener { pointItem ->

            detailsView.visibility = View.VISIBLE
            deleteBtn.setOnClickListener { detailsView.visibility = View.GONE }

            orgNameTv.text = StringUtil.getNotNullValue(pointItem.title, "暂无")
            orgAddressTv.text = StringUtil.getNotNullValue(pointItem.snippet, "暂无")
            linkPersonTv.text = StringUtil.getNotNullValue(pointItem.`object`.toString(), "暂无")

            val phoneNum: String
            if (StringUtil.isNotEmpty(pointItem.customerId)) {
                if (pointItem.customerId.contains("|")) {
                    val phoneArr: List<String>? = pointItem.customerId.split("|")

                    phoneNum = if (phoneArr != null && phoneArr.isNotEmpty()) {

                        val phoneNum1 = phoneArr[0]
                        val phoneNum2 = phoneArr[1]

                        if (StringUtil.isNotEmpty(phoneNum2)) {
                            phoneNum2
                        } else {
                            phoneNum1.replace("-", "")
                        }

                    } else {
                        pointItem.customerId
                    }
                } else {
                    phoneNum = pointItem.customerId
                }

                // 点击后设置一键报警信息
                policeStationName = pointItem.title
                policeStationPhone = phoneNum

                // 设置展示
                linkPhoneNumTv.text = phoneNum
                linkPhoneNumTv.setOnClickListener {

                    doCall(policeStationName, policeStationPhone)

//                    if (StringUtil.isNotEmpty(phoneNum)) {
//                        try {
//                            Thread {
//                                submitCallRecord(pointItem.title, phoneNum)
//                            }.start()
//
//                            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNum"))
//                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//                            startActivity(intent)
//                        } catch (e: Exception) {
//                            e.printStackTrace()
//                            ToastUtil.show("拨号功能不可用")
//                        }
//                    }
                }

            } else {
                linkPhoneNumTv.text = "暂无"
            }

            false
        }
    }


    private var mapLocEntity: AMapLocationEntity? = null

    private fun submitCallRecord(officeName: String, phoneNum: String) {
        if (!NetUtil.isConnected(this)) {
            return
        }

        val vo = PoliceStationRecordEntity()
        val userInfo = DataCache.getUserInfo()

        if (userInfo != null) {
            vo.phone = userInfo.phone
            vo.callUserId = userInfo.userId.toString()

            //
            vo.callPhone = phoneNum
            vo.callName = officeName

            vo.callTime = DateUtil.getDateStrByDate(Date(), "yyyy-MM-dd HH:mm:ss")
            vo.isAnswerStatus = true

            if (mapLocEntity != null) {
                vo.addr = mapLocEntity!!.address
                vo.callLat = mapLocEntity!!.latitude
                vo.callLon = mapLocEntity!!.longitude
            }
            presenter?.submitPoliceCallRecord(vo)
        }
    }
}
