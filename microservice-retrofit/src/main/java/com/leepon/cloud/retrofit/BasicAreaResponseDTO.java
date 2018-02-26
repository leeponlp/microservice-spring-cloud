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
public class BasicAreaResponseDTO implements Serializable {

    private static final long serialVersionUID = 2399564378231913352L;

    private Object  gender;

    private HospitalResponseDTO hospitalResponseDTO;

    private HospitalTypeResponseDTO hospital_type;

    private Object  invoice_number;

    private MedicalInsuranceTypeResponseDTO medical_insurance_type;


}
