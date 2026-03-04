package allProductTest.BlogspotWebsite;

import org.testng.annotations.Test;
import testFramework.base.BlogspotBaseTest;

public class testEnterName extends BlogspotBaseTest {

    @Test(priority = 1)
    public void addName() throws InterruptedException {
        String name = "Gulshan";
        bfl.enterName(name);
        logToExtent(name + " is added in Name Field");
        logger.info("{} is added in Name Field", name);

        String email = "abc@gmail.com";
        bfl.enterEmail(email);
        logToExtent(email + " is added in Email Field");
        logger.info("{} is added in Email Field", email);

        Thread.sleep(4000);
    }
} // testEnterName
