package com.leepon.cloud.retrofit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 基于fastjson封装的工具包
 *
 * @author 苏小城
 */
public class FastJsonUtil {


    /**
     * 将JSON格式的字符串转换成任意Java类型的对象
     *
     * @param json JSON格式的字符串
     * @param type 任意Java类型
     * @return 任意Java类型的对象
     */
    public static <T> T toAny(String json, TypeReference<T> type) {
        return JSON.parseObject(json, type);
    }


    /**
     * 把JSON文本parse为JSONObject或者JSONArray
     *
     * @param str
     * @return
     */
    public static Object toObject(String text) {

        Object object = null;

        if (!StringUtils.isEmpty(text)) {
            object = JSON.parse(text);
        }
        return object;
    }


    /**
     * 把JSON文本parse成JSONObject
     *
     * @param str
     * @return
     */
    public static JSONObject toJSONObject(String text) {

        JSONObject jsonObject = null;

        if (!StringUtils.isEmpty(text)) {
            jsonObject = JSON.parseObject(text);
        }

        return jsonObject;
    }

    /**
     * 将JSON格式的字符串转换为java类型的对象或者java数组类型的对象，不包括java集合类型
     *
     * @param json JSON格式的字符串
     * @param clz  java类型或者java数组类型，不包括java集合类型
     * @return java类型的对象或者java数组类型的对象，不包括java集合类型的对象
     */
    public static <T> T toBean(String text, Class<T> clazz) {

        T t = null;

        if (!StringUtils.isEmpty(text)) {
            t = JSON.parseObject(text, clazz);
        }

        return t;
    }

    /**
     * 把JSON文本parse成JSONArray
     *
     * @param text
     * @return
     */
    public static JSONArray toJSONArray(String text) {

        JSONArray jsonArray = null;

        if (!StringUtils.isEmpty(text)) {
            jsonArray = JSON.parseArray(text);
        }
        return jsonArray;
    }

    /**
     * 将JavaBean序列化为JSON文本
     *
     * @param object
     * @return
     */
    public static String toJSONString(Object object) {

        String jsonString = "";

        if (object != null) {
            jsonString = JSON.toJSONString(object);
        }

        return jsonString;
    }

    /**
     * 将JavaBean序列化为带格式的JSON文本
     *
     * @param object
     * @param prettyFormat
     * @return
     */
    public static String toJSONString(Object object, boolean prettyFormat) {

        String jsonString = "";

        if (object != null) {
            jsonString = JSON.toJSONString(object, prettyFormat);
        }

        return jsonString;
    }

    /**
     * 将JavaBean转换为JSONObject或者JSONArray
     *
     * @param javaObject
     * @return
     */
    public static Object toJSON(Object javaObject) {

        Object json = null;

        if (javaObject != null) {
            json = JSON.toJSON(javaObject);
        }

        return json;
    }

