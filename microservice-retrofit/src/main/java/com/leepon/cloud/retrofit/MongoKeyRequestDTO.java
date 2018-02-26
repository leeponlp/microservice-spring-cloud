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
public class MongoKeyRequestDTO implements Serializable {

    /**
     * oss中发票影像的地址
     */
    private String   picture_path;

    /**
     * 发票影像编号
     */
    private String   file_id;

    /**
     * 发票所属医院名称  非必传
     */
    private String   hospital_name;

    private String   claim_id;
}
