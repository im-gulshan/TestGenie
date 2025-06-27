package allProductTest.SauceDemoWebsite;

import mainFrameworkUtils.ContentRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import productFunctionLibrary.SauceDemoFunctionLibrary;
import testFrameworkUtils.BaseTest;
import testFrameworkUtils.WebDriverSetup;
import xpathRepo.SauceDemoXpathRepo;

import java.util.ArrayList;
import java.util.List;

public class verifyProductFilterOptions extends BaseTest {

    public WebDriver driver;
    SauceDemoFunctionLibrary sdfl;
    Logger logger = LogManager.getLogger(this.getClass());

    @BeforeMethod
    public void setUp() throws Exception {
        WebDriverSetup webDriverSetup = new WebDriverSetup();
        driver = webDriverSetup.launchApplication();
        sdfl = new SauceDemoFunctionLibrary(driver);

        logger.info("SauceDemo application launched successfully.");
    }

    @Test(description = "Verify that product filter options match the expected values")
    public void TC_003_verifyProductFilterOptions() {
        logToExtent("SauceDemo application launched successfully.");

        // Step 1: Login
        sdfl.loginInSauceDemo();
        logToExtent("✅ User logged in successfully.");

        // Step 2: Fetch actual filter options
        List<WebElement> elements = sdfl.getListOfElements(SauceDemoXpathRepo.productFilterOptions);
        List<String> actualFilterOptions = new ArrayList<>();
        for (WebElement element : elements) {
            actualFilterOptions.add(element.getText().trim());
        }

        logToExtent("Extracted actual product filter options: " + actualFilterOptions);

        // Step 3: Expected options from ContentRepo
        List<String> expectedFilterOptions = ContentRepo.productFilterOptions;
        logToExtent("Expected product filter options: " + expectedFilterOptions);

        // Step 4: Assertion & Logging
        try {
            Assert.assertEquals(actualFilterOptions, expectedFilterOptions,
                    "❌ Mismatch in product filter options. Expected: " + expectedFilterOptions +
                            " but Found: " + actualFilterOptions);
            logToExtent("✅ Product Filter Options match expected values.");
        } catch (AssertionError e) {
            logToExtent("❌ Assertion Failed: " + e.getMessage());
            throw e; // Re-throw to let TestNG mark the test as failed
        }
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
