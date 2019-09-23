package com.zkhy.community.presenter

import com.sinothk.rxretrofit.RxRetrofit
import com.zkhy.community.model.api.*
import com.zkhy.community.model.bean.LoveHeartEntity
import com.zkhy.community.model.bean.WishEntity
import com.zkhy.library.mvp.AndroidExt2View
import com.zkhy.library.mvp.AndroidExt3View
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
open class ZMExtPresenter(private val view: AndroidExt3View) {

    /**
     * 认领心愿
     */
    fun claimWish(vo: CommReq) {
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .claimWish(vo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<Boolean>>() {

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

                override fun onNext(resultData: BaseData<Boolean>?) {
                    if (resultData != null) {
                        view.load2Complete(resultData)
                    } else {
                        view.showTip("服务器无数据返回...")
                    }
                }
            })
    }

    /**
     * 加载心愿详情
     */
    fun loadWishDetails(vo: CommReq) {
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .loadWishDetails(vo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<WishEntity>>() {

                override fun onCompleted() {
                }

                override fun onError(e: Throwable) {
                    view.showTip("访问出错，请稍后重试")
                }

                override fun onNext(resultData: BaseData<WishEntity>?) {
                    if (resultData != null) {
                        view.loadComplete(resultData)
                    } else {
                        view.showTip("服务器无数据返回...")
                    }
                }
            })
    }

    fun loadLoveHeartDetails(id: String?) {
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .loadLoveHeartDetails(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<LoveHeartEntity>>() {

                override fun onCompleted() {
                }

                override fun onError(e: Throwable) {
                    view.showTip("访问出错，请稍后重试")
                }

                override fun onNext(resultData: BaseData<LoveHeartEntity>?) {
                    if (resultData != null) {
                        view.loadComplete(resultData)
                    } else {
                        view.showTip("服务器无数据返回...")
                    }
                }
            })
    }
}