package com.zkhy.fenggang.community.model.bean;

/**
 * <pre>
 *  创建:  梁玉涛 2019/4/17 on 16:49
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class DictionaryEntity {
    public static String ALL_CODE = "all_code";
//     "code": "wumin_stable_problem_civil",
//             "name": "民事类"


    public DictionaryEntity() {
    }

    public DictionaryEntity(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
