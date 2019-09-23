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
 *  描述:
 *  更新:
 * <pre>
 */
class LMPresenter(private val view: AndroidView) {

    /**
     * 获得场所列表
     */
    fun loadStadiumList(pageParam: PageReq<CommReq>) {
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .loadStadiumList(pageParam)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<PageData<StadiumEntity>>>() {

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

                override fun onNext(resultData: BaseData<PageData<StadiumEntity>>?) {
                    if (resultData != null) {
                        view.loadComplete(resultData)
                    } else {
                        view.showTip("服务器无数据返回...")
                    }
                }
            })
    }

    /**
     * 获得场馆详情
     */
    fun loadStadiumDetails(id: String?) {
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .loadStadiumDetails(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<StadiumEntity>>() {

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

                override fun onNext(resultData: BaseData<StadiumEntity>?) {
                    if (resultData != null) {
                        view.loadComplete(resultData)
                    } else {
                        view.showTip("服务器无数据返回...")
                    }
                }
            })
    }

    /**
     * 获得周边活动列表
     */
    fun loadNearbyActiveList(pageParam: PageReq<CommReq>) {

        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .loadNearbyActiveList(pageParam)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<PageData<NearbyActiveEntity>>>() {

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

                override fun onNext(resultData: BaseData<PageData<NearbyActiveEntity>>?) {
                    if (resultData != null) {
                        view.loadComplete(resultData)
                    } else {
                        view.showTip("服务器无数据返回...")
                    }
                }
            })
    }

    /**
     * 获得周边活动详情
     */
    fun loadNearbyActiveDetails(id: String?) {
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .loadNearbyActiveDetails(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<NearbyActiveEntity>>() {

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

                override fun onNext(resultData: BaseData<NearbyActiveEntity>?) {
                    if (resultData != null) {
                        view.loadComplete(resultData)
                    } else {
                        view.showTip("服务器无数据返回...")
                    }
                }
            })
    }
}