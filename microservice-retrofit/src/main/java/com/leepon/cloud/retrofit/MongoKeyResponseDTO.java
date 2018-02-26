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
public class MongoKeyResponseDTO implements Serializable {

    /**
     * 返回结果的说明
     */
    private String errMsg;

    /**
     * 接口调用出错类型:0代表未出错
     */
    private Integer errNum;

    /**
     * 用于后续的发票识别和检测结果查询
     */
    private Long recordId;

    /**
     * “success”表示调用成功
     */
    private String resultData;
}
