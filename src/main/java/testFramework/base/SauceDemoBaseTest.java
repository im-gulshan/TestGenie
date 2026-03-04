package testFramework.base;

import mainFrameworkUtils.ConfigurationManager;
import productFunctionLibrary.SauceDemoFunctionLibrary;
import org.testng.annotations.BeforeClass;
import testFramework.core.BaseTest;

public class SauceDemoBaseTest extends BaseTest {
    protected SauceDemoFunctionLibrary sdfl;

    @BeforeClass(alwaysRun = true, dependsOnMethods = "setUp")
    public void loginToSauceDemo() {
        // Navigate to SauceDemo URL (read from SauceDemo.properties)
        String url = ConfigurationManager.getProperty("SauceDemo", "url");
        driver.get(url);

        // Initialize SauceDemoFunctionLibrary and perform login
        sdfl = new SauceDemoFunctionLibrary(driver);
        sdfl.loginInSauceDemo();
        logger.info("Logged into SauceDemo successfully.");
        logToExtent("User logged in successfully.");
    }
}
