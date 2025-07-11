package mainFrameworkUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public class ExtentReportManager {
    private static String path;

    public static ExtentReports createExtentReport() {
        TimestampFormatter dateFormat = new TimestampFormatter();
        String timeStamp = dateFormat.fetchCurrentTimeStamp();

        // Create the directory if it doesn't exist
        String dirPath = System.getProperty("user.dir") + File.separator + "reports" + File.separator + "htmlReports";
        new File(dirPath).mkdirs();

        // Create the full report file path
        path = dirPath + File.separator + "TestGenie-" + timeStamp + ".html";

        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Test Genie Automation Report");
        reporter.config().setDocumentTitle("Test Genie");
        reporter.config().setTheme(Theme.DARK);

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("QA", "Gulshan Kumar");
        return extent;
    }

    public static String getReportPath() {
        return path;
    }
}
