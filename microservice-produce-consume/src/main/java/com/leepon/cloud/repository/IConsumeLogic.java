/*
 * Copyright By ZATI
 * Copyright By 3a3c88295d37870dfd3b25056092d1a9209824b256c341f2cdc296437f671617
 * All rights reserved.
 *
 * If you are not the intended user, you are hereby notified that any use, disclosure, copying, printing, forwarding or
 * dissemination of this property is strictly prohibited. If you have got this file in error, delete it from your system.
 */
package com.leepon.cloud.repository;

import com.leepon.cloud.entity.CreateHisPolicyRequestDTO;

/**
 * @author: 苏小城
 * @Date: 2020-4-2
 * @Description:
 */
public class IConsumeLogic implements Runnable {

    private IRepositoryService repositoryService;

    public IConsumeLogic(IRepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @Override
    public void run() {
        while (true) {

            CreateHisPolicyRequestDTO hisPolicyRequestDTO = (CreateHisPolicyRequestDTO) repositoryService.consume();
            //todo: 调用投保逻辑
            if (null != hisPolicyRequestDTO) {
                System.out.println("消费者" + Thread.currentThread().getName() + "取得元素：" + hisPolicyRequestDTO.getReservationNo());
            }
        }
    }
}
