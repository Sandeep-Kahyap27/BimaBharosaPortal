package Base;

import ObjectRepository.LoginPage;
import org.openqa.selenium.*;
import javax.swing.*;
import java.io.IOException;

public class LoginHelper extends MainClass{

    public static void login(String userId, String password) throws InterruptedException, IOException{

        LoginPage lp = new LoginPage(driver);
        lp.enterUsername(userId);
        lp.enterPassword(password);

        String str1 = JOptionPane.showInputDialog("Enter Captcha");
        lp.enterCaptcha(str1);
        Thread.sleep(2000);

        //Captcha Automation using Tesseract
        /*File src = driver.findElement(By.id("m_imgCaptcha")).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir")+"\\Screenshot\\captcha.png";
        FileHandler.copy(src, new File(path));

        ITesseract image = new Tesseract();
        String str = image.doOCR(new File(path));

        System.out.println(str);*/

        lp.clkLoginbtn();
    }


    public static void backToSafety(){
        //handle unsecure warning pages
        try{
            JavascriptExecutor jse1 = (JavascriptExecutor) driver;
            jse1.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@id='details-button']")));

            JavascriptExecutor jse2 = (JavascriptExecutor) driver;
            jse2.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@id='proceed-link']")));

        }
        catch (NoSuchElementException e){

        }
    }
}
