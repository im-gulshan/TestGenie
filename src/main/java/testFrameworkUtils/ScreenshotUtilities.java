package testFrameworkUtils;

import mainFrameworkUtils.TimestampFormatter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class ScreenshotUtilities {
    public String getScreenshot(String testCaseName, WebDriver driver) {
        TimestampFormatter dateFormat = new TimestampFormatter();
        String timeStamp = dateFormat.fetchCurrentTimeStamp();

        String destDir = System.getProperty("user.dir") + File.separator + "reports" + File.separator + "screenshot";
        String fileName = testCaseName + "-" + timeStamp + ".png";
        String destPath = destDir + File.separator + fileName;
        File destination = new File(destPath);

        try {
            new File(destDir).mkdirs();
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, destination);
        } catch (Exception e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        }

        // Return relative path from HTML report location
        return "../screenshot/" + fileName;
    }
}

