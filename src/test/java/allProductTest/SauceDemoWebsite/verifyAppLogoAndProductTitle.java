package allProductTest.SauceDemoWebsite;

import mainFrameworkUtils.ContentRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import productFunctionLibrary.SauceDemoFunctionLibrary;
import testFrameworkUtils.BaseTest;
import testFrameworkUtils.WebDriverSetup;
import xpathRepo.SauceDemoXpathRepo;

//@Listeners(testFrameworkUtils.Listeners.class)
public class verifyAppLogoAndProductTitle extends BaseTest {

    public WebDriver driver; // Must be public for reflection-based screenshot capture
    SauceDemoFunctionLibrary sdfl;
    Logger logger = LogManager.getLogger(this.getClass());

    @BeforeMethod
    public void setUp() throws Exception {
        // Launch browser and application
        WebDriverSetup webDriverSetup = new WebDriverSetup();
        driver = webDriverSetup.launchApplication();

        // Initialize function library
        sdfl = new SauceDemoFunctionLibrary(driver);

        // Log application launch
        logger.info("SauceDemo application launched successfully.");

    }

    @Test(description = "Verify the App Logo and Product Title on home page")
    public void TC_002_testAppLogoAndProductTitle() {
        logToExtent("SauceDemo application launched successfully.");

        // Login to the application
        sdfl.loginInSauceDemo();
        logToExtent("User logged in successfully.");

        // Verify App Logo
        String expectedAppLogoText = ContentRepo.appLogo;
        String actualAppLogoText = sdfl.getTextOfElement(SauceDemoXpathRepo.appLogo);
        logToExtent("Expected App Logo: " + expectedAppLogoText);
        logToExtent("Actual App Logo: " + actualAppLogoText);

        logger.info("Verifying App Logo...");
        Assert.assertEquals(actualAppLogoText, expectedAppLogoText, "App logo text does not match!");
        logToExtent("App Logo verified successfully.");

        // Verify Product Title
        String expectedProductTitleText = ContentRepo.productTitle;
        String actualProductTitleText = sdfl.getTextOfElement(SauceDemoXpathRepo.productTitle);
        logToExtent("Expected Product Title: " + expectedProductTitleText);
        logToExtent("Actual Product Title: " + actualProductTitleText);

        logger.info("Verifying Product Title...");
        Assert.assertEquals(actualProductTitleText, expectedProductTitleText, "Product title text does not match!");
        logToExtent("Product Title verified successfully.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Browser closed successfully.");
            logToExtent("Browser closed successfully.");
        }
    }
}
