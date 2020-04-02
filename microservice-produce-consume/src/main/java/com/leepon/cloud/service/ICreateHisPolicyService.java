/* 
 * Copyright By ZATI
 * Copyright By 3a3c88295d37870dfd3b25056092d1a9209824b256c341f2cdc296437f671617
 * All rights reserved.
 *
 * If you are not the intended user, you are hereby notified that any use, disclosure, copying, printing, forwarding or 
 * dissemination of this property is strictly prohibited. If you have got this file in error, delete it from your system. 
 */
package com.leepon.cloud.service;


import com.leepon.cloud.entity.CreateHisPolicyRequestDTO;
import com.leepon.cloud.repository.IExecutorBeanFactory;
import com.leepon.cloud.repository.IRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: 苏小城
 * @Date: 2020-4-2
 * @Description:
 */
@Service
public class ICreateHisPolicyService {

    @Autowired
    private IRepositoryService repositoryService;

    @Autowired
    private IExecutorBeanFactory executorBeanFactory;

    public void createPolicy() {

        for (int i = 1; i <= 100; i++) {

            CreateHisPolicyRequestDTO his = new CreateHisPolicyRequestDTO();
            his.setReservationNo("R0000000" + i);
            repositoryService.produce(his);
            executorBeanFactory.create();
        }
        //出单
        //线程对象回收
        executorBeanFactory.shutDown();
    }
}
