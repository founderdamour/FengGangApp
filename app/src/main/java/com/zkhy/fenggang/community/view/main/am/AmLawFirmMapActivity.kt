package com.zkhy.fenggang.community.view.main.am

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.amap.api.maps.AMap
import com.amap.api.maps.model.LatLng
import com.amap.api.maps.model.MultiPointItem
import com.sinothk.comm.utils.StringUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.dialog.loading.LoadingDialog
import com.sinothk.view.status.statusViews.StatusView
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.comm.base.MapViewBaseActivity
import com.zkhy.fenggang.community.model.api.BaseData
import com.zkhy.fenggang.community.model.api.CommReq
import com.zkhy.fenggang.community.model.api.PageData
import com.zkhy.fenggang.community.model.api.PageReq
import com.zkhy.fenggang.community.model.bean.LawFirmEntity
import com.zkhy.fenggang.community.presenter.AMPresenter
import com.zkhy.library.mvp.AndroidExt2View
import kotlinx.android.synthetic.main.activity_lm_stadium_main.*

/**
 * 法律服务机构
 */
class AmLawFirmMapActivity : MapViewBaseActivity(), AndroidExt2View {
    override fun load2Complete(resultData: Any?) {
    }

    private var presenter: AMPresenter? = null
    override fun getLayout(): Int = R.layout.activity_lm_stadium_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("法律服务机构")

        presenter = AMPresenter(this)
        refreshData()

        initView()
    }

    private fun initView() {
    }

    private fun refreshData() {
        val pageParam = PageReq<CommReq>()
        pageParam.pageNo = 1
        pageParam.pageSize = 100
        val comm = CommReq()
        comm.addr = ""
        comm.orgName = ""
        comm.orgType = ""
        pageParam.data = comm
        presenter!!.loadLawFirmList(pageParam)
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
        val result: BaseData<PageData<LawFirmEntity>> = resultData as BaseData<PageData<LawFirmEntity>>

        if (result.errcode == 0) {

            if (result.data == null || result.data.list == null || result.data.list.size == 0) {
                showTip("暂无数据")
            } else {
                val pageData = result.data
                val listData = pageData.list as ArrayList<LawFirmEntity>
                getMapData(listData)
                StatusView.showContent()
            }
        } else {
            StatusView.showError(result.errmsg)
        }
    }

    private fun getMapData(listData: ArrayList<LawFirmEntity>) {
        val mapList: ArrayList<MultiPointItem> = ArrayList()
        for (entity: LawFirmEntity in listData.iterator()) {

            if (entity.lat > 0 && entity.lon > 0) {
                val multiPoint = MultiPointItem(LatLng(entity.lat, entity.lon))
                multiPoint.title = entity.orgName
                multiPoint.snippet = entity.addr
                multiPoint.`object` = entity.linkPerson

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

                // 设置
                linkPhoneNumTv.text = phoneNum
                linkPhoneNumTv.setOnClickListener {
                    if (StringUtil.isNotEmpty(phoneNum)) {
                        try {
                            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNum"))
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                        } catch (e: Exception) {
                            e.printStackTrace()
                            ToastUtil.show("拨号功能不可用")
                        }
                    }
                }

            } else {
                linkPhoneNumTv.text = "暂无"
            }

            false
        }
    }
}
