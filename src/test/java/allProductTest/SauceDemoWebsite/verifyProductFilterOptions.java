package allProductTest.SauceDemoWebsite;

import mainFrameworkUtils.ContentRepo;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import testFrameworkUtils.BaseTest;
import xpathRepo.SauceDemoXpathRepo;

import java.util.ArrayList;
import java.util.List;

public class verifyProductFilterOptions extends BaseTest {

    @Test(description = "Verify that product filter options match the expected values")
    public void TC_003_testProductFilterOptions() {
        logToExtent("SauceDemo application launched successfully.");

        // Step 1: Login
        sdfl.loginInSauceDemo();
        logToExtent("User logged in successfully.");

        // Step 2: Fetch actual filter options
        List<WebElement> elements = sdfl.getListOfElements(SauceDemoXpathRepo.productFilterOptions);
        List<String> actualFilterOptions = new ArrayList<>();
        for (WebElement element : elements) {
            actualFilterOptions.add(element.getText().trim());
        }

        logToExtent("Actual product filter options: " + actualFilterOptions);

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
}
