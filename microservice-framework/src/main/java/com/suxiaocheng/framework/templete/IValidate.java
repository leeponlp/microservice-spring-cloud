package com.suxiaocheng.framework.templete;


public interface IValidate<IN, OUT> {

    OUT validate(IN req) throws RuntimeException;


    /**
     * 双入参验证方法
     *
     * @param <IN1>
     * @param <IN2>
     * @param <OUT>
     */
    interface IValidate2<IN1, IN2, OUT> {
        OUT validate(IN1 req1, IN2 req2) throws RuntimeException;
    }

    /**
     * 三入参验证方法
     *
     * @param <IN1>
     * @param <IN2>
     * @param <IN3>
     */
    interface IValidate3<IN1, IN2, IN3> {
        void validate(IN1 req1, IN2 req2, IN3 req3) throws RuntimeException;
    }

    interface IValidateNoReturn<IN1, IN2> {
        void validate(IN1 req1, IN2 req2) throws RuntimeException;
    }

}


