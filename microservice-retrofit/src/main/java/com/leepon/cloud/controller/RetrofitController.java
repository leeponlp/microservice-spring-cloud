package com.leepon.cloud.controller;

import com.alibaba.fastjson.JSON;
import com.leepon.cloud.retrofit.ClaimDetectResponseDTO;
import com.leepon.cloud.retrofit.FastJsonUtil;
import com.leepon.cloud.retrofit.MongoKeyRequestDTO;
import com.leepon.cloud.retrofit.MongoKeyResponseDTO;
import com.leepon.cloud.retrofit.OcrInvoiceService;
import com.leepon.cloud.retrofit.QueryDetectRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/retrofit")
public class RetrofitController {


    @RequestMapping("/demo")
    public String demo() {

        return "测试数据";
    }


    @Autowired
    OcrInvoiceService ocrInvoiceService;


    @RequestMapping("/queryKey")
    public String testOcr() {

        MongoKeyRequestDTO requestDTO = new MongoKeyRequestDTO();
        requestDTO.setPicture_path("za-graphene/uploadfile/product/14922218420410863.jpg");
        requestDTO.setFile_id("hexin_test1");
        requestDTO.setClaim_id("bf0a9560-ac00-4112-8bc7-2ff76e0ede62");

        try {
            Call<MongoKeyResponseDTO> mongoKeyResponseDTOCall = ocrInvoiceService.queryRecordId(requestDTO);

            mongoKeyResponseDTOCall.enqueue(new Callback<MongoKeyResponseDTO>() {
                @Override
                public void onResponse(Call<MongoKeyResponseDTO> call, Response<MongoKeyResponseDTO> response) {
                    System.err.println(JSON.toJSONString(response.body()));
                }

                @Override
                public void onFailure(Call<MongoKeyResponseDTO> call, Throwable t) {

                }
            });
            //Response<MongoKeyResponseDTO> execute = mongoKeyResponseDTOCall.execute();

            //return JSON.toJSONString(execute.body());
            return "";
        } catch (Exception e) {

            log.error("" + e);

        }

        return "测试";

    }


    @RequestMapping("/queryDetect")
    public ClaimDetectResponseDTO testOcrDetect() {

        QueryDetectRequestDTO requestDTO = new QueryDetectRequestDTO();
        requestDTO.setMongo_key("1516846350044");

        try {
            Call<ClaimDetectResponseDTO> claimDetectResponseDTOCall = ocrInvoiceService.queryDectResult(requestDTO);

            claimDetectResponseDTOCall.enqueue(new Callback<ClaimDetectResponseDTO>() {
                @Override
                public void onResponse(Call<ClaimDetectResponseDTO> call, Response<ClaimDetectResponseDTO> response) {


                }

                @Override
                public void onFailure(Call<ClaimDetectResponseDTO> call, Throwable t) {

                }
            });

            Response<ClaimDetectResponseDTO> execute = claimDetectResponseDTOCall.execute();
            return execute.body();
        } catch (Exception e) {

            log.error("" + e);

        }
        return null;

    }
}
