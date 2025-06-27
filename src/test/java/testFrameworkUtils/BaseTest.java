package testFrameworkUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import mainFrameworkUtils.ExtentReportManager;

public class BaseTest {
    // Thread-safe static ExtentReports instance (shared)
    public static final ExtentReports extent = ExtentReportManager.createExtentReport();

    // Thread-local ExtentTest will be managed in Listeners.java
    protected void logToExtent(String message) {
        ExtentTest test = Listeners.getExtentTest();
        if (test != null) {
            test.info(message);
        }
    }
}
