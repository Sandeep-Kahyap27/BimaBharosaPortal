package ObjectRepository;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Random;

public class CallcenterRegPage {

    WebDriver driver = null;
    Actions actions;

    @FindBy(id = "yescomplaint")
    @CacheLookup
    private WebElement yesComplaint;

    @FindBy(id = "nocomplaint")
    @CacheLookup
    private WebElement noComplaint;

    @FindBy(id = "txtfirstname_individual")
    @CacheLookup
    private WebElement name;

    @FindBy(id = "lblMobile")
    @CacheLookup
    private  WebElement label_MobileNo;

    //Added by abhishek
    @FindBy(id = "lblEmail")
    @CacheLookup
    private WebElement label_EmailId;

    @FindBy(id = "txtmob_individual")
    @CacheLookup
    private WebElement mobileNumber;

    @FindBy(id = "txtemailofcompl_individual")
    @CacheLookup
    private WebElement email;


    @FindBy(xpath = "//div[@id='ddlidentifyby_chosen']")
    @CacheLookup
    private WebElement identifyBy;

    @FindBy(xpath = "//div[@id='ddlidentifyby_chosen']//input[@type='text']")
    @CacheLookup
    private WebElement identifyBy_inputField;

    //modified by abhishek
    @FindBy(xpath = "//input[@id='txtenternumber_search']")
    @CacheLookup
    private WebElement txtSearch;

    //Error message elements
    @FindBy(id = "warningMessage")
    @CacheLookup
    private WebElement warningMsg;

    @FindBy(id = "btnwarningModal")
    @CacheLookup
    private WebElement warningPopUp;

    @FindBy(xpath = "//small[@id='txtenternumber_search-error']")
    @CacheLookup
    private WebElement errorMsg;


    @FindBy(id = "btnsearch")
    @CacheLookup
    private WebElement searchBtn;

    @FindBy(id = "txtmob_search")
    @CacheLookup
    private WebElement mobile_search_disable;

    @FindBy(id = "txtemail_search")
    @CacheLookup
    private WebElement email_search_disable;

    @FindBy(id = "chkPinCode")
    @CacheLookup
    private WebElement chkPinCode;

    @FindBy(id = "txtPinCode")
    @CacheLookup
    private WebElement pinCodeField;

    @FindBy(id = "txtDistrict")
    @CacheLookup
    private WebElement districtField;

    @FindBy(id = "txtState")
    @CacheLookup
    private WebElement stateField;

    @FindBy(id = "ddlcomplagainstinsurer_chosen")
    @CacheLookup
    private WebElement comp_against_dropdown_list;

    @FindBy(xpath = "//div[@id='ddlcomplagainstinsurer_chosen']//input[@type='text']")
    @CacheLookup
    private WebElement comp_against_dropdown_input_field;

    @FindBy(xpath = "//small[@id='ddlcomplagainstinsurer-error']")
    @CacheLookup
    private WebElement comp_against_dropdown_errmsg;

    @FindBy(id = "ddlintermediarytype_chosen")
    @CacheLookup
    private WebElement intermediary_type_dropdown_list;

    @FindBy(xpath = "//div[@id='ddlintermediarytype_chosen']//input[@type='text']")
    @CacheLookup
    private WebElement intermediary_type_input_field;

    @FindBy(id = "ddlinsurancecomp_chosen")
    @CacheLookup
    private WebElement ins_company_dropdown_list;

    @FindBy(xpath = "//div[@id='ddlinsurancecomp_chosen']//input[@type='text']")
    @CacheLookup
    private WebElement ins_company_dropdown_input_field;

    @FindBy(id = "ddlpolicytype_chosen")
    @CacheLookup
    private WebElement policy_type_dropdown_list;

    @FindBy(xpath = "//div[@id='ddlpolicytype_chosen']//input[@type='text']")
    @CacheLookup
    private WebElement policy_type_dropdown_input_field;

    @FindBy(id = "ddlcomplainttype_chosen")
    @CacheLookup
    private WebElement comp_type_dropdown_list;

    @FindBy(xpath = "//div[@id='ddlcomplainttype_chosen']//input[@type='text']")
    @CacheLookup
    private WebElement comp_type_dropdown_input_field;

    @FindBy(id = "ddlcomplaintdesctype_chosen")
    @CacheLookup
    private WebElement comp_desc_type_dropdown_list;

