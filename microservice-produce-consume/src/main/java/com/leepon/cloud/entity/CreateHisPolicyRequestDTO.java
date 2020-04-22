
package com.leepon.cloud.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: 苏小城
 * @Date: 2020-4-2
 * @Description:
 */
@Data
public class CreateHisPolicyRequestDTO implements Serializable {

    /**
     * 预约番号
     */
    private String  reservationNo;
}
