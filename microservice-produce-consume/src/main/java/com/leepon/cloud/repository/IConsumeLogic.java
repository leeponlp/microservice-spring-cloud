
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
