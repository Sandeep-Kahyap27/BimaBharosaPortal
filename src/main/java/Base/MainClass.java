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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainClass extends ExtentReport implements ITestListener {

    public static Random random;

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

    public void scrollToComplaint() throws InterruptedException {

        Thread.sleep(3000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)");
        Thread.sleep(3000);

    }

    public void scrollToUpward() throws InterruptedException {
        Thread.sleep(3000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,-400)");
        Thread.sleep(3000);
    }

    //Abhishek :  Added method to print stacktrace in log file for failed Testcases
    @AfterMethod
    public void afterFailedTestMethod(ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE){
            Throwable t = result.getThrowable();
            StringWriter error = new StringWriter();
            t.printStackTrace(new PrintWriter(error));
            logger.error(error.toString());
        }
    }

    //Generate random string for specified length
    public static String generateRandomString(int length) {
        // Define the characters allowed in the random string (both uppercase and lowercase alphabet)
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        // Initialize a Random object
        random = new Random();

        // Initialize a StringBuilder to store the random string
        StringBuilder sb = new StringBuilder(length);

        // Generate random characters from the alphabet and append them to the StringBuilder until it reaches the desired length
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(randomIndex);
            sb.append(randomChar);
        }

        // Convert the StringBuilder to a String and return
        return sb.toString();
    }

    //Generate random mobile number
    public static String generateRandomMobileNumber() {
        random = new Random();

        // First digit should be between 6 and 9 (inclusive)
        int firstDigit = random.nextInt(6)+1 ;

        // Remaining 9 digits can be between 0 and 9
        StringBuilder mobileNumber = new StringBuilder();
        mobileNumber.append(firstDigit);

        for (int i = 0; i < 9; i++) {
            mobileNumber.append(random.nextInt(10));
        }
        //System.out.println(mobileNumber);
        return mobileNumber.toString();
    }


    //Generate entity reference number
    public static String generateEntityReferenceNo() {
        random = new Random();
        String entityCompRefNo = "EXL" + String.valueOf(random.nextInt(Integer.parseInt("1000000")));

        return entityCompRefNo;
    }

    //Generate policy number
    public static String generatePolicyNumber() {
        random = new Random();
        String entityCompRefNo = "PL" + String.valueOf(random.nextInt(Integer.parseInt("1000000")));

        return entityCompRefNo;
    }


    //Select random name from string of array
    public static String getRandomName(){
        //Create a string array with names
        String[] names = {"Rohit Gupta", "Nutan Bhor", "Sandeep kashyap", "Manisha Buwa", "Debabrata Jena", "Rahul Yadav", "Rakesh Yadav", "Priyanka Gupta", "Anchal Soni", "Ajay Gupta" };

        // Create an instance of Random class
        random = new Random();

        //Generate a random index within the range of the array length
        int randomIndex = random.nextInt(names.length);

        // Select a random name from the array
        String name = names[randomIndex];

        return name;
    }

    //Generate random email with combination of string and numbers
    public static String generateEmailId(){
        String CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
        String NUMBERS = "0123456789";
        String[] DOMAINS = {"gmail.com", "yahoo.com", "outlook.com", "example.com"};

        String characterSet = CHARACTERS+NUMBERS;

        random = new Random();
        int domain_index = random.nextInt(DOMAINS.length);

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(characterSet.length());
            stringBuilder.append(characterSet.charAt(index));
        }

        return stringBuilder.toString()+"@"+DOMAINS[domain_index];

    }


    //Method to get current date
    public static String getCurrentDate() {

        LocalDate currentDate = LocalDate.now();

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String presentDate = currentDate.format(dateFormat);

        return presentDate;

    }

    //Method to get Future date
    public static String getFutureDate() {
        // Get the current date
        Calendar calendar = Calendar.getInstance();

        // Add 7 days to the current date (to get a future date)
        calendar.add(Calendar.DAY_OF_YEAR, 7);

        // Format the date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String futureDate = sdf.format(calendar.getTime());

        return futureDate;
    }

    //Method to get Past date
    public static String getPastDate() {

        // Get the current date
        Calendar calendar = Calendar.getInstance();

        //  63 days back to the current date (to get a past date)
        calendar.add(Calendar.DAY_OF_YEAR, -63);

        // Format the date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String pastDate = sdf.format(calendar.getTime());

        return pastDate;
    }


    /*@AfterClass
    public void teardown() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }*/

    @AfterSuite
    public void reportTearDown() throws Exception {
        ExtentReport.endTest();
    }

}
