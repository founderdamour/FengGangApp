package com.zkhy.community.model.cache;

import com.alibaba.fastjson.JSON;
import com.sinothk.comm.utils.PreferUtil;
import com.sinothk.widget.bannerView.style1.ext.BannerBean;
import com.zkhy.community.BuildConfig;
import com.zkhy.community.model.bean.LatLngEntity;
import com.zkhy.community.model.bean.TerminalLoginDTO;
import com.zkhy.community.model.bean.WmUser;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/22 on 14:15
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class DataCache extends StaticCache {

    public static void clearAll() {
        try {
            PreferUtil.set("userToken", "");

            PreferUtil.set("loginUserId", "");
            PreferUtil.set("loginUserAccount", "");
            PreferUtil.set("loginUserInfo", "");
            PreferUtil.set("rolesInfo", "");

//            PreferUtil.set("NeedTipInputUserInfo", 1);
            setNeedTipInputUserInfo(false);

            PreferUtil.set("isAutoLogin", false);

        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace();
            }
        }
    }

    //    /**
//     * 保存用户信息
//     *
//     * @param loginInfo
//     */
    public static void saveUserToken(TerminalLoginDTO loginInfo) {
        try {
            if (loginInfo.getUserToken() != null) {
                PreferUtil.set("userToken", loginInfo.getUserToken().getToken());
            }
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace();
            }
        }
    }

    public static String getUserToken() {
        try {
            return (String) PreferUtil.get("userToken", "");
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace();
            }
            return "";
        }
    }

    /**
     * 保存用户信息
     *
     * @param loginUserInfo
     */
    public static void saveLoginUserInfo(WmUser loginUserInfo) {
        try {
            if (loginUserInfo != null) {

                PreferUtil.set("loginUserId", loginUserInfo.getUserId());
                PreferUtil.set("loginUserAccount", loginUserInfo.getAccount());

                String userInfoStr = JSON.toJSONString(loginUserInfo);
                PreferUtil.set("loginUserInfo", userInfoStr);

                String rolesInfo = JSON.toJSONString(loginUserInfo.getRoles());
                PreferUtil.set("rolesInfo", rolesInfo);
            }
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace();
            }
        }
    }

    public static String getUserId() {
        try {
            return (String) PreferUtil.get("loginUserId", "");
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace();
            }
            return "";
        }
    }

    public static String getUserAccount() {
        try {
            return (String) PreferUtil.get("loginUserAccount", "");
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace();
            }
            return "";
        }
    }

    public static WmUser getUserInfo() {
        try {
            String userInfoStr = (String) PreferUtil.get("loginUserInfo", "");
            return JSON.parseObject(userInfoStr, WmUser.class);
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace();
            }
            return new WmUser();
        }
    }

    public static List<String> getUserRoles() {
        try {
            String rolesStr = (String) PreferUtil.get("rolesInfo", "");
            List<String> rolesList = JSON.parseArray(rolesStr, String.class);
            return rolesList;
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace();
            }
            return new ArrayList<>();
        }
    }

    public static void setHomeBannerInfo(ArrayList<BannerBean> netDataList) {
        try {
            if (netDataList != null) {
                String homeBannerDataList = JSON.toJSONString(netDataList);
                PreferUtil.set("homeBannerDataList", homeBannerDataList);
            }
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<BannerBean> getHomeBannerInfo() {
        try {
            String homeBannerDataListStr = (String) PreferUtil.get("homeBannerDataList", "");
            List<BannerBean> homeBannerDataList = JSON.parseArray(homeBannerDataListStr, BannerBean.class);
            return (ArrayList<BannerBean>) homeBannerDataList;
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace();
            }
            return new ArrayList<>();
        }
    }

    public static void setAutoLogin(boolean isAutoLogin) {
        PreferUtil.set("isAutoLogin", isAutoLogin);
    }

    public static boolean isAutoLogin() {
        return (boolean) PreferUtil.get("isAutoLogin", false);//"true".equals();
    }

    /**
     * 保存普法活动
     *
     * @param netDataList
     */
    public static void setLawActivityList(ArrayList<BannerBean> netDataList) {
        try {
            if (netDataList != null) {
                String homeBannerDataList = JSON.toJSONString(netDataList);
                PreferUtil.set("lawActivityList", homeBannerDataList);
            }
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 返回普法活动列表
     *
     * @return
     */
    public static ArrayList<BannerBean> getLawActivityList() {
        try {
            String homeBannerDataListStr = (String) PreferUtil.get("lawActivityList", "");
            List<BannerBean> homeBannerDataList = JSON.parseArray(homeBannerDataListStr, BannerBean.class);
            return (ArrayList<BannerBean>) homeBannerDataList;
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace();
            }
            return new ArrayList<>();
        }
    }

    /**
     * 版本信息提醒标志
     *
     * @param hasNew
     */
    public static void setNewVersion(boolean hasNew) {
        if (hasNew) {
            PreferUtil.set("haveNewVersion", 1);
        } else {
            PreferUtil.set("haveNewVersion", 0);
        }
    }

    public static boolean getNewVersion() {
        int value = (int) PreferUtil.get("haveNewVersion", 0);
        return value == 1;
    }

    public static void saveLatLng(LatLngEntity latLng) {
        try {
            if (latLng != null) {
                String latLngStr = JSON.toJSONString(latLng);
                PreferUtil.set("latLng", latLngStr);
            }
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace();
            }
        }
    }

    public static LatLngEntity findLatLng() {
        try {
            String latLngStr = (String) PreferUtil.get("latLng", "");
            return JSON.parseObject(latLngStr, LatLngEntity.class);
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace();
            }
            return new LatLngEntity(27.7220800000, 106.9245300000);
        }
    }

    public static void setNeedTipInputUserInfo(boolean needTip) {
        if (needTip) {
            PreferUtil.set("NeedTipInputUserInfo", 1);
        } else {
            PreferUtil.set("NeedTipInputUserInfo", 0);
        }
    }

    public static boolean getNeedTipInputUserInfo() {
        int value = (int) PreferUtil.get("NeedTipInputUserInfo", 0);
        return value == 1;
    }


    public static void setNoticeTxtList(ArrayList<String> noticeList) {
        try {
            if (noticeList != null) {
                String noticeListStr = JSON.toJSONString(noticeList);
                PreferUtil.set("noticeList", noticeListStr);
            }
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<String> getNoticeTxtList() {
        try {
            String noticeListStr = (String) PreferUtil.get("noticeList", "");
            List<String> homeBannerDataList = JSON.parseArray(noticeListStr, String.class);
            return (ArrayList<String>) homeBannerDataList;
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace();
            }
            return new ArrayList<>();
        }
    }
}
