package testFramework.core;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import mainFrameworkUtils.ExtentReportManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    // Thread-safe static ExtentReports instance (shared)
    public static final ExtentReports extent = ExtentReportManager.createExtentReport();

    protected WebDriver driver;
    protected Logger logger = LogManager.getLogger(this.getClass());

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        WebDriverSetup webDriverSetup = new WebDriverSetup();
        driver = webDriverSetup.launchApplication();
        logger.info("Browser launched successfully.");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Browser closed successfully.");
            logToExtent("Browser closed successfully.");
        }
    }

    // Thread-local ExtentTest will be managed in Listeners.java
    protected void logToExtent(String message) {
        ExtentTest test = testFramework.reporting.Listeners.getExtentTest();
        if (test != null) {
            test.info(message);
        }
    }
}
