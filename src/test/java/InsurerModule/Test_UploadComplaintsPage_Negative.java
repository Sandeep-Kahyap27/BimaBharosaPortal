package InsurerModule;

import Base.ExcelHelper;
import Base.LoginHelper;
import Base.MainClass;
import Constants.*;
import ObjectRepository.LoginPage;
import ObjectRepository.PopUpPage;
import ObjectRepository.SidebarHeader;
import ObjectRepository.UploadComplaintsPage;
import Utilities.Property;
import com.aventstack.extentreports.Status;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class Test_UploadComplaintsPage_Negative extends MainClass {

    @BeforeClass
    public void beforeMethod() throws IOException, InterruptedException {
        loadURL("LoginURL");
        //read cred from prop file and login with valid credentials
        Property.readPropertiesFile(System.getProperty("user.dir") + "/src/main/resources/Properties/Credentials.properties");
        LoginHelper.backToSafety();
        LoginHelper.login(Property.getHDFCErgoUserID(), Property.getHDFCErgoPassword());
        SidebarHeader sb = new SidebarHeader(driver);
        sb.clickMenu();
        Thread.sleep(1000);
        sb.clickOnComplaints();
        Thread.sleep(1000);
        sb.clickUploadComplaintsSidebarTab();
    }






    @AfterClass
    public void tearDown() throws InterruptedException {
        LoginPage lp = new LoginPage(driver);
        lp.logOutSuccess();
    }
}
