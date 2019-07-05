import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @ClassName CalculateMedicalBillTest
 * @Description TODO...
 * @Author 苏小城
 * @Date 2019-7-5 14:20
 * @Version 1.0
 */
@Slf4j
public class CalculateMedicalBillTest {

    ExpressRunner runner = new ExpressRunner();
    DefaultContext<String, Object> context = new DefaultContext<>();

    /**
     * room and board
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {

        context.put("remainDays", 100);
        context.put("billAmount", 500);
        String express = "如果 (remainDays < 180 and remainDays > 0 )" +
                "{return min(750,billAmount);} " +
                "否则 {return 0;}";
        execute(express);

    }

    /**
     * Miscellaneous charges
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {

        context.put("billAmount",5000);
        context.put("perYearLimitationAmount",14000);
        String express = "return min(perYearLimitationAmount,billAmount);";

        execute(express);

    }

    /**
     * Attending doctor’s fee
     */
    @Test
    public void test3() throws Exception {

        context.put("perYearLimitedDays",180);
        context.put("remainDays",100);
        context.put("billAmount",1000);
        String express = "if (remainDays < perYearLimitedDays and remainDays > 0){ return min(billAmount,750);} else {return 0;}";
        execute(express);
    }

    /**
     * Specialist’s fee
     * @throws Exception
     */
    @Test
    public void test4() throws Exception {

        context.put("billAmount",5000);
        context.put("perYearLimitedAmount",4300);
        String express = "return min(perYearLimitationAmount,billAmount);";
        execute(express);

    }

    /**
     * Intensive care
     * @throws Exception
     */
    @Test
    public void  test5() throws Exception {
        context.put("remainDays",5);
        context.put("inpatientDays",20);
        context.put("perYearLimitedDays",25);
        context.put("billAmount",1000);
        String express = "if (remainDays <= 0){return 0;} else { return min(billAmount,3500);}";
        execute(express);
    }

    /**
     * Surgeon’s fee
     * Anaesthetist's fee
     * Operating theatre charges
     * @throws Exception
     */
    @Test
    public void  test6() throws Exception {

        context.put("surgeonLevel",1);
        context.put("billAmount",100000);
        String express = "if (surgeonLevel == 1){return min(billAmount,50000);}" +
                "else if (surgeonLevel == 2){return min(billAmount,25000);}" +
                "else if (surgeonLevel == 3){return min(billAmount,12500);}" +
                "else {return min(billAmount,5000);}";
        execute(express);

    }

    /**
     * Prescribed Diagnostic Imaging Tests
     */
    @Test
    public void test7(){
        context.put("billAmount",1000);

    }

    private void execute(String express) throws Exception {
        Object o = runner.execute(express, context, null, true, false);
        System.err.println("result: " + o);
    }

}
