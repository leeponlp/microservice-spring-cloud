package com.suxiaocheng.framework.templete;


public abstract class AbstractExecutorFunction<IN, OUT>
        implements ExecutorFunction<IN, OUT>, IValidate.IValidateNoReturn<IN, OUT> {

    /**
     * 执行过程
     *
     * @param req
     * @param out
     */
    protected abstract void executing(IN req, OUT out);

    /**
     * 执行完后的可操作行为，每一个action单个的行为
     *
     * @param req
     * @param out
     */
    protected void executed(IN req, OUT out) {
    }

    @Override
    public OUT accept(IN req, OUT out) {

        validate(req, out);
        //执行
        executing(req, out);
        //执行后
        executed(req, out);
        return out;
    }
}
