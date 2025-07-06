package productFunctionLibrary;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * SeleniumFunctionLibrary provides reusable utility methods for common Selenium operations
 * such as clicking, sending text, waiting for visibility, etc.
 */
public class SeleniumFunctionLibrary {

    WebDriver driver;                         // WebDriver instance to control the browser
    WebDriverWait wait;                       // WebDriverWait for explicit waits
    JavascriptExecutor js;                    // For executing JavaScript when needed
    Logger logger = LogManager.getLogger(this.getClass());  // Logger for logging actions

    /**
     * Constructor initializes driver, wait, and JavaScript executor.
     * @param driver WebDriver instance passed from test class
     */
    public SeleniumFunctionLibrary(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // wait for max 10 seconds
        js = (JavascriptExecutor) driver;
    }

    public WebElement getWebElement(String xpath){
        return driver.findElement(By.xpath(xpath));
    }

    /**
     * Enters text into a field located using XPath.
     * @param xpath XPath of the element
     * @param text Text to enter
     */
    public void enterText(String xpath, String text){
        getWebElement(xpath).sendKeys(text);
        logger.info("Entered Text: " + text + " in element: " + xpath);
    }

    /**
     * Clicks on a web element identified by the given XPath.
     * @param xpath XPath of the element to click
     */
    public void clickOnElement(String xpath){
        getWebElement(xpath).click();
        logger.info("Clicked on element: " + xpath);
    }

    /**
     * Returns a list of web elements matching the given XPath.
     * @param xpath XPath of the elements
     * @return List of WebElements
     */
    public List<WebElement> getListOfElements(String xpath){
        return driver.findElements(By.xpath(xpath));
    }

    /**
     * Waits until the given WebElement is visible on the page.
     * @param element WebElement to wait for
     */
    public void waitForVisibilityOfElement(WebElement element){
        logger.info("Waiting for visibility of element: " + element);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Retrieves the visible text from the web element located using XPath.
     * @param xpath XPath of the element
     * @return Text content of the element
     */
    public String getTextOfElement(String xpath){
        WebElement element = getWebElement(xpath);
        logger.info("Text retrieved from element: " + xpath);
        return element.getText();
    }

    /**
     * Selects a value from a dropdown using the visible text.
     * Element is located using its class name (not XPath).
     * @param text Visible text to select from the dropdown
     * @param className Class name of the dropdown element
     */
    public void selectDropdownByVisibleText(String text, String className){
        WebElement element = driver.findElement(By.className(className));
        Select select = new Select(element);
        select.selectByVisibleText(text);
        logger.info("Selected '" + text + "' from dropdown with class name: " + className);
    }

} // End of SeleniumFunctionLibrary
