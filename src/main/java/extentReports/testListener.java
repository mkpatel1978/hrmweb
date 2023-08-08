package extentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.ITestResult;
import org.testng.annotations.IListenersAnnotation;
import org.testng.internal.annotations.IListeners;

public class testListener implements ITestListener {
//    private static ExtentReports extentReports;
//    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public void onTestStart(ITestResult result) {
//        ExtentTest test =
        extentTestManager.startTest("TestCase Name : " + result.getTestClass().getName() + " - " + result.getMethod().getMethodName());

//        extentTest.set(test);
    }

    public void onTestSuccess(ITestResult result) {
        extentTestManager.getTest().log(Status.PASS, "PASS");
    }

    public void onTestFailure(ITestResult result) {
        extentTestManager.getTest().log(Status.FAIL, "FAIL");
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
