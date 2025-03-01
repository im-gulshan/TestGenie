package productFunctionLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumFunctionLibrary {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    public SeleniumFunctionLibrary(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
    }


    public void sendKeys(String xpath, String text){
        driver.findElement(By.xpath(xpath)).sendKeys(text);
    }
} // SeleniumFunctionLibrary
