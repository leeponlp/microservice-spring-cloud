import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import org.junit.Test;

/**
 * @Description TODO
 * @Author 苏小城
 * @Date 2019/3/28 2:12 PM
 * @Version 1.0
 */
public class HelloWorldTest {


    /**
     * hello world
     * @throws Exception
     */
    @Test
    public void helloTest()throws Exception{
        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String, Object> context = new DefaultContext<>();
        context.put("a", 1);
        context.put("b", 2);
        String express = "a+b";
        Object r = runner.execute(express, context, null, true, false);
        System.err.println("result:"+r);

    }

    /**
     * 循环
     * @throws Exception
     */
    @Test
    public void loopTest()throws Exception{

        String express = "\n" +
                "        int sum = 0;\n" +
                "        for (int i = 1;i<=10;i++){\n" +
                "           sum = sum+i;\n" +
                "        }\n" +
                "        return sum;";
        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String,Object> context = new DefaultContext<>();
        Object o = runner.execute(express, context, null, true, false);
        System.err.println("result:"+o);
    }

    /**
     * 不支持三目运算
     * @throws Exception
     */
    @Test
    public void threeMeshTest() throws Exception {
        String express = "int a=1,b=2,max=0;return max = a>b?a:b;";
        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String, Object> context = new DefaultContext<>();
        Object o = runner.execute(express, context, null, true, false);
        System.err.println("result:"+o);
    }
}
