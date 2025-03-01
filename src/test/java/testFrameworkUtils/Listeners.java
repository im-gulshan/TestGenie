package testFrameworkUtils;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {

    private BaseTest baseTest;

    @Override
    public void onTestStart(ITestResult result){
        baseTest = (BaseTest) result.getInstance();
        baseTest.test = baseTest.extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        baseTest.test.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        baseTest.test.fail(result.getThrowable());

        WebDriver driver;
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e){
            throw new RuntimeException(e);
        }

        ScreenshotUtilities screenshot = new ScreenshotUtilities();
        String filePath = null;
        String testCaseName = result.getMethod().getMethodName();

        try {
            filePath = screenshot.getScreenshot(testCaseName, driver);
        }catch (Exception e){
            e.printStackTrace();
        }
        baseTest.test.addScreenCaptureFromPath(filePath, testCaseName);
    } // onTestFailure

    @Override
    public void onFinish(ITestContext context) {
        baseTest.extent.flush();
    }

} // Listeners
