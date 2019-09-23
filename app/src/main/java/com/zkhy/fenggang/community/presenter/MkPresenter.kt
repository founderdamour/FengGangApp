package com.zkhy.fenggang.community.presenter

import com.sinothk.rxretrofit.RxRetrofit
import com.zkhy.fenggang.community.model.api.*
import com.zkhy.fenggang.community.model.bean.*
import com.zkhy.library.mvp.AndroidView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MkPresenter(private val view: AndroidView) {

    /**
     * 加载煤矿监管系统矿点列表
     */
    fun loadMkMonitoringSystemList(kbh: String) {
        RxRetrofit.init(ServerConfig.mkUrl)
                .create(AllApi::class.java)
                .loadMkMonitoringSystemList(kbh)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<ArrayList<MkMonitoringSystemEntity>>() {

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

                    override fun onNext(resultData: ArrayList<MkMonitoringSystemEntity>?) {
                        if (resultData != null) {
                            view.loadComplete(resultData)
                        } else {
                            view.showTip("服务器无数据返回...")
                        }
                    }
                })
    }

    /**
     * 加载煤矿监管系统列表
     */
    fun loadMkMonitoringSystem2List(kbh: String) {
        RxRetrofit.init(ServerConfig.mkUrl)
                .create(AllApi::class.java)
                .loadMkMonitoringSystem2List(kbh)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<ArrayList<MkMonitoringSystem2Entity>>() {

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

                    override fun onNext(resultData: ArrayList<MkMonitoringSystem2Entity>?) {
                        if (resultData != null) {
                            view.loadComplete(resultData)
                        } else {
                            view.showTip("服务器无数据返回...")
                        }
                    }
                })
    }

    /**
     * 加载煤矿监管系统列表
     */
    fun loadMkMonitoringSystemDetail(kbh: String) {
        RxRetrofit.init(ServerConfig.mkUrl)
                .create(AllApi::class.java)
                .loadMkMonitoringSystemDetail(kbh)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<ArrayList<MkMonitoringSystemDetailEntity>>() {

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

                    override fun onNext(resultData: ArrayList<MkMonitoringSystemDetailEntity>?) {
                        if (resultData != null) {
                            view.loadComplete(resultData)
                        } else {
                            view.showTip("服务器无数据返回...")
                        }
                    }
                })
    }

    /**
     * 人员定位系统
     */
    fun loadMkPersonLocationList(kbh: String) {
        RxRetrofit.init(ServerConfig.mkUrl)
                .create(AllApi::class.java)
                .loadMkPersonLocationList(kbh)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<ArrayList<MkMonitoringSystem2Entity>>() {

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

                    override fun onNext(resultData: ArrayList<MkMonitoringSystem2Entity>?) {
                        if (resultData != null) {
                            view.loadComplete(resultData)
                        } else {
                            view.showTip("服务器无数据返回...")
                        }
                    }
                })
    }

    /**
     * 人员定位系统详情
     */
    fun loadMkPersonLocationDetail(kbh: String) {
        RxRetrofit.init(ServerConfig.mkUrl)
                .create(AllApi::class.java)
                .loadMkPersonLocationDetail(kbh)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<ArrayList<MkPersonLocationDetailEntity>>() {

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

                    override fun onNext(resultData: ArrayList<MkPersonLocationDetailEntity>?) {
                        if (resultData != null) {
                            view.loadComplete(resultData)
                        } else {
                            view.showTip("服务器无数据返回...")
                        }
                    }
                })
    }

    /**
     * 人员大屏
     */
    fun loadMkPerson(kbh: String) {
        RxRetrofit.init(ServerConfig.mkUrl)
                .create(AllApi::class.java)
                .loadMkPerson(kbh)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<ArrayList<MkPersonShowEntity>>() {

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

                    override fun onNext(resultData: ArrayList<MkPersonShowEntity>?) {
                        if (resultData != null) {
                            view.loadComplete(resultData)
                        } else {
                            view.showTip("服务器无数据返回...")
                        }
                    }
                })
    }

}