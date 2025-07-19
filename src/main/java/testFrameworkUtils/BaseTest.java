package testFrameworkUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import mainFrameworkUtils.ExtentReportManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import productFunctionLibrary.SauceDemoFunctionLibrary;

public class BaseTest {
    // Thread-safe static ExtentReports instance (shared)
    public static final ExtentReports extent = ExtentReportManager.createExtentReport();

    protected SauceDemoFunctionLibrary sdfl;
    protected WebDriver driver;
    protected Logger logger = LogManager.getLogger(this.getClass());


    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        WebDriverSetup webDriverSetup = new WebDriverSetup();
        driver = webDriverSetup.launchApplication();
        sdfl = new SauceDemoFunctionLibrary(driver);
        logger.info("Application launched successfully.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Browser closed successfully.");
            logToExtent("Browser closed successfully.");
        }
    }

    // Thread-local ExtentTest will be managed in Listeners.java
    protected void logToExtent(String message) {
        ExtentTest test = Listeners.getExtentTest();
        if (test != null) {
            test.info(message);
        }
    }
}
