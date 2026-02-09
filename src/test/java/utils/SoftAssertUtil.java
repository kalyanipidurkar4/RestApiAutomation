package utils;
import org.testng.asserts.SoftAssert;


public class SoftAssertUtil {
   //making it singletone class--implementing singletone design pattern
    private static SoftAssertUtil softassertutilObj; //it will belong to class so obj share common memory
    private SoftAssert testngAssert=new SoftAssert(); //composition ?-->softassertutilObj contains assert object

    private SoftAssertUtil()
    {}

    public static SoftAssertUtil getInstance()
    {
        if(softassertutilObj==null)
        {
            softassertutilObj=new SoftAssertUtil();
        }
        return softassertutilObj;
    }

    public void assertTrue(Boolean condition,String msg)
    {
        try {
            testngAssert.assertTrue(condition, msg);
        }
        catch (AssertionError e)
        {
            System.out.println(msg);
        }
    }
    public void assertEquals(int actual,int expected,String msg)
    {
        try
        {
            testngAssert.assertEquals(actual,expected,msg);
        }
        catch (AssertionError e)
        {
            System.out.println(msg);
        }
    }

    public void assertEquals(String actual,String expected,String msg)
    {
        try
        {
            testngAssert.assertEquals(actual,expected,msg);
        }
        catch (AssertionError e)
        {
            System.out.println(msg);
        }
    }
    public void assertAll()
    {
        testngAssert.assertAll();
    }
}
