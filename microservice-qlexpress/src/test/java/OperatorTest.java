import com.leepon.cloud.util.JoinOperator;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import org.junit.Test;

/**
 * @ClassName OperatorTest
 * @Description TODO...
 * @Author 苏小城
 * @Date 2019-4-4 14:04
 * @Version 1.0
 */
public class OperatorTest {


    @Test
    public void test1() throws Exception {

        String express = "1 join 2 join 3";
        ExpressRunner runner = new ExpressRunner();
        runner.addOperator("join",new JoinOperator());
        DefaultContext<String,Object> context = new DefaultContext<>();
        Object o = runner.execute(express, context, null, true, false);
        System.err.println("result: "+o);

    }

    @Test
    public void test2() throws Exception {

        String express = "join(1,2,3)";
        ExpressRunner runner = new ExpressRunner();
        runner.addFunction("join",new JoinOperator());
        DefaultContext<String,Object> context = new DefaultContext<>();
        Object o = runner.execute(express, context, null, true, false);
        System.err.println("result: "+o);

    }


    @Test
    public void test3() throws Exception {

        ExpressRunner runner = new ExpressRunner();
        runner.replaceOperator("+",new JoinOperator());
        DefaultContext<String,Object> context = new DefaultContext<>();
        Object o = runner.execute("1+2+3", context, null, true, false);
        System.err.println("result: "+o);

    }

    /**
     * 宏定义
     * @throws Exception
     */
    @Test
    public void test4() throws Exception {

        ExpressRunner runner = new ExpressRunner();
        runner.addMacro("平均成绩","(数学+英语+语文)/3");
        runner.addMacro("是否优秀","平均成绩>90");
        DefaultContext<String,Object> context = new DefaultContext<>();
        context.put("数学",95);
        context.put("英语",99);
        context.put("语文",95);
        Object o = runner.execute("是否优秀", context, null, true, false);
        System.err.println("result: "+o);
    }

    /**
     * 绑定java对象或method方法
     */
    @Test
    public void test5() throws Exception {
        ExpressRunner runner = new ExpressRunner();
        runner.addFunctionOfClassMethod("取绝对值",Math.class.getName(),"abs",new String[]{"double"},null);
        runner.addFunctionOfServiceMethod("输出",System.out,"print",new String[]{"String"},null);
        DefaultContext<String,Object> context = new DefaultContext<>();
        Object o1 = runner.execute("取绝对值(-100);", context, null, true, false);
        System.err.println("o1: "+o1);
    }

}
