package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CallCenterEscalateComplaintPage {

    @FindBy(xpath = "(//*[contains(text(),'Registration Against Entity By IRDAI')])[1]")
    @CacheLookup
    private WebElement newRegistrationDropDown;

    @FindBy(xpath = "//*[@id='nocomplaint']")
    @CacheLookup
    private WebElement no;

    @FindBy(xpath = "//*[@id='txtfirstname_individual']")
    @CacheLookup
    private WebElement name;

    @FindBy(xpath = "//*[@id='txtmob_individual']")
    @CacheLookup
    private WebElement mobNum;

    @FindBy(xpath = "//*[@id='ddlcomplagainstinsurer_chosen']")
    @CacheLookup
    private WebElement complaintAgainst;

    @FindBy(xpath = "//*[@id='ddlinsurancecomp_chosen']")
    @CacheLookup
    private WebElement nameInsuranceCompany;

    @FindBy(xpath = "//div[@id='ddlinsurancecomp_chosen']//input[@type='text']")
    @CacheLookup
    private WebElement sendKeysNameInsuranceCompany;

    @FindBy(xpath = "//*[@id='ddlpolicytype_chosen']")
    @CacheLookup
    private WebElement policyType;

    @FindBy(xpath = "//*[@id='ddlcomplainttype_chosen']")
    @CacheLookup
    private WebElement complaintType;

    @FindBy(xpath = "//*[@id='ddlcomplaintdesctype_chosen']")
    @CacheLookup
    private WebElement complaintDescriptionType;




    static WebDriver driver = null;

    public CallCenterEscalateComplaintPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clkNewRegistrationDropDown(){
        newRegistrationDropDown.click();
    }

    public void clkNoRadioBtn(){
        no.click();
    }

    public void enterName(String userName){
        name.sendKeys(userName);
    }

    public void enterMobile(String mob){
        mobNum.sendKeys(mob);
    }

    public void clkComplaintAgainst(){
        complaintAgainst.click();
    }

    public void clkNameInsuranceCompany(){
        nameInsuranceCompany.click();
    }

    public void enterNameInsuranceCompany(String insCom){
        sendKeysNameInsuranceCompany.sendKeys(insCom);
    }

    public void clkPolicyType(){
        policyType.click();
    }

    public void clkComplaintType(){
        complaintType.click();
    }

    public void clkComplaintDescriptionType(){
        complaintDescriptionType.click();
    }


}
