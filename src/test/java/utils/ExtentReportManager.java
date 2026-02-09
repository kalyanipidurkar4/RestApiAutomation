package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.io.IOException;

public class ExtentReportManager {

    public static ExtentReports initialize()  {
        ExtentSparkReporter spark=new ExtentSparkReporter("test-output/ExtentReport.html");
        ExtentReports extent=new ExtentReports();
        try {
            spark.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
            extent.attachReporter(spark);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return extent;
    }

    // Add these to ExtentReportManager

    private static int passedTests = 0;
    private static int failedTests = 0;
    private static int skippedTests = 0;

    public static void incrementPassed() {
        passedTests++;
    }

    public static void incrementFailed() {
        failedTests++;
    }

    public static void incrementSkipped() {
        skippedTests++;
    }


}
