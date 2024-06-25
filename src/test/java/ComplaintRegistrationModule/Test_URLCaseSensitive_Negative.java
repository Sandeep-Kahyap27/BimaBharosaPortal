package ComplaintRegistrationModule;

import Base.MainClass;
import ObjectRepository.HomePage;
import Utilities.Property;
import Utilities.Screenshot;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;


public class Test_URLCaseSensitive_Negative extends MainClass {
    @Test
    public void test_caseSensitiveURL() throws IOException, InterruptedException {

        loadURL("Url");

        test = extent.createTest("Verify URL is not case sensitive");

        Properties prop = Property.readPropertiesFile(filePath);
        String CaseSensitiveURL = (prop.getProperty("Url")).toUpperCase();

        driver.get(CaseSensitiveURL);
        Thread.sleep(3000);

        HomePage h = new HomePage(driver);
        if(h.getBimaBharosaPortalLogo()){
            test.log(Status.PASS, "URL is redirecting to the correct page");
            logger.info("Test Passed : URL is redirecting to the correct page");
        }
        else{
            test.log(Status.FAIL, "URL is not redirecting to the correct page");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : URL is not redirecting to the correct page");
        }



    }
}
