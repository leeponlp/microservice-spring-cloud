
package com.leepon.cloud.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: 苏小城
 * @Date: 2020-4-2
 * @Description:
 */
@Slf4j
@Component
public class IExecutorBeanFactory implements InitializingBean {


    @Autowired
    private IRepositoryService repositoryService;

    private ExecutorService consumerThreadPool = null;      //消费者线程池


    public void create() {
        consumerThreadPool.execute(new IConsumeLogic(repositoryService));
    }

    public void shutDown() {

        if (null != consumerThreadPool) {
            log.info("==============回收消费者线程==============");
            consumerThreadPool.shutdown();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        consumerThreadPool = Executors.newFixedThreadPool(10);
    }
}
