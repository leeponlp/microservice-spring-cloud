package com.leepon.cloud.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName StringTool
 * @Description TODO...
 * @Author 苏小城
 * @Date 2019-4-1 17:56
 * @Version 1.0
 */
public class StringTool {

    private final static String EMPTY = "";

    /**
     * 清除search字符
     */
    public static String clear(String src, String search) {
        if (src == null) return null;
        return StringUtils.replace(src, search, EMPTY);
    }

    /**
     * 清除字符集
     */
    public static String clear(String src, String... searchList) {
        if (src == null) return null;
        if (searchList == null || searchList.length <= 0) return src;
        for (String s : searchList) {
            src = clear(src, s);
        }
        return src;
    }

    /**
     * 首字母大写
     *
     * @param value 字符串
     * @return 转换后的信息
     */
    public static String firstToUpper(String value) {
        if (StringUtils.isBlank(value))
            return "";
        value = StringUtils.trim(value);
        String f = StringUtils.substring(value, 0, 1);
        String s = "";
        if (value.length() > 1) {
            s = StringUtils.substring(value, 1);
        }
        return f.toUpperCase() + s;
    }
}
