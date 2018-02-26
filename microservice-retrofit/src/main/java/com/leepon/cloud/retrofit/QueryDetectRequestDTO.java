package com.leepon.cloud.retrofit;

import lombok.Data;

import java.io.Serializable;

/**
 * @task:
 * @discrption:
 * @author: 苏小城
 * @date: 18/1/23
 * @version: 1.0.0
 */
@Data
public class QueryDetectRequestDTO implements Serializable {

    /**
     * mongoKey用于mongodb查询数据
     * 使用发票检测识别返回的recordId即可（转成string）必填
     */
    private String   mongo_key;
}
