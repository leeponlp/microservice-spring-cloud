import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import org.junit.Test;

/**
 * @ClassName CollectionTest
 * @Description TODO...
 * @Author 苏小城
 * @Date 2019-4-4 18:09
 * @Version 1.0
 */
public class CollectionTest {

    /**
     * 集合简写
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {

        String express1 = "a=NewMap(1:1,2:2);return a.get(1)+a.get(2);";
        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String,Object> context = new DefaultContext<>();
        Object o1 = runner.execute(express1, context, null, true, false);
        System.err.println("result: "+o1);

        String express2 = "b=NewList(1,2,3);return b.get(0)+b.get(2);";
        Object o2 = runner.execute(express2, context, null, true, false);
        System.err.println("result: "+o2);

        String express3 = "c=[1,2,3];return c[1]+c[2];";
        Object o3 = runner.execute(express3, context, null, true, false);
        System.err.println("result: "+o3);

    }
}
