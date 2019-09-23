package com.zkhy.fenggang.community.presenter

import com.sinothk.rxretrofit.RxRetrofit
import com.zkhy.fenggang.community.model.api.*
import com.zkhy.fenggang.community.model.bean.*
import com.zkhy.fenggang.community.view.main.am.AmLawHomeActivity
import com.zkhy.library.mvp.AndroidExt2View
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.ArrayList

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/10 on 17:01
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
class AMPresenter(private val view: AndroidExt2View) {

    /**
     * 获得问答列表
     */
    fun loadLawQAList(pageParam: PageReq<CommReq>) {
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .loadLawQAList(pageParam)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<PageData<LawQuestionEntity>>>() {

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

                override fun onNext(resultData: BaseData<PageData<LawQuestionEntity>>?) {
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
     * 获得我的法律咨询列表
     */
    fun loadLawConsultHistoryList(pageParam: PageReq<String>) {
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .loadLawConsultHistoryList(pageParam)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<PageData<LawQuestionEntity>>>() {

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

                override fun onNext(resultData: BaseData<PageData<LawQuestionEntity>>?) {
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
    fun getConsultAnswerDetailsById(id: String) {
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .getConsultAnswerDetailsById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<LawQuestionDetailsBean>>() {

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

                override fun onNext(resultData: BaseData<LawQuestionDetailsBean>?) {
                    if (resultData != null) {
                        view.loadComplete(resultData)
                    } else {
                        view.showTip("服务器无数据返回...")
                    }
                }
            })
    }

    /**
     * 安民 > 法律咨询 > 回复
     */
    fun addLawQuestionReply(answer: CommReq) {

        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .addLawQuestionReply(answer)
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

    fun getLawCount() {
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .lawCount
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<LawCountEntity>>() {

                override fun onCompleted() {
                }

                override fun onError(e: Throwable) {
                    view.load2Complete(null)
                }

                override fun onNext(resultData: BaseData<LawCountEntity>?) {
                    if (resultData != null) {
                        if (resultData.errcode == 0) {
                            view.load2Complete(resultData)
                        } else {
                            view.load2Complete(null)
                        }

                    } else {
                        view.load2Complete(null)
                    }
                }
            })
    }

    fun getOnlineLawyer(view: AmLawHomeActivity) {

        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .onlineLawyer
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<ArrayList<BizUserEntity>>>() {

                override fun onCompleted() {
                }

                override fun onError(e: Throwable) {
                    view.showOnlineLawyer(null)
                }

                override fun onNext(resultData: BaseData<ArrayList<BizUserEntity>>?) {
                    if (resultData != null) {
                        if (resultData.errcode == 0) {
                            view.showOnlineLawyer(resultData)
                        } else {
                            view.showOnlineLawyer(null)
                        }
                    } else {
                        view.showOnlineLawyer(null)
                    }
                }
            })
    }

    fun createImpeach(entity: ImpeachEntity) {

        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create(AllApi::class.java)
            .createImpeach(entity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<ImpeachEntity>>() {

                override fun onStart() {
                    view.loadingShow()
                }

                override fun onCompleted() {
                    view.loadingDismiss()
                }

                override fun onError(e: Throwable) {
                    view.showTip(e.message)
                    view.loadingDismiss()
                }

                override fun onNext(resultData: BaseData<ImpeachEntity>?) {
                    if (resultData != null) {
                        if (resultData.errcode == 0) {
                            view.showTip("提交成功")
                            view.loadComplete(resultData.data)
                        } else {
                            view.showTip(resultData.errmsg)
                        }
                    } else {
                        view.showTip("服务器数据异常")
                    }
                }
            })
    }

    fun loadImpeachList(page: PageReq<ImpeachEntity>) {

        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .loadImpeachList(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<PageData<ImpeachEntity>>>() {

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

                override fun onNext(resultData: BaseData<PageData<ImpeachEntity>>?) {
                    if (resultData != null) {
                        view.loadComplete(resultData)
                    } else {
                        view.showTip("服务器无数据返回...")
                    }
                }
            })

    }

    fun loadImpeach(id: String) {

        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create<AllApi>(AllApi::class.java)
            .loadImpeach(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<ImpeachEntity>>() {

                override fun onStart() {
                    view.loadingShow()
                }

                override fun onCompleted() {
                    view.loadingDismiss()
                }

                override fun onError(e: Throwable) {
                    view.showTip(e.message)
                    view.loadingDismiss()
                }

                override fun onNext(resultData: BaseData<ImpeachEntity>?) {
                    if (resultData != null) {
                        if (resultData.errcode == 0) {
                            view.loadComplete(resultData.data)
                        } else {
                            view.showTip(resultData.errmsg)
                        }
                    } else {
                        view.showTip("服务器数据异常")
                    }
                }
            })
    }

    /**
     * 投诉：新增
     */
    fun createComplaint(entity: ComplaintEntity) {

        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create(AllApi::class.java)
            .createComplaint(entity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<ComplaintEntity>>() {

                override fun onStart() {
                    view.loadingShow()
                }

                override fun onCompleted() {
                    view.loadingDismiss()
                }

                override fun onError(e: Throwable) {
                    view.showTip(e.message)
                    view.loadingDismiss()
                }

                override fun onNext(resultData: BaseData<ComplaintEntity>?) {
                    if (resultData != null) {
                        if (resultData.errcode == 0) {
                            view.showTip("提交成功")
                            view.loadComplete(resultData.data)
                        } else {
                            view.showTip(resultData.errmsg)
                        }
                    } else {
                        view.showTip("服务器数据异常")
                    }
                }
            })
    }

    /**
     * 获得投诉记录列表
     */
    fun loadComplaintList(page: PageReq<ComplaintEntity>) {

        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create(AllApi::class.java)
            .loadComplaintList(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<PageData<ComplaintEntity>>>() {

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

                override fun onNext(resultData: BaseData<PageData<ComplaintEntity>>?) {
                    if (resultData != null) {
                        view.loadComplete(resultData)
                    } else {
                        view.showTip("服务器无数据返回...")
                    }
                }
            })

    }

    fun loadComplaintDetail(id: String) {

        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create(AllApi::class.java)
            .loadComplaintDetail(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<ComplaintEntity>>() {

                override fun onStart() {
                    view.loadingShow()
                }

                override fun onCompleted() {
                    view.loadingDismiss()
                }

                override fun onError(e: Throwable) {
                    view.showTip(e.message)
                    view.loadingDismiss()
                }

                override fun onNext(resultData: BaseData<ComplaintEntity>?) {
                    if (resultData != null) {
                        if (resultData.errcode == 0) {
                            view.loadComplete(resultData.data)
                        } else {
                            view.showTip(resultData.errmsg)
                        }
                    } else {
                        view.showTip("服务器数据异常")
                    }
                }
            })
    }

    fun loadLawyerDetail(bizId: String?) {
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create(AllApi::class.java)
            .loadLawyerDetail(bizId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<LawyerInfoEntity>>() {

                override fun onStart() {
                    view.loadingShow()
                }

                override fun onCompleted() {
                    view.loadingDismiss()
                }

                override fun onError(e: Throwable) {
                    view.showTip(e.message)
                    view.loadingDismiss()
                }

                override fun onNext(resultData: BaseData<LawyerInfoEntity>?) {
                    if (resultData != null) {
                        if (resultData.errcode == 0) {
                            view.loadComplete(resultData.data)
                        } else {
                            view.showTip(resultData.errmsg)
                        }
                    } else {
                        view.showTip("服务器数据异常")
                    }
                }
            })
    }

    fun loadDocDetail(bizId: String?) {
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap())
            .create(AllApi::class.java)
            .loadHomeDocDetail(bizId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<HomeDocInfoEntity>>() {

                override fun onStart() {
                    view.loadingShow()
                }

                override fun onCompleted() {
                    view.loadingDismiss()
                }

                override fun onError(e: Throwable) {
                    view.showTip(e.message)
                    view.loadingDismiss()
                }

                override fun onNext(resultData: BaseData<HomeDocInfoEntity>?) {
                    if (resultData != null) {
                        if (resultData.errcode == 0) {
                            view.loadComplete(resultData.data)
                        } else {
                            view.showTip(resultData.errmsg)
                        }
                    } else {
                        view.showTip("服务器数据异常")
                    }
                }
            })
    }
}