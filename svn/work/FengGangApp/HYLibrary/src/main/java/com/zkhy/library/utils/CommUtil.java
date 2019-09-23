package com.zkhy.library.utils;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 *  创建:  梁玉涛 2019/1/3 on 14:30
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class CommUtil {
    /**
     * 手机号：验证正确性
     *
     * @param userPhone
     * @return
     */
    public static boolean checkPhoneNum(String userPhone) { //^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$
        Pattern pattern = Pattern.compile("^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$");
        Matcher isNum = pattern.matcher(userPhone);
        return isNum.matches();
    }

    /**
     * 手机号验证
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    /**
     * 电话号码验证
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isPhone(String str) {
        Pattern p1 = null, p2 = null;
        Matcher m = null;
        boolean b = false;
        p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");  // 验证带区号的
        p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");         // 验证没有区号的
        if (str.length() > 9) {
            m = p1.matcher(str);
            b = m.matches();
        } else {
            m = p2.matcher(str);
            b = m.matches();
        }
        return b;
    }

    /**
     * 是手机号或者座机号
     *
     * @param phoneValue
     * @return
     */
    public static boolean isPhoneNum(String phoneValue) {
        return isMobile(phoneValue) || isPhone(phoneValue);
    }
}
