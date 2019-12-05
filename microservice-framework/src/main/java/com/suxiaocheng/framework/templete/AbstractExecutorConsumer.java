package com.suxiaocheng.framework.templete;



public abstract class AbstractExecutorConsumer<IN, C, OUT> implements ExecutorConsumer<IN, C, OUT>, IValidate.IValidate3<IN, C, OUT> {


    protected abstract void executing(IN req, C c, OUT out);

    /**
     * 执行动作
     *
     * @param req
     * @param c
     * @param out
     * @throws RuntimeException
     */
    @Override
    public final void accept(IN req, C c, OUT out) throws RuntimeException {
        validate(req, c, out);
        executing(req, c, out);
    }


    protected void exit() {
        throw new RuntimeException();
    }
}
