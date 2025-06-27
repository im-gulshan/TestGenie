package productFunctionLibrary;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SeleniumFunctionLibrary {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    Logger logger = LogManager.getLogger(this.getClass());

    //Constructor
    public SeleniumFunctionLibrary(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
    }


    public void enterText(String xpath, String text){
        driver.findElement(By.xpath(xpath)).sendKeys(text);
        logger.info("Entered Text : "+text+", in "+xpath);
    }

    public void clickOnElement(String xpath){
        driver.findElement(By.xpath(xpath)).click();
        logger.info("Clicked on : "+xpath);
    }

    public List<WebElement> getListOfElements(String xpath){
        return driver.findElements(By.xpath(xpath));
    }

    public void waitForVisibilityOfElement(WebElement xpath){
        logger.info("Waiting for visbility of element : "+xpath);
        wait.until(ExpectedConditions.visibilityOf(xpath));
    }

    public String getTextOfElement(String xpath){
        WebElement element =  driver.findElement(By.xpath(xpath));
        logger.info("Text retrieved from element : "+xpath);
        return element.getText();
    }

} // SeleniumFunctionLibrary
