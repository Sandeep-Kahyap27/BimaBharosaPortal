package ObjectRepository;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class AdvanceSearchPage {
    WebDriver driver;
    @FindBy(id = "IrdaTokenMumber")
    private WebElement  complaint_number;

    @FindBy(xpath = "//input[@class='statusCheckboxGroup'][1]")
    private WebElement new_status;

    @FindBy(xpath = "//input[@class='statusCheckboxGroup'][2]")
    private WebElement acknowledge_status;

    @FindBy(xpath = "//input[@class='statusCheckboxGroup'][3]")
    private WebElement pending_status;

    @FindBy(xpath = "//input[@class='statusCheckboxGroup'][4]")
    private WebElement attended_to_status;

    @FindBy(xpath = "//input[@class='statusCheckboxGroup'][5]")
    private WebElement escalated_status;

    @FindBy(xpath = "//input[@class='statusCheckboxGroup'][6]")
    private WebElement reopen_status;

    @FindBy(xpath = "//input[@class='statusCheckboxGroup'][7]")
    private WebElement closed_status;

    @FindBy(id = "chkclosureReuqest")
    private WebElement closureRequest;

    @FindBy(id ="chktatgreater15")
    private WebElement tat_GreaterThan15;

    @FindBy(id = "chktatgreater30")
    private WebElement tat_greaterthan30;

    @FindBy(id = "chktatgreater60")
    private WebElement tat_greaterThan60;

    @FindBy(id = "chktatzerotolerance")
    private WebElement vip_complaint;

    @FindBy(id = "btnsearch")
    private  WebElement search_btn;

    @FindBy(id = "btnclear")
    private WebElement clear_btn;

    @FindBy(xpath = "//table[@id= 'VieweditDataTables']/tbody/tr[1]/td[2]/a/i")
    private WebElement viewEditIcon;
    @FindBy(xpath = "//label[text()= 'Insurance Type' ]")
    private WebElement insuranceType;

    @FindBy(xpath = "//label[text() = 'Entity Name' ]")
    private WebElement entityName;
    @FindBy(xpath = "//input[@id = 'IrdaTokenMumber' ]")
    private WebElement complaintNumber;
    @FindBy(xpath = "//input[@id = 'EntityReferenceNumber' ]")
    private WebElement entityReferenceNumber;

    @FindBy(xpath = "//input[@id = 'policyNumber' ]")
    private WebElement policyNumber;
    @FindBy(xpath = "//div[@id = 'ddlcomplaintmode_chosen' ]")
    private WebElement complaintMode;
    @FindBy(xpath = "//input[@id = 'complainantName' ]")
    private WebElement complaintName;

    @FindBy(xpath = "//table[@id='VieweditDataTables']")
    private WebElement searchedComplaintTable;

    @FindBy(xpath = "//table[@id='VieweditDataTables']/tbody/tr[1]/td[2]/a[1]/i[1]")
    private WebElement view_edit_complaints_checkbox;

    @FindBy(xpath = "//div[@id='VieweditDataTables_wrapper']/table/tbody/tr/td[4]")
    private WebElement first_complaintNo_from_complaintTable;


    public AdvanceSearchPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //Enter value in text field
    public void enterComplaintNumber(String complaint_number){
       try {
           this.complaint_number.sendKeys(complaint_number);
       }catch (NoSuchElementException e){
           e.printStackTrace();
       }
    }

    //Added by Abhishek
    public void enterEntityCompRefNumber(String entityReferenceNumber){
        try{
            this.entityReferenceNumber.sendKeys(entityReferenceNumber);
        }catch (NoSuchElementException e){

        }
    }


    public boolean ComplaintNumberIsEmpty(){
        try {

            if(complaint_number.getAttribute("value").isEmpty()){
              return true;
            }
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
        return false;
    }

    //Click methods
    public void clickNewStatus(){
        try {
            this.new_status.click();
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }
    public void clickUncheckNewStatus(){
        try {
            if(new_status.isSelected()){
                new_status.click();
            }
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }
    public void clickAcknowledgeStatus(){
        try {
            this.acknowledge_status.click();
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }
    public void clickUncheckAcknowledgeStatus(){
        try {
            if(acknowledge_status.isSelected()){
                acknowledge_status.click();
            }
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }
    public void clickPendingStatus(){
        try {
            this.pending_status.click();
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }
    public void clickUncheckPendingStatus(){
        try {
            if(pending_status.isSelected()){
                pending_status.click();
            }
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }
    public void clickAttendedToStatus(){
        try {
            this.attended_to_status.click();
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }
    public void clickUncheckAttendedToStatus(){
        try {
            if(attended_to_status.isSelected()){
                attended_to_status.click();
            }
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }
    public void clickEscalatedStatus(){
        try {
            this.escalated_status.click();
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }
    public void clickUncheckEscalatedStatus(){
        try {
            if(escalated_status.isSelected()){
                escalated_status.click();
            }
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }
    public void clickReopenStatus(){
        try {
            this.reopen_status.click();
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }
    public void clickUncheckReopenStatus(){
        try {
            if(reopen_status.isSelected()){
                reopen_status.click();
            }
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }
    public void clickClosedStatus(){
        try {
            this.closed_status.click();
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }
    public void clickUncheckClosedStatus(){
        try {
            if(closed_status.isSelected()){
                closed_status.click();
            }
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }
    public void clickClosureRequestStatus(){
        try {
            this.closureRequest.click();
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }



    public void clickTatGreaterThan15(){
        try {
            this.tat_GreaterThan15.click();
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }
    public void clickUncheckTatGreaterThan15(){
        try {
            if(tat_GreaterThan15.isSelected()){
                tat_GreaterThan15.click();
            }
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }
    public void clickTatGreaterThan30(){
        try {
            this.tat_greaterthan30.click();
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }
    public void clickUncheckTatGreaterThan30(){
        try {
            if(tat_greaterthan30.isSelected()){
                tat_greaterthan30.click();
            }
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }
    public void clickTatGreaterThan60(){
        try {
            this.tat_greaterThan60.click();
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }
    public void clickUncheckTatGreaterThan60(){
        try {
            if(tat_greaterThan60.isSelected()){
                tat_greaterThan60.click();
            }
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }
    public void clickVipComplaint(){
        try {
            this.vip_complaint.click();
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }
    public void clickSearchBtn(){
        try {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].click();", search_btn);
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }
    public void clickClearBtn(){
        try {
            this.clear_btn.click();
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }
    public void click_View_Edit_Complaints_Checkbox(){
        try {
            this.view_edit_complaints_checkbox.click();
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }
    public boolean visibilityOfViewEditIcon(){
        try {
            this.viewEditIcon.isDisplayed();
            return true;
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean visibilityOfAdvanceSearchFilterParameter(){
        boolean visibility = false ;
        try {
            if(insuranceType.isDisplayed() && entityName.isDisplayed() && complaintNumber.isDisplayed() && entityReferenceNumber.isDisplayed() && policyNumber.isDisplayed() && complaintMode.isDisplayed() && complaintName.isDisplayed()){
                visibility = true;
            }

        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return visibility;
    }

    public boolean visibilityOfStatusTypeCheckbox(){
        boolean visibility = false ;
        try {
            if(new_status.isDisplayed() && acknowledge_status.isDisplayed() && pending_status.isDisplayed() && attended_to_status.isDisplayed() && escalated_status.isDisplayed() && reopen_status.isDisplayed() && closed_status.isDisplayed()){
                visibility = true;
            }

        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return visibility;
    }
    public boolean visibilityOfTATTypeCheckbox(){
        boolean visibility = false ;
        try {
            if(tat_GreaterThan15.isDisplayed() && tat_greaterthan30.isDisplayed() && tat_greaterThan60.isDisplayed() && vip_complaint.isDisplayed()){
                visibility = true;
            }

        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return visibility;
    }

    public boolean getDataFromSearchTable(String status) {
        boolean flag =false;
        try {
            // Get all the rows from the table
            List<WebElement> rows = searchedComplaintTable.findElements(By.tagName("tr"));

            // Create a list to store the table data
            List<List<String>> tableData = new ArrayList<>();

            for(int i=1; i<10; i++){
                List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
                // Create a list to store the cell data for the current row
                List<String> rowData = new ArrayList<>();
                // Iterate through each cell and get the text
                for (WebElement cell : cells) {
                    rowData.add(cell.getText());
                }
                // Add the row data to the table data
                tableData.add(rowData);
            }

            for (List<String> rowData : tableData) {
                for (String cellData : rowData) {
                    if (cellData.equals(status)) {
                        flag = true;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean getTATDataFromSearchTable(int startRange, int endRange){
        try {
            // Locate the table element
            WebElement table = driver.findElement(By.xpath("//table[@id = 'VieweditDataTables']"));

            // Locate the rows of the table (excluding the header row if present)
            List<WebElement> rows = table.findElements(By.tagName("tr"));
            // Specify the column index to search (index starts from 0)
            int searchColIndex = 2; // Example: 2nd column
            // Flag to indicate if the value was found
            boolean valueFound = false;
            // Iterate through each row
            for (int rowIndex = 1; rowIndex < 10; rowIndex++) {
                WebElement row = rows.get(rowIndex);
                // Locate the cells within the row
                List<WebElement> cells = row.findElements(By.tagName("td"));
                if (searchColIndex < 10) {
                    WebElement cell = cells.get(searchColIndex);
                    if(endRange !=100){
                        if (Integer.parseInt(cell.getText())>=startRange && Integer.parseInt(cell.getText())<endRange) {
                            valueFound = true;
                        }else{
                            valueFound =false;
                        }
                    }else{
                        if (Integer.parseInt(cell.getText())>startRange) {
                            valueFound = true;
                        }else{
                            valueFound =false;
                        }
                    }

                }
            }
            return valueFound;
        } finally {

        }

    }
    public String getFirstRowComplaintFromTable(){
        String complaintNo="";
        complaintNo = first_complaintNo_from_complaintTable.getText();
        return complaintNo;
    }

    public void verifyEntityReferenceNumber(String expected_entity_ref_number){
        Assert.assertEquals(driver.findElement(By.xpath("(//table[@id='VieweditDataTables']//td[5])")).getText(), expected_entity_ref_number);
    }

    public List<Object> getSearchRecords(){
        List<Object> records = new ArrayList<>();
        for(int i = 3; i < 12; i++){
            records.add(driver.findElement(By.xpath("//table[@id='VieweditDataTables']//tr["+i+"]")));
        }

        return records;
    }
}
