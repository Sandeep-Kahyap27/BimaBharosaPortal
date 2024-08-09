package ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.List;

public class UploadComplaintsPage {

    WebDriver driver = null;

    @FindBy(id = "uploadBrowse")
    private WebElement uploadFile;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement uploadBtn;

    @FindBy(id = "btnClearData")
    private WebElement clearBtn;

   /* @FindBy(xpath = "(//table[@id='Grid']//td[2])")
    private WebElement message;*/

    @FindBy(xpath = "//table[@id='Grid']/tbody/tr/td[2]")
    private WebElement message;

    @FindBy(id = "downloadUploadTemplate")
    private WebElement downloadTemplate;

    @FindBy(xpath = "//table[@id='Grid']")
    private WebElement table_rows;

    public UploadComplaintsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void uploadExcelFile(String filePath){
        try{
            uploadFile.sendKeys(filePath);
        }
        catch (NoSuchElementException e){

        }
    }

    public void clkUploadBtn(){
        try{
            uploadBtn.click();
        }
        catch (NoSuchElementException e){

        }
    }

    public void clkClearBtn(){
        try{
            clearBtn.click();
        }
        catch (NoSuchElementException e){

        }
    }


    /*Validation methods*/
    public boolean verifySuccessMsg(){
        return message.isDisplayed() && message.getText().equals("Successfully Uploaded");
    }

    public boolean verifyErrorDescription(String error_desc){
        return message.getText().contains(error_desc);
    }


    public boolean verifyStatusOfUploadBrowse(){
        return uploadFile.isEnabled();
    }

    public boolean verifyStatusOfUploadBtn(){
        return uploadBtn.isDisplayed() && uploadBtn.isEnabled();
    }

    public boolean verifyStatusOfClearBtn(){
        return clearBtn.isDisplayed() && clearBtn.isEnabled();
    }

    public boolean verifyDownloadTemplateBtn(){
        return downloadTemplate.isDisplayed() && downloadTemplate.isEnabled();
    }

    public String getMessage(){
        try{
            return this.message.getText();
        }
        catch (NoSuchElementException e){
        }
        return null;
    }

    public boolean verifyNoRowsDisplayed(){
        return !table_rows.isDisplayed();
    }





}
