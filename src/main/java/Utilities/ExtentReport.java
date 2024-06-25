package Utilities;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReport {

    public static ExtentTest test;
    public static ExtentReports extent;

    public static ExtentSparkReporter sparkReporter;

    public static void startTest() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss");
        String timeStamp = dateFormat.format(new Date());
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/Reports/ExtentReport" + timeStamp + ".html")
                .viewConfigurer()
                .viewOrder()
                .as(new ViewName[]{
                        ViewName.TEST,
                        ViewName.DASHBOARD,
                        ViewName.LOG,
                        ViewName.CATEGORY,
                        ViewName.AUTHOR,
                        ViewName.DEVICE,
                        ViewName.EXCEPTION,
                })
                .apply();


        extent = new ExtentReports();

        sparkReporter.config().setDocumentTitle("Automation Test Report");
        sparkReporter.config().setReportName("Bima Bharosha Report");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

        // Attach the ExtentSparkReporter to the ExtentReports object
        extent.attachReporter(sparkReporter);


        // This line of code for screenshot at the time of test case failure
        //test.fail(MediaEntityBuilder.createScreenCaptureFromPath("img.png").build());

    }


    public static void endTest() {
        extent.flush();
    }

}
