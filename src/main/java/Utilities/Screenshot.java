package Utilities;


import Base.MainClass;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.Date;


public class Screenshot extends MainClass {

    public static void Takescreenshot(String method) throws IOException {

        System.out.println(method);

        Date d = new Date();
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String TimeStamp = d.toString().replace(":", "_").replace(" ", "_");
        try {
            FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") + "/Screenshot/screenshot" + TimeStamp + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Image utility method filePath
    public static String captureScreenShot(String fileName){
        Date currentDate = new Date();
        String timeStamp = currentDate.toString().replace(" ", "_").replace(":","_");

        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File("./Screenshot/"+fileName+"_"+timeStamp+".jpg");
        try{
            FileUtils.copyFile(srcFile, destFile);
        }
        catch (IOException e){

        }
        return destFile.getAbsolutePath();
    }

    //Image utility method base64Img
    public static String captureScreenShot(){
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        String base64_Img = screenshot.getScreenshotAs(OutputType.BASE64);
        return base64_Img;
    }


}
