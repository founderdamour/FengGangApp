package com.zkhy.community.view.main.bm

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import com.amap.api.maps.AMap
import com.amap.api.maps.model.LatLng
import com.amap.api.maps.model.MultiPointItem
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.StringUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.dialog.loading.LoadingDialog
import com.zkhy.community.R
import com.zkhy.comm.base.MapViewBaseActivity
import com.zkhy.community.model.bean.AreaDTO
import com.zkhy.community.model.bean.CommListItem0Entity
import com.zkhy.community.model.bean.WmUser
import com.zkhy.community.model.cache.DataCache
import com.zkhy.community.presenter.BizHandlePresenter
import com.zkhy.community.view.comm.LocationStreetListActivity
import com.zkhy.community.view.main.bm.adapters.CommGridViewAdapter
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.mvp.AndroidView
import kotlinx.android.synthetic.main.activity_convenience_query_home.*

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/19 on 16:42
 *  项目:  WuMinAndroid
 *  描述:  便民查询
 *  更新:
 * <pre>
 */
class ConvenienceQueryHomeActivity : MapViewBaseActivity(), AndroidExt2View {

    override fun load2Complete(resultData: Any?) {
    }

    var areaCode = ""
    private var presenter: BizHandlePresenter? = null

    override fun getLayout(): Int = R.layout.activity_convenience_query_home

//    private fun getMapPointLatLngList() {
//        val list: ArrayList<MultiPointItem> = ArrayList()
//        list.add(MultiPointItem(LatLng(26.5989651565, 106.6947555542)))
//        list.add(MultiPointItem(LatLng(26.6331890046, 106.6358757019)))
//        list.add(MultiPointItem(LatLng(26.6385595303, 106.5975952148)))
//        list.add(MultiPointItem(LatLng(26.6256698444, 106.7017936707)))
//        list.add(MultiPointItem(LatLng(26.6024954442, 106.6906356812)))
//
//        setMultiPointOverlay(list)
//    }

    private fun getPayObjList(): ArrayList<CommListItem0Entity> {
        val payObjList = ArrayList<CommListItem0Entity>()
        payObjList.add(CommListItem0Entity(1, R.drawable.dhcx01, "代驾服务", 1, "wm_info_driving"))
        payObjList.add(CommListItem0Entity(2, R.drawable.dhcx02, "搬运服务", 2, "wm_info_transport"))
        payObjList.add(CommListItem0Entity(3, R.drawable.dhcx03, "送水服务", 3, "wm_info_water"))
        payObjList.add(CommListItem0Entity(4, R.drawable.dhcx04, "水电服务", 4, "wm_info_waterel"))

        payObjList.add(CommListItem0Entity(5, R.drawable.dhcx05, "社区服务", 5, "wm_info_comm"))
        payObjList.add(CommListItem0Entity(6, R.drawable.dhcx06, "家政服务", 6, "wm_info_house"))
        payObjList.add(CommListItem0Entity(7, R.drawable.dhcx07, "包裹代收", 7, "wm_info_package"))
        payObjList.add(CommListItem0Entity(8, R.drawable.dhcx08, "洗车服务", 8, "wm_info_washcar"))

        payObjList.add(CommListItem0Entity(9, R.drawable.dhcx09, "送餐服务", 9, "wm_info_food"))
        payObjList.add(CommListItem0Entity(10, R.drawable.dhcx010, "理发服务", 10, "wm_info_haircut"))
        payObjList.add(CommListItem0Entity(11, R.drawable.dhcx0101, "洗衣服务", 11, "wm_info_wash"))
        payObjList.add(CommListItem0Entity(12, R.drawable.dhcx0102, "紧急服务", 12, "wm_info_service"))

        payObjList.add(CommListItem0Entity(13, R.drawable.dhcx11, "管道疏通", 13, "wm_info_gdss"))
        payObjList.add(CommListItem0Entity(14, R.drawable.dhcx12, "家电维修", 14, "wm_info_jdwx"))
        payObjList.add(CommListItem0Entity(15, R.drawable.dhcx13, "跑跑服务", 15, "wm_info_ppfw"))
        payObjList.add(CommListItem0Entity(16, R.drawable.sqfw14, "送气服务", 16, "wm_info_sqfw"))
        return payObjList
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("便民查询")

        val payObjList: ArrayList<CommListItem0Entity> = getPayObjList()

        val gridViewAdapter = CommGridViewAdapter(this,
            CommGridViewAdapter.OnListItemClickListener { _, itemData ->
                IntentUtil.openActivity(this@ConvenienceQueryHomeActivity, ConvenienceQueryListActivity::class.java)
                    .putStringExtra("classCode", itemData.classCode)
                    .putStringExtra("className", itemData.objName)
                    .putStringExtra("areaCode", areaCode)
                    .start()
            })
        gridViewAdapter.setData(payObjList)
        gridView.adapter = gridViewAdapter
        presenter = BizHandlePresenter(this)

        val wmUser: WmUser? = DataCache.getUserInfo()
        if (wmUser != null && StringUtil.isNotEmpty(wmUser.parentId)) {
            presenter!!.loadCommunitiesByStreetId(DataCache.getUserInfo().parentId)
            initView(wmUser)
        } else {
            selectLoc()
        }

        areaNameTv.setOnClickListener {
            selectLoc()
        }
    }

