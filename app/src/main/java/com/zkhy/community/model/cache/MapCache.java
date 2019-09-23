package com.zkhy.community.model.cache;

import android.text.TextUtils;
import android.util.SparseArray;

/**
 * <pre>
 *  创建:  梁玉涛 2019/1/10 on 10:06
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class MapCache {

    public static String getFlowName(int key, String defaultValue) {
        // 处理办理事项：1.高龄补贴 2.一孩生育登记 3.二孩生育登记 4.汇川区残疾人证发放和管理
        // 5.居住证明 6.关系证明 7.政审证明 8.文化户口登记 9.小孩入学登记
        SparseArray<String> flowNameMap = new SparseArray<>();
        flowNameMap.put(1, "高龄补贴");
        flowNameMap.put(2, "一孩生育登记");
        flowNameMap.put(3, "二孩生育登记");
        flowNameMap.put(4, "残疾人证办理");
        flowNameMap.put(5, "居住证明");
        flowNameMap.put(6, "关系证明");
        flowNameMap.put(7, "政审证明");
        flowNameMap.put(8, "文化户口登记");
        flowNameMap.put(9, "小孩入学登记");

        String value = flowNameMap.get(key);
        return TextUtils.isEmpty(value) ? defaultValue : value;
    }
}