    /**
     * 将Json文本转成HashMap
     *
     * @param text
     * @return
     */
    public static Map<String, Object> toHashMap(String text) {

        HashMap<String, Object> map = new HashMap<>(32);

        JSONObject jsonObject = toJSONObject(text);
        Set<String> set = jsonObject.keySet();
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String key = String.valueOf(it.next());
            Object value = jsonObject.get(key);
            map.put(key, value);
        }
        return map;
    }


    /**
     * 将Json文本转成HashMap
     *
     * @param text
     * @return
     */
    public static Map<String, Object> toHashMap(HashMap<String, Object> map, String text) {

        JSONObject jsonObject = toJSONObject(text);
        Set<String> set = jsonObject.keySet();
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String key = String.valueOf(it.next());
            Object value = jsonObject.get(key);
            map.put(key, value);
            if (isJson(value + "")) {
                toHashMap(map, value + "");
            }
        }
        return map;
    }

    /**
     * 将JSON格式的字符串转换为List<T>类型的对象
     *
     * @param text JSON格式的字符串
     * @param clz  指定泛型集合里面的T类型
     * @return List<T>类型的对象
     */
    public static <T> List<T> toList(String text, Class<T> clazz) {

        List<T> list = null;

        if (!StringUtils.isEmpty(text)) {
            list = JSON.parseArray(text, clazz);
        }
        return list;
    }

    /**
     * 判断字符串是否是json串
     *
     * @param content
     * @return
     */
    public static boolean isJson(String content) {

        try {
            JSONObject jsonStr = JSONObject.parseObject(content);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public static void main(String[] args) {
        String str = "{\n" +
                "    \"basic_area\": {\n" +
                "        \"gender\": {},\n" +
                "        \"hospital\": {\n" +
                "            \"detect_box\": [\n" +
                "                542,\n" +
                "                149,\n" +
                "                345,\n" +
                "                41,\n" +
                "                \"1.0\"\n" +
                "            ],\n" +
                "            \"recog_value\": \"复旦大学附属肿瘤医院\",\n" +
                "            \"values\": [\n" +
                "                {\n" +
                "                    \"score\": \"0.629012\",\n" +
                "                    \"value\": \"复旦大学附属肿瘤医院\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"score\": \"0.258905\",\n" +
                "                    \"value\": \"复旦大学附属华山医院\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"score\": \"0.0828125\",\n" +
                "                    \"value\": \"松江区方塔中医医院\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"score\": \"0.0153318\",\n" +
                "                    \"value\": \"复旦大学附属华山医院北院\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"score\": \"0.00994486\",\n" +
                "                    \"value\": \"复旦大学附属金山医院\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        \"hospital_type\": {\n" +
                "            \"detect_box\": [\n" +
                "                839,\n" +
                "                200,\n" +
                "                233,\n" +
                "                35,\n" +
                "                \"0.999998\"\n" +
                "            ],\n" +
                "            \"recog_value\": \"三级乙综合医院\",\n" +
                "            \"values\": [\n" +
                "                {\n" +
                "                    \"score\": \"0.97082\",\n" +
                "                    \"value\": \"三级乙综合医院\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"score\": \"0.0238392\",\n" +
                "                    \"value\": \"三级乙等综合医院\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"score\": \"0.00273586\",\n" +
                "                    \"value\": \"二级综合性医院\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"score\": \"0.00257187\",\n" +
                "                    \"value\": \"三级乙等医院\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"score\": \"1.21438e-05\",\n" +
                "                    \"value\": \"二级甲等综合医院\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        \"invoice_number\": {},\n" +
                "        \"medical_insurance_type\": {\n" +
                "            \"detect_box\": [\n" +
                "                821,\n" +
                "                251,\n" +
                "                121,\n" +
                "                35,\n" +
                "                \"0.999012\"\n" +
                "            ],\n" +
                "            \"recog_value\": \"普通医保\",\n" +
                "            \"values\": [\n" +
                "                {\n" +
                "                    \"score\": \"0.999927\",\n" +
                "                    \"value\": \"普通医保\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"score\": \"2.76e-05\",\n" +
                "                    \"value\": \"城保医保\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"score\": \"1.58155e-05\",\n" +
                "                    \"value\": \"门急诊医保\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"score\": \"9.33971e-06\",\n" +
                "                    \"value\": \"实时医保\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"score\": \"5.83601e-06\",\n" +
                "                    \"value\": \"(普通医保)\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        \"name\": {}\n" +
                "    },\n" +
                "    \"file_id\": \"za-nw-goods\",\n" +
                "    \"flag\": true,\n" +
                "    \"image_id\": \"za-nw-goods\",\n" +
                "    \"image_size\": [\n" +
                "        [\n" +
                "            1982,\n" +
                "            1201\n" +
                "        ]\n" +
                "    ],\n" +
                "    \"item_area\": {\n" +
                "        \"item_list\": [\n" +
                "            {\n" +
                "                \"amount\": {\n" +
                "                    \"detect_box\": [\n" +
                "                        615,\n" +
                "                        359,\n" +
                "                        107,\n" +
                "                        34,\n" +
                "                        \"0.999663\"\n" +
                "                    ],\n" +
                "                    \"recog_value\": \"104.60\",\n" +
                "                    \"score\": \"0.113429\"\n" +
                "                },\n" +
                "                \"item_name\": {\n" +
                "                    \"detect_box\": [\n" +
                "                        303,\n" +
                "                        358,\n" +
                "                        107,\n" +
                "                        36,\n" +
                "                        \"0.999988\"\n" +
                "                    ],\n" +
                "                    \"recog_value\": \"西药费\",\n" +
                "                    \"values\": [\n" +
                "                        {\n" +
                "                            \"score\": \"0.686891\",\n" +
                "                            \"value\": \"西药费\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"score\": \"0.0239177\",\n" +
                "                            \"value\": \"拍摄费\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"score\": \"0.02126\",\n" +
                "                            \"value\": \"中药费\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"score\": \"0.0164866\",\n" +
                "                            \"value\": \"放射费\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"score\": \"0.0159372\",\n" +
                "                            \"value\": \"超声费\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"amount\": {\n" +
                "                    \"detect_box\": [\n" +
                "                        617,\n" +
                "                        399,\n" +
                "                        96,\n" +
                "                        32,\n" +
                "                        \"0.997153\"\n" +
                "                    ],\n" +
                "                    \"recog_value\": \"17.10\",\n" +
                "                    \"score\": \"0.130122\"\n" +
                "                },\n" +
                "                \"item_name\": {\n" +
                "                    \"detect_box\": [\n" +
                "                        300,\n" +
                "                        400,\n" +
                "                        107,\n" +
                "                        34,\n" +
                "                        \"0.999993\"\n" +
                "                    ],\n" +
                "                    \"recog_value\": \"治疗费\",\n" +
                "                    \"values\": [\n" +
                "                        {\n" +
                "                            \"score\": \"0.977051\",\n" +
                "                            \"value\": \"治疗费\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"score\": \"0.00871247\",\n" +
                "                            \"value\": \"材料费\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"score\": \"0.00217735\",\n" +
                "                            \"value\": \"拍摄费\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"score\": \"0.0013559\",\n" +
                "                            \"value\": \"诊疗费\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"score\": \"0.00118351\",\n" +
                "                            \"value\": \"磁卡工本费\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"mongo_key\": 1516766682481,\n" +
                "    \"oss_path\": \"test_good_sample\",\n" +
                "    \"pay_info_area\": {\n" +
                "        \"total_amount\": {}\n" +
                "    }\n" +
                "}";

//        Map<String, Object> objectMap = toHashMap(new HashMap<String, Object>(32), str);
//
//        Map<String, Object> objectMap = toHashMap(str);
//
//        Iterator iter = objectMap.entrySet().iterator();
//
//        while (iter.hasNext()) {
//            Map.Entry entry = (Map.Entry) iter.next();
//            Object key = entry.getKey();
//            Object value = entry.getValue();
//            System.err.println("key:" + key + "------>value:" + value);
//
//        }

        ClaimDetectResponseDTO claimDetectResponseDTO = toBean(str, ClaimDetectResponseDTO.class);

        System.err.println(JSON.toJSONString(claimDetectResponseDTO));

    }

}
