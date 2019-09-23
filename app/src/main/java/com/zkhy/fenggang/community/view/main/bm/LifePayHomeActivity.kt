package com.zkhy.fenggang.community.view.main.bm

import android.os.Bundle
import android.view.View
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.ToastUtil
import com.zkhy.fenggang.community.R
import com.zkhy.fenggang.community.model.bean.PayObjectEntity
import com.zkhy.fenggang.comm.plugin.activity.WebPageActivity
import com.zkhy.fenggang.community.view.main.bm.adapters.PayLifePayObjectGridViewAdapter
import com.zkhy.library.base.activity.TitleBarBaseActivity
import kotlinx.android.synthetic.main.activity_zm_life_pay_home.*

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/11 on 16:26
 *  项目:  WuMinAndroid
 *  描述:  预约项目
 *  更新:
 * <pre>
 */
class LifePayHomeActivity : TitleBarBaseActivity(), View.OnClickListener {

    private fun getPayObjList(): ArrayList<PayObjectEntity> {
        val payObjList = ArrayList<PayObjectEntity>()
        payObjList.add(PayObjectEntity(1, R.drawable.shjf_icon04, "水费", 1))
        payObjList.add(PayObjectEntity(2, R.drawable.shjf_icon01, "电费", 2))
        payObjList.add(PayObjectEntity(3, R.drawable.shjf_icon06, "燃气费", 3))
        payObjList.add(PayObjectEntity(4, R.drawable.shjf_icon07, "有线电视", 4))

        payObjList.add(PayObjectEntity(5, R.drawable.shjf_icon08, "电话缴费", 5))
        payObjList.add(PayObjectEntity(6, R.drawable.shjf_icon09, "宽带", 6))
        payObjList.add(PayObjectEntity(7, R.drawable.shjf_icon10, "物业费", 7))
        return payObjList
    }

    override fun getLayout(): Int = R.layout.activity_zm_life_pay_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleBar("生活缴费", "缴费记录") {
            ToastUtil.show("缴费列表")
        }

        val payObjList: ArrayList<PayObjectEntity> = getPayObjList()

