package com.suxiaocheng.framework.templete;


public interface ExecutorFunction<IN, OUT> {

    OUT accept(IN req, OUT out);

    /**
     * 动态添加新的执行动作
     *
     * @param after 后置行为的action
     * @return 函数结构体
     */
    default ExecutorFunction<IN, OUT> andThen(ExecutorFunction<? super IN, ? super OUT> after) {
        return (in, y) -> {
            this.accept(in, y);
            after.accept(in, y);
            return y;
        };


    }
}
