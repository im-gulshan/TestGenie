package testFrameworkUtils;

import mainFrameworkUtils.TimestampFormatter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class ScreenshotUtilities {
    public String getScreenshot(String testCaseName, WebDriver driver){
        TimestampFormatter dateFormat = new TimestampFormatter();
        String timeStamp = dateFormat.fetchCurrentTimeStamp();

        String destString = System.getProperty("user.dir")+"//reports//"+"//screenshot//"+testCaseName+"-"+timeStamp+".png";
        File destination = new File(destString);

        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, destination);
        }catch (Exception e){
            System.err.println("Failed to capture screenshot: "+e.getMessage());
        }
        return destString;
    }
} // ScreenshotUtilities