        val dateGridViewAdapter = PayLifePayObjectGridViewAdapter(this,
            PayLifePayObjectGridViewAdapter.OnListItemClickListener { _, itemData ->

//                when {
//                    itemData.id == 1 ->
//                        IntentUtil.openActivity(this, WebPageActivity::class.java)
//                            .putStringExtra("webUrl", "http://www.zysgps.com/fee?userId=")
//                            .putStringExtra("webTitle", "水费业务")
//                            .start()
//
//                    itemData.id == 2 -> {
//                        IntentUtil.openActivity(this, WebPageActivity::class.java)
//                            .putStringExtra("webUrl", "https://app.95516.com/web/main.do?groupId=1")
//                            .putStringExtra("webTitle", "电费业务")
//                            .start()
//                    }
//                    itemData.id == 3 -> {
//                        IntentUtil.openActivity(this, WebPageActivity::class.java)
//                            .putStringExtra("webUrl", "https://app.95516.com/web/main.do?groupId=1")
//                            .putStringExtra("webTitle", "燃气费业务")
//                            .start()
//                    }
//
//                    itemData.id == 5 -> IntentUtil.openActivity(this, WebPageActivity::class.java)
//                        .putStringExtra(
//                            "webUrl",
//                            "https://gaxq.govpay.ccb.com/online/bmjf/MainPlat?Vno=2&Sgn_Algr=SHA256withRSA&MrchCd=105000086510577&PyF_ClCd=01006&Admn_Rgon_Cd=&Fee_Itm_PyF_MtdCd=01&Tms=20190114150209&isMobile=2&SIGN_INF=L3pHUtoa3zBcMHrM6gX9Qk3cJW1mS3iSXff/%2BOOnMiSgOretRHrOnhUee%2BD0MP/6WORzS04LQ0Eoq18gKu1FDYwRUAhWp1kBPZaH/%2BWQ6wgJBAoRt3Vzekr%2BtrJv8E2q7WZLzldjnd4Y%2BL9yLPvpBeWJRwYYG4l9qORJ87Mf4kzrrC8ooaR0faje2O3Wo4MIu632A2qj0T7BRY5YsvlFXqnDHufIaj4R4WKAH0fGbI0ttofi29LdgfNk4xahKFj460DSOkFxH2tTKKXqSfAqAtMlbo/iLg8Z%2BW47ea7bkEKLv72DQN%2BOWwHbw0G3wVTF9%2BxmClk3h5ObZV4wOXvIZQ=="
//                        )
//                        .putStringExtra("webTitle", "电话缴费")
//                        .start()
//                    else -> ToastUtil.show("功能暂未开放")
//                }

                when {
                    itemData.id == 1 ->
                        IntentUtil.openActivity(this, WebPageActivity::class.java)
                            .putStringExtra("webUrl", "http://life.ccb.com/cn/paymentv3/bill_item/2012032220243191063788.html?CTPPARAM=UKqJDUqi22G3CeKloX7PZmwozn,TyX3hilJWeCkeJUVrHUgksmaDFewpDG83umsjFmPG7PKejVYWdC2e5VOWm4Fi123Xjg8kuH3PMVilGGziaPafnlYa2CpezVNSQCvbY26P7irn6mHLnxhilGtPXWQh5lZmDEreQVKedC6euUHrXfnjtndbDVnkaWPXggenHVBGP4ZilG4PhfUlLl7Gl4nmR2,mODXiuG3Pdglhtk2rBkej2l9boV7lCG0KfPcbV5Rbp3tt9oKX6ErsVHcjaLquUYJmpDvhRkVr9ESrNoWHMzcuH4k.fmfwypDTi3Zex1gG4RXjMm")
                            .putStringExtra("webTitle", "水费业务")
                            .start()

                    itemData.id == 2 -> {
                        IntentUtil.openActivity(this, WebPageActivity::class.java)
                            .putStringExtra("webUrl", "http://life.ccb.com/cn/paymentv3/bill_item/2012032220261854138674.html?CTPPARAM=NMrEJbeehR1L2Md5kTW1O6lVnUmPSWWbgnk5V4B8dpTqqiTdjKlxCmdzotFt2klfi2lTF7OqdsUtVeBvdqUDZu3bhR1xWdfjj7GgO9UykrF5hdOoeRk4ZdBad5UURIBRap1VO0hxm6lXKUweh8FSOZVOgQkVleDCdgUKdABwdrT1qfeui7meawUXjYVzW6fImzUSFxCHaF1Oegg0kelQFtOdd5jAqkjHiUkHWBUJkUF4JyOOaV2FaXW3dw1CeGg1ko0fFe3ttR3om1,kpXo4aF8hmUYH25B,qHkCVFORaN4Tap2bswn3WTDArLG2iUK2tgXcltDgguh6QbQw7VrxPe")
                            .putStringExtra("webTitle", "电费业务")
                            .start()
                    }
                    itemData.id == 3 -> {
                        IntentUtil.openActivity(this, WebPageActivity::class.java)
                            .putStringExtra("webUrl", "http://life.ccb.com/cn/paymentv3/bill_item/2012032220273435331329.html?CTPPARAM=O1J8xMXYNsItxU6bjNoFDcceKd7JqNBX9bQMo4rULKvhTKwaYci5NUlfE8ttHcvZBcu82FVUbL0Mj4IUPL2UKuiYRsxNgWpaD9tFULPbx8gYJFiVrbHQJ4IUaLdI34JRxsTFbYpdhc8BQnTYE8pF1MQXCbZMzuwbtczdZaBYF8WpVJIbDt9YEFdUhabh7Lxbo8H9PVUXlb5MSukcbsFcG5UYV8CFQWdXeaQh1anZlbwRPLpbF8uAsFNRavhRHtIjqeeN46Rif9vZ5BMkwOEcD54XxaxhY69hnef93pAkauF1gcZmifxJ5tjUqr28BHg63W")
                            .putStringExtra("webTitle", "燃气费业务")
                            .start()
                    }

                    itemData.id == 5 -> IntentUtil.openActivity(this, WebPageActivity::class.java)
                        .putStringExtra(
                            "webUrl",
                            "http://chongzhi.bl.com/cz/czpage.html?type=ds"
                        )
                        .putStringExtra("webTitle", "电话缴费")
                        .start()
                    else -> ToastUtil.show("功能暂未开放")
                }
            })
        dateGridViewAdapter.setData(payObjList)
        gridView.adapter = dateGridViewAdapter

        item0Layout.setOnClickListener(this)
        item1Layout.setOnClickListener(this)
        item2Layout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        ToastUtil.show("暂未开放")
//        when (v) {
////            item0Layout -> {
////                IntentUtil.openActivity(this@ZmLifePayHomeActivity, BmOrderDateActivity::class.java)
////                    .putStringExtra("typeCode", "wm_con_or_marry")//wm_con_or_marry
////                    .start()
////            }
////
////            item1Layout -> {
////                IntentUtil.openActivity(this@ZmLifePayHomeActivity, BmOrderDateActivity::class.java)
////                    .putStringExtra("typeCode", "wm_con_or_disa")//wm_con_or_disa
////                    .start()
////            }
////            item2Layout -> {
////
////            }
//        }
    }
}
