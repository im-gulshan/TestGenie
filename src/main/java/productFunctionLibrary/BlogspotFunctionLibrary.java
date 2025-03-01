package productFunctionLibrary;

import org.openqa.selenium.WebDriver;
import xpathRepo.BlogspotXpathRepo;

public class BlogspotFunctionLibrary extends SeleniumFunctionLibrary{
    WebDriver driver;

    public BlogspotFunctionLibrary(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public void enterName(String name){
        sendKeys(BlogspotXpathRepo.name, name);
    }


} // BlogspotFunctionLibrary
