package ObjectRepository;


import Utilities.WaitTimeHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SidebarHeader {
    WebDriver driver;

    public SidebarHeader(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath = "//span[text()= 'Menu']")
    private WebElement menuBar;

   /* @FindBy(id = "navbardrop")
    @CacheLookup
    private WebElement complaintDropDownList;*/

    //Modified by Abhishek
    @FindBy(xpath = "(//a[@id='navbardrop'])[2]")
    @CacheLookup
    private WebElement complaintDropDownList;

    @FindBy(xpath = "//a[text()='Registration Against Entity By IRDAI']")
    @CacheLookup
    private WebElement regAgainstEntity;

    @FindBy(xpath = "//a[text()=\"View / Edit Complaints\"]")
    private WebElement viewEditcomplaintSidebarTab;

    @FindBy(xpath = "//h4[text() ='View / Edit Complaints']")
    private WebElement viewEditcomplaintPage;

    @FindBy(xpath = "(//*[contains(@id,'navbardrop')])[1]")
    private WebElement complaintSidebarTab;

    //Author : Abhishek
    @FindBy(xpath = "//a[text()='Upload Complaints']")
    private WebElement uploadComplaintsSidebarTab;

    public void clickMenu() {
        try {
            menuBar.click();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }

    }

   /* public void clickOnComplaints() throws InterruptedException {
        try {
            Thread.sleep(2000);
            complaintDropDownList.click();
        } catch (NoSuchElementException e) {

        }
    }*/

    //Modified by Abhishek
    public void clickOnComplaints() throws InterruptedException {
        try {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].click();", complaintDropDownList);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }


    public void clickOnRegisterAgainstEntity() throws InterruptedException {
        try {
            Thread.sleep(2000);
            regAgainstEntity.click();
        } catch (NoSuchElementException e) {

        }
    }

    public void clickcomplaintSidebarTab() {
        try {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].click();", complaintSidebarTab);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public void clickviewEditcomplaintSidebarTab() {
        try {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].click();", viewEditcomplaintSidebarTab);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    //Author : Abhishek
    public void clickUploadComplaintsSidebarTab() {
        try {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].click();", uploadComplaintsSidebarTab);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public boolean ViewEditComplaintsPageVisiblity() {
        boolean visibility = false;
        try {
            if (viewEditcomplaintPage.isEnabled() && viewEditcomplaintPage.isDisplayed()) {
                visibility = true;
            }

        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return visibility;
    }


    //Getters
    public boolean getMenuBtnStatus() {
        boolean status = false;
        try {
            if (menuBar.isDisplayed() && menuBar.isEnabled()) {
                status = true;
            }

        } catch (NoSuchElementException e) {

        }
        return status;
    }


}
