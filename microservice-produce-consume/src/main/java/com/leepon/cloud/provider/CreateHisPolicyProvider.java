/* 
 * Copyright By ZATI
 * Copyright By 3a3c88295d37870dfd3b25056092d1a9209824b256c341f2cdc296437f671617
 * All rights reserved.
 *
 * If you are not the intended user, you are hereby notified that any use, disclosure, copying, printing, forwarding or 
 * dissemination of this property is strictly prohibited. If you have got this file in error, delete it from your system. 
 */
package com.leepon.cloud.provider;

import com.leepon.cloud.service.ICreateHisPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 苏小城
 * @Date: 2020-4-2
 * @Description:
 */
@RestController
public class CreateHisPolicyProvider {



    @Autowired
    private ICreateHisPolicyService   createHisPolicyService;

    @RequestMapping("/create")
    public void  create(){
        createHisPolicyService.createPolicy();
    }
}
