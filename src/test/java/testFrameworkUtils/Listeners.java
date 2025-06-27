package testFrameworkUtils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import mainFrameworkUtils.ExtentReportManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {

    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentTest test = BaseTest.extent.createTest(testName);
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "✅ Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().log(Status.FAIL, result.getThrowable());

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
        String relativePath = screenshot.getScreenshot(testCaseName, driver);

        try {
            extentTest.get().addScreenCaptureFromPath(relativePath, testCaseName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP, "⚠️ Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        BaseTest.extent.flush();
        extentTest.remove(); // clean up thread-local
    }
    public static ExtentTest getExtentTest() {
        return extentTest.get();
    }
}
