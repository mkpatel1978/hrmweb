package pageObjects;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class loginPage extends basePage {

    public loginPage(WebDriver browserdriver) {
        super(browserdriver);
    }

    By userName = By.xpath("//input[@placeholder='Username']");
    By password = By.xpath("//input[@placeholder='Password']");
    By submitBtn = By.xpath("//button[@type='submit']");//button[@type='submit']
    By verifyLogin = By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']");
    By logout = By.linkText("Logout");
//    By verifyFailLogin = By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']");
//
    By verifyFailLogin = By.cssSelector(".oxd-text.oxd-text--p.oxd-alert-content-text");


    By forgotLink = By.xpath("//p[@class='oxd-text oxd-text--p orangehrm-login-forgot-header']");
    By forgotMsg = By.xpath("//h6[@class='oxd-text oxd-text--h6 orangehrm-forgot-password-title']");

    public void inputCredential(String username, String pwd) {
        driver.findElement(userName).clear();
        driver.findElement(userName).sendKeys(username);
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(pwd);
    }

    public String loginSuccess() {
        driver.findElement(submitBtn).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(verifyLogin));
        driver.findElement(verifyLogin).click();

        return driver.findElement(logout).getText();
    }

    public String loginFail() {
        driver.findElement(submitBtn).click();

//        wait.until(ExpectedConditions.visibilityOfElementLocated(verifyFailLogin));

        wait.until(ExpectedConditions.presenceOfElementLocated(verifyFailLogin));


//        wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(verifyFailLogin)));

//        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(verifyFailLogin));

        return driver.findElement(verifyFailLogin).getText();
    }

    public void logout() {
        driver.findElement(verifyLogin).click();
        driver.findElement(logout).click();
    }

    public String forgotPage(String username) {
        driver.findElement(forgotLink).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(submitBtn));
        driver.findElement(userName).sendKeys(username);
        driver.findElement(submitBtn).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(forgotMsg));

        return driver.findElement(forgotMsg).getText();
    }
}