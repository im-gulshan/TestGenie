package allProductTest.SauceDemoWebsite;

import mainFrameworkUtils.ContentRepo;
import org.testng.Assert;
import org.testng.annotations.Test;
import testFrameworkUtils.BaseTest;
import xpathRepo.SauceDemoXpathRepo;

import java.time.Duration;

//@Listeners(testFrameworkUtils.Listeners.class)
public class verifyAppLogoAndProductTitle extends BaseTest {

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
}
