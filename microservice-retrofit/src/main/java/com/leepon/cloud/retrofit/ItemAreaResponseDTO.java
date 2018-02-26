package com.leepon.cloud.retrofit;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @task:
 * @discrption:
 * @author: 苏小城
 * @date: 18/1/24
 * @version: 1.0.0
 */
@Data
public class ItemAreaResponseDTO implements Serializable{

    private static final long serialVersionUID = 7654793253563766829L;

    private List<ItemResponseDTO> item_list;
}
