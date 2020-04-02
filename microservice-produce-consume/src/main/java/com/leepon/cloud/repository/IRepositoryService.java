/* 
 * Copyright By ZATI
 * Copyright By 3a3c88295d37870dfd3b25056092d1a9209824b256c341f2cdc296437f671617
 * All rights reserved.
 *
 * If you are not the intended user, you are hereby notified that any use, disclosure, copying, printing, forwarding or 
 * dissemination of this property is strictly prohibited. If you have got this file in error, delete it from your system. 
 */
package com.leepon.cloud.repository;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author: 苏小城
 * @Date: 2020-4-2
 * @Description:
 */
@Slf4j
@Component
public class IRepositoryService<E> {

    private final int MAX_SIZE = 100;
    private LinkedBlockingDeque<E> list = new LinkedBlockingDeque<>(MAX_SIZE);

    public void produce(E ele) {
        if (list.size() == MAX_SIZE) {
            System.out.println("队列已满,不能再继续生产");
        }
        try {
            System.out.println("生产者:" + JSON.toJSONString(ele));
            list.put(ele);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public E consume() {
        E entity = null;
        if (!list.isEmpty()) {
            try {
                entity = list.take();
                System.out.println("消费者:" + JSON.toJSONString(entity));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return entity;
    }
}
