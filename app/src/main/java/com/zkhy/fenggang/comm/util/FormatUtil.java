package com.zkhy.fenggang.comm.util;

public class FormatUtil {

    /**
     * 将手机号码除了首三位和末四位的中间号码替换为*
     *
     * @param phoneNum 原手机号码;
     * @return 返回替换后的手机号码
     */
    public static String replacePhoneNum(String phoneNum) {
        if (phoneNum == null || phoneNum.length() < 11) {
            return "";
        }
        int len = phoneNum.length();
        return phoneNum.substring(0, len - 3) + "****" + phoneNum.substring(len - 7, len);
    }
}
