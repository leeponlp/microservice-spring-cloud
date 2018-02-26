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
public class ItemResponseDTO implements Serializable{

    private static final long serialVersionUID = 2294256114171807141L;

    private AmountResponseDTO amount;


    private ItemNameResponseDTO item_name;


}
