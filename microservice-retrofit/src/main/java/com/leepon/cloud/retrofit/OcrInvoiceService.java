package com.leepon.cloud.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @task:
 * @discrption:
 * @author: 苏小城
 * @date: 18/1/23
 * @version: 1.0.0
 */
public interface OcrInvoiceService {

    /**
     * 发票检测识别
     * @param arg
     * @return
     */
    @POST("/zalabs/ocr/invoice/claim_id")
    Call<MongoKeyResponseDTO> queryRecordId(@Body MongoKeyRequestDTO arg);

    @POST("/zalabs/ocr/invoice/query_recog")
    Call<ClaimDetectResponseDTO> queryDectResult(@Body QueryDetectRequestDTO arg);


}
