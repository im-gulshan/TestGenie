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
        enterText(BlogspotXpathRepo.name, name);
    }

    public void enterEmail(String name){
        enterText(BlogspotXpathRepo.email, name);
    }


} // BlogspotFunctionLibrary
