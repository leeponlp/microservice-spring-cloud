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
public class BaseDTO implements Serializable{

    private static final long serialVersionUID = -852123003371530561L;

    private String  recog_value;

}
