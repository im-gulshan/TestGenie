package testFramework.base;

import mainFrameworkUtils.ConfigurationManager;
import productFunctionLibrary.BlogspotFunctionLibrary;
import org.testng.annotations.BeforeClass;
import testFramework.core.BaseTest;

public class BlogspotBaseTest extends BaseTest {
    protected BlogspotFunctionLibrary bfl;

    @BeforeClass(alwaysRun = true, dependsOnMethods = "setUp")
    public void initBlogspot() {
        // Navigate to Blogspot URL (read from Blogspot.properties)
        String url = ConfigurationManager.getProperty("Blogspot", "url");
        driver.get(url);

        // Initialize BlogspotFunctionLibrary
        bfl = new BlogspotFunctionLibrary(driver);
        logger.info("Blogspot application launched and initialized successfully.");
        logToExtent("Blogspot application launched successfully.");
    }
}
