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


public class hamburgerMenuSubOptions extends BaseTest {
    WebDriver driver;
    SauceDemoFunctionLibrary sdfl;
    Logger logger = LogManager.getLogger(this.getClass());

    @BeforeMethod
    public void setUp() throws Exception {
        WebDriverSetup webDriverSetup = new WebDriverSetup();
        driver = webDriverSetup.launchApplication();

        sdfl = new SauceDemoFunctionLibrary(driver);
        logger.info("SauceDemo application launched successfully....");
    }

    @Test
    public void testHamburgerMenuSubOptions() throws Exception{
        sdfl.loginInSauceDemo();
        logToExtent("User logged in successfully....");
        sdfl.clickOnElement(SauceDemoXpathRepo.hamburgerMenu);

        List<String> expectedOptions = ContentRepo.hamBurgerMenuSubOptions;
        List<WebElement> actualOptionsElement = sdfl.getListOfElements(SauceDemoXpathRepo.hamburgerMenuSubOptions);

        List<String> actualOptions = new ArrayList<>();
        for (WebElement webElement : actualOptionsElement) {
            sdfl.waitForVisibilityOfElement(webElement);
            String currentOption = webElement.getText();
            actualOptions.add(currentOption);
        }
        logToExtent("Expected Options : "+expectedOptions);
        logToExtent("Actual Options: " + actualOptions);

        if (!actualOptions.equals(expectedOptions)) {
            logToExtent("Mismatch in hamburger menu options. Test will be marked as failed.");
            Assert.fail("Mismatch in hamburger menu options.\nExpected: " + expectedOptions + "\nActual: " + actualOptions);
        }
    }


    @AfterMethod
    public void tearDown(){
        driver.quit();
        logger.info("Browser closed successfully....");
    }



}
