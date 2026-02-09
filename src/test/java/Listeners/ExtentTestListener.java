package Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentReportManager;

public class ExtentTestListener implements ITestListener {

    private static ExtentReports extent= ExtentReportManager.initialize();
    //For parallel execution we used following
    private static ThreadLocal<ExtentTest> threadLocal=new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest=extent.createTest(result.getMethod().getMethodName());
        threadLocal.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        threadLocal.get().pass("Test passed !!");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        threadLocal.get().fail("Test Fail");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        threadLocal.get().skip("Test Skip");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }


}