    @FindBy(xpath = "//div[@id='ddlcomplaintdesctype_chosen']//input[@type='text']")
    @CacheLookup
    private WebElement comp_desc_type_dropdown_input_field;

    @FindBy(id = "ddlbrokername_chosen")
    @CacheLookup
    private WebElement broker_dropdown_list;

    @FindBy(xpath = "//div[@id='ddlbrokername_chosen']//input[@type='text']")
    @CacheLookup
    private WebElement broker_input_field;

    @FindBy(id = "txtbrokerlicensenum")
    @CacheLookup
    private WebElement broker_license_number;

    @FindBy(id = "ddlSurveyorname_chosen")
    @CacheLookup
    private WebElement surveyor_dropdown_list;

    @FindBy(xpath = "//div[@id='ddlSurveyorname_chosen']//input[@type='text']")
    @CacheLookup
    private WebElement surveyor_input_field;

    @FindBy(id = "ddltpaname_chosen")
    @CacheLookup
    private WebElement tpa_dropdown_list;

    @FindBy(xpath = "//div[@id='ddltpaname_chosen']//input[@type='text']")
    @CacheLookup
    private WebElement tpa_input_field;

    @FindBy(id = "txtagentname")
    @CacheLookup
    private WebElement agent_input_field;

    @FindBy(id = "txtcompname_unregcomp")
    @CacheLookup
    private WebElement unregistered_company_name;

    @FindBy(id = "txtadd_unregcomp")
    @CacheLookup
    private WebElement unregistered_company_address;

    @FindBy(id = "txtphone_unregcomp")
    @CacheLookup
    private WebElement unregistered_company_phone;

    @FindBy(id = "txtdesc_unregcomp")
    @CacheLookup
    private WebElement unregistered_company_desc;

    @FindBy(id = "txttypeofserv_unregcomp")
    @CacheLookup
    private WebElement type_of_svc_obtain;

    @FindBy(id = "txtcontpername_unregcomp")
    @CacheLookup
    private WebElement unregistered_contact_person_name;

    @FindBy(id = "txtcontperphone_unregcomp")
    @CacheLookup
    private WebElement unregistered_contact_person_phone;

    @FindBy(id = "txtcontperemail_unregcomp")
    @CacheLookup
    private WebElement unregistered_contact_person_email;

    @FindBy(id = "txtcontperadd__unregcomp")
    @CacheLookup
    private WebElement unregistered_contact_person_address;

    @FindBy(id = "rdPolicyNumber")
    @CacheLookup
    private WebElement policyNumber;

    @FindBy(id = "rdProposalNumber")
    @CacheLookup
    private WebElement proposalNumber;

    @FindBy(id = "rdCertNumber")
    @CacheLookup
    private WebElement certificateNumber;

    @FindBy(id = "rdClaimNumber")
    @CacheLookup
    private WebElement claimNumber;

    @FindBy(id = "txtPolicyProposalCertifiateClaimNumber")
    @CacheLookup
    private WebElement policyNumber_field;

    @FindBy(id = "lblPolicy")
    @CacheLookup
    private WebElement policy_number_errormsg;

    @FindBy(id = "txtcompldetails")
    @CacheLookup
    private WebElement complaintDetails_field;

    @FindBy(id = "lblComplaintdetails")
    @CacheLookup
    private WebElement complaint_detail_errormsg;

    @FindBy(xpath = "//small[@id='txtcompldetails-error']")
    @CacheLookup
    private WebElement complaint_detail_validation_errormsg;

    @FindBy(id = "ddlsrcofcomplaint_chosen")
    @CacheLookup
    private WebElement source_of_comp_dropdown;

    @FindBy(xpath = "//div[@id='ddlsrcofcomplaint_chosen']//input[@type='text']")
    @CacheLookup
    private WebElement source_of_comp_input_field;

    @FindBy(xpath = "//small[@id='ddlsrcofcomplaint-error']")
    @CacheLookup
    private WebElement source_of_comp_errormsg;

    @FindBy(id = "dtdateofletter")
    @CacheLookup
    private WebElement date_of_letter;

    @FindBy(xpath = "//input[@id='dtdateofletter']")
    @CacheLookup
    private WebElement date_of_letter_input_field;

    @FindBy(xpath = "//small[@id='dtdateofletter-error']")
    @CacheLookup
    private WebElement date_of_letter_errorMsg;

