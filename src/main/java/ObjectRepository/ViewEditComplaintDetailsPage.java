package ObjectRepository;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewEditComplaintDetailsPage {
    static WebDriver driver = null;
    Actions action;
    @FindBy(xpath = "//span[text()='Menu']")
    private WebElement menu_button;
    @FindBy(id = "navbardrop")
    private WebElement complaint_option;
    @FindBy(xpath = "//a[text()='View / Edit Complaints']")
    private WebElement view_edit_complaints_option;
    @FindBy(xpath = "//table[@id='VieweditDataTables']/tbody/tr[1]/td[2]/a[1]/i[1]")
    private WebElement view_edit_complaints_checkbox;
    @FindBy(id = "ddlBranch")
    private WebElement branch_code;
    @FindBy(id = "ddlBranch-error")
    private WebElement barnch_code_error_message;
    @FindBy(id = "ddlstate")
    private WebElement state_code;
    @FindBy(id = "ddlstatus_chosen")
    private WebElement complaint_status_dropdown;
    @FindBy(xpath = "//div[@id='ddlstatus_chosen']//input[@type='text']")
    private WebElement complaint_status_field;
    @FindBy(id = "chkDoesNotPertain")
    private WebElement does_not_pertain_button;
    @FindBy(id = "txtPertain")
    private WebElement does_not_pertain_field;
    @FindBy(xpath = "//small[@id = 'txtPertain-error']")
    private WebElement does_not_pertain_field_errorMsg;
    @FindBy(id = "txtremarkbyRegulatedEntity")
    private WebElement remark_by_regulated_entity_field;
    @FindBy(id = "chkfrrdocument")
    private WebElement iF_final_resolution_letter_button;
    @FindBy(id = "FileUpload1")
    private WebElement upload_attachment_field;
    @FindBy(id = "btnupload")
    private WebElement upload_button;
    @FindBy(id = "btnupdate")
    private WebElement update_button;
    @FindBy(id = "btnclear")
    private WebElement clear_button;
    @FindBy(id = "btncancel")
    private WebElement cancel_button;
    @FindBy(id = "rdaccept")
    private WebElement type_of_disposal_Infavour_button;
    @FindBy(id = "rdpartialfavour")
    private WebElement type_of_disposal_PartialInfavour_button;
    @FindBy(id = "rdrejected")
    private WebElement type_of_disposal_NotInfavour_button;
    @FindBy(id = "rdduplicate")
    private WebElement type_of_disposal_Duplicate_button;
    //escalation remark field from policyholder
    @FindBy(id = "txtremarkbyescalation")
    private WebElement escalation_remark_field;
    @FindBy(id = "btnescalate")
    private WebElement escalate_button;
    @FindBy(id = "btnConfirmCancel")
    private WebElement update_record_cancel;
    //    receive success message
    @FindBy(id = "btnsuccessModal")
    @CacheLookup
    private WebElement complaint_updated_successfully_button;
    @FindBy(xpath = "//strong[@id='lblTokenNum']")
    private WebElement complaintNumberOnComplaintDetailsPage;
    @FindBy(id = "ddlstate-error")
    private WebElement state_name_error_message;
    @FindBy(id = "ddldistrict-error")
    private WebElement district_code_error_message;
    @FindBy(xpath = "//select[@id='ddldistrict']")
    private WebElement district_code;
    @FindBy(xpath = "//*[@id='ddldistrict_chosen']")
    @CacheLookup
    private WebElement district;
    @FindBy(xpath = "//button[@id='btnConfirmSuccess']")
    @CacheLookup
    private WebElement yesButtonToConfirmRecordUpdate;
    @FindBy(id = "btnsuccessModal")
    @CacheLookup
    private WebElement okButtonConfirmUpdate;
    @FindBy(xpath = "//small[@id = 'txtremarkbyRegulatedEntity-error']")
    @CacheLookup
    private WebElement remarkbyRegulatedEntityError;
    @FindBy(id = "btnwarningModal")
    @CacheLookup
    private WebElement typeOfDisposalMandatory_errorMsg;
    @FindBy(xpath = "//small[@id = 'txtOriginalirdaTokennNumber-error']")
    @CacheLookup
    private WebElement originalIRDAITokenNo_errorMsg;
    @FindBy(xpath = "//input[@id = 'txtOriginalirdaTokennNumber']")
    @CacheLookup
    private WebElement originalIRDAITokenNo;
    @FindBy(xpath = "//button[@id = 'Logout']")
    @CacheLookup
    private WebElement logout_btn;
    @FindBy(xpath = "//small[@id = 'txtfrremark-error']")
    @CacheLookup
    private WebElement final_resolution_remarks_errorMsg;
    @FindBy(xpath = "//textarea[@id = 'txtfrremark']")
    @CacheLookup
    private WebElement final_resolution_remarks_field;
    @FindBy(xpath = "//input[@id = 'txtfrrclosuredate']")
    @CacheLookup
    private WebElement final_resolution_remarks_date;
    @FindBy(xpath = "//small[@id ='txtfrrclosuredate-error']")
    @CacheLookup
    private WebElement final_resolution_remarks_date_errorMsg;
    @FindBy(id = "warningMessage")
    @CacheLookup
    private WebElement finalResolutionDoccument_errorMsg;
    @FindBy(id = "chkReqstIRDAfirclose")
    @CacheLookup
    private WebElement closureRequestToIrdaiCheckbox;
    @FindBy(xpath = "//small[@id = 'txtcomplaintdtpaymentdate-error']")
    @CacheLookup
    private WebElement dateOfPayment_errorMsg;
    @FindBy(xpath = "//input[@id = 'txtcomplaintdtpaymentdate']")
    @CacheLookup
    private WebElement dateOfPayment;
    @FindBy(xpath = "//input[@id = 'txtpaychequedate']")
    @CacheLookup
    private WebElement paymentChequeDate;
    @FindBy(xpath = "//small[@id = 'txtpaychequedate-error']")
    @CacheLookup
    private WebElement paymentChequeDate_errorMsg;
    @FindBy(xpath = "//input[@id = 'txtinsresolutindate']")
    @CacheLookup
    private WebElement insurersResolutionLetterDate;
    @FindBy(xpath = "//small[@id = 'txtinsresolutindate-error']")
    @CacheLookup
    private WebElement insurersResolutionLetterDate_errorMsg;
    @FindBy(xpath = "//input[@id = 'txthonoringservicedate']")
    @CacheLookup
    private WebElement dateOfHonoringTheService;
    @FindBy(xpath = "//small[@id = 'txthonoringservicedate-error']")
    @CacheLookup
    private WebElement dateOfHonoringTheService_errorMsg;
    @FindBy(id = "btnfeedback")
    @CacheLookup
    private WebElement feedbackForIGMSPortalEaseOfUse_visibility;
    @FindBy(id = "btnresolsatisfaction")
    @CacheLookup
    private WebElement ResolutionSatisfactionLevel_visibility;
    @FindBy(id = "btnnext")
    private WebElement pagination_nextBtn;
    @FindBy(id = "btnnext")
    private WebElement pagination_previousBtn;
    //Nutan code
    @FindBy(xpath = "//input[@id='chkchangeComplDetails']")
    private WebElement check_change_complaint_details;
    @FindBy(id = "txtpolicynum_Insu")
    private WebElement policy_no_field;
    @FindBy(id = "ddlpolicyType_Insu_chosen")
    private WebElement select_policy_type;
    @FindBy(xpath="//small[contains(text(),'Please Enter Valid Policy Number')]")
    private WebElement policy_no_field_error_message;
    @FindBy(xpath="//small[contains(text(),'Please Enter Policy Number')]")
    private WebElement blank_field_error_message;
    @FindBy(id="ConfirmationMessage")
    private WebElement update_record_confirmation_message;
    @FindBy(xpath="//div[@id='ddlpolicyType_Insu_chosen']//input[@type='text']")
    private WebElement policy_input_field;
    @FindBy(id = "ddlComplaintType_Insu_chosen")
    private WebElement select_complaint_type;
    @FindBy(xpath = "//div[@id='ddlComplaintType_Insu_chosen']//input[@type='text']")
    private WebElement select_input_complaint_field;
    @FindBy(xpath = "//small[@id='ddlComplaintDescType_Insu-error']")
    private WebElement complaint_description_type_error_message;
    @FindBy(xpath = "//small[contains(text(),'Please Select Commplaint Type')]")
    private WebElement complaint_type_error_msg;
    @FindBy(id = "ddlComplaintDescType_Insu_chosen")
    private WebElement complaint_description_type;
    @FindBy(xpath = "//div[@id='ddlComplaintDescType_Insu_chosen']//input[@type='text']")
    private WebElement select_input_complaint_description_type_field;
    @FindBy(xpath = "//h4[@id='successMessage']")
    private WebElement complaint_update_success_message;
    @FindBy(xpath = "//div[@id='ddldistrict_chosen']//input[@type='text']")
    private WebElement select_district_code;
    @FindBy(id = "chkcitizen")
    private WebElement check_senior_citizen_button;


    public ViewEditComplaintDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Click on Web element.
    public void click_View_Edit_Complaints_Checkbox() {
        try {
         //   this.view_edit_complaints_checkbox.click();
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].click();", view_edit_complaints_checkbox);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    //create method for complaint status dropdown
    public void click_Complaint_Status_Dropdown(String status) throws InterruptedException {
        action = new Actions(driver);
        complaint_status_dropdown.click();
        Thread.sleep(1000);
        complaint_status_field.sendKeys(status);
        action.sendKeys(Keys.ENTER).perform();

    }

    public void click_Does_Not_Pertain_Button() {
        try {
        //    this.does_not_pertain_button.click();
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].click();", does_not_pertain_button);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }
    public void click_upload_attachment_field(String filepath) {
        try {
            this.upload_attachment_field.sendKeys(filepath);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public void click_Upload_Button() {
        try {
//            this.upload_button.click();
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].click();", upload_button);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public void click_Update_Button() {
        try {
//            this.update_button.click();
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].click();", update_button);

        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public void click_Clear_Button() {
        try {
            this.clear_button.click();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public void click_Cancel_Button() {
        try {
            this.cancel_button.click();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public void click_Type_of_Disposal_Infavour_Button() {
        try {
            this.type_of_disposal_Infavour_button.click();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public void click_Type_of_Disposal_PartialInfavour_Button() {
        try {
            this.type_of_disposal_PartialInfavour_button.click();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }
    public void click_Type_of_Disposal_NotInfavour_Button() {
        try {
            this.type_of_disposal_NotInfavour_button.click();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }
    public void click_Type_of_Disposal_Duplicate_Button() {
        try {
            this.type_of_disposal_Duplicate_button.click();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }
    public void click_iF_final_resolution_letter_button() {
        try {
            this.iF_final_resolution_letter_button.click();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }
    public void click_Escalate_Button() {
        try {
            this.escalate_button.click();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public boolean click_Update_Record_Cancel() {
        boolean flag = true;
        if(flag){
            update_record_cancel.click();
            return true;
        }else{
            return false;
        }
    }
    public void click_Complaint_Updated_Successfully_Button() {
        try {
            this.complaint_updated_successfully_button.click();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }
    public void clkDistric() {
        district.click();

    }

    public void clickToYesButtonToConfirmRecordUpdate() {
        yesButtonToConfirmRecordUpdate.click();
//        JavascriptExecutor jse = (JavascriptExecutor) driver;
//        jse.executeScript("arguments[0].click();", yesButtonToConfirmRecordUpdate);

    }
    public boolean clickToYesButtonToConfirmRecordUpdateWithReturn(){
        boolean flag =true;
        if(flag){
            yesButtonToConfirmRecordUpdate.click();
            return true;
        }else{
            return false;
        }

    }
    public boolean clickToOkButtonConfirmUpdate() throws InterruptedException {
        boolean flag = true;
        if(flag){
            okButtonConfirmUpdate.click();
//            JavascriptExecutor jse = (JavascriptExecutor) driver;
//            jse.executeScript("arguments[0].click();", okButtonConfirmUpdate);
            Thread.sleep(2000);
            return true;
        }else{
            return false;
        }
    }
    public boolean clickToTypeOfDisposalMandatory_errorMsg() {
        boolean flag = true;
        if(flag){
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].click();", typeOfDisposalMandatory_errorMsg);
            return flag;
        }else{
            return flag;
        }
    }
    public void clickOnLogoutBtn() {
        try {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].click();", logout_btn);
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }
    public void clickOnClosureRequestToIRDAI_checkbox(){
        closureRequestToIrdaiCheckbox.click();
    }
    public boolean clickOnPagination_nextBtn(){
        boolean flag =true;
        if(flag){
            pagination_nextBtn.click();
            return true;
        }else{
            return false;
        }

    }
    public boolean clickOnPagination_previousBtn(){
        boolean flag =true;
        if(flag){
            pagination_previousBtn.click();
            return true;
        }else{
            return false;
        }
    }
    //Nutan code
    public void clkComplaintcheckbox(){
        try{
            if(!check_change_complaint_details.isSelected()){
                check_change_complaint_details.click();
                System.out.println("Able to click");
            }
        }
        catch (Exception e){
        }
    }
    public void click_policy_type_field() {
        try {
            select_policy_type.click();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }
    public void click_senior_citizen_button() {
        try {
            if(!check_senior_citizen_button.isSelected()){
                check_senior_citizen_button.click();
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    //Enter value into input field
    public void enter_Value_Does_Not_Pertain_Field(String does_not_pertain_field) {
        try {
            this.does_not_pertain_field.clear();
            this.does_not_pertain_field.sendKeys(does_not_pertain_field);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }
    public void enter_Value_Remark_by_Regulated_Entity_Field(String remark_by_regulated_entity_field) {
        try {
            this.remark_by_regulated_entity_field.clear();
            this.remark_by_regulated_entity_field.sendKeys(remark_by_regulated_entity_field);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }
    public void enter_Value_Escalation_Remark_Field(String escalation_remark_field) {
        try {
            this.escalation_remark_field.sendKeys(escalation_remark_field);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }
    public void enter_Value_Branch_Code_field(int index) throws InterruptedException {
        handleDropdown(branch_code, 2);
    }
    public void enter_Value_State_Name_Field(int index) throws InterruptedException {
        handleDropdown(state_code, 2);
    }
    public void enterKeyBoard() {
        Actions action = new Actions(driver);

        action.sendKeys(Keys.ENTER).build().perform();
    }
    public void enterOriginalIRDAITokenNo(String originalIrdaiTokenNo) {
        originalIRDAITokenNo.sendKeys(originalIrdaiTokenNo);
    }
    public void enterFinalResolutionRemarks(String FinalResolutionRemarks){
        final_resolution_remarks_field.clear();
        final_resolution_remarks_field.sendKeys(FinalResolutionRemarks);
    }
    public void enterFinalResolutionRemarkDateField(String finalResRemarkDate){
        final_resolution_remarks_date.clear();
        final_resolution_remarks_date.sendKeys(finalResRemarkDate);
    }
    //Enter final Resolution Remark date as current date
    public void enterFinalResolutionRemarkDateField() throws InterruptedException {
        final_resolution_remarks_date.clear();
        final_resolution_remarks_date.click();
        Thread.sleep(2000);

        // Get the current date
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        String currentDay = sdf.format(currentDate);

        // Select the current date
        WebElement currentDayElement = driver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']//td/a[text()='"+currentDay+"']"));
        currentDayElement.click();
        Thread.sleep(2000);
    }
    public void enterDateOfPayment(String date) throws InterruptedException {
        dateOfPayment.clear();
        if(date.isEmpty()){
            getCurrentDate(dateOfPayment);
        }else{
            dateOfPayment.sendKeys(date);
        }
    }
    public void enterPaymentChequeDate(String date) throws InterruptedException {
        paymentChequeDate.clear();
        if(date.isEmpty()){
            getCurrentDate(paymentChequeDate);
        }else{
            paymentChequeDate.sendKeys(date);
        }
    }
    public void enterInsurersResolutionLetterDate(String date) throws InterruptedException {
        insurersResolutionLetterDate.clear();
        if(date.isEmpty()){
            getCurrentDate(insurersResolutionLetterDate);
        }else{
            insurersResolutionLetterDate.sendKeys(date);
        }
    }
    public void enterDateOfHonoringTheService(String date) throws InterruptedException {
        dateOfHonoringTheService.clear();
        if(date.isEmpty()){
            getCurrentDate(dateOfHonoringTheService);
        }else{
            dateOfHonoringTheService.sendKeys(date);
        }
    }
    //policy details by insurer
    public void enter_policy_number(String policy_no_field) {
        try {
            if ( !this.policy_no_field.getAttribute("value").isEmpty())
            {
                this.policy_no_field.clear();
            }
            this.policy_no_field.sendKeys(policy_no_field);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public String enter_special_character_policy_no_field(){

        return policy_no_field_error_message.getText();
    }
    public void enter_value_policy_type_field(String field) throws InterruptedException {
        action = new Actions(driver);
        select_policy_type.click();
        Thread.sleep(3000);
        policy_input_field.sendKeys(field);
        action.sendKeys(Keys.ENTER).perform();
    }
    public void enter_value_complaint_type_field(String field) throws InterruptedException {
        action = new Actions(driver);
        select_complaint_type.click();
        Thread.sleep(3000);
        select_input_complaint_field.sendKeys(field);
        action.sendKeys(Keys.ENTER).perform();

    }
    public void enter_value_complaint_description_type(String field) throws InterruptedException {
        action = new Actions(driver);
        complaint_description_type.click();
        Thread.sleep(3000);
        select_input_complaint_description_type_field.sendKeys(field);
        action.sendKeys(Keys.ENTER).perform();
    }
    public void enter_Value_District_Name_Field(String district) throws InterruptedException {
        try {
            action = new Actions(driver);
            district_code.click();
            Thread.sleep(1000);
            select_district_code.sendKeys(district);
            action.sendKeys(Keys.ENTER).perform();
            Thread.sleep(2000);
        } catch (NoSuchElementException e) {
        }
    }

    //Getter methods
    public String getErrorMsg_DoesNotPertain_withoutRemarkField() {
        try {
           return this.does_not_pertain_field_errorMsg.getText();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String Get_branch_code_Error_Message() {

        return barnch_code_error_message.getText();
    }
    public String getRemarkbyRegulatedEntityError() {
        return remarkbyRegulatedEntityError.getText();
    }

    public String getOriginalIRDAITokenNo_ErrorMsg() {
        return originalIRDAITokenNo_errorMsg.getText();
    }

    public String getFinalResolutionRemarksErrorMsg(){
        return final_resolution_remarks_errorMsg.getText();
    }

    public String getFinalResolutionRemarksDate_errorMsg(){
        return final_resolution_remarks_date_errorMsg.getText();
    }

    public void getCurrentDate(WebElement locator) throws InterruptedException {
        locator.click();
        Thread.sleep(2000);

        // Get the current date
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        String currentDay = sdf.format(currentDate);

        // Select the current date
        WebElement currentDayElement = driver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']//td/a[text()='"+currentDay+"']"));
        currentDayElement.click();
        Thread.sleep(2000);
    }
    public String getFinalResolutionDoccument_errorMsg(){
        return finalResolutionDoccument_errorMsg.getText();
    }

    public String getDateOfPayment_errorMsg(){
        return dateOfPayment_errorMsg.getText();
    }

    public String getPaymentChequeDate_errorMsg(){
        return paymentChequeDate_errorMsg.getText();
    }

    public String getInsurersResolutionLetterDate_errorMsg(){
        return insurersResolutionLetterDate_errorMsg.getText();
    }

    public String getDateOfHonoringTheService_errorMsg(){
        return dateOfHonoringTheService_errorMsg.getText();
    }
    public String get_success_message() {
        if (complaint_update_success_message.isDisplayed()) {
            String msg = complaint_update_success_message.getText();
            System.out.println(msg);
            complaint_updated_successfully_button.click();
            return msg;
        }
        else{
            return null;
        }
    }
    public String complaint_desc_error_msg(){
        complaint_description_type_error_message.getText();
        return complaint_description_type_error_message.getText();
    }
    public String state_Name_Error_Message() {
        return state_name_error_message.getText();
    }
    public String district_Error_Message() {
        return district_code_error_message.getText();
    }
    public String complaint_type_error_msg(){
        complaint_type_error_msg.getText();
        return complaint_type_error_msg.getText();
    }
    //Verify Visibility of element on web page
    public boolean Visibility_iF_final_resolution_letter_button() {
        try {
            this.iF_final_resolution_letter_button.isEnabled();
            return true;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String visibilityComplaintNumberOnComplaintDetailsPage() {
        String actualComplaintNumber = "";
        try {
            actualComplaintNumber = complaintNumberOnComplaintDetailsPage.getText();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return actualComplaintNumber;
    }

    public boolean visibilityOfClosureRequestToIRDAI_checkbox(){
        return closureRequestToIrdaiCheckbox.isSelected();
    }

    public boolean visibility_FeedbackAndResolutionSatisfactionFields_CompDetailsPage(){
        if(feedbackForIGMSPortalEaseOfUse_visibility.isDisplayed() && ResolutionSatisfactionLevel_visibility.isDisplayed()){
            return true;
        }else{
            return false;
        }
    }
    public void handleDropdown(WebElement dropdown, int index) throws InterruptedException {
        try {
            Thread.sleep(3000);
            Select branch_code_dropdown = new Select(dropdown);
            branch_code_dropdown.selectByIndex(index);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    // create getter method to get some values

    public void downArrow() {
        Actions action = new Actions(driver);

        action.sendKeys(Keys.ARROW_DOWN).build().perform();
    }
    public boolean IsDisable_Does_Not_Pertain_Button() {
        try {
        //  this.does_not_pertain_button.click();
            return does_not_pertain_button.isEnabled();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String addOneToLastDigitOfComplaintNo(String complaintNo){
        String str[] = complaintNo.split("-");
        int number = Integer.parseInt(str[2]);
        int num = number + 1;
        String numStr = String.valueOf(num);
        String ComplaintNoAfterOneAdd = str[0] +"-" +str[1] +"-000"+numStr;
        return ComplaintNoAfterOneAdd;
    }

    public boolean verify_policy_no_blank_field_error_message() {
        try {
            if (blank_field_error_message.isDisplayed()) {
                return true;
            } else if (update_record_confirmation_message.isDisplayed()) {
                update_record_cancel.click();
                return false;
            } else {
                return false;
            }
        }
        catch (NoSuchElementException n){
            return false;
        }
    }

    public boolean verify_clear_button_functionality() {
        try {
            if (remark_by_regulated_entity_field.getAttribute("value").isEmpty()) {
                return true;
            }
        } catch (NoSuchElementException e) {
        }
        return false;
    }
}






    













