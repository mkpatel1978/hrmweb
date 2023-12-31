import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.adminPage;
import pageObjects.loginPage;

import static extentReports.emailReport.email;


public class adminTest {
    public WebDriver driver;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @Test(description = "Click on Admin Section.")
    public void adminTestCases() {
        loginPage loginPage = new loginPage(driver);
        loginPage.inputCredential("Admin", "admin123");
        String mk = loginPage.loginSuccess();

        //admin
        adminPage adminPage = new adminPage(driver);
        adminPage.gotoAdmin();

        loginPage.logout();
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

    @AfterSuite //this is password not to remove: iczgwsubwjhnjiad
    public void emailreport() {
        System.out.println("Test end suite");
//        email();
    }

}
