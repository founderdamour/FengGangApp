package com.zkhy.fenggang.community.model.api;

import com.zkhy.fenggang.comm.plugin.version.VersionEntity;
import com.zkhy.fenggang.community.model.bean.*;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;

import retrofit2.Call;
import retrofit2.http.*;
import rx.Observable;

import java.util.ArrayList;
import java.util.Map;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/5 on 17:11
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public interface AllApi {

    /**
     * 文件上传接口
     *
     * @param bizId
     * @param bizType
     * @param file
     * @return
     */
    @Multipart
    @POST("wmapi/admin/file/upload")
    Observable<BaseData<AttachmentEntity>> uploadFile(@Query("bizId") String bizId, @Query("bizType") String bizType, @Part MultipartBody.Part file);

    @POST("wmapi/admin/file/remove")
    Observable<BaseData<Boolean>> delFileSysById(@Query("ids[]") String[] ids);

    /**
     * 获取验证码
     *
     * @param userPhone
     * @return
     */
    @POST("wmapi/biz/wmUser/register/sms/send")
    Observable<BaseData<String>> registerCode(@Query("phone") String userPhone);

    /**
     * 重置密码
     *
     * @param pageParam
     * @return
     */
    @POST("wmapi/biz/wmUser/reset/password/update")
    Observable<BaseData<String>> changeUserPwd(@Body CommReq pageParam);

    /**
     * 验证用户是否完善基本信息
     *
     * @param userId
     * @return
     */
    @POST("wmapi/biz/wmUser/perfect/info/validate")
    Observable<BaseData<String>> checkUserInfo(@Query("userId") String userId);

    /**
     * 注册
     *
     * @param pageParam
     * @return
     */
    @POST("wmapi/biz/wmUser/register/save")
    Observable<BaseData<RegisterEntity>> registerUser(@Body CommReq pageParam);

    @GET("wmapi/admin/user/usertoken")
    Observable<BaseData<TerminalLoginDTO>> login(@Query("username") String account, @Query("password") String pwd);

    /**
     * 获取用户信息
     *
     * @return
     */
    @GET("wmapi/biz/wmUser/getUserInfo")
    Observable<BaseData<WmUser>> loadingUserInfo();

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    @POST("wmapi/biz/wmUser/update")
    Observable<BaseData<WmUser>> updateUserInfo(@Body WmUser user);

    /**
     * 根据账号找手机号
     *
     * @param account
     * @return
     */
    @GET("wmapi/admin/user/account/get")
    Observable<BaseData<WmUser>> getUserPhoneByAccount(@Query("account") String account);

    /**
     * 根据账号或身份证找用户账号信息
     *
     * @param idcard
     * @return
     */
    @GET("wmapi/admin/user/idcard/get")
    Observable<BaseData<WmUser>> getUserAccountInfoByAccountOrIDCard(@Query("idcard") String idcard);

    /**
     * 密码》修改验证码
     *
     * @param phone
     * @return
     */
    @POST("wmapi/biz/wmUser/reset/password/sms/send")
    Observable<BaseData<String>> resetPwdPhoneCode(@Query("phone") String phone);

    /**
     * 获得部门列表
     *
     * @return
     */
    @GET("wmapi/biz/CommonExt/find")
    Observable<BaseData<ArrayList<DepartmentEntity>>> loadDepartmentList();

    /**
     * 获得办事指南列表
     *
     * @param pageParam
     * @return
     */
    @POST("wmapi/biz/BmFlowGuidance/page")
    Observable<BaseData<PageData<BizDeptFunctionEntity>>> loadBizIndexList(@Body PageReq pageParam);

    /**
     * 获取指南详情
     *
     * @param id
     * @return
     */
    @GET("wmapi/biz/BmFlowGuidance/find")
    Observable<BaseData<FlowInfoEntity>> loadHandleInfo(@Query("id") String id);


    /**
     * 获得预约排班信息
     *
     * @param appnoTypeCode
     * @return
     */
    @GET("wmapi/biz/appointment/getAppointmentInfo")
    Observable<BaseData<AppointmentBaseEntity>> getBmOrderDate(@Query("appnoTypeCode") String appnoTypeCode);

    /**
     * 获得某天的预约情况
     *
     * @param id
     * @param currDate
     * @return
     */
    @GET("wmapi/biz/appointment/getAppByDateOrId")
    Observable<BaseData<AppointmentDayEntity>> getAppointmentStateByDate(@Query("id") String id, @Query("currDate") String currDate);

    /**
     * 提交预约信息
     *
     * @param dto
     * @return
     */
    @POST("wmapi/biz/appointment/save")
    Observable<BaseData<AppointmentResultEntity>> submitBmOrder(@Body AppointmentVo dto);

    /**
     * 办事: 人办新增
     *
     * @param vo
     * @return
     */
    @POST("wmapi/biz/personwork/flow/save")
    Observable<BaseData<PersonWorkEntity>> personWorkFlowSave(@Body PersonWorkVo vo);

    /**
     * 办事: 获取办事详情
     *
     * @param flowId
     * @param id
     * @return
     */
    @GET("wmapi/biz/personwork/flow/detail/get")
    Observable<BaseData<HandleDetailsEntity>> loadHandleDetails(@Query("flowId") int flowId, @Query("id") String id);

    /**
     * 查询办事进度：所有列表
     *
     * @param pageParam
     * @return
     */
    @POST("wmapi/biz/personwork/flow/person/page")
    Observable<BaseData<PageData<HandleProgressEntity>>> loadAllHandleProgressList(@Body PageReq pageParam);

    /**
     * 乐民/场所列表
     *
     * @param pageParam
     * @return
     */
    @POST("wmapi/biz/culturepalce/findList")
    Observable<BaseData<PageData<StadiumEntity>>> loadStadiumList(@Body PageReq<CommReq> pageParam);

    /**
     * 乐民/场所详情
     *
     * @param id
     * @return
     */
    @GET("wmapi/biz/culturepalce/getById")
    Observable<BaseData<StadiumEntity>> loadStadiumDetails(@Query("id") String id);

    /**
     * 乐民/周边活动列表
     *
     * @param pageParam
     * @return
     */
    @POST("wmapi/biz/aroundactivity/findList")
    Observable<BaseData<PageData<NearbyActiveEntity>>> loadNearbyActiveList(@Body PageReq<CommReq> pageParam);

    /**
     * 获得乐民/周边活动详情
     *
     * @param id
     * @return
     */
    @GET("wmapi/biz/aroundactivity/getById")
    Observable<BaseData<NearbyActiveEntity>> loadNearbyActiveDetails(@Query("id") String id);

    /**
     * 安民/法律问答列表
     *
     * @param pageParam
     * @return
     */
    @POST("wmapi/biz/lawyerQuestion/page")
    Observable<BaseData<PageData<LawQuestionEntity>>> loadLawQAList(@Body PageReq<CommReq> pageParam);

    /**
     * 安民/法律我的咨询
     *
     * @return
     */
    @POST("wmapi/biz/lawyerConsult/getMyConsult")
    Observable<BaseData<PageData<LawQuestionEntity>>> loadLawConsultHistoryList(@Body PageReq<String> pageParam);

    /**
     * 安民/法律服务 > 律师详情
     *
     * @return
     */
    @GET("wmapi/biz/lawyer/getById")
    Observable<BaseData<LawyerInfoEntity>> loadLawyerDetail(@Query("id") String bizId);

    /**
     * 安民/法律 > 关闭问题
     *
     * @param id
     * @return
     */
    @POST("wmapi/biz/lawyerConsult/closeQuestion")
    Observable<BaseData<Boolean>> closeLawConsultAnswer(@Query("id") String id);

    @GET("wmapi/biz/lawyerConsult/getStableInfoNum")
    Observable<BaseData<LawCountEntity>> getLawCount();

    /**
     * 安民/新增法律我的咨询
     *
     * @param question
     * @return
     */
    @POST("wmapi/biz/lawyerConsult/add")
    Observable<BaseData<Boolean>> addMyLawConsult(@Query("question") String question);

    /**
     * 安民/律师事务所列表
     *
     * @param pageParam
     * @return
     */
    @POST("wmapi/biz/lawService/page")
    Observable<BaseData<PageData<LawFirmEntity>>> loadLawFirmList(@Body PageReq<CommReq> pageParam);

    /**
     * 安民/警务列表
     *
     * @param pageParam
     * @return
     */
    @POST("wmapi/biz/policeunit/page")
    Observable<BaseData<PageData<PoliceStationEntity>>> loadPoliceStationList(@Body PageReq<CommReq> pageParam);

    /**
     * 安民 > 新增报警记录
     *
     * @param vo
     * @return
     */
    @POST("wmapi/biz/callrecord/save")
    Observable<BaseData<Boolean>> submitPoliceCallRecord(@Body PoliceStationRecordEntity vo);

    /**
     * 获得便民查询 > 分类列表
     *
     * @param pageParam
     * @return
     */
    @POST("wmapi/biz/bmInformation/page")
    Observable<BaseData<PageData<ConvenienceQueryEntity>>> loadConvenienceQueryList(@Body PageReq<CommReq> pageParam);

    /**
     * 安民/普法活动列表
     *
     * @param pageParam
     * @return
     */
    @POST("wmapi/biz/lawActivity/page")
    Observable<BaseData<PageData<LawActivityBean>>> loadLawActivityList(@Body PageReq<String> pageParam);

    /**
     * 党建 > 党费未/已支付列表
     *
     * @param pageParam
     * @return
     */
    @POST("wmapi/biz/party/payment/findList")
    Observable<BaseData<PageData<PartyPayInfoEntity>>> loadDJUnpaidList(@Body PageReq<CommReq> pageParam);

    /**
     * 党建 > 更新缴费状态
     *
     * @param commParam
     * @return
     */
    @POST("wmapi/biz/party/payment/updatePayState")
    Observable<BaseData<String>> updatePartyPayStatus(@Body CommReq commParam);

    /**
     * 党建 > 宣传平台列表
     *
     * @param pageParam
     * @return
     */
    // @POST("api/biz/wechat/get/material")/systemset/page
    @POST("wmapi/biz//systemset/page")
    Observable<BaseData<PageData<WechatEntity>>> loadPropagandaListData(@Body PageReq<String> pageParam);

    /**
     * 助民 > 心愿清单
     *
     * @param pageParam
     * @return
     */
    @POST("wmapi/biz/wishAppMana/page")
    Observable<BaseData<PageData<WishEntity>>> loadWishList(@Body PageReq<CommReq> pageParam);

    /**
     * 康民 > 获得家庭医生问题库
     *
     * @param pageParam
     * @return
     */
    @POST("wmapi/biz/question/page")
    Observable<BaseData<PageData<HouseDocQAEntity>>> loadHouseDocQAList(@Body PageReq pageParam);

    /**
     * 康民 > 获得家庭医生详情
     *
     * @return
     */
    @GET("wmapi/biz/doctor/getById")
    Observable<BaseData<HomeDocInfoEntity>> loadHomeDocDetail(@Query("id") String bizId);

    /**
     * 新增家庭医生咨询
     *
     * @param question
     * @return
     */
    @GET("wmapi/biz/consult/add")
    Observable<BaseData<Boolean>> addMyDocConsult(@Query("question") String question);

    /**
     * 获得我的咨询列表
     *
     * @param pageParam
     * @return
     */
    @POST("wmapi/biz/consult/getMyConsult")
    Observable<BaseData<PageData<HouseDocQAEntity>>> loadHouseDocQAHistoryList(@Body PageReq<String> pageParam);

    /**
     * 康民》获得在线医生
     *
     * @return
     */
    @GET("wmapi/biz/doctor/getOnlineDoc")
    Observable<BaseData<ArrayList<BizUserEntity>>> loadOnlineDocList();


    /**
     * 助民 > 统计心愿
     *
     * @param vo
     * @return
     */
    @POST("wmapi/biz/wishcountapp/find")
    Observable<BaseData<CommResult>> loadWishTotal(@Body CommReq vo);

    /**
     * 助民 > 微心愿 > 发布
     *
     * @param vo
     * @return
     */
    @POST("wmapi/biz/wishAppMana/save")
    Observable<BaseData<Boolean>> publicWish(@Body CommReq vo);

    /**
     * 助民 > 微心愿 > 获取心愿详情
     *
     * @param vo
     * @return
     */
    @POST("wmapi/biz/wishAppMana/getWish")
    Observable<BaseData<WishEntity>> loadWishDetails(@Body CommReq vo);

    /**
     * 助民 > 微心愿 > 认领/完成
     *
     * @param vo
     * @return
     */
    @POST("wmapi/biz/wishAppMana/updateByClaim")
    Observable<BaseData<Boolean>> claimWish(@Body CommReq vo);

    /**
     * 助民 > 获得爱心筹集详情
     *
     * @param id
     * @return
     */
    @GET("wmapi/biz/raisingApp/get")
    Observable<BaseData<LoveHeartEntity>> loadLoveHeartDetails(@Query("id") String id);

    /**
     * 助民 > 爱心筹列表清单
     *
     * @param pageParam
     * @return
     */
    @POST("wmapi/biz/raisingApp/page")
    Observable<BaseData<PageData<LoveHeartEntity>>> loadLoveHeartList(@Body PageReq<String> pageParam);

    /**
     * 获得镇列表
     *
     * @param parentId
     * @return
     */
    @GET("wmapi/admin/area/find")
    Observable<BaseData<ArrayList<AreaDTO>>> loadTownList(@Query("parentId") String parentId);

    /**
     * 获得法律咨询>我的咨询及回复列表
     *
     * @param id
     * @return
     */
    @GET("wmapi/biz/lawyerConsultAnswer/getById")
    Observable<BaseData<LawQuestionDetailsBean>> getConsultAnswerDetailsById(@Query("id") String id);

    /**
     * 获得法律咨询>我的咨询 > 回复他人
     *
     * @param answer
     * @return
     */
    @POST("wmapi/biz/lawyerConsultAnswer/add")
    Observable<BaseData<Boolean>> addLawQuestionReply(@Body CommReq answer);


    /**
     * 获得家庭医生>我的咨询 > 我的咨询及回复列表
     *
     * @param id
     * @return
     */
    @GET("wmapi/biz/consultAnswer/getById")
    Observable<BaseData<DocQADetailsEntity>> getDocConsultAnswerDetailsById(@Query("id") String id);

    /**
     * 获得家庭医生咨询>我的咨询 > 回复他人
     *
     * @param answer
     * @return
     */
    @POST("wmapi/biz/consultAnswer/add")
    Observable<BaseData<Boolean>> addDocQuestionReply(@Body CommReq answer);

    /**
     * 医生> 关闭问题
     *
     * @param id
     * @return
     */
    @POST("wmapi/biz/consult/closeQuestion")
    Observable<BaseData<Boolean>> closeDocConsultAnswer(@Query("id") String id);

    /**
     * Home > 轮播图
     *
     * @param pageParam
     * @return
     */
    @POST("wmapi/biz/systemset/page")
    Observable<BaseData<PageData<BannerEntity>>> loadHomeBannerInfo(@Body PageReq<String> pageParam);

    /**
     * 获取数据字典
     *
     * @param typeCode
     * @return
     */
    @GET("wmapi/admin/dictionary/findDictionary")
    Observable<BaseData<Map<String, Map<String, String>>>> loadDictionary(@Query("typeCode[]") String[] typeCode);

    /**
     * 预约取件,预约记录
     *
     * @return
     */
    @GET("wmapi/biz/appointment/gettake")
    Observable<BaseData<ArrayList<OrderTakeFileEntity>>> loadOrderTakeFileList(@Query("status") int status);

    /**
     * 获取普法活动详情
     *
     * @param id
     * @return
     */
    @GET("wmapi/biz/lawActivity/getById")
    Observable<BaseData<LawActivityBean>> getLawActivityById(@Query("id") String id);

    /**
     * 安民/法律我的咨询
     *
     * @return
     */
    @GET("wmapi/admin/area/find")
    Observable<BaseData<ArrayList<AreaDTO>>> loadCommunitiesByStreetId(@Query("parentId") String parentId);

    @POST("wmapi/biz/wmUser/reset/password/captcha/check")
    Observable<BaseData<String>> findPwdShortMsgCheck(@Query("phone") String phone, @Query("captcha") String captcha);

    @GET("wmapi/biz/lawyer/getOnlineLaywer")
    Observable<BaseData<ArrayList<BizUserEntity>>> getOnlineLawyer();

    /**
     * 获取版本信息
     *
     * @return
     */
    @GET("wmapi/biz/clientVersion/getLastVersionInfo")
    Observable<BaseData<VersionEntity>> getLastVersionInfo(@Query("pversion") String pVersion);

    /**
     * 文件下载
     *
     * @param url
     * @return
     */
    @Streaming
    @GET
    Call<ResponseBody> downloadFile(@Url String url);

    /**
     * 新增版本下载数量
     *
     * @param id
     * @return
     */
    @GET("wmapi/biz/clientVersion/addUpdataOne")
    Observable<BaseData<Boolean>> updateDownLoadNum(@Query("id") String id);

    /**
     * 三会一课/所有会议列表
     *
     * @param pageParam
     * @return
     */
    @POST("wmapi/biz/Meeeting/page")
    Observable<BaseData<PageData<ThreeMeetEntity>>> loadMeetForAllList(@Body PageReq<CommReq> pageParam);

    /**
     * 三会一课/我的会议列表
     *
     * @param pageParam
     * @return
     */
    @POST("wmapi/biz/Meeeting/page")
    Observable<BaseData<PageData<ThreeMeetEntity>>> loadMeetForMeList(@Body PageReq<CommReq> pageParam);

    @GET("wmapi/biz/Meeeting/get")
    Observable<BaseData<DjThreeMeetInfoDTO>> loadMeetDetails(@Query("id") String id);

    @GET("wmapi/biz/Meeeting/leave")
    Observable<BaseData<Boolean>> leaveMeet(@Query("meetId") String meetId, @Query("leaveReason") String leaveReason);

    /**
     * 会议签到
     *
     * @param meetId
     * @param signAddr
     * @return
     */
    @GET("wmapi/biz/Meeeting/signIn")
    Observable<BaseData<Boolean>> attendMeet(@Query("meetId") String meetId, @Query("signAddr") String signAddr);

    // ===================================================================

    /**
     * 新增随手拍
     *
     * @param vo
     * @return
     */
    @POST("wmapi/biz/bmShotQuestion/save")
    Observable<BaseData<Boolean>> submitPhotosCreatorInfo(@Body PhotosCreatorVo vo);

    /**
     * 处理完成随手拍
     *
     * @param vo
     * @return
     */
    @POST("wmapi/biz/bmShotQuestion/workSceneDeal")
    Observable<BaseData<Boolean>> submitPhotosHandle(@Body PhotosCreatorVo vo);

    /**
     * 处理完成随手拍，评分
     *
     * @param qid
     * @param evaluateStar
     * @return
     */
    @GET("wmapi/biz/bmShotQuestion/evaluate")
    Observable<BaseData<Boolean>> bmShotQuestionEvaluate(@Query("qid") String qid, @Query("evaluateStar") String evaluateStar);

    /**
     * 获取我的随手拍：所有
     *
     * @param pageParam
     * @return
     */
    @POST("wmapi/biz/bmShotQuestion/page")
    Observable<BaseData<PageData<PhotoShotEntity>>> loadMyAllShotPhotoList(@Body PageReq<PhotosCreatorVo> pageParam);

    /**
     * 随手拍：上传处理人位置
     *
     * @param location
     * @return
     */
    @POST("wmapi/biz/bmShotQuestion/saveUpPlace")
    Observable<BaseData<Boolean>> uploadLocationInfo(@Body LocationEntity location);

    /**
     * 获取
     *
     * @param id
     * @return
     */
    @GET("wmapi/biz/bmShotQuestion/get")
    Observable<BaseData<BmShotQuestionInfoDTO>> loadShotPhotoDetail(@Query("id") String id);

    @GET("wmapi/admin/dictionary/getChild")
    Observable<BaseData<ArrayList<DictionaryEntity>>> loadDictionaryChild(@Query("code") String code);

    /**
     * 信访：新增
     *
     * @param impeach
     * @return
     */
    @POST("wmapi/biz/amNetLetter/save")
    Observable<BaseData<ImpeachEntity>> createImpeach(@Body ImpeachEntity impeach);

    /**
     * 信访：所有
     *
     * @param pageParam
     * @return
     */
    @POST("wmapi/biz/amNetLetter/page")
    Observable<BaseData<PageData<ImpeachEntity>>> loadImpeachList(@Body PageReq<ImpeachEntity> pageParam);

    @GET("wmapi/biz/amNetLetter/get/{id}")
    Observable<BaseData<ImpeachEntity>> loadImpeach(@Path("id") String id);

    @GET("wmapi/biz/bmShotQuestion/manEnterRoom")
    Observable<BaseData<String>> loadingVideoUrl();

    /**
     * 咨询投诉：新增
     *
     * @param impeach
     * @return
     */
    @POST("wmapi/biz/amComplaintAdvice/save")
    Observable<BaseData<ComplaintEntity>> createComplaint(@Body ComplaintEntity impeach);

    /**
     * 咨询投诉：所有
     *
     * @param pageParam
     * @return
     */
    @POST("wmapi/biz/amComplaintAdvice/page")
    Observable<BaseData<PageData<ComplaintEntity>>> loadComplaintList(@Body PageReq<ComplaintEntity> pageParam);

    /**
     * 咨询投诉：详情
     *
     * @param id
     * @return
     */
    @GET("wmapi/biz/amComplaintAdvice/{id}")
    Observable<BaseData<ComplaintEntity>> loadComplaintDetail(@Path("id") String id);

    /**
     * 加载党务公开列表数据
     *
     * @param pageParam
     * @return
     */
    @POST("wmapi/biz/djRulesParty/page")
    Observable<BaseData<PageData<DWOpenListEntity>>> loadDWOpenAllList(@Body PageReq<CommReq> pageParam);

    /**
     * 通知消息：所有列表
     *
     * @param pageParam
     * @return
     */
    @POST("wmapi/msgs/msgsCenterInfo/page")
    Observable<BaseData<PageData<NoticeMsgEntity>>> loadNoticeMsgList(@Body PageReq pageParam);

    /**
     * 通知消息：设置为已读
     *
     * @param ids
     * @return
     */
    @POST("wmapi/msgs/msgsCenterInfo/mark")
    Observable<BaseData<Boolean>> loadNoticeMsgStatus(@Query("receivedIdList[]") ArrayList<String> ids);

    /**
     * 通知消息：通知、未读提醒信息
     *
     * @param userId
     * @return
     */
    @GET("wmapi/msgs/msgsCenterInfo/get/pull/msgs")
    Observable<BaseData<NoticeTipEntity>> loadSysNotice(@Query("userId") String userId);

    /**
     * 煤矿监管系统矿点列表
     *
     * @param kbh
     * @return
     */
    @GET("KJWeb/AQJK/AqjkMkmcJson.aspx")
    Observable<ArrayList<MkMonitoringSystemEntity>> loadMkMonitoringSystemList(@Query("kbh") String kbh);

    /**
     * 煤矿监管系统列表
     *
     * @param kbh
     * @return
     */
    @GET("KJWeb/AQJK/XtcsztJson.aspx")
    Observable<ArrayList<MkMonitoringSystem2Entity>> loadMkMonitoringSystem2List(@Query("kbh") String kbh);

    /**
     * 煤矿监管系统详情
     *
     * @param kbh
     * @return
     */
    @GET("KJWeb/AQJK/SssjxsJson.aspx")
    Observable<ArrayList<MkMonitoringSystemDetailEntity>> loadMkMonitoringSystemDetail(@Query("kbh") String kbh);

    /**
     * 人员定位系统
     *
     * @param kbh
     * @return
     */
    @GET("KJWeb/RYDW/XtcsztJson.aspx")
    Observable<ArrayList<MkMonitoringSystem2Entity>> loadMkPersonLocationList(@Query("kbh") String kbh);

    /**
     * 人员定位系统详情
     *
     * @param kbh
     * @return
     */
    @GET("KJWeb/RYDW/CdryfbJson.aspx")
    Observable<ArrayList<MkPersonLocationDetailEntity>> loadMkPersonLocationDetail(@Query("kbh") String kbh);

    /**
     * 人员大屏
     *
     * @param kbh
     * @return
     */
    @GET("KJWeb/RYDW/JxrydpJson.aspx")
    Observable<ArrayList<MkPersonShowEntity>> loadMkPerson(@Query("kbh") String kbh);

    /**
     * 培训就业列表
     *
     * @param pageParam
     * @return
     */
    @POST("wmapi/biz/fgTrainJob/page")
    Observable<BaseData<PageData<TrainEmploymentEntity>>> loadPxJyList(@Body PageReq<CommReq> pageParam);

    /**
     * 房屋租赁
     *
     * @param pageParam
     * @return
     */
    @POST("wmapi/biz/fgRenting/page")
    Observable<BaseData<PageData<FgRentingEntity>>> loadFgRentingList(@Body PageReq<CommReq> pageParam);
}
