package com.zkhy.community.model.cache;

import com.zkhy.comm.plugin.entity.KValueEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *  创建:  梁玉涛 2019/1/8 on 11:31
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class StaticCache {

    private static Map<String, Map<String, String>> allMap;
    private static Map<String, String> level1Map;
    private static ArrayList<KValueEntity> nationList = new ArrayList<>();
    private static ArrayList<KValueEntity> educationList = new ArrayList<>();


    public static void saveDictionary(Map<String, Map<String, String>> maps) {
        if (maps == null) {
            return;
        }

        nationList.clear();
        educationList.clear();

        allMap = maps;
        level1Map = new HashMap<>();

        for (String key1 : maps.keySet()) {

            Map<String, String> l2Maps = maps.get(key1);
            if (l2Maps != null) {
                for (String key2 : l2Maps.keySet()) {
                    level1Map.put(key2, l2Maps.get(key2) + "");

                    if ("NATION".equals(key1)) {
                        nationList.add(new KValueEntity(key2, l2Maps.get(key2)));

                    } else if ("party_member_education".equals(key1)) {
                        educationList.add(new KValueEntity(key2, l2Maps.get(key2)));
                    }
                }
            }
        }
    }

    public static String findDictionary(String key) {
        if (key == null || level1Map == null) {
            return "";
        } else {
            return level1Map.get(key);
        }
    }

    public static ArrayList<KValueEntity> getNationList() {
        return nationList;
    }

    public static ArrayList<KValueEntity> getEducationList() {
        return educationList;
    }
}
