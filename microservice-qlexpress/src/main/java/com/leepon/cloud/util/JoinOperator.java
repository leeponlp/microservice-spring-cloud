package com.leepon.cloud.util;

import com.ql.util.express.Operator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName JoinOperator
 * @Description TODO...
 * @Author 苏小城
 * @Date 2019-4-4 14:01
 * @Version 1.0
 */
public class JoinOperator extends Operator {

    @Override
    public Object executeInner(Object[] objects) throws Exception {

        List<Object> result = new ArrayList<>();
        for(Object object : objects){
            if (object instanceof List){
                result.addAll((Collection<?>) object);
            }else {
                result.add(object);
            }
        }
        return result;
    }
}
