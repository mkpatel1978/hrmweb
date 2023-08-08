package extentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

public class extentTestManager {
    static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
    static ExtentReports extent = extentManager.createExtentReports();

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) (long) Thread.currentThread().getId());
    }

    public static synchronized ExtentTest startTest(String testName) {
        ExtentTest test = extent.createTest(testName);
        extentTestMap.put((int) Thread.currentThread().getId(), test);

        return test;
    }

    public static synchronized void endTest() {
        if (extent != null) {
            extent.flush();
        }
    }

}

