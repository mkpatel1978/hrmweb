
import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.loginPage;
import org.openqa.selenium.WebDriver;


import java.time.Duration;


public class loginTest {
    public WebDriver driver;
//    loginPage loginPage;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @Test(priority = 1, description = "Validate Success Login.")
    public void loginSuccessTest() {
        loginPage loginPage = new loginPage(driver);
//        loginPage = new loginPage(driver);
//        Faker faker = new Faker();

        //Successful login
        loginPage.inputCredential("Admin", "admin123");

        String loginSuccess = loginPage.loginSuccess();
        Assert.assertEquals(loginSuccess, "Logout");

        loginPage.logout();
    }

    @Test(priority = 2, description = "Validate Failture Login.")
    public void loginFailed() {
        //Failure login
        loginPage loginPage = new loginPage(driver);
        Faker faker = new Faker();

        loginPage.inputCredential(faker.name().username(), faker.name().lastName());

        String loginFailed = loginPage.loginFail();
        Assert.assertEquals(loginFailed, "Invalid credentials");

    }

    @Test(priority = 3, description = "Validate Forgot Password Link.")
    public void forgotPassword() {
        //forgot login
        loginPage loginPage = new loginPage(driver);
        Faker faker = new Faker();

        String forgotPwdValidate = loginPage.forgotPage(faker.name().username());
        Assert.assertEquals(forgotPwdValidate, "Reset Password1 link sent successfully");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}
