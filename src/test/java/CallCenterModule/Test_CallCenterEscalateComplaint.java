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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;

public class Test_CallCenterEscalateComplaint extends MainClass {

    Properties pro = null;
    private String complaint_number;

    @BeforeClass
    public void launchUrl() {
        loadURL("LoginURL");
    }


    @Test(priority = 1, dataProvider = "Call_Center_NewUser", dataProviderClass = DataReader.class)
    public void test_Call_Center_Escalate_Complain_Empty(String complaint_name, String mobile_number, String email, String pinCode, String compAgainst, String insCompany, String policyType, String complaintType, String complaintDescType, String policyNumber, String complaintDetails, String source, String priority, String TAT, String priority_handling_details, String remarks) throws InterruptedException, IOException {
        test = extent.createTest("Negative: Verify Escalation empty field");
        LoginHelper.backToSafety();
        pro = Property.readPropertiesFile(System.getProperty("user.dir") + "/src/main/resources/Properties/Credentials.properties");
        LoginHelper.login(pro.getProperty("CallCenter_UserID"), pro.getProperty("CallCenter_Password"));

        SidebarHeader sd = new SidebarHeader(driver);
        sd.clickMenu();
        sd.clickOnComplaints();
        sd.clickOnRegisterAgainstEntity();

        //Register new complaint on behalf of policy holder
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.IsComplaintNotRegistered();
        cr.enterName(complaint_name);
        cr.enterMobileNumber(generateRandomMobileNumber());
        cr.enterEmailId(email);
        cr.enterPinCode(pinCode);
        cr.enterComplaintAgainstType(compAgainst);
        scrollToComplaint();
        cr.enterInsuranceCompanyName(insCompany);
        cr.enterPolicyType(policyType);
        cr.enterComplaintType(complaintType);
        cr.enterComplaintDescType(complaintDescType);
        cr.selectPolicyNumber();
        cr.enter_Policy_Proposal_Certificate_Claim_Number(policyNumber);
        cr.enterComplaintDetails(complaintDetails);
        cr.enterSourceOfComplaint(source);
        cr.select_Complaint_Date();
        cr.select_Complaint_Receipt_Date();
        cr.clickOnRegister();

        PopUpPage pg = new PopUpPage(driver);
        pg.acceptAlertWindow();
        Thread.sleep(3000);

        complaint_number = pg.getComplaintNumber();
        System.out.println(complaint_number);
        cr.logOutSuccess();


        //Resolve complaint and make it to Attended to status
        LoginHelper.login(pro.getProperty("Hdfc_Ergo_User_Id"), pro.getProperty("Hdfc_Ergo_Password"));

        //Menu-->Complaints-->View/Edit complaints
        SidebarHeader sbr = new SidebarHeader(driver);
        Thread.sleep(1000);
        sbr.clickMenu();
        Thread.sleep(1000);
        sbr.clickcomplaintSidebarTab();
        Thread.sleep(1000);
        sbr.clickviewEditcomplaintSidebarTab();

        Thread.sleep(1000);
        //Menu-->Complaints-->View/Edit complaints-->Advance Search
        AdvanceSearchPage asp = new AdvanceSearchPage(driver);
        asp.enterComplaintNumber(complaint_number);
        asp.clickSearchBtn();
        //Complaint Details Page
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        vcd.click_View_Edit_Complaints_Checkbox();

        //New to Acknowledge
        vcd.enter_Value_Branch_Code_field(2);
        vcd.enter_Value_State_Name_Field(1);
        vcd.clkDistric();
        Thread.sleep(1000);
        vcd.downArrow();
        Thread.sleep(1000);
        vcd.enterKeyBoard();
        vcd.click_Complaint_Status_Dropdown("Acknowledged");
        vcd.click_Update_Button();
        try{
            vcd.clickToYesButtonToConfirmRecordUpdate();
            vcd.click_Complaint_Updated_Successfully_Button();
        }
        catch (Exception e){
            System.out.println("Web service not available!!");
        }

        //Acknowledge to Pending
        asp.enterComplaintNumber(complaint_number);
        asp.clickSearchBtn();
        ViewEditComplaintDetailsPage vcdAtPending = new ViewEditComplaintDetailsPage(driver);
        vcdAtPending.click_View_Edit_Complaints_Checkbox();
        ComplaintRegPage crg = new ComplaintRegPage(driver);
        crg.scrollToComplaint();
        vcdAtPending.click_Complaint_Status_Dropdown("Pending");
        vcdAtPending.click_Update_Button();
        vcdAtPending.clickToYesButtonToConfirmRecordUpdate();
        vcdAtPending.click_Complaint_Updated_Successfully_Button();

        //Pending to Attended To
        asp.enterComplaintNumber(complaint_number);
        asp.clickSearchBtn();
        ViewEditComplaintDetailsPage vcdAtAttendedTo = new ViewEditComplaintDetailsPage(driver);
        vcdAtAttendedTo.click_View_Edit_Complaints_Checkbox();
        crg.scrollToComplaint();
        vcdAtAttendedTo.click_Complaint_Status_Dropdown("Attended to");
        vcdAtAttendedTo.enter_Value_Remark_by_Regulated_Entity_Field("Test remark");
        vcdAtAttendedTo.click_Type_of_Disposal_Infavour_Button();
        vcdAtAttendedTo.click_Update_Button();
        vcdAtAttendedTo.clickToYesButtonToConfirmRecordUpdate();
        vcdAtAttendedTo.click_Complaint_Updated_Successfully_Button();

        CallcenterViewEditPage cve = new CallcenterViewEditPage(driver);
        cve.logOutSuccess();


        //Call center login
        LoginHelper.login(pro.getProperty("CallCenter_UserID"), pro.getProperty("CallCenter_Password"));
        cve.enterComplaintNumber(complaint_number);
        cve.clkSearchBtn();
        scrollToComplaint();
        cve.clkEditIcon();
        scrollToComplaint();
        scrollToComplaint();
        scrollToComplaint();
        cve.clkExclationBtn();
        //scrollToUpward();
        String expctErrMsg = "Please Enter Escalation Remark";

        if (cve.getTextErrExclation().equals(expctErrMsg)) {
            test.log(Status.PASS, "Test Pass : Escalation can not be empty");
            logger.info("Test Passed : Escalation can not be empty");


        } else {

            test.log(Status.FAIL, "Test Fail : Error msg not displayed when Escalation field is empty ");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Error msg not displayed when Escalation field is empty ");

        }

    }


