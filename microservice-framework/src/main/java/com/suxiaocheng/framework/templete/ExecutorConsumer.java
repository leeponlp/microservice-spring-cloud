package com.suxiaocheng.framework.templete;

@FunctionalInterface
public interface ExecutorConsumer<R, P, C> {

    void accept(R r, P p, C c);

    /**
     * 动态添加新的执行动作
     *
     * @param after 后置行为的action
     * @return 函数结构体
     */
    default ExecutorConsumer<R, P, C> andThen(ExecutorConsumer<? super R, ? super P, ? super C> after) {
        return (r, p, c) -> {
            this.accept(r, p, c);
            after.accept(r, p, c);
        };
    }

}
