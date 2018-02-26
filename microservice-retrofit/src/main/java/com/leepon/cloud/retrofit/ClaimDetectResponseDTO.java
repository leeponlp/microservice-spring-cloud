package com.leepon.cloud.retrofit;

import lombok.Data;

import java.io.Serializable;

/**
 * @task:
 * @discrption:
 * @author: 苏小城
 * @date: 18/1/24
 * @version: 1.0.0
 */
@Data
public class ClaimDetectResponseDTO implements Serializable{

    private static final long serialVersionUID = 7463725539504994249L;


    private BasicAreaResponseDTO basic_area;

    private String      file_id;

    private Boolean     flag;

    private String      image_id;

    private ItemAreaResponseDTO item_area;

    private String      mongo_key;

    private String      oss_path;

    private PayInfoAreaResponseDTO pay_info_area;


}
