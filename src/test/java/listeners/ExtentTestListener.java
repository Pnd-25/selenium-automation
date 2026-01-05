package listeners;

import com.aventstack.extentreports.*;
import org.testng.*;
import utils.ExtentManager;
import base.BaseTest;
import utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;

public class ExtentTestListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getExtent();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        test.set(extent.createTest(result.getMethod().getMethodName()));
        test.get().info("Test Started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());

        // Attach screenshot on failure
        WebDriver driver = ((BaseTest) result.getInstance()).driver;
        String screenshotPath =
                ScreenshotUtil.takeScreenshot(driver, result.getMethod().getMethodName());

        test.get().addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
