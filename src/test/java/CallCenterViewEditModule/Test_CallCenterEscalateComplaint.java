package CallCenterViewEditModule;

import Base.LoginHelper;
import Base.MainClass;
import ObjectRepository.CallCenterDoesNotPertainPage;
import ObjectRepository.CallCenterEscalateComplaintPage;
import ObjectRepository.CallcenterViewEditPage;
import ObjectRepository.LoginPage;
import Utilities.DataReader;
import Utilities.Property;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;

public class Test_CallCenterEscalateComplaint extends MainClass {

    Properties pro = null;

    @BeforeClass
    public void launchUrl() {
        loadURL("LoginURL");
    }


    @Test(priority = 1, dataProvider = "CompAgainstInsComapny", dataProviderClass = DataReader.class)
    public void test_Call_Center_Escalate_Complain(String name, String mob, String pinCode, String compAgainst, String insCompanyName, String policyType, String compType, String compDescType,String policyNumber, String compDesc) throws InterruptedException, IOException {
        test = extent.createTest("Verify we able to navigate Does Not Pertain Page");
        LoginHelper.backtoSafty();
        CallcenterViewEditPage ve = new CallcenterViewEditPage(driver);
        pro = Property.readPropertiesFile(System.getProperty("user.dir") + "/src/main/resources/Properties/Credentials.properties");
        LoginHelper.login(pro.getProperty("CallCenter_UserID"), pro.getProperty("CallCenter_Password"));
        ve.clkMenuButton();
        ve.clkComplaintDropDown();
        LoginPage lp=new LoginPage(driver);
        CallCenterEscalateComplaintPage cec=new CallCenterEscalateComplaintPage(driver);
        cec.clkNewRegistrationDropDown();
        cec.clkNoRadioBtn();
        cec.enterName(name);
        cec.enterMobile(generateRandomMobileNumber());
        cec.clkComplaintAgainst();
        lp.UseDownArrowKey();
        lp.UseEnterKey();
        cec.clkNameInsuranceCompany();
        cec.enterNameInsuranceCompany("HDFC Ergo");
        lp.UseEnterKey();
        cec.clkPolicyType();
        lp.UseDownArrowKey();
        lp.UseEnterKey();
        cec.clkComplaintType();
        lp.UseDownArrowKey();
        lp.UseEnterKey();
        cec.clkComplaintDescriptionType();
        lp.UseDownArrowKey();
        lp.UseEnterKey();








    }
}
