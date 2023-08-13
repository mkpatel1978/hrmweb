package extentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class extentTestManager {
    static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
    static ExtentReports extent = extentManager.createExtentReports();

    public static ExtentTest getTest() {
        return extentTestMap.get((int) (long) Thread.currentThread().getId());
    }

    public static ExtentTest startTest(String testName) {
        ExtentTest test = extent.createTest(testName);
        extentTestMap.put((int) Thread.currentThread().getId(), test);

        return test;
    }

    public static void endTest() {
        if (extent != null) {
            extent.flush();
        }
    }

    public static String snapshot(WebDriver driver) throws IOException {

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

//        File destionationFile = new File("test-output/mk.png");
        File destionationFile = new File("test-output/" + System.currentTimeMillis() + ".png");
        String absolutepath_screen = destionationFile.getAbsolutePath();
        FileUtils.copyFile(scrFile, destionationFile);
        System.out.println("indise: " + absolutepath_screen);

        return absolutepath_screen;
    }

}

