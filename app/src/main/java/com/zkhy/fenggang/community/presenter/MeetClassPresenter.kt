package com.zkhy.fenggang.community.presenter

import com.sinothk.rxretrofit.RxRetrofit
import com.zkhy.fenggang.community.model.api.*
import com.zkhy.fenggang.community.model.bean.*
import com.zkhy.fenggang.community.view.main.dj.MeetingDetailsActivity
import com.zkhy.library.mvp.AndroidExt2View
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
class MeetClassPresenter(private val view: AndroidExt2View) {

    /**
     * 三会一课/我的会议列表
     */
    fun loadMeetForAllList(pageParam: PageReq<CommReq>) {

        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .loadMeetForAllList(pageParam)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<PageData<ThreeMeetEntity>>>() {

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

                override fun onNext(resultData: BaseData<PageData<ThreeMeetEntity>>?) {
                    if (resultData != null) {
                        view.loadComplete(resultData)
                    } else {
                        view.showTip("服务器无数据返回...")
                    }
                }
            })
    }

    /**
     * 三会一课/所有会议列表
     */
    fun loadMeetForMeList(pageParam: PageReq<CommReq>) {

        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .loadMeetForMeList(pageParam)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<PageData<ThreeMeetEntity>>>() {

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

                override fun onNext(resultData: BaseData<PageData<ThreeMeetEntity>>?) {
                    if (resultData != null) {
                        view.loadComplete(resultData)
                    } else {
                        view.showTip("服务器无数据返回...")
                    }
                }
            })
    }

    /**
     * 获得律师事务所列表
     */
    fun loadLawFirmList(pageParam: PageReq<CommReq>) {
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .loadLawFirmList(pageParam)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<PageData<LawFirmEntity>>>() {

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

                override fun onNext(resultData: BaseData<PageData<LawFirmEntity>>?) {
                    if (resultData != null) {
                        view.loadComplete(resultData)
                    } else {
                        view.showTip("服务器无数据返回...")
                    }
                }
            })

    }



    /**
     * 新增法律咨询
     */
    fun addMyLawConsult(content: String) {
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .addMyLawConsult(content)
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
                        view.loadComplete(resultData)
                    } else {
                        view.showTip("服务器无数据返回...")
                    }
                }
            })
    }

    /**
     * 获得警务列表
     */
    fun loadPoliceStationList(pageParam: PageReq<CommReq>) {
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .loadPoliceStationList(pageParam)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<PageData<PoliceStationEntity>>>() {

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

                override fun onNext(resultData: BaseData<PageData<PoliceStationEntity>>?) {
                    if (resultData != null) {
                        view.loadComplete(resultData)
                    } else {
                        view.showTip("服务器无数据返回...")
                    }
                }
            })
    }

    /**
     * 提交报警记录
     */
    fun submitPoliceCallRecord(vo: PoliceStationRecordEntity) {
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .submitPoliceCallRecord(vo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<Boolean>>() {

                override fun onStart() {
                }

                override fun onCompleted() {
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

                override fun onNext(resultData: BaseData<Boolean>?) {
                    if (resultData != null) {
                        if (resultData.errcode == 0) {

                        } else {
                        }
                    } else {
                    }
                }
            })
    }

    /**
     * 获取咨询详情
     */
    fun loadMeetDetails(id: String) {
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .loadMeetDetails(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<DjThreeMeetInfoDTO>>() {

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

                override fun onNext(resultData: BaseData<DjThreeMeetInfoDTO>?) {
                    if (resultData != null) {
                        if (resultData.errcode == 0 && resultData.data != null) {
                            view.loadComplete(resultData.data)
                        } else {
                            view.showTip(resultData.errmsg)
                        }
                    } else {
                        view.showTip("服务器无数据返回...")
                    }
                }
            })
    }

//    /**
//     * 安民 > 法律咨询 > 回复
//     */
//    fun addLawQuestionReply(consId: String, content: String) {
//
//        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
//            .create<AllApi>(AllApi::class.java)
//            .addLawQuestionReply(consId, content, false)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(object : Subscriber<BaseData<Boolean>>() {
//
//                override fun onStart() {
//                    super.onStart()
//                    view.loadingShow()
//                }
//
//                override fun onCompleted() {
//                    view.loadingDismiss()
//                }
//
//                override fun onError(e: Throwable) {
//                    view.showTip("访问出错，请稍后重试")
//                }
//
//                override fun onNext(resultData: BaseData<Boolean>?) {
//                    if (resultData != null) {
//                        view.loadComplete(resultData)
//                    } else {
//                        view.showTip("服务器无数据返回...")
//                    }
//                }
//            })
//    }

    fun loadLawActivityList(pageParam: PageReq<String>) {
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .loadLawActivityList(pageParam)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<PageData<LawActivityBean>>>() {

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

                override fun onNext(resultData: BaseData<PageData<LawActivityBean>>?) {
                    if (resultData != null) {
                        if (resultData.errcode == 0) {
                            view.loadComplete(resultData)
                        } else {
                            view.showTip(resultData.errmsg)
                        }

                    } else {
                        view.showTip("服务器无数据返回...")
                    }
                }
            })
    }

    /**
     * 获得普法活动列表
     */
    fun getLawActivityById(id: String) {
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .getLawActivityById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<LawActivityBean>>() {

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

                override fun onNext(resultData: BaseData<LawActivityBean>?) {
                    if (resultData != null) {
                        if (resultData.errcode == 0) {
                            view.loadComplete(resultData)
                        } else {
                            view.showTip(resultData.errmsg)
                        }
                    } else {
                        view.showTip("服务器无数据返回...")
                    }
                }
            })
    }

    fun closeLawConsultAnswer(id: String?) {

        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .closeLawConsultAnswer(id)
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
                        if (resultData.errcode == 0) {
                            view.load2Complete(resultData)
                        } else {
                            view.showTip(resultData.errmsg)
                        }
                    } else {
                        view.showTip("服务器无数据返回...")
                    }
                }
            })
    }

    fun leaveMeet(view: MeetingDetailsActivity, meetId: String, leaveReason: String) {

        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .leaveMeet(meetId, leaveReason)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<Boolean>>() {

                override fun onStart() {
                    view.showLoading()
                }

                override fun onCompleted() {
                    view.dismissLoading()
                }

                override fun onError(e: Throwable) {
                    view.dismissLoading()
                }

                override fun onNext(resultData: BaseData<Boolean>?) {
                    if (resultData != null) {
                        if (resultData.errcode == 0) {
                            view.leaveMeetEnd(resultData)
                        } else {
                            view.showLeaveMeetTip(resultData.errmsg)
                        }
                    } else {
                        view.showLeaveMeetTip("服务器无数据返回...")
                    }
                }
            })
    }

    fun attendMeet(view: MeetingDetailsActivity, meetId: String, signAddr: String) {
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .attendMeet(meetId, signAddr)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<Boolean>>() {

                override fun onStart() {
                    view.showLoading()
                }

                override fun onCompleted() {
                    view.dismissLoading()
                }

                override fun onError(e: Throwable) {
                    view.dismissLoading()
                }

                override fun onNext(resultData: BaseData<Boolean>?) {
                    if (resultData != null) {
                        if (resultData.errcode == 0) {
                            view.attendMeetEnd(resultData)
                        } else {
                            view.showLeaveMeetTip(resultData.errmsg)
                        }
                    } else {
                        view.showLeaveMeetTip("服务器无数据返回...")
                    }
                }
            })
    }
}