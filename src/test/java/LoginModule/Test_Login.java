package LoginModule;

import Base.LoginHelper;
import Base.MainClass;
import ObjectRepository.CallcenterRegPage;
import ObjectRepository.LoginPage;
import Utilities.Property;
import Utilities.Screenshot;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.*;
import java.io.IOException;
import java.util.Properties;

public class Test_Login extends MainClass {
    Properties pro = null;

    @BeforeClass
    public void launchUrl() {
        loadURL("LoginURL");
    }

    @Test(priority = 1)
    public void test_Visibility_Buttons_LoginPage() throws InterruptedException {
        test = extent.createTest("Positive: Verify the buttons are visible and clickable");
        LoginHelper.backToSafety();
        LoginPage lp = new LoginPage(driver);


        if (lp.displayLoginBtn() && lp.displayClearBtn()) {
            test.log(Status.PASS, "Test Pass : Login and Clear buttons are visible and clickable");
            logger.info("Test Passed : Login and Clear buttons are visible and clickable");
        } else {

            test.log(Status.FAIL, "Test Fail : Login and Clear buttons are not visible and clickable");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Login and Clear buttons are not visible and clickable");
        }

    }

    @Test(priority = 2)
    public void test_Visibility_Captcha() throws InterruptedException {
        test = extent.createTest("Positive: Verify the Captcha is visible on login page");
        LoginPage lp = new LoginPage(driver);

        if (lp.displayCaptcha()) {
            test.log(Status.PASS, "Test Pass : Captcha is visible on login page");
            logger.info("Test Passed : Captcha is visible on login page");
        } else {

            test.log(Status.FAIL, "Test Fail : Captcha is not visible on login page");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Captcha is not visible on login page");

        }
    }

    @Test(priority = 3)
    public void test_Refresh_Captcha() throws InterruptedException {
        test = extent.createTest("Positive: Verify the refresh Captcha ");
        LoginPage lp = new LoginPage(driver);
        String initialCaptchaSrc = lp.attributCaptch();
        lp.clkRefreshCaptcha();
        String newCaptchaSrc = lp.attributCaptch();

        if (!initialCaptchaSrc.equals(newCaptchaSrc)) {

            test.log(Status.PASS, "Test Pass : Captcha refresh is working. The captcha has changed.");
            logger.info("Test Passed : Captcha refresh is working. The captcha has changed.");
        } else {

            test.log(Status.FAIL, "Test Fail : Captcha refresh is not working. The captcha has not changed.");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Captcha refresh is not working. The captcha has not changed.");
        }

    }

    @Test(priority = 4)
    public void test_Clear_Button() throws InterruptedException, IOException {
        test = extent.createTest("Positive: Verify the refresh Captcha ");
        LoginPage lp = new LoginPage(driver);
        lp.enterUsername("USER1");
        lp.enterPassword("Password");
        Thread.sleep(2000);
        lp.clkClrbtn();
        if (lp.atrributUsername()) {
            test.log(Status.PASS, "Test Pass : Clear button functionality");
            logger.info("Test Passed : Clear button functionality");
        } else {
            test.log(Status.FAIL, "Test Fail : Clear button functionality");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Clear button functionality");
        }
    }

    @Test(priority = 5)
    public void test_Empty_User_Password() throws InterruptedException {
        test = extent.createTest("Negative: Verify Empty Username and Password ");
        LoginPage lp = new LoginPage(driver);
        String str1 = JOptionPane.showInputDialog("Enter Captcha");
        lp.enterCaptcha(str1);
        lp.clkLoginbtn();

        String expectErrUsername = "Please Enter User Name";
        String expectErrPassword = "Please Enter Password";
        String actualErrUsername = lp.getTextErrUsername();
        Thread.sleep(1000);
        String actualErrPassword = lp.getTextErrPassword();
        Thread.sleep(1000);

        if (actualErrUsername.equalsIgnoreCase(expectErrUsername) && actualErrPassword.equalsIgnoreCase(expectErrPassword)) {
            test.log(Status.PASS, "Test Pass : Correct error messages displayed for empty fields.");
            logger.info("Test Passed : Correct error messages displayed for empty fields.");
        } else {
            test.log(Status.FAIL, "Test Fail : Error messages not displayed.");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Error messages not displayed.");
        }
    }

    @Test(priority = 6)
    public void test_Empty_Username() throws InterruptedException {
        test = extent.createTest("Negative: Verify Empty Username");
        LoginPage lp = new LoginPage(driver);
        lp.enterPassword("Karvy@123");
        lp.clrUsername();
        lp.clkLoginbtn();
        String expectErrUsername = "Please Enter User Name";
        String actualErrUsername = lp.getTextErrUsername();
        Thread.sleep(1000);
        if (actualErrUsername.equalsIgnoreCase(expectErrUsername)) {
            test.log(Status.PASS, "Test Pass : Correct error messages displayed for empty Username");
            logger.info("Test Passed : Correct error messages displayed for empty Username");
        } else {
            test.log(Status.FAIL, "Test Fail : Error messages not displayed for Username");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Error messages not displayed for Username");
        }
    }

