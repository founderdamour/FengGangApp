package com.zkhy.community.presenter

import com.sinothk.rxretrofit.RetrofitFactory
import com.sinothk.rxretrofit.RxRetrofit
import com.zkhy.community.model.api.*
import com.zkhy.community.model.bean.*
import com.zkhy.library.mvp.AndroidView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/10 on 17:01
 *  项目:  WuMinAndroid
 *  描述:  党建
 *  更新:
 * <pre>
 */
class DJPresenter(private val view: AndroidView) {

    /**
     * 获得党费代缴记录
     */
    fun loadDJUnpaidList(pageParam: PageReq<CommReq>) {
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .loadDJUnpaidList(pageParam)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<PageData<PartyPayInfoEntity>>>() {

                override fun onStart() {
                    super.onStart()
                    view.loadingShow()
                }

                override fun onCompleted() {
                    view.loadingDismiss()
                }

                override fun onError(e: Throwable) {
                    view.showTip("访问出错，请稍后重试")
                }

                override fun onNext(resultData: BaseData<PageData<PartyPayInfoEntity>>?) {
                    if (resultData != null) {
                        view.loadComplete(resultData)
                    } else {
                        view.showTip("服务器无数据返回...")
                    }
                }
            })
    }

    /**
     * 更新缴费状态
     */
    fun updatePartyPayStatus(commParam: CommReq) {
        RetrofitFactory.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .updatePartyPayStatus(commParam)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<String>>() {

                override fun onStart() {
                    super.onStart()
                    view.loadingShow()
                }

                override fun onCompleted() {
                    view.loadingDismiss()
                }

                override fun onError(e: Throwable) {
                    view.showTip("访问出错，请稍后重试")
                }

                override fun onNext(resultData: BaseData<String>?) {
                    if (resultData != null) {
                        view.loadComplete(resultData)
                    } else {
                        view.showTip("服务器无数据返回...")
                    }
                }
            })
    }

    fun loadPropagandaListData(pageParam: PageReq<WechatParam>) {
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .loadPropagandaListData(pageParam)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<PageData<WechatEntity>>>() {

                override fun onStart() {
                    super.onStart()
                    view.loadingShow()
                }

                override fun onCompleted() {
                    view.loadingDismiss()
                }

                override fun onError(e: Throwable) {
                    view.showTip("访问出错，请稍后重试")
                }

                override fun onNext(resultData: BaseData<PageData<WechatEntity>>?) {
                    if (resultData != null) {
                        view.loadComplete(resultData)
                    } else {
                        view.showTip("服务器无数据返回...")
                    }
                }
            })
    }
}