package allProductTest.SauceDemoWebsite;

import mainFrameworkUtils.ContentRepo;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import testFrameworkUtils.BaseTest;
import xpathRepo.SauceDemoXpathRepo;

import java.util.ArrayList;
import java.util.List;

public class verifyHamburgerMenuSubOptions extends BaseTest {

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
}