    @Test(priority = 7)
    public void test_Empty_Password() throws InterruptedException {
        test = extent.createTest("Negative: Verify Empty Password");
        LoginPage lp = new LoginPage(driver);
        lp.clrPassword();
        lp.enterUsername("CSAUSER1");
        lp.clkLoginbtn();

        String expectErrPassword = "Please Enter Password";
        String actualErrPassword = lp.getTextErrPassword();
        Thread.sleep(1000);
        if (actualErrPassword.equalsIgnoreCase(expectErrPassword)) {
            test.log(Status.PASS, "Test Pass : Correct error messages displayed for empty Password");
            logger.info("Test Passed : Correct error messages displayed for empty Password");
        } else {
            test.log(Status.FAIL, "Test Fail : Error messages not displayed for Password");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Error messages not displayed for Password");
        }
    }

    @Test(priority = 8)
    public void test_Invalid_Username() throws InterruptedException {
        test = extent.createTest("Negative: Verify Invalid Username");
        LoginPage lp = new LoginPage(driver);
        lp.clrPassword();
        lp.clrUsername();
        lp.enterUsername("USER1");
        lp.enterPassword("Karvy@123");
        //String str1 = JOptionPane.showInputDialog("Enter Captcha");
        //lp.enterCaptcha(str1);
        lp.clkLoginbtn();
        String expectErrCredential = "Invalid Credential";
        String actualErrCredential = lp.getTextErrInvalidCredential();
        Thread.sleep(1000);
        if (actualErrCredential.equalsIgnoreCase(expectErrCredential)) {
            test.log(Status.PASS, "Test Pass : Correct error messages displayed for Invalid Username");
            logger.info("Test Passed : Correct error messages displayed for Invalid Username");
        } else {
            test.log(Status.FAIL, "Test Fail : Error messages not displayed for Invalid Username");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Error messages not displayed for Invalid Username");
        }

        lp.clkOK();

    }

    @Test(priority = 9)
    public void test_Invalid_Password() throws InterruptedException {
        test = extent.createTest("Negative: Verify Invalid Password");
        LoginPage lp = new LoginPage(driver);
        lp.clrPassword();
        lp.clrUsername();
        lp.enterUsername("CSAUSER1");
        lp.enterPassword("Karvy123");
        // String str1 = JOptionPane.showInputDialog("Enter Captcha");
        //lp.enterCaptcha(str1);
        Thread.sleep(3000);
        lp.clkLoginbtn();
        String expectErrCredential = "Invalid Credential";
        String actualErrCredential = lp.getTextErrInvalidCredential();
        Thread.sleep(1000);
        if (actualErrCredential.equalsIgnoreCase(expectErrCredential)) {
            test.log(Status.PASS, "Test Pass : Correct error messages displayed for Invalid Password");
            logger.info("Test Passed : Correct error messages displayed for Invalid Password");
        } else {
            test.log(Status.FAIL, "Test Fail : Error messages not displayed for Invalid Password");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Error messages not displayed for Invalid Password");
        }
        lp.clkOK();
    }

    @Test(priority = 10)
    public void test_Invalid_Username_Password() throws InterruptedException {
        test = extent.createTest("Negative: Verify Invalid Username and Password");
        LoginPage lp = new LoginPage(driver);
        lp.clrPassword();
        lp.clrUsername();
        lp.enterUsername("USER1");
        lp.enterPassword("Kary123");
        lp.clkLoginbtn();
        String expectErrCredential = "Invalid Credential";
        String actualErrCredential = lp.getTextErrInvalidCredential();
        Thread.sleep(1000);
        if (actualErrCredential.equalsIgnoreCase(expectErrCredential)) {
            test.log(Status.PASS, "Test Pass : Correct error messages displayed for Invalid Username and Password");
            logger.info("Test Passed : Correct error messages displayed for Invalid Username and Password");
        } else {
            test.log(Status.FAIL, "Test Fail : Error messages not displayed for Invalid Username and Password");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Error messages not displayed for Invalid Username and Password");
        }
        lp.clkOK();
    }

    @Test(priority = 11)
    public void test_Empty_Captcha() throws InterruptedException {
        test = extent.createTest("Negative: Verify Empty Captcha");
        LoginPage lp = new LoginPage(driver);
        lp.clrPassword();
        lp.clrUsername();
        lp.clrCaptcha();
        lp.enterUsername("CSAUSER1");
        lp.enterPassword("Karvy@123");
        lp.clkLoginbtn();

        String expectErrCaptcha = "Please Enter Security Code";
        String actualErrCaptcha = lp.getTextErrCaptcha();
        Thread.sleep(1000);

        if (actualErrCaptcha.equalsIgnoreCase(expectErrCaptcha)) {
            test.log(Status.PASS, "Test Pass : Correct error messages displayed for Empty Captcha ");
            logger.info("Test Passed : Correct error messages displayed for Empty Captcha");
        } else {
            test.log(Status.FAIL, "Test Fail : Error messages not displayed for Empty Captcha");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Error messages not displayed for Empty Captcha");
        }

    }

    @Test(priority = 12)
    public void test_Invalid_Captcha() throws InterruptedException {
        test = extent.createTest("Negative: Verify Invalid Captcha");
        LoginPage lp = new LoginPage(driver);
        lp.clrPassword();
        lp.clrUsername();
        lp.enterCaptcha("12aws3");
        lp.enterUsername("CSAUSER1");
        lp.enterPassword("Karvy@123");
        lp.clkLoginbtn();

        String expectErrCaptcha = "Invalid Captcha";
        String actualErrCaptcha = lp.getTextErrCaptchaPopUp();
        Thread.sleep(1000);
        if (actualErrCaptcha.equalsIgnoreCase(expectErrCaptcha)) {
            test.log(Status.PASS, "Test Pass : Correct error messages displayed for Invalid Captcha ");
            logger.info("Test Passed : Correct error messages displayed for Invalid Captcha");
        } else {
            test.log(Status.FAIL, "Test Fail : Error messages not displayed for Invalid Captcha");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Error messages not displayed for Invalid Captcha");
        }
        lp.clkOK();
    }

    @Test(priority = 13)
    public void test_Credential_Case_Sensetive() throws IOException, InterruptedException {

        test = extent.createTest("Negative: Verify Credential Case Sensitive");
        pro = Property.readPropertiesFile(System.getProperty("user.dir") + "/src/main/resources/Properties/Credentials.properties");
        LoginHelper.login(pro.getProperty("CallCenter_UserID").toLowerCase(), pro.getProperty("CallCenter_Password").toUpperCase());
        LoginPage lp = new LoginPage(driver);
        String expectErrCredential = "Invalid Credential";
        String actualErrCredential = lp.getTextErrInvalidCredential();
        Thread.sleep(1000);
        if (actualErrCredential.equalsIgnoreCase(expectErrCredential)) {
            test.log(Status.PASS, "Test Pass : Correct error messages displayed for Case Sensitive Username and Password");
            logger.info("Test Passed : Correct error messages displayed for Case Sensitive Username and Password");
        } else {
            test.log(Status.FAIL, "Test Fail : Error messages not displayed for Case Sensitive Username and Password");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Error messages not displayed for Case Sensitive Username and Password");
        }
        lp.clkOK();
    }

    @Test(priority = 14)
    public void test_Hindi_Language() throws InterruptedException {

        test = extent.createTest("Positive: Verify Hindi Language UI");
        LoginPage lp = new LoginPage(driver);
        lp.clkLanguageDropdown();
        Thread.sleep(1000);
        lp.UseDownArrowKey();
        Thread.sleep(1000);
        lp.UseEnterKey();
        String actualHindLang = lp.getTextHindiLanguage();
        Thread.sleep(1000);
        String expectedHindiLang = "भाषा";

        if (actualHindLang.equals(expectedHindiLang)) {

            test.log(Status.PASS, "Test Pass : Expected Hindi UI present on the page. ");
            logger.info("Test Passed : Expected Hindi UI present on the page.");
        } else {
            test.log(Status.FAIL, "Test Fail : Expected Hindi UI is not present on the page.");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Expected Hindi UI is not present on the page.");
        }

    }

    @Test(priority = 15)
    public void test_English_Language() throws InterruptedException {

        test = extent.createTest("Positive: Verify English Language UI");
        LoginPage lp = new LoginPage(driver);
        lp.clkLanguageDropdown();
        Thread.sleep(1000);
        lp.UseUpArrowKey();
        Thread.sleep(1000);
        lp.UseEnterKey();
        lp.refreshPage();
        String actualEnglishLang = lp.getTextEnglishLanguage();
        String expectedEnglishLang = "Language";

        if (actualEnglishLang.equals(expectedEnglishLang)) {

            test.log(Status.PASS, "Test Pass : Expected English UI present on the page. ");
            logger.info("Test Passed : Expected English UI present on the page.");
        } else {
            test.log(Status.FAIL, "Test Fail : Expected English UI is not present on the page.");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Expected English UI is not present on the page.");
        }
    }

    @Test(priority = 16)
    public void test_Valid_Credentials() throws IOException, InterruptedException {

        test = extent.createTest("Positive: Verify Valid Credentials");
        LoginPage lp = new LoginPage(driver);
        lp.clrUsername();
        lp.clrPassword();
        pro = Property.readPropertiesFile(System.getProperty("user.dir") + "/src/main/resources/Properties/Credentials.properties");
        LoginHelper.login(pro.getProperty("CallCenter_UserID"), pro.getProperty("CallCenter_Password"));

        String expectedViewEdit = "View / Edit Complaints";
        String actualViewEdit = lp.getTextViewEdit();

        if (actualViewEdit.equals(expectedViewEdit)) {

            test.log(Status.PASS, "Test Pass : Login is successful");
            logger.info("Test Passed : Login is successful");
        } else {
            test.log(Status.FAIL, "Test Fail : Login is not successful");
            test.fail(new Throwable(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.captureScreenShot()).build());
            logger.error("Test Failed : Login is not successful");
        }

    }


    @AfterClass
    public void logOut() throws InterruptedException {
        CallcenterRegPage cr = new CallcenterRegPage(driver);
        cr.logOutSuccess();
    }


}

