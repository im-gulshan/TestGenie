package productFunctionLibrary;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import xpathRepo.SauceDemoXpathRepo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SauceDemoFunctionLibrary extends SeleniumFunctionLibrary{
    WebDriver driver;
    Logger logger = LogManager.getLogger(this.getClass());

    public SauceDemoFunctionLibrary(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public void enterUserName(String userName){
        enterText(SauceDemoXpathRepo.userName, userName);
    }

    public void enterPassword(String password){
        enterText(SauceDemoXpathRepo.password, password);
    }


    public void loginInSauceDemo(){
        enterUserName("standard_user");
        enterPassword("secret_sauce");
        clickOnElement(SauceDemoXpathRepo.login);
        logger.info("Logged in into SauceDemo successfully....");
    }

    public boolean isSortedAZ(List<String> productList){
        List<String> sortedList = new ArrayList<>(productList);
        Collections.sort(productList);
        return productList.equals(sortedList);
    }

    public boolean isSortedZA(List<String> productList){
        List<String> sortedList = new ArrayList<>(productList);
        sortedList.sort(Collections.reverseOrder());
        return productList.equals(sortedList);
    }

    public boolean isSortedLowToHigh(List<Double> productList){
        List<Double> sortedList = new ArrayList<>(productList);
        Collections.sort(productList);
        return productList.equals(sortedList);
    }

    public boolean isSortedHighToLow(List<Double> productList){
        List<Double> sortedList = new ArrayList<>(productList);
        sortedList.sort(Collections.reverseOrder());
        return productList.equals(sortedList);
    }



}//SauceDemoFunctionLibrary
