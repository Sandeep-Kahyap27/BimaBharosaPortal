package Base;


import ObjectRepository.HomePage;
//import Utilities.ExcelReport;
import Utilities.ExtentReport;
import Utilities.Property;
import Utilities.Screenshot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainClass extends ExtentReport implements ITestListener {

    public static WebDriver driver;
    public String filePath;

    public static boolean flag = false;
    protected long startTime;

    //Log4j implementation
    public static Logger logger = LogManager.getLogger(MainClass.class);

    //Exception throwing instance declaration
    Throwable throwable;

    Properties properties;

    @BeforeSuite
    public void reportSetup() {

        ExtentReport.startTest();
    }

    //Abhishek
    @BeforeClass
    public void setup() throws InterruptedException, IOException {

        filePath = System.getProperty("user.dir") + "/src/main/resources/Properties/config.properties";

        properties = Property.readPropertiesFile(filePath);

        try {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Launch define URL
    public void loadURL(String urlKey) {
        String url = properties.getProperty(urlKey);
        if (url != null) {
            driver.get(url);
        } else {
            throw new RuntimeException("URL not found in properties file: " + urlKey);
        }
    }


    public void onTestFailure(ITestResult result)
    {
        System.out.println("Test Failed");
        try
        {
            Screenshot.Takescreenshot(result.getName());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public static String generateRandomMobileNumber() {
        Random random = new Random();

        // First digit should be between 6 and 9 (inclusive)
        int firstDigit = random.nextInt(4) + 6;

        // Remaining 9 digits can be between 0 and 9
        StringBuilder mobileNumber = new StringBuilder();
        mobileNumber.append(firstDigit);

        for (int i = 0; i < 9; i++) {
            mobileNumber.append(random.nextInt(10));
        }

        return mobileNumber.toString();
    }

    //Abhishe :  Added method to print stacktrace in log file for failed Testcases
    @AfterMethod
    public void afterFailedTestMethod(ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE){
            Throwable t = result.getThrowable();
            StringWriter error = new StringWriter();
            t.printStackTrace(new PrintWriter(error));
            logger.error(error.toString());
        }
    }


    @AfterClass
    public void teardown() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }

    @AfterSuite
    public void reportTearDown() throws Exception {
        ExtentReport.endTest();
        //ExcelReport.generateExcelReport();
    }

}
