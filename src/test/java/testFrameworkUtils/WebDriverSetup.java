package testFrameworkUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class WebDriverSetup {
    public WebDriver driver;
    public String testEnvUrl;

    public WebDriver launchApplication() throws IOException {
        // Load configuration
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +
                "\\src\\main\\java\\configSettings\\GlobalData.properties");
        prop.load(fis);

        String browserName = prop.getProperty("browser").toLowerCase();
        testEnvUrl = prop.getProperty("testEnvURL");
        boolean isHeadLess = Boolean.parseBoolean(prop.getProperty("headLess","false"));

        // Launch browser based on config
        switch (browserName) {
            case "chrome":
                driver = new ChromeDriver(getChromeOptions(isHeadLess));
                break;
            case "edge":
                driver = new EdgeDriver(getEdgeOptions(isHeadLess));
                break;
            case "firefox":
                driver = new FirefoxDriver(getFirefoxOptions(isHeadLess));
                break;
            default:
                throw new RuntimeException("Browser not supported: " + browserName);
        }

        driver.manage().window().maximize();
        driver.get(testEnvUrl);
        return driver;
    }

    private ChromeOptions getChromeOptions(boolean isHeadLess) {
        ChromeOptions options = new ChromeOptions();

        HashMap<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.default_content_setting_values.notifications", 2);

        options.setExperimentalOption("prefs", prefs);
        if (isHeadLess) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--window-size=1920,1080");
//        options.addArguments("--start-maximized");
        options.addArguments("--disable-features=PasswordManagerEnabled,AutofillServerCommunication,AutofillCreditCardAssist");

        return options;
    }

    private EdgeOptions getEdgeOptions(boolean isHeadLess) {
        EdgeOptions options = new EdgeOptions();

        HashMap<String, Object> edgePrefs = new HashMap<>();
        edgePrefs.put("credentials_enable_service", false);
        edgePrefs.put("profile.password_manager_enabled", false);
        edgePrefs.put("profile.default_content_setting_values.notifications", 2);

        options.setExperimentalOption("prefs", edgePrefs);
        options.addArguments("--disable-save-password-bubble");
        if (isHeadLess){
            options.addArguments("--headless=new");
        }
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");
//        options.addArguments("--start-maximized");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-features=PasswordManagerEnabled,AutofillServerCommunication,AutofillCreditCardAssist");

        return options;
    }

    private FirefoxOptions getFirefoxOptions(boolean isHeadLess) {
        FirefoxOptions options = new FirefoxOptions();

        // Set Firefox to headless mode
        if (isHeadLess){
            options.addArguments("--headless");
        }

        // Set window size using capability
        options.addArguments("--width=1920");
        options.addArguments("--height=1080");

        // Disable password saving in Firefox
        options.addPreference("signon.rememberSignons", false);
        options.addPreference("signon.autofillForms", false);
        options.addPreference("signon.storeWhenAutocompleteOff", false);
        options.addPreference("network.http.prompt-temp-redirect", false);
        options.addPreference("dom.webnotifications.enabled", false);

        return options;
    }
}