    @FindBy(xpath = "//input[@id='dtreceiptofcompl']")
    @CacheLookup
    private WebElement receiptOfComplaint;

    @FindBy(xpath = "//small[@id='dtreceiptofcompl-error']")
    @CacheLookup
    private WebElement receiptOfComplaint_errorMsg;

    @FindBy(id = "FileUpload1")
    @CacheLookup
    private WebElement fileAttachment;

    @FindBy(id= "btnupload")
    @CacheLookup
    private WebElement uploadBtn;


    @FindBy(id = "ddlpriohandl_chosen")
    @CacheLookup
    private WebElement priorityHandling_dropdown;

    @FindBy(xpath = "//div[@id='ddlpriohandl_chosen']//input[@type='text']")
    @CacheLookup
    private WebElement priorityHandling_input_field;

    @FindBy(id = "txttatday")
    @CacheLookup
    private WebElement TATElement;

    @FindBy(id = "txtpriohandldetails")
    @CacheLookup
    private WebElement priorityHandlingDetails;

    @FindBy(xpath = "//small[@id='txtpriohandldetails-error']")
    @CacheLookup
    private WebElement priorityHandlingDetails_errormsg;

    @FindBy(id = "txtremarks")
    @CacheLookup
    private WebElement remarks;

    @FindBy(xpath = "//small[@id='txtremarks-error']")
    @CacheLookup
    private WebElement remarks_errormsg;

    @FindBy(xpath = "//button[@id='btnsave']")
    @CacheLookup
    private WebElement registerBtn;

    @FindBy(xpath = "//button[@id='btncancel']")
    @CacheLookup
    private WebElement backBtn;

    @FindBy(xpath = "//button[@id='Logout']")
    @CacheLookup
    private WebElement logoutBtn;

    @FindBy(xpath = "//button[@id='btnConfirmSuccess']")
    @CacheLookup
    private WebElement logOffSuccess;

    @FindBy(id = "btnConfirmCancel")
    @CacheLookup
    private static WebElement logOffCancel;



    public CallcenterRegPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



    /*Action methods*/
    public void IsComplaintRegistered(){
        try{
            yesComplaint.click();
        }
        catch (NoSuchElementException e){

        }
    }

