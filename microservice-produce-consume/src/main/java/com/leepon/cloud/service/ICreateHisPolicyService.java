
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
