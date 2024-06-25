package Base;

import ObjectRepository.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;

public class LoginHelper extends MainClass {


    public static void login(String userId, String password) throws InterruptedException {

        LoginPage lp = new LoginPage(driver);
        lp.enterUsername(userId);
        lp.enterPassword(password);

        String str1 = JOptionPane.showInputDialog("Enter Captcha");
        lp.enterCaptcha(str1);
        Thread.sleep(2000);

        lp.clkLoginbtn();
    }

    public static void backtoSafty() {

        WebElement advance = driver.findElement(By.xpath("//button[@id='details-button']"));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", advance);

        WebElement proceed = driver.findElement(By.xpath("//a[@id='proceed-link']"));
        jse.executeScript("arguments[0].click();", proceed);

    }
}
