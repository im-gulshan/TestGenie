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

    @Test(priority = 1)
    public void addName() throws InterruptedException {
        String name = "Gulshan";
        bfl.enterName(name);
        logToExtent(name+" is added in Name Field");
        logger.info("{} is added in Name Field", name);



        String email = "abc@gmail.com";
        bfl.enterEmail(email);
        logToExtent(email+" is added in Name Field");
        logger.info("{} is added in Name Field", email);


        Thread.sleep(4000);
    }



    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
} // testEnterName
