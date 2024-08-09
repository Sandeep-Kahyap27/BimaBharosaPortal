package CallCenterModule;

import Base.LoginHelper;
import Base.MainClass;
import ObjectRepository.*;
import Utilities.DataReader;
import Utilities.Property;
import Utilities.Screenshot;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;

public class Test_CallCenterRegistration_DuplicateComplaint extends MainClass {

    @Test(dataProvider = "Call_Center_Register", dataProviderClass = DataReader.class)
    public void test_callCenter_registration_duplicate_complaint(String identifyBy, String mob, String pinCode, String compAgainst, String insCompany, String policyType, String complaintType, String complaintDescType, String policyNumber, String complaintDetails, String source, String priority, String TAT, String priority_handling_details, String remarks) throws IOException, InterruptedException {

        test = extent.createTest("Negative: Verify call center user is unable to register duplicate complaint");
        loadURL("LoginURL");

        Properties property = Property.readPropertiesFile(System.getProperty("user.dir") + "/src/main/resources/Properties/Credentials.properties");
        LoginHelper.backToSafety();
        LoginHelper.login(property.getProperty("CallCenter_UserID"), property.getProperty("CallCenter_Password"));

        //Pre-requisite
        String complaint_number = "06-24-001869";

        CallcenterViewEditPage cve = new CallcenterViewEditPage(driver);
        cve.enterComplaintNumber(complaint_number);
        cve.clkSearchBtn();
        scrollToComplaint();
        cve.clkEditIcon();
        scrollToComplaint();

        String duplicate_complaint_desc_type = cve.getComplaintDescType();
        System.out.println(duplicate_complaint_desc_type);
        String duplicate_policy_number = cve.getPolicyNumber();
        String duplicate_complaint_details = cve.getComplaintDetails();

        scrollToComplaint();
        cve.clkCancelBtn();
        Thread.sleep(2000);

        //Registration starting from here
        SidebarHeader sb = new SidebarHeader(driver);
        sb.clickMenu();
        sb.clickOnComplaints();
        sb.clickOnRegisterAgainstEntity();

        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.IsComplaintRegistered();
        cr.identifyBy(identifyBy);
        cr.enterMobileNumberOrEmail(mob);
        cr.search();
        cr.enterPinCode(pinCode);
        cr.enterComplaintAgainstType(compAgainst);
        scrollToComplaint();
        cr.enterInsuranceCompanyName(insCompany);
        cr.enterPolicyType(policyType);
        cr.enterComplaintType(complaintType);
        cr.enterComplaintDescType(duplicate_complaint_desc_type);
        cr.selectPolicyNumber();
        cr.enter_Policy_Proposal_Certificate_Claim_Number(duplicate_policy_number);
        cr.enterComplaintDetails(duplicate_complaint_details);
        cr.enterSourceOfComplaint(source);
        cr.select_Complaint_Date();
        cr.select_Complaint_Receipt_Date();
        cr.clickOnRegister();
        PopUpPage pg = new PopUpPage(driver);
        pg.acceptAlertWindow();
        Thread.sleep(2000);
        String expected_warning_message = "Complaint has been already registered with same policy number and same complaint description";
        if(pg.getWarningMessage().equals(expected_warning_message)){
            test.log(Status.PASS, "Call center user not able to register duplicate complaint");
            logger.info("Test Passed : Call center user not able to register duplicate complaint");
        }
        else{
            test.log(Status.FAIL, "Duplicate complaint registered!");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Duplicate complaint registered!");
        }

    }

    @AfterClass
    public void logOut() throws InterruptedException {
        LoginPage lp = new LoginPage(driver);
        lp.logOutSuccess();
    }
}
