package Utilities;

import Base.MainClass;
import org.apache.commons.math3.analysis.function.Exp;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitTimeHelper extends MainClass {

    public static void implicitlyWait(int wait_time_in_sec){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(wait_time_in_sec));
    }


    public static void visibilityOfElementExplicitWait(WebElement element, int wait_time_in_sec){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(wait_time_in_sec));
            wait.until(ExpectedConditions.visibilityOf(element));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void invisibilityOfElementExplicitWait(WebElement element, int wait_time_in_sec){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(wait_time_in_sec));
            wait.until(ExpectedConditions.invisibilityOf(element));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void elementToBeClickableExplicitWait(WebElement element, int wait_time_in_sec){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(wait_time_in_sec));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void alertIsPresentExplicitWait(int wait_time_in_sec){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(wait_time_in_sec));
            wait.until(ExpectedConditions.alertIsPresent());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void textToBePresentInElementValueExplicitWait(WebElement element, String linktext_data, int wait_time_in_sec_data) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(wait_time_in_sec_data));
            wait.until(ExpectedConditions.textToBePresentInElementValue(element, linktext_data));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void attributeContainsInElementExplicitWait(WebElement element, String attribute_name_data, String partial_attribute_value_data, int wait_time_in_sec_data) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(wait_time_in_sec_data));
            wait.until(ExpectedConditions.attributeContains(element, attribute_name_data, partial_attribute_value_data));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