    private fun initView(wmUser: WmUser) {
        areaCode = StringUtil.getNotNullValue(wmUser.areaCode, "shljd_wjsq")//
        areaNameTv.text = StringUtil.getNotNullValue(wmUser.areaName, "乌江恬苑社区") + "服务中心"  //"乌江恬苑社区服务中心" //
    }


    override fun setMapMultiPointClickListener(): AMap.OnMultiPointClickListener {
        // 定义海量点点击事件
        return AMap.OnMultiPointClickListener { pointItem ->

            areaCode = pointItem.customerId
            areaNameTv.text = StringUtil.getNotNullValue(pointItem.title) + "服务中心"
            false
        }
    }

    private fun getMapData(listData: ArrayList<AreaDTO>) {
        val mapList: ArrayList<MultiPointItem> = ArrayList()
        for (entity: AreaDTO in listData.iterator()) {

            if (entity.latitude > 0 && entity.longitude > 0) {
                val multiPoint = MultiPointItem(LatLng(entity.latitude, entity.longitude))
                multiPoint.title = entity.name
                multiPoint.customerId = entity.code
                mapList.add(multiPoint)
            } else {
                continue
            }
        }
        if (mapList.size > 0) {
            setMultiPointOverlay(mapList)
        }
    }

    override fun loadComplete(resultData: Any?) {
        val areaData: ArrayList<AreaDTO> = resultData as ArrayList<AreaDTO>
        getMapData(areaData)

    }

    override fun loadingShow() {
        LoadingDialog.show(this, "加载中...")
    }

    override fun loadingDismiss() {
        LoadingDialog.hidden()
    }

    override fun showTip(msg: String?) {
        ToastUtil.show(msg)
        loadingDismiss()
    }

    private fun selectLoc() {
        IntentUtil.openActivity(this, LocationStreetListActivity::class.java)
            .putStringExtra("id", "525629318659833921")
            .requestCode(REQUEST_CODE_ADDRESS)
            .start()
    }

    val REQUEST_CODE_ADDRESS = 200

    @SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQUEST_CODE_ADDRESS -> {
                if (data == null) {
                    return
                }

                val entity: AreaDTO = data.getSerializableExtra("entity") as AreaDTO
                areaCode = entity.code
                areaNameTv.text = StringUtil.getNotNullValue(entity.name) + "服务中心"

                presenter!!.loadCommunitiesByStreetId(entity.parentId.toString())
            }
        }
    }
}