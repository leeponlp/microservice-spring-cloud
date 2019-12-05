/*
 * Copyright By ZATI
 * Copyright By 3a3c88295d37870dfd3b25056092d1a9209824b256c341f2cdc296437f671617
 * All rights reserved.
 *
 * If you are not the intended user, you are hereby notified that any use, disclosure, copying, printing, forwarding or
 * dissemination of this property is strictly prohibited. If you have got this file in error, delete it from your system.
 */
package com.suxiaocheng.framework.validate;

import com.suxiaocheng.framework.validate.annotation.RequestFieldValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;


@Component
public class RequestFieldValidator implements ConstraintValidator<RequestFieldValidate, String> {

    @Autowired
    private RequestValidateConfig requestValidateConfig;

    private static final Map<String, Pattern> matchMaps = new HashMap<>(24);


    public RequestFieldValidator() {
        //全角文字型（排除全角片假名, 包括空格）
        matchMaps.put("fullWidthText", Pattern.compile("^[Ａ-ｚ０-９ぁ-ん一-龠々ー\\u3000]+$"));
        //全角片假名（カナ），全角英字，全角数字
        matchMaps.put("fullWidthKatakanas", Pattern.compile("^((?!＾|＿)[ァ-ヾー０-９Ａ-ｚ])+$"));//^((?!＾|＿)[ァ-ンー０-９Ａ-ｚ])+$
        //全角文字型（包括特殊字符）
        matchMaps.put("fullWidthCharacter", Pattern.compile("^[^\\x00-\\xff]+$"));
        //全角字符('谢ひらがなコン１２３ａＡ々ー＃＄％＾＆＊（）＿＋－')
        matchMaps.put("fullWidthCharactors", Pattern.compile("^[\\uFF00-\\uFFFFぁ-んァ-ン一-龠々ー]+$"));
        //汉字文字型
        matchMaps.put("fullWidthChineseText", Pattern.compile("^[\\u4e00-\\u9fa5〇]+$"));
        //全角英数字
        matchMaps.put("fullWidthAlphabetsNumbers", Pattern.compile("^[Ａ-ｚ０-９]+$"));
        //全角数字('１２３')
        matchMaps.put("fullWidthNumbers", Pattern.compile("^[０-９]+$"));

        //全角文字型
        matchMaps.put("fullWidthKatakanasAddress", Pattern.compile("^[\\u4e00-\\u9fa5０-９Ａ-ｚ－]+$"));


        //全角英文
        matchMaps.put("fullWidthAlphabets", Pattern.compile("^[Ａ-ｚ]+$"));
        //全角片假名('コンー')
        matchMaps.put("fullWidthKatakana", Pattern.compile("^[ァ-ンー]+$"));

        //半角数字型
        matchMaps.put("halfWidthNumbers", Pattern.compile("^[0-9]+$"));


        //半角文字型（包括特殊符号）
        matchMaps.put("halfWidthText", Pattern.compile("^[\\u0000-\\u00FFｧ-ﾝﾞﾟー]+$"));
        //半角片假名
        matchMaps.put("halfWidthKatakanas", Pattern.compile("^[ｧ-ﾝﾞﾟー-]+$"));
        //半角英数字
        matchMaps.put("halfWidthAlphabetsNumbers", Pattern.compile("^[a-zA-Z0-9]+$"));


        //半角片假名地址
        matchMaps.put("halfWidthKatakanasAddress", Pattern.compile("^[ｧ-ﾝﾞﾟー0-9-]+$"));

        //半角英数字
        matchMaps.put("halfWidthAlphabets", Pattern.compile("^[a-zA-Z]+$"));

        //半角账户人姓名
        matchMaps.put("japaneseAccountName", Pattern.compile("^[ｧ-ﾝﾞﾟーA-Z\\/0-9().\\u0020]+$"));


        //可以输入：非空字符串,
        matchMaps.put("nonEmptyString", Pattern.compile("[^\\s]"));
        //不校验格式只校验字符的邮箱
        matchMaps.put("noFormatMail" ,Pattern.compile("^[A-Za-z0-9.@_-]+$"));

        //邮箱正则
        matchMaps.put("mailReg", Pattern.compile("^[A-Za-z0-9._-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$"));

        //7位半角数字('3567263')
        matchMaps.put("halfWidthNumbers7", Pattern.compile("^[0-9]{7}$"));

        //全角姓名
        matchMaps.put("fullWithName", Pattern.compile("^[^\\x00-\\xff]+$"));


        //全角住址
        matchMaps.put("fullWidthAddress", Pattern.compile("^[^\\x00-\\xff]+$"));


    }


    private Pattern customRegex;

    @Override
    public void initialize(RequestFieldValidate constraintAnnotation) {

        customRegex = matchMaps.get(Optional.ofNullable(constraintAnnotation.key()).orElse("fullWidthText"));
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (null == requestValidateConfig.getValidateField() || !requestValidateConfig.getValidateField()) {
            return true;
        }

        if (null == value || "".equals(value) ) {
//            context.disableDefaultConstraintViolation();//禁用默认的message的值
//            //重新添加错误提示语句
//            context.buildConstraintViolationWithTemplate("字符串不能为空").addConstraintViolation();
            return true;
        }

        return customRegex.matcher(value).matches();
    }
}