    public void IsComplaintNotRegistered(){
        try{
            Thread.sleep(1000);
            noComplaint.click();
        }
        catch (NoSuchElementException e){

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void identifyBy(String searchValue) throws InterruptedException {
        try{
            actions = new Actions(driver);
            identifyBy.click();

            Thread.sleep(1000);
            identifyBy_inputField.sendKeys(searchValue);
            actions.sendKeys(Keys.ENTER).perform();

        }
        catch (NoSuchElementException e){

        }
    }

    public void enterMobileNumberOrEmail(String value) throws InterruptedException {
        try{
            txtSearch.sendKeys(value);
            Thread.sleep(2000);
        }
        catch (NoSuchElementException e){

        }
    }


    public void clearMobileNumberOrEmail() throws InterruptedException {
        try{
          txtSearch.clear();
        }
        catch (NoSuchElementException e){

        }
    }

    public void search(){
        try{
            searchBtn.click();
        }
        catch (NoSuchElementException e){

        }
    }


    public void enterName(String phName){
        try{
            name.sendKeys(phName);
        }
        catch (NoSuchElementException e){

        }
    }

    public void enterMobileNumber(String mob){
        try{
            mobileNumber.sendKeys(mob);
        }
        catch (NoSuchElementException e){

        }
    }

    public void enterEmailId(String emailId){
        try{
            email.sendKeys(emailId);
        }
        catch (NoSuchElementException e){

        }
    }

    public void selectIdentifyBy() throws InterruptedException {
        try{
            identifyBy.click();
            Thread.sleep(1000);
        }
        catch (NoSuchElementException e){

        }
    }

    public void enterPinCode(String pinCode) throws InterruptedException {
        try{
            if(!chkPinCode.isSelected()){
                chkPinCode.click();
            }
            pinCodeField.sendKeys(pinCode);
            Thread.sleep(1000);
            actions = new Actions(driver);
            actions.keyDown(Keys.ARROW_DOWN).perform();
            actions.sendKeys(Keys.ENTER).perform();
        }
        catch (NoSuchElementException e){

        }
    }

    public void enterComplaintAgainstType(String complaint_Against) throws InterruptedException {
        actions = new Actions(driver);
        comp_against_dropdown_list.click();
        Thread.sleep(1000);
        comp_against_dropdown_input_field.sendKeys(complaint_Against);
        actions.sendKeys(Keys.ENTER).perform();
        Thread.sleep(1000);
    }

    public void enterIntermediaryType(String intermediary) throws InterruptedException {
        actions = new Actions(driver);
        intermediary_type_dropdown_list.click();
        Thread.sleep(1000);
        intermediary_type_input_field.sendKeys(intermediary);
        actions.sendKeys(Keys.ENTER).perform();
        Thread.sleep(1000);
    }

    public void enterInsuranceCompanyName(String ins_company_name) throws InterruptedException {
        actions = new Actions(driver);
        ins_company_dropdown_list.click();
        Thread.sleep(1000);
        ins_company_dropdown_input_field.sendKeys(ins_company_name);
        actions.sendKeys(Keys.ENTER).perform();
        Thread.sleep(1000);
    }

    public void enterPolicyType(String policy_type) throws InterruptedException {
        actions = new Actions(driver);
        policy_type_dropdown_list.click();
        Thread.sleep(1000);
        policy_type_dropdown_input_field.sendKeys(policy_type);
        actions.sendKeys(Keys.ENTER).perform();
        Thread.sleep(1000);
    }

    public void enterComplaintType(String complaint_type) throws InterruptedException {
        actions = new Actions(driver);
        comp_type_dropdown_list.click();
        Thread.sleep(1000);
        comp_type_dropdown_input_field.sendKeys(complaint_type);
        actions.sendKeys(Keys.ENTER).perform();
        Thread.sleep(1000);
    }

    public void enterComplaintDescType(String complaint_desc_type) throws InterruptedException {
        actions = new Actions(driver);
        comp_desc_type_dropdown_list.click();
        Thread.sleep(1000);
        comp_desc_type_dropdown_input_field.sendKeys(complaint_desc_type);
        actions.sendKeys(Keys.ENTER).perform();
        Thread.sleep(1000);
    }

    public void enterBrokerName(String broker_name) throws InterruptedException {
        actions = new Actions(driver);
        broker_dropdown_list.click();
        Thread.sleep(1000);
        broker_input_field.sendKeys(broker_name);
        actions.sendKeys(Keys.ENTER).perform();
        Thread.sleep(1000);
    }

    public void enterUnregCompanyName(String unreg_company_name){
        try{
            unregistered_company_name.sendKeys(unreg_company_name);
        }
        catch (NoSuchElementException n){

        }
    }

    public void enterUnregCompanyAddress(String unreg_company_address){
        try{
            unregistered_company_address.sendKeys(unreg_company_address);
        }
        catch (NoSuchElementException n){

        }
    }

    public void enterUnregCompanyPhone(String unreg_company_phone){
        try{
            unregistered_company_phone.sendKeys(unreg_company_phone);
        }
        catch (NoSuchElementException n){

        }
    }

    public void enterUnregCompanyDescription(String unreg_company_desc){
        try{
            unregistered_company_desc.sendKeys(unreg_company_desc);
        }
        catch (NoSuchElementException n){

        }
    }

    public void enterTypeOfSvcObtained(String type_svc_obtain){
        try{
            type_of_svc_obtain.sendKeys(type_svc_obtain);
        }
        catch (NoSuchElementException n){

        }
    }

    public void enterContactPersonName(String contact_person_name){
        try{
            unregistered_contact_person_name.sendKeys(contact_person_name);
        }
        catch (NoSuchElementException n){

        }
    }

    public void enterSourceOfComplaint(String source_of_complaint) throws InterruptedException {
        actions = new Actions(driver);
        source_of_comp_dropdown.click();
        Thread.sleep(1000);
        source_of_comp_input_field.sendKeys(source_of_complaint);
        actions.sendKeys(Keys.ENTER).perform();
        Thread.sleep(1000);
    }

    public void enterDateOfLetter(String dol){
        try{
            date_of_letter_input_field.sendKeys(dol);
        }
        catch (NoSuchElementException n){

        }
    }

    public void enterDateOfReceipt(String dor){
        try{
            receiptOfComplaint.sendKeys(dor);
        }
        catch (NoSuchElementException n){

        }
    }

    public void select_Complaint_Date() throws InterruptedException {
        date_of_letter.click();
        Thread.sleep(1000);

        // Get the current date
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd");

        //Modified-01/07/24
        String currentDay = sdf.format(currentDate).replaceFirst("0","");

        // Select the current date
        WebElement currentDayElement = driver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']//td/a[text()='"+currentDay+"']"));
        currentDayElement.click();
    }

    public void select_Complaint_Receipt_Date() throws InterruptedException {
        receiptOfComplaint.click();
        Thread.sleep(1000);

        // Get the current date
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd");

        //Modified-01/07/24
        String currentDay = sdf.format(currentDate).replaceFirst("0","");;

        // Select the current date
        WebElement currentDayElement = driver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']//td/a[text()='"+currentDay+"']"));
        currentDayElement.click();
    }

    public void selectPriorityHandling(String priority) throws InterruptedException {
        actions = new Actions(driver);
        priorityHandling_dropdown.click();
        Thread.sleep(1000);
        priorityHandling_input_field.sendKeys(priority);
        actions.sendKeys(Keys.ENTER).perform();
        Thread.sleep(1000);
    }


    public void clickOnDistrict(){
        try{
            districtField.click();
        }
        catch (NoSuchElementException n)
        {

        }
    }

    public void selectPolicyNumber(){
        try{
            if(!policyNumber.isSelected()){
                policyNumber.click();
            }
        }
        catch (NoSuchElementException e){

        }
    }

    public void selectProposalNumber(){
        try{
            proposalNumber.click();
        }
        catch (NoSuchElementException e){

        }
    }

    public void selectCertificateNumber(){
        try{
            certificateNumber.click();
        }
        catch (NoSuchElementException e){

        }
    }

    public void selectClaimNumber(){
        try{
            claimNumber.click();
        }
        catch (NoSuchElementException e){

        }
    }

    public void clickOnPolicyNumber(){
        try{
            policyNumber_field.click();
        }
        catch (NoSuchElementException e){

        }
    }

    public void clearPolicyNumberField(){
        try{
            policyNumber_field.clear();
        }
        catch (NoSuchElementException e){

        }
    }


    public void enter_Policy_Proposal_Certificate_Claim_Number(String policyNumber) throws InterruptedException {
        if(!policyNumber.contains("PL")){
            try{
                System.out.println(policyNumber);
                Thread.sleep(1000);
                policyNumber_field.sendKeys(policyNumber);
            }
            catch (NoSuchElementException e){
                e.printStackTrace();
            }
            catch (ElementNotInteractableException e)
            {
                e.printStackTrace();
            }
        }
        else {
            try{
                Random random = new Random();
                policyNumber_field.sendKeys(policyNumber+random.nextInt(100000000));
                Thread.sleep(2000);
            }
            catch (NoSuchElementException e){
                e.printStackTrace();
            }
            catch (ElementNotInteractableException e)
            {
                e.printStackTrace();
            }
        }

    }

    public void enterComplaintDetails(String details) throws InterruptedException {
        try{
            if(!complaintDetails_field.getAttribute("value").isEmpty()){
                complaintDetails_field.clear();
            }
            Thread.sleep(1000);
            complaintDetails_field.sendKeys(details);
        }
        catch (NoSuchElementException e){

        }
    }

    public void attachFile(String filePath){
        try{
            fileAttachment.sendKeys(filePath);
        }
        catch (NoSuchElementException e){

        }
    }

    public void upload() throws InterruptedException {
        try{
            Thread.sleep(1000);
            uploadBtn.click();
        }
        catch (NoSuchElementException e){

        }
    }

    public void enterTATDays(String days){
        try{
            TATElement.sendKeys(days);
        }
        catch (NoSuchElementException e){

        }
    }

    public void enterPriorityHandlingDetails(String days){
        try{
            if(!priorityHandlingDetails.getAttribute("value").isEmpty()){
                priorityHandlingDetails.clear();
            }
            priorityHandlingDetails.sendKeys(days);
        }
        catch (NoSuchElementException e){

        }
    }

    public void enterRemarks(String r){
        try
        {
            if(!remarks.getAttribute("value").isEmpty()){
                remarks.clear();
            }
            remarks.sendKeys(r);
        }
        catch (NoSuchElementException e){

        }

    }

    public void clearRemarksField(){
        try{
            remarks.clear();
        }
        catch (NoSuchElementException e){

        }
    }


    public void clickOnRegister(){
        try
        {

            registerBtn.click();
        }
        catch (NoSuchElementException e){

        }
        catch (ElementNotInteractableException e){

        }
    }


    public void clickOnBack() throws InterruptedException {
        try
        {
            backBtn.click();
            Thread.sleep(1000);
        }
        catch (NoSuchElementException e){

        }
    }

    public void logOutSuccess() throws InterruptedException {
        try{
            logoutBtn.click();
            Thread.sleep(1000);
            logOffSuccess.click();
        }
        catch (NoSuchElementException e){

        }
    }

    public void logOutCancel() throws InterruptedException {
        try{
            logoutBtn.click();
            Thread.sleep(1000);
            logOffCancel.click();
        }
        catch (NoSuchElementException e){

        }
    }


    public void acceptWarningPopup() throws InterruptedException {
        try{
            warningPopUp.click();
            Thread.sleep(2000);
        }
        catch (NoSuchElementException e){

        }
    }


    /*Getter Methods*/
    public String errorMessage(){
        String errmsg = null;
        if(errorMsg.isDisplayed()){
            errmsg = errorMsg.getText();
        }
        return errmsg;
    }

    public String warningMessage() throws InterruptedException {
        String msg = null;
        if(warningMsg.isDisplayed()){
            msg = warningMsg.getText();
            warningPopUp.click();
            Thread.sleep(1000);

        }
        return msg;

    }

    public String getComplaintAgainst_errorMsg(){
        if(comp_against_dropdown_errmsg.isDisplayed()){
            return comp_against_dropdown_errmsg.getText();
        }
        else{
            return null;
        }
    }

    public String getPolicyOrProposalOrCertificateOrClaim_errorMsg(){
        if(policy_number_errormsg.isDisplayed()){
            return policy_number_errormsg.getText();
        }
        else{
            return null;
        }
    }

    public String getComplaintDetails_errorMsg(){
        if(complaint_detail_errormsg.isDisplayed()){
            return complaint_detail_errormsg.getText();
        }
        else{
            return null;
        }
    }

    public String getComplaintDetails_validation_errorMsg(){
        if(complaint_detail_validation_errormsg.isDisplayed()){
            return complaint_detail_validation_errormsg.getText();
        }
        else{
            return null;
        }
    }

    public String getSourceOfComplaint_errorMsg(){
        if(source_of_comp_errormsg.isDisplayed()){
            return source_of_comp_errormsg.getText();
        }
        else{
            return null;
        }
    }

    public String getDateOfLetter_errorMsg(){
        if(date_of_letter_errorMsg.isDisplayed()){
            return date_of_letter_errorMsg.getText();
        }
        else{
            return null;
        }
    }

    public String getDateOfReceipt_errorMsg(){
        if(receiptOfComplaint_errorMsg.isDisplayed()){
            return receiptOfComplaint_errorMsg.getText();
        }
        else{
            return null;
        }
    }

    public String getPriorityHandlingDetails_errorMsg(){
        if(priorityHandlingDetails_errormsg.isDisplayed()){
            return priorityHandlingDetails_errormsg.getText();
        }
        else{
            return null;
        }
    }

    public String getRemarks_errorMsg(){
        if(remarks_errormsg.isDisplayed()){
            return remarks_errormsg.getText();
        }
        else{
            return null;
        }
    }

    public String getMaximumLimit_error_handleAlert(){
        String maxiumLimit_errormsg = null;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // wait for up to 1 seconds
        for (int i = 0; i < 10; i++) { // Arbitrary upper limit to prevent infinite loop
            try {
                wait.until(ExpectedConditions.alertIsPresent());
                Alert alert = driver.switchTo().alert();
                maxiumLimit_errormsg = alert.getText();
                alert.accept(); // Accept the alert
                System.out.println("Alert accepted!!");
            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println("No more alerts present");
                break;
            }
        }
        System.out.println(maxiumLimit_errormsg);
        return  maxiumLimit_errormsg;
    }



    /*Validation Methods*/
    public boolean verifyEmailIdOption(){
        boolean status = false;
        try{
            if(label_EmailId.isDisplayed() && txtSearch.isDisplayed()){
                status = true;
            }
        }
        catch (NoSuchElementException n){

        }

        return status;

    }

    public boolean verifyDistrictStatus() {
        boolean status = false;
        try{
            String district = districtField.getAttribute("value");
            if(!district.isEmpty()){
                status = true;
            }
        }
        catch (Exception e){
        }
        return status;
    }

    public boolean verifyStateStatus() {

        boolean status = false;
        try{
            String state = stateField.getAttribute("value");
            if(!state.isEmpty()){
                status = true;
            }
        }
        catch (Exception e){
        }
        return status;
    }

    public boolean verifyPinCodeField(){
        boolean pinCodeFieldStatus = false;
        try{
            if(chkPinCode.isDisplayed() && chkPinCode.isEnabled()){
                if(!chkPinCode.isSelected()){
                    chkPinCode.click();
                }
                if(pinCodeField.isDisplayed()){
                    pinCodeFieldStatus = true;
                }
            }

        }
        catch (NoSuchElementException n){

        }
        return pinCodeFieldStatus;
    }

    public boolean verifyPinCodeField_ForInvalidData() throws InterruptedException {
        Thread.sleep(2000);
        return pinCodeField.getAttribute("value").isEmpty()
                && districtField.getAttribute("value").isEmpty()
                && stateField.getAttribute("value").isEmpty();
    }

    public boolean verifyPinCodeField_ForValidData(String valid_pinCode) throws InterruptedException {
        Thread.sleep(2000);
        if(!pinCodeField.getAttribute("value").isEmpty() && pinCodeField.getAttribute("value").equals(valid_pinCode)){
            return !districtField.getAttribute("value").isEmpty()
                    && !stateField.getAttribute("value").isEmpty();
        }

        else{
            return false;
        }
    }


    public boolean verifyMobileNumberOption(){
        boolean status = false;
        try{
            if(label_MobileNo.isDisplayed() && txtSearch.isDisplayed()){
                status = true;
            }
        }
        catch (NoSuchElementException n){

        }

        return status;

    }

    public boolean verifyIsComplaintRegisteredBtnStatus() throws InterruptedException {
        boolean IsComplaintRegisteredBtnStatus = false;
        try{
            if(yesComplaint.isEnabled() && noComplaint.isEnabled()){

                Thread.sleep(1000);
                IsComplaintNotRegistered();

                Thread.sleep(1000);
                IsComplaintRegistered();

                Thread.sleep(1000);

                IsComplaintRegisteredBtnStatus = true;
            }
        }
        catch (NoSuchElementException n){

        }
        return IsComplaintRegisteredBtnStatus;
    }


    public boolean  verifyStatusOfIdentifyByDropdown(){
        boolean identifyByDropdown = false;
        try{
            if(identifyBy.isDisplayed()){
                identifyByDropdown = true;
            }
        }
        catch (NoSuchElementException n){

        }

        return identifyByDropdown;

    }

    public boolean  verifyStatusOfSearchField(){
        boolean searchFieldStatus = false;
        try{
            if(txtSearch.isDisplayed()){
                searchFieldStatus = true;
            }
        }
        catch (NoSuchElementException n){

        }

        return searchFieldStatus;

    }

    public boolean  verifyStatusOfSearchBtn(){
        boolean searchBtnStatus = false;
        try{
            if(searchBtn.isDisplayed()){
                searchBtnStatus = true;
            }
        }
        catch (NoSuchElementException n){

        }

        return searchBtnStatus;

    }

    public boolean  verifyStatusOfPinCode(){
        boolean pinCodeStatus = false;
        try{
            if(chkPinCode.isDisplayed() && chkPinCode.isEnabled()){
                pinCodeStatus = true;
            }
        }
        catch (NoSuchElementException n){
        }

        return pinCodeStatus;
    }

    public boolean verifyComplaintClassficationFields(){
        try{
            return comp_against_dropdown_list.isDisplayed() && ins_company_dropdown_list.isDisplayed() && policy_type_dropdown_list.isDisplayed() && comp_type_dropdown_list.isDisplayed() && comp_desc_type_dropdown_list.isDisplayed();
        }
        catch (NoSuchElementException n){

        }
        return false;
    }

    public boolean verifyIdentifierTypes(){
        try{
            return policyNumber.isDisplayed() && proposalNumber.isDisplayed() && certificateNumber.isDisplayed() && claimNumber.isDisplayed();
        }
        catch (NoSuchElementException n){

        }
        return false;
    }

    public boolean verifyPolicyNumberField(){
        try{
            return policyNumber_field.isDisplayed() && policyNumber_field.isEnabled();
        }
        catch (NoSuchElementException n){

        }
        return false;
    }

    public boolean verifyComplaintDetailsField(){
        try{
            return complaintDetails_field.isDisplayed() && complaintDetails_field.isEnabled();
        }
        catch (NoSuchElementException n){

        }
        return false;
    }


    public boolean verifyRegisterButton(){
        try{
            return registerBtn.isDisplayed() && !registerBtn.isEnabled();
        }
        catch (NoSuchElementException n){

        }
        return false;
    }

    public boolean verifySearchBtnIntractable(){
        boolean searchBtnStatus = false;
        try{
            if(searchBtn.isEnabled()){
                searchBtnStatus = true;
            }
        }
        catch (NoSuchElementException n){

        }
        return searchBtnStatus;
    }

    public boolean verifyExistingUserDetails_MobileNumber(String mob){
        String mobile_number = mobile_search_disable.getAttribute("value");
        System.out.println(mobile_number);
        return !mobile_number.isEmpty() && mobile_number.equals(mob);
    }

    public boolean verifyExistingUserDetails_EmailID(String emailId){
        String email = email_search_disable.getAttribute("value");
        System.out.println(email);
        return !email.isEmpty() && email.equals(emailId);
    }

    public boolean verifyComplaintAgainst_dropDown_intractable(){
        if(comp_against_dropdown_list.isDisplayed() && comp_against_dropdown_list.isEnabled()){
            comp_against_dropdown_list.click();
            return comp_against_dropdown_input_field.isEnabled();
        }
        else{
            return false;
        }

    }

    public boolean verify_fields_against_insuranceCompany(){
        return ins_company_dropdown_list.isDisplayed() && policy_type_dropdown_list.isDisplayed()
                && comp_type_dropdown_list.isDisplayed() && comp_desc_type_dropdown_list.isDisplayed();

    }

    public boolean verify_fields_against_unregEntity_with_insCompany_involvement(){
        return ins_company_dropdown_list.isDisplayed() && policy_type_dropdown_list.isDisplayed() && comp_type_dropdown_list.isDisplayed() && comp_desc_type_dropdown_list.isDisplayed()
                && unregistered_company_name.isDisplayed() && unregistered_company_address.isDisplayed() && unregistered_company_phone.isDisplayed() && unregistered_company_desc.isDisplayed() && type_of_svc_obtain.isDisplayed() && unregistered_contact_person_name.isDisplayed() && unregistered_contact_person_phone.isDisplayed() && unregistered_contact_person_email.isDisplayed() && unregistered_contact_person_address.isDisplayed();
    }

    public boolean verify_fields_against_unregEntity_without_insCompany_involvement(){
        return !ins_company_dropdown_list.isDisplayed() && !policy_type_dropdown_list.isDisplayed() && !comp_type_dropdown_list.isDisplayed() && !comp_desc_type_dropdown_list.isDisplayed()
                && unregistered_company_name.isDisplayed() && unregistered_company_address.isDisplayed() && unregistered_company_phone.isDisplayed() && unregistered_company_desc.isDisplayed() && type_of_svc_obtain.isDisplayed() && unregistered_contact_person_name.isDisplayed() && unregistered_contact_person_phone.isDisplayed() && unregistered_contact_person_email.isDisplayed() && unregistered_contact_person_address.isDisplayed();
    }

    public boolean verify_fields_against_insurance_intermediary(){
        return intermediary_type_dropdown_list.isDisplayed() && ins_company_dropdown_list.isDisplayed() && policy_type_dropdown_list.isDisplayed() && comp_type_dropdown_list.isDisplayed() && comp_desc_type_dropdown_list.isDisplayed();
    }

    public boolean verify_fields_against_broker_intermediary_type(){
        return broker_dropdown_list.isDisplayed() && broker_license_number.isDisplayed();
    }

    public boolean verify_broker_license_number(String expected_broker_license_number){
        String actual_broker_license_number = broker_license_number.getAttribute("value");
        System.out.println(actual_broker_license_number);
        return (!actual_broker_license_number.isEmpty()) && actual_broker_license_number.equals(expected_broker_license_number);
    }

    public boolean verify_identifier_type_radiobutton(){
        return policyNumber.isEnabled() && proposalNumber.isEnabled() && certificateNumber.isEnabled() && claimNumber.isEnabled();
    }

}
