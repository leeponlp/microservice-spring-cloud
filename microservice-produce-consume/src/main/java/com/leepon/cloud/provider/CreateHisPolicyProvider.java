
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
