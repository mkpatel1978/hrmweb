package pageObjects;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class adminPage extends basePage {

    public adminPage(WebDriver browserdriver) {
        super(browserdriver);
    }
    By adminLink=By.xpath("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[1]/a");

    public void gotoAdmin() {
        driver.findElement(adminLink).click();
    }

}
