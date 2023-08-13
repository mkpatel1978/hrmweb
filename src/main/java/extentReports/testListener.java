package extentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.ITestResult;
import org.testng.annotations.IListenersAnnotation;
import org.testng.internal.annotations.IListeners;

import java.io.File;
import java.io.IOException;

import static extentReports.extentTestManager.*;

public class testListener implements ITestListener {
    private static ExtentReports extentReports;
    public static ExtentTest test;
    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public void onTestStart(ITestResult result) {
//        ExtentTest test = extentTestManager.startTest("TestCase Name : " + result.getTestClass().getName() + " - " +
//                        result.getMethod().getMethodName())
//                .assignCategory("System Testing");
        test = startTest("TestCase Name : " + result.getTestClass().getName() + " - " +
                result.getMethod().getMethodName())
                .assignCategory("System Testing");

        extentTest.set(test);

    }

    public void onTestSuccess(ITestResult result) {
        getTest().log(Status.PASS, "PASS");
    }

    public void onTestFailure(ITestResult result) {
        WebDriver driver = null;

        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }

        String imagePath = null;

        try {
            imagePath = snapshot(driver);
            System.out.println("mk: " + imagePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        getTest().log(Status.FAIL, getTest().addScreenCaptureFromPath(imagePath) + "FAIL");

    }

    public void onStart(ITestContext context) {
//        extentReports = extentManager.createExtentReports();
        System.out.println("Execution Start : " + context.getStartDate());

    }

    public void onFinish(ITestContext context) {
        extentTestManager.endTest();
        System.out.println("Execution End : " + context.getEndDate());
    }
}
