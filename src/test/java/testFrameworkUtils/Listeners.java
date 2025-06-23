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

        WebDriver driver = null;
        try {
            Object testInstance = result.getInstance();
            Class<?> clazz = testInstance.getClass();
            java.lang.reflect.Field field = clazz.getDeclaredField("driver");
            field.setAccessible(true);
            driver = (WebDriver) field.get(testInstance);
        } catch (Exception e) {
            throw new RuntimeException("Failed to access WebDriver 'driver' field", e);
        }

        ScreenshotUtilities screenshot = new ScreenshotUtilities();
        String testCaseName = result.getMethod().getMethodName();
        String relativePath = screenshot.getScreenshot(testCaseName, driver); // now returns relative path

        try {
            baseTest.test.addScreenCaptureFromPath(relativePath, testCaseName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        baseTest.extent.flush();
    }

} // Listeners
