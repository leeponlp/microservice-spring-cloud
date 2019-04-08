import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ShortCircuitTest
 * @Description TODO...
 * @Author 苏小城
 * @Date 2019-4-8 20:43
 * @Version 1.0
 */
public class ShortCircuitTest {

    @Test
    public void test1() throws Exception {

        ExpressRunner runner = new ExpressRunner();
        runner.addOperatorWithAlias("小于","<","$1 小于 $2 不符合期望");
        runner.addOperatorWithAlias("大于",">","$1 大于 $2 不符合期望");
        DefaultContext<String,Object> context = new DefaultContext<>();
        context.put("违规天数",100);
        context.put("交易扣分",10);
        String express = "违规天数 小于 90 and 交易扣分 大于 12";
        List<String> errorList = new ArrayList<>();
        Object o = runner.execute(express, context, errorList, true, false);
        System.err.println("result: "+o);
        for(String error : errorList){
            System.out.println(error);
        }

    }
}
