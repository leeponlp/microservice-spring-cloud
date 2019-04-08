import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import org.junit.Test;

/**
 * @ClassName PreciseTest
 * @Description TODO...
 * @Author 苏小城
 * @Date 2019-4-8 20:43
 * @Version 1.0
 */
public class PreciseTest {

    @Test
    public void test1() throws Exception {

        String express = "11/3";
        ExpressRunner runner = new ExpressRunner(true,false);
        DefaultContext<String,Object> context = new DefaultContext<>();
        Object o = runner.execute(express, context, null, true, false);
        System.err.println("result: "+o);
    }


    @Test
    public void test2() throws Exception {

        String express = "11/3";
        ExpressRunner runner = new ExpressRunner(false,false);
        DefaultContext<String,Object> context = new DefaultContext<>();
        Object o = runner.execute(express, context, null, true, false);
        System.err.println("result: "+o);
    }
}
