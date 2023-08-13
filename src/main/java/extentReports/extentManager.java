package extentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class extentManager {
    public static final ExtentReports extentReports = new ExtentReports();
    public synchronized static ExtentReports createExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("test-output/extentreport.html");
        reporter.config().setEncoding("utf-8");
        reporter.config().setTheme(Theme.STANDARD);
        reporter.config().setReportName("POC : HRM WEB Based Extent Report");
        reporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Author", "Mayank Patel");

        return extentReports;
    }
}
