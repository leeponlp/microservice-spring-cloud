import com.leepon.cloud.dto.User;
import com.leepon.cloud.util.StringTool;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import org.junit.Test;

/**
 * @ClassName ObjectTest
 * @Description TODO...
 * @Author 苏小城
 * @Date 2019-4-3 14:38
 * @Version 1.0
 */
public class ObjectTest {

    @Test
    public void test1()throws Exception{

        String express = "import com.leepon.cloud.dto.User;" +
                "User user = new User();" +
                "user.setUsername('哈哈');" +
                "return user.getUsername();";
        DefaultContext<String,Object> context = new DefaultContext<>();
        ExpressRunner runner = new ExpressRunner();
        Object o = runner.execute(express, context, null, true, false);
        System.err.println("result: "+o);
    }

    @Test
    public void test2()throws Exception{
        String express = "user.setUsername('哈哈');" +
                "return user.getUsername();";
        DefaultContext<String,Object> context = new DefaultContext<>();
        User user = new User();
        context.put("user",user);
        ExpressRunner runner = new ExpressRunner();
        Object o = runner.execute(express, context, null, true, false);
        System.err.println("result: "+o);
    }

    @Test
    public void test3()throws Exception{
        DefaultContext<String,Object> context = new DefaultContext<>();
        String express = "首字母大写(\"hello World\")";
        ExpressRunner runner = new ExpressRunner();
        runner.addFunctionOfClassMethod("首字母大写", StringTool.class.getName(), "firstToUpper", new String[]{"String"}, null);
        Object o = runner.execute(express, context, null, true, false);
        System.err.println("result: "+o);
    }

    /**
     * 使用别名
     */
    @Test
    public void test4()throws Exception{

        String express = "user.setUsername('大黄');" +
                "使用别名 userName user.username;" +
                "return userName;";

        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String,Object> context = new DefaultContext<>();
        User user = new User();
        context.put("user",user);
        runner.addOperatorWithAlias("使用别名","alias",null);
        Object o = runner.execute(express, context, null, true, false);
        System.err.println("result: "+o);
    }

    /**
     * 使用宏
     */
    @Test
    public void test5()throws Exception{

        String express = "user.setUsername('大黄');" +
                "使用宏 userName {user.username};" +
                "return userName;";

        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String,Object> context = new DefaultContext<>();
        User user = new User();
        context.put("user",user);
        runner.addOperatorWithAlias("使用宏","macro",null);
        Object o = runner.execute(express,context,null,true,false);
        System.err.println("result: "+o);
    }
}
