package allProductTest.BlogspotWebsite;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import productFunctionLibrary.BlogspotFunctionLibrary;
import testFrameworkUtils.BaseTest;
import testFrameworkUtils.WebDriverSetup;

public class testEnterName extends BaseTest {
    WebDriver driver;
    BlogspotFunctionLibrary bfl;
    Logger logger = LogManager.getLogger(this.getClass());

    @BeforeMethod
    public void setUp() throws Exception {
        WebDriverSetup webDriverSetup = new WebDriverSetup();
        driver = webDriverSetup.launchApplication();

        bfl = new BlogspotFunctionLibrary(driver);
        logger.info("Application launched successfully....");
    }

    @Test
    public void addName(){
        String name = "Gulshan";
        bfl.enterName(name);
        logToExtent(name+" is added in Name Field");
        System.out.println("Hi this is feature/gulshan");
    }



    @AfterMethod
    public void teaDown(){
        driver.quit();
    }
} // testEnterName
