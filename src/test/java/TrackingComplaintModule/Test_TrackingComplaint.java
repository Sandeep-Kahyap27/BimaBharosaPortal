package TrackingComplaintModule;

import Base.MainClass;
import ObjectRepository.ComplaintRegPage;
import ObjectRepository.HomePage;
import ObjectRepository.TrackComplaint_Page;
import Utilities.DataReader;
import Utilities.Screenshot;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Test_TrackingComplaint extends MainClass {

    @BeforeClass
    public void launchURL(){
        loadURL("Url");
    }

    @Test(priority = 1)
    public void test_trckng_mobno_splcharaph_chk_negative() throws InterruptedException {
        test = extent.createTest("Negative: Verify user is not able to enter special characters");
        HomePage h = new HomePage(driver);
        TrackComplaint_Page t = new TrackComplaint_Page(driver);
        h.enterMobileNumber("abcdef!@#$");
        String s = h.get_spl_char_mob();

        if (t.hasSpecialCharacters(s)) {
            test.log(Status.FAIL, "Mobile number field accepting alphabet and special characters");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Mobile number field accepting alphabet and special characters");
        } else {
            test.log(Status.PASS, "Mobile number field doesn't accept alphabet and special characters");
            logger.info("Test Passed : Mobile number field doesn't accept alphabet and special characters");
        }
    }

    @Test(priority = 2)
    public void test_trckng_unregistered_mobno_chk_negative() throws InterruptedException {
        test = extent.createTest("Negative: Verify user is not able to track complaint with unregistered mobile number");
        HomePage h = new HomePage(driver);
        h.clearField_mob();
        h.enterMobileNumber("9503623160");
        h.trackComplaint();

        TrackComplaint_Page t = new TrackComplaint_Page(driver);


        if (t.OTP_Field_visible()) {
            test.log(Status.FAIL, "With unregistered mobile number able to track complaint");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : With unregistered mobile number able to track complaint");
        } else {
            test.log(Status.PASS, "With unregistered mobile number not able to track complaint");
            logger.info("Test Passed : With unregistered mobile number not able to track complaint");
        }
    }

    @Test(priority = 3, dataProvider = "CompAgainstInsCompanyData", dataProviderClass = DataReader.class)
    public void test_trckng_registered_mobno_chk(String name, String mob, String pinCode, String compAgainst, String insCompany, String policyType, String compType, String compDescType, String policyNumber, String compDesc) throws InterruptedException {
        test = extent.createTest("Positive: Verify user is able to track complaint with registered mobile number");
        HomePage h = new HomePage(driver);
        h.clearField_mob();
        h.enterMobileNumber(mob);
        h.trackComplaint();
        Thread.sleep(3000);
        TrackComplaint_Page t = new TrackComplaint_Page(driver);

        if (t.OTP_Field_visible()) {
            test.log(Status.PASS, "With registered mobile number able to track complaint");
            logger.info("Test Passed : With registered mobile number able to track complaint");
        } else {
            test.log(Status.FAIL, "With registered mobile number not able to track complaint");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : With registered mobile number not able to track complaint");
        }
    }

    @Test(priority = 4)
    public void test_trckng_otp_pass_chk() throws InterruptedException {
        test = extent.createTest("Positive: Verify user is able to check valid otp pass");
        HomePage h = new HomePage(driver);
        h.otp_sendkeys();


        if (h.otp_pass_chk()) {
            test.log(Status.PASS, "After entering one digit of OTP next digit OTP field is appearing");
            logger.info("Test Passed : After entering one digit of OTP next digit OTP field is appearing");
        } else {
            test.log(Status.FAIL, "After entering one digit of OTP next digit OTP field is not appearing");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : After entering one digit of OTP next digit OTP field is not appearing");
        }
    }

    @Test(priority = 5)
    public void test_trckng_invalid_otp_chk_negative() throws InterruptedException {
        test = extent.createTest("Negative: Verify user is able to check invalid otp pass");
        HomePage h = new HomePage(driver);
        h.enterOTP_manually_invalid();
        TrackComplaint_Page t = new TrackComplaint_Page(driver);

        if (t.registed_complaint_visibility()) {
            test.log(Status.FAIL, "After entering invalid OTP next page is appearing");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : After entering invalid OTP next page is appearing");
        } else {
            test.log(Status.PASS, "After entering invalid OTP next page is not appearing");
            logger.info("Test Passed : After entering invalid OTP next page is not appearing");
        }

    }

    @Test(priority = 6)
    public void test_trckng_valid_Otp_VisRsndBtn() throws InterruptedException {
        test = extent.createTest("Positive: Verify user is able to check resend button visibility for valid otp");
        TrackComplaint_Page t = new TrackComplaint_Page(driver);
        Thread.sleep(60000);

        if (t.resend_button_visibility()) {
            test.log(Status.PASS, "After waiting for 60 seconds resend button is visible");
            logger.info("Test Passed : After waiting for 60 seconds resend button is visible");
        } else {
            test.log(Status.FAIL, "After waiting for 60 seconds resend button is not visible");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : After waiting for 60 seconds resend button is not visible");
        }
    }

    @Test(priority = 7)
    public void test_trckng_RsendBtn_AgainVisible() throws InterruptedException {
        test = extent.createTest("Positive: Verify user is able to check resend button visible again after passing otp");
        TrackComplaint_Page t = new TrackComplaint_Page(driver);
        t.ResendBtnClk();
        Thread.sleep(60000);
        Thread.sleep(1000);

        if (t.resend_button_visibility()) {
            test.log(Status.PASS, "After clicking resend bttton and waiting for 60 seconds resend button again visible");
            logger.info("Test Passed : After clicking resend bttton and waiting for 60 seconds resend button again visible");
        } else {
            test.log(Status.FAIL, "After completion of 60 seconds resend button is not visible");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : After completion of 60 seconds resend button is not visible");
        }
    }

    @Test(priority = 8)
    public void test_trckng_splchar_otp_chk_negative() throws InterruptedException {
        test = extent.createTest("Negative: Verify user is able to enter special characters in otp field");
        TrackComplaint_Page t = new TrackComplaint_Page(driver);
        HomePage h = new HomePage(driver);
        t.ResendBtnClk();
        h.enterOTP_manually_splchar();

        if (t.registed_complaint_visibility()) {
            test.log(Status.FAIL, "After entering special character as OTP next page is appearing");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : After entering special character as OTP next page is appearing");
        } else {
            test.log(Status.PASS, "After entering special character as OTP next page is not appearing");
            logger.info("Test Passed : After entering special character as OTP next page is not appearing");
        }
    }


    @Test(priority = 9, dataProvider = "CompAgainstInsCompanyData", dataProviderClass = DataReader.class)
    public void test_trckng_Arrow_Visible(String name, String mob, String pinCode, String compAgainst, String insCompany, String policyType, String compType, String compDescType, String policyNumber, String compDesc) throws InterruptedException {
        test = extent.createTest("Positive: Verify user is able to check visibility of arrow button for passing otp");
        HomePage h = new HomePage(driver);
        TrackComplaint_Page t = new TrackComplaint_Page(driver);
        h.enterOTP(mob);

        if (h.OTP_submit_arrow_visible()) {
            test.log(Status.PASS, "after entering OTP arrow field is visible");
            logger.info("Test Passed : after entering OTP arrow field is visible");
        } else {
            test.log(Status.FAIL, "after entering OTP arrow field is not visible");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : after entering OTP arrow field is not visible");
        }
    }

    @Test(priority = 10)
    public void test_trckng_valid_otp_next_page_visibility_chk() throws InterruptedException {
        test = extent.createTest("Positive: Verify user is able to check next page is visible after otp pass");
        Thread.sleep(1000);
        TrackComplaint_Page t = new TrackComplaint_Page(driver);
        HomePage h = new HomePage(driver);

        if (t.registed_complaint_visibility()) {
            test.log(Status.PASS, "After entering valid OTP next page is appearing");
            logger.info("Test Passed : After entering valid OTP next page is appearing");
        } else {
            test.log(Status.FAIL, "After entering valid OTP next page is not appearing");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : After entering valid OTP next page is not appearing");
        }
    }

    @Test(priority = 11)
    public void test_export_button_visibility() throws InterruptedException {
        test = extent.createTest("Positive: Verify user is able to check export button visibility");
        TrackComplaint_Page t = new TrackComplaint_Page(driver);

        if (t.visibility_export()) {
            test.log(Status.PASS, "Export button is visible");
            logger.info("Test Passed : Export button is visible");
        } else {
            test.log(Status.FAIL, "Export button  is not visible");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Export button  is not visible");
        }
    }

    @Test(priority = 12)
    public void test_trckng_All_Field_Visible() throws InterruptedException {
        test = extent.createTest("Positive: Verify user is able to check all field visible in PHViewEditComplaint page");
        TrackComplaint_Page t = new TrackComplaint_Page(driver);

        if (t.visibility_all_fields_of_table()) {
            test.log(Status.PASS, "all the fields visible on table.");
            logger.info("Test Passed : all the fields visible on table.");
        } else {
            test.log(Status.FAIL, "all the fields not visible on table");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : all the fields not visible on table");
        }
    }


    @Test(priority = 13)
    public void test_trckng_MenuBtn_Click() throws InterruptedException {
        test = extent.createTest("Positive: Verify user is able to check menu button");
        TrackComplaint_Page t = new TrackComplaint_Page(driver);
        t.MenuBtnClk();
        t.complaintClk();

        if (t.reg_new_complaint_visibility() && t.view_edit_complaint_visibility()) {
            test.log(Status.PASS, "After clicking on menu button,  complaints dropdown visible");
            logger.info("Test Passed : After clicking on menu button,  complaints dropdown visible");
        } else {
            test.log(Status.FAIL, "After clicking on menu button, complaints dropdown not visible");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : After clicking on menu button, complaints dropdown not visible");
        }
    }


    @Test(priority = 14, dataProvider = "NewComplaintRegister", dataProviderClass = DataReader.class)
    public void test_trckng_new_complaint_chk(String pinCode, String compAgainst, String insCompany, String policyType, String compType, String compDescType, String policyNumber, String compDesc) throws InterruptedException {
        test = extent.createTest("Positive: Verify user is able to check all complaint field");
        TrackComplaint_Page t = new TrackComplaint_Page(driver);
        t.newRegisterClick();

        //scroll page
        t.scrollTrackingPage();

        ComplaintRegPage reg = new ComplaintRegPage(driver);
        reg.enterPinCode(pinCode);
        reg.districClick();
        //reg.scrollToComplaint();
        reg.complaint_Against(compAgainst);
        reg.insCompanyName(insCompany);
        reg.policyType(policyType);
        reg.complaintType(compType);
        reg.complaintDescType(compDescType);
        reg.policyNumberClick();
        reg.enterPolicyNumber(policyNumber);
        reg.enterCompDesc(compDesc);
        reg.registerClick();
        reg.acceptAlertWindow();
        Thread.sleep(3000);
        String Msg = reg.getMessage();

        if (reg.registerComplaint()) {
            if (Msg.equals("Complaint Registered Successfully.")) {
                test.log(Status.PASS, "Complaint Registered Against Insurance company Successfully.");
                logger.info("Test Passed : Complaint Registered Against Insurance company Successfully.");
            } else {
                test.log(Status.FAIL, "Complaint not registered");
                test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
                logger.error("Test Failed : Complaint not registered");
            }
        } else {
            if (Msg.equals("Complaint has been already registered with same policy number and same complaint description")) {
                test.log(Status.PASS, "Complaint not registering with duplicate policy number and complaint description");
                logger.info("Test Passed : Complaint not registering with duplicate policy number and complaint description");
            } else {
                test.log(Status.FAIL, "Duplicate complaint registered");
                test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
                logger.error("Test Failed : Duplicate complaint registered");
            }
            t.MenuBtnClk();
            t.complaintClk();
            t.ViewEditCompl();
            Thread.sleep(3000);
        }
    }


    @Test(priority = 15)
    public void test_trckng_ShowEntries_Dropdwn() throws InterruptedException {
        test = extent.createTest("Positive: Verify user is able to check show entries dropdown button");
        TrackComplaint_Page t = new TrackComplaint_Page(driver);

        if (t.show_entries()) {
            test.log(Status.PASS, "user able to handle show entries dropdown");
            logger.info("Test Passed : user able to handle show entries dropdown");
        } else {
            test.log(Status.FAIL, "user not able to handle show entries dropdown");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : user not able to handle show entries dropdown");
        }
    }


    @Test(priority = 16)
    public void test_view_edit_click() throws InterruptedException {
        test = extent.createTest("Positive: Verify user is able to check view edit check box");
        TrackComplaint_Page t = new TrackComplaint_Page(driver);
        t.view_edit_button_click();
        Thread.sleep(5000);

        if (t.visibility_PH_update_complaint()) {
            test.log(Status.PASS, "View edit page is visible to user");
            logger.info("Test Passed : View edit page is visible to user");
        } else {
            test.log(Status.FAIL, "View edit page is not visible to user");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : View edit page is not visible to user");
        }
    }


    @Test(priority = 17)
    public void test_trckng_UploadedDocument() throws InterruptedException {
        test = extent.createTest("Positive: Verify user is able to check upload document field");
        TrackComplaint_Page t = new TrackComplaint_Page(driver);
        t.uploaded_document_click();
        Thread.sleep(3000);

        if (t.visibility_uploaded_doc()) {
            test.log(Status.PASS, "Uploaded document on Check Uploaded Attachment is visible");
            logger.info("Test Passed : Uploaded document on Check Uploaded Attachment is visible");
        } else {
            test.log(Status.FAIL, "Uploaded document on Check Uploaded Attachment is not visible");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Uploaded document on Check Uploaded Attachment is not visible");
        }
        t.uploaded_document_close();
    }

    @Test(priority = 18)
    public void test_trckng_SplChars_Compldetails_negative() throws InterruptedException {
        test = extent.createTest("Negative: Verify user is able to check complaint details field while passing special characters");
        TrackComplaint_Page t = new TrackComplaint_Page(driver);
        t.clearField_complaint_desc();
        t.enter_ComplaintDesc("!@#$%^&*()");
        t.update_complaint_desc();

        if (t.visibilityOfYesBtn()) {
            test.log(Status.FAIL, "With special characters complaint description updating");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : With special characters complaint description updating");
        } else {
            test.log(Status.PASS, "With special characters complaint description not updating");
            logger.info("Test Passed : With special characters complaint description not updating");
        }
    }


    @Test(priority = 19)
    public void test_trckng_EditInformation() throws InterruptedException {
        test = extent.createTest("Positive: Verify user is able to check edit information on PHViewEditComplaint page");
        TrackComplaint_Page t = new TrackComplaint_Page(driver);
        t.clearField_complaint_desc();
        t.enter_ComplaintDesc("This complaint description edited");
        String s1 = t.get_complaint_desc();
        t.update_complaint_desc();
        t.SelectYesBtn_click();
        t.click_ok();
        String s2 = t.get_complaint_dec_table();

        if (s1.equals(s2)) {
            test.log(Status.PASS, "user can edit information on Complaint Details field and update the same.");
            logger.info("Test Passed : user can edit information on Complaint Details field and update the same.");
        } else {
            test.log(Status.FAIL, "user can not edit information on Complaint Details field and update the same.");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : user can not edit information on Complaint Details field and update the same.");
        }
    }


    @Test(priority = 20)
    public void test_trckng_BackBtn_click() throws InterruptedException {
        test = extent.createTest("Positive: Verify user is able to check back button function from PolicyholderUpdateComplaint page");
        TrackComplaint_Page t = new TrackComplaint_Page(driver);
        t.view_edit_button_click();
        t.SelectCancelBtn_click();

        if (t.registed_complaint_visibility()) {
            test.log(Status.PASS, "After click on back button on PHViewEditComplaint page user able to back on previous page");
            logger.info("Test Passed : After click on back button on PHViewEditComplaint page user able to back on previous page");
        } else {
            test.log(Status.FAIL, "After click on back button on PHViewEditComplaint page user not  able to back on previous page");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : After click on back button on PHViewEditComplaint page user not  able to back on previous page");
        }
    }
}
