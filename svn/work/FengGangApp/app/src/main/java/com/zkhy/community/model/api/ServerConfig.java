package com.zkhy.community.model.api;

import android.util.Log;

import com.zkhy.community.model.cache.DataCache;

import java.util.HashMap;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/12 on 14:26
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class ServerConfig {

//    public static String baseUrl = "http://112.74.58.200:10086/"; // 正式
    public static String baseUrl = "http://58.16.181.23:10086/"; // 测试

    public static String mkUrl = "http://218.201.212.134/"; // 煤矿系统

    public static HashMap<String, String> getHeaderMap() {
        String userToken = DataCache.getUserToken();
        HashMap<String, String> map = new HashMap<>();
        map.put("token", userToken);
        map.put("user-token", userToken);
//        map.put("pversion", "gbl");
//       map.put("pversion", "madengbo");

        Log.e("userToken", userToken);
        return map;
    }

    public static String getImgUrl(String bizType, String id) {
        // "http://58.16.181.23:10086/api/admin/file/find/lm_aroundactivity/530326279774275137"
        return baseUrl + "api/admin/file/find/" + bizType + "/" + id;
    }
}
