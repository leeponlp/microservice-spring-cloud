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
}
