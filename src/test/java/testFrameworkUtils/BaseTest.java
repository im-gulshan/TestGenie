package testFrameworkUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import mainFrameworkUtils.ExtentReportManager;

public class BaseTest {
    protected ExtentTest test;
    private ExtentReportManager extentReportManager = new ExtentReportManager();
    protected ExtentReports extent = extentReportManager.createExtentReport();

    protected void logToExtent(String messge){
        test.info(messge);
    }
} // BaseTest
