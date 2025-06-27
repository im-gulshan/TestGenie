package allProductTest.SauceDemoWebsite;

import mainFrameworkUtils.ContentRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import productFunctionLibrary.SauceDemoFunctionLibrary;
import testFrameworkUtils.BaseTest;
import testFrameworkUtils.WebDriverSetup;
import xpathRepo.SauceDemoXpathRepo;

import java.util.ArrayList;
import java.util.List;

public class verifyHamburgerMenuSubOptions extends BaseTest {

    public WebDriver driver; // Must be public for reflection in Listeners
    SauceDemoFunctionLibrary sdfl;
    Logger logger = LogManager.getLogger(this.getClass());

    @BeforeMethod
    public void setUp() throws Exception {
        WebDriverSetup webDriverSetup = new WebDriverSetup();
        driver = webDriverSetup.launchApplication();
        sdfl = new SauceDemoFunctionLibrary(driver);

        logger.info("SauceDemo application launched successfully.");

    }

    @Test(description = "Verify hamburger menu sub-options match the expected values")
    public void TC_001_testHamburgerMenuSubOptions() {
        logToExtent("SauceDemo application launched successfully.");

        // Login
        sdfl.loginInSauceDemo();
        logToExtent("User logged in successfully.");

        // Click on hamburger menu
        sdfl.clickOnElement(SauceDemoXpathRepo.hamburgerMenu);
        logToExtent("Hamburger menu opened.");

        // Get expected and actual options
        List<String> expectedOptions = ContentRepo.hamBurgerMenuSubOptions;
        List<WebElement> actualOptionsElement = sdfl.getListOfElements(SauceDemoXpathRepo.hamburgerMenuSubOptions);

        List<String> actualOptions = new ArrayList<>();
        for (WebElement element : actualOptionsElement) {
            sdfl.waitForVisibilityOfElement(element);
            actualOptions.add(element.getText().trim());
        }

        logToExtent("Expected Hamburger Menu Options: " + expectedOptions);
        logToExtent("Actual Hamburger Menu Options: " + actualOptions);

        // Assertion
        if (!actualOptions.equals(expectedOptions)) {
            String errorMessage = "Mismatch in hamburger menu options.\nExpected: " + expectedOptions + "\nActual: " + actualOptions;
            logger.error(errorMessage);
            logToExtent("❌ " + errorMessage);
            Assert.fail(errorMessage);
        } else {
            logToExtent("✅ Hamburger menu options match expected values.");
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