    @Test(priority = 2)
    public void test_Call_Center_Escalate_Complain_Invalid() throws InterruptedException, IOException {
        test = extent.createTest("Negative: Verify Escalation field with less the twenty Characters");
        CallcenterViewEditPage cve = new CallcenterViewEditPage(driver);
        cve.enterExclation("Less the 20 Chars");
        scrollToComplaint();
        cve.clkExclationBtn();
        //scrollToUpward();

        String expectedErrMsgInvalid="Please Enter Atleast 20 Characters";

        if (cve.getTextErrExclation().equals(expectedErrMsgInvalid)) {
            test.log(Status.PASS, "Test Pass : Error message displayed when entering less than 20 characters in escalation field");
            logger.info("Test Passed : Error message displayed when entering less than 20 characters in escalation field");
        } else
        {
            test.log(Status.FAIL, "Test Fail : Error msg not displayed when less than 20 character entered");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Error msg not displayed when less than 20 character entered");
        }
    }


    @Test(priority = 3)
    public void test_Call_Center_Escalate_Complaint_Special_Character() throws InterruptedException, IOException {
        test = extent.createTest("Negative: Verify Escalation field with Special Characters");
        CallcenterViewEditPage cve = new CallcenterViewEditPage(driver);
        cve.clrExclationField();
        cve.enterExclation("$#@&dfdg");
        scrollToComplaint();
        cve.clkExclationBtn();
        //scrollToUpward();

        String expectedErrMsgInvalid="Please Enter Valid Escalation Remark";

        if (cve.getTextErrExclation().equals(expectedErrMsgInvalid)) {
            test.log(Status.PASS, "Test Pass : Error message displayed for special characters in escalation field");
            logger.info("Test Passed : Error message displayed for special characters in escalation field");
        } else
        {
            test.log(Status.FAIL, "Test Fail : Error msg not displayed!");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("TTest Failed : Error msg not displayed!");
        }
    }


    @Test(priority = 4)
    public void test_Call_Center_Escalate_Complaint_Valid() throws InterruptedException, IOException {
        test = extent.createTest("Positive: Verify Escalation field with Special Characters");
        CallcenterViewEditPage cve = new CallcenterViewEditPage(driver);
        cve.clrExclationField();
        cve.enterExclation("Complaint escalated by call center user");
        scrollToComplaint();
        cve.clkExclationBtn();
        PopUpPage pg = new PopUpPage(driver);
        pg.acceptAlertWindow();
        Thread.sleep(3000);
        if(pg.getUpdateConfirmationMessage().equals("Complaint Updated Successfully")){
            test.log(Status.PASS, "Complaint Escalated Successfully");
            logger.info("Test Passed : Complaint Escalated Successfully");
        }
        else{
            test.log(Status.FAIL, "Complaint Not Escalated Successfully");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Complaint Not Escalated Successfully");
        }
        cve.enterComplaintNumber(complaint_number);
        cve.clkSearchBtn();
        scrollToComplaint();

        if(cve.verifyComplaintStatus().equals("Escalated")){
            test.log(Status.PASS, "Complaint status matching with Escalated status");
            logger.info("Test Passed : Complaint status matching with Escalated status");
        }
        else{
            test.log(Status.FAIL, "Complaint status does not match!");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Complaint status does not match!");
        }
    }


    @AfterClass
    public void logOut() throws InterruptedException {
        LoginPage lp = new LoginPage(driver);
        lp.logOutSuccess();
    }


}
