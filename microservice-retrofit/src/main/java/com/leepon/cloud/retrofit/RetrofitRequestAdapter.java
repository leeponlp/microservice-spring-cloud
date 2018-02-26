package com.leepon.cloud.retrofit;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * @task:
 * @discrption:
 * @author: 苏小城
 * @date: 18/1/23
 * @version: 1.0.0
 */
@Slf4j
@Configuration
public class RetrofitRequestAdapter {

    @Bean
    public OcrInvoiceService  ocrInvoiceService(@Value("${ocr.url}") String baseUrl){
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl).client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(OcrInvoiceService.class);
    }
}
