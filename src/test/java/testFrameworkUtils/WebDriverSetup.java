package testFrameworkUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class WebDriverSetup {
    public WebDriver driver;
    public String testEnvUrl;

    public WebDriver launchApplication() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\configSettings\\GlobalData.properties");
        prop.load(fis);

        String browserName = prop.getProperty("browser");
        testEnvUrl = prop.getProperty("testEnvURL");

        if (browserName.equalsIgnoreCase("edge")){
            driver = new EdgeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else {
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        driver.get(testEnvUrl);
        return driver;
    } // launchApplication


} // WebDriverSetup
