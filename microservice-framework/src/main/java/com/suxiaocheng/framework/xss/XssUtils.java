/*
  * Copyright By ZATI
  * Copyright By 3a3c88295d37870dfd3b25056092d1a9209824b256c341f2cdc296437f671617
  * All rights reserved.
  *
  * If you are not the intended user, you are hereby notified that any use, disclosure, copying, printing, forwarding or
  * dissemination of this property is strictly prohibited. If you have got this file in error, delete it from your system.
  */
package com.suxiaocheng.framework.xss;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XssUtils {

    private static List<Pattern> patternList = new ArrayList<>(13);
    static {
        patternList.add(Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE));
        patternList.add(Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL));
        patternList.add(Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL));
        patternList.add(Pattern.compile("</script>", Pattern.CASE_INSENSITIVE));
        patternList.add(Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL));
        patternList.add(Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL));
        patternList.add(Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL));
        patternList.add(Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE));
        patternList.add(Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE));
        patternList.add(Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL));
        patternList.add(Pattern.compile("type[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL));
        patternList.add(Pattern.compile("type[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL));
        patternList.add(Pattern.compile("<[\r\n]*(.*?)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL));
    }






    private XssUtils() {
    }

    /**
     * 替换xss关键字
     * @param value
     * @return
     */
    public static String stripXSS(String value) {
        String rlt = value;
        if (StringUtils.isNotBlank(value)) {
            for (Pattern scriptPattern : patternList) {
                rlt = scriptPattern.matcher(rlt).replaceAll("");
            }

        }

        return rlt;
    }

    /***
     * 是否包含xss 内容
     * @param value
     * @return
     */
    public static Boolean isContainXSS(String value) {
        if (StringUtils.isNotBlank(value)) {
            for (Pattern scriptPattern : patternList) {
                Matcher matcher = scriptPattern.matcher(value);
                if (matcher.find()) {
                    return true;
                }
            }
        }

        return false;
    }


}
