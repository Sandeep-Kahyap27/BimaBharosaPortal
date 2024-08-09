package Base;

import Constants.ExcelFileConstants;
import ObjectRepository.AdvanceSearchPage;
import ObjectRepository.SidebarHeader;
import ObjectRepository.UploadComplaintsPage;
import ObjectRepository.ViewEditComplaintDetailsPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ComplaintUploadUpdateHelper extends MainClass {
    public String entityRefNo;
    public String policyNo;
    public String complaintNo;

    public boolean uploadComplaint_RegisterComplaint() throws IOException, InterruptedException {
        //Setup data to excel file
        ExcelHelper ex = new ExcelHelper();
        entityRefNo = generateEntityReferenceNo();
        policyNo = generatePolicyNumber();
        ex.setFirstName(ExcelFileConstants.NEW_NONLIFE, getRandomName(), 4);
        ex.setMobileNumber(ExcelFileConstants.NEW_NONLIFE, generateRandomMobileNumber(), 4);
        ex.setEntityCompRefNumber(ExcelFileConstants.NEW_NONLIFE, entityRefNo, 4);
        ex.setComplaintReceiptDate(ExcelFileConstants.NEW_NONLIFE, getCurrentDate(), 4);
        ex.setComplaintDate(ExcelFileConstants.NEW_NONLIFE, getCurrentDate(), 4);
        ex.setPolicyProposalCertClaimNum(ExcelFileConstants.NEW_NONLIFE, policyNo, 4);

        //Upload complaint through excel file
        UploadComplaintsPage up = new UploadComplaintsPage(driver);
        up.uploadExcelFile(System.getProperty("user.dir") + "/src/main/resources/TestData/" + ExcelFileConstants.NEW_NONLIFE);
        up.clkUploadBtn();

        boolean status = up.verifySuccessMsg();
        complaintNo = getComplaintNo(entityRefNo);

        return status;
    }


    public boolean uploadComplaint_UpdateNewToAck() throws IOException, InterruptedException {
        if (uploadComplaint_RegisterComplaint()) {
            //Search Complaint in Advance Search.
            SidebarHeader sb = new SidebarHeader(driver);
            sb.clickMenu();
            Thread.sleep(1000);
            sb.clickcomplaintSidebarTab();
            Thread.sleep(1000);
            sb.clickviewEditcomplaintSidebarTab();
            AdvanceSearchPage asp = new AdvanceSearchPage(driver);
            asp.enterEntityCompRefNumber(entityRefNo);
            asp.clickSearchBtn();

            //Navigate to upload complaint sidebar
            SidebarHeader sbNewToAck = new SidebarHeader(driver);
            sbNewToAck.clickMenu();
            Thread.sleep(1000);
            sbNewToAck.clickOnComplaints();
            Thread.sleep(1000);
            sbNewToAck.clickUploadComplaintsSidebarTab();
            //Setup data to excel file
            ExcelHelper exNewToAck = new ExcelHelper();
            exNewToAck.setEntityCompRefNumber(ExcelFileConstants.ACKNOWLEDGE, entityRefNo, 4);
            exNewToAck.setIRDATokenNumber(ExcelFileConstants.ACKNOWLEDGE, complaintNo, 4);
            exNewToAck.setStatusChangeDate(ExcelFileConstants.ACKNOWLEDGE, getCurrentDate(), 4);
            exNewToAck.setPolicyProposalCertClaimNum(ExcelFileConstants.ACKNOWLEDGE, policyNo, 4);

            //Upload complaint through excel file
            UploadComplaintsPage upNewToAck = new UploadComplaintsPage(driver);
            upNewToAck.uploadExcelFile(System.getProperty("user.dir") + "/src/main/resources/TestData/" + ExcelFileConstants.ACKNOWLEDGE);
            upNewToAck.clkUploadBtn();
            return upNewToAck.verifySuccessMsg();
        }
        return false;

    }

    public boolean uploadComplaint_UpdateAckToPending() throws IOException, InterruptedException {
        if (uploadComplaint_UpdateNewToAck()) {
            //Setup data to Pending excel file
            ExcelHelper exAckToPending = new ExcelHelper();
            exAckToPending.setEntityCompRefNumber(ExcelFileConstants.PENDING, entityRefNo, 4);
            exAckToPending.setIRDATokenNumber(ExcelFileConstants.PENDING, complaintNo, 4);
            exAckToPending.setStatusChangeDate(ExcelFileConstants.PENDING, getCurrentDate(), 4);
            exAckToPending.setPolicyProposalCertClaimNum(ExcelFileConstants.PENDING, policyNo, 4);
            //Upload complaint through Pending excel file
            UploadComplaintsPage upAckToPending = new UploadComplaintsPage(driver);
            upAckToPending.uploadExcelFile(System.getProperty("user.dir") + "/src/main/resources/TestData/" + ExcelFileConstants.PENDING);
            upAckToPending.clkUploadBtn();
            return upAckToPending.verifySuccessMsg();
        }
        return false;
    }

    public boolean uploadComplaint_UpdatePendingToAttendedTo() throws IOException, InterruptedException {
        if (uploadComplaint_UpdateAckToPending()) {
            //Setup data to Pending excel file
            ExcelHelper exPendingToAttendeTo = new ExcelHelper();
            exPendingToAttendeTo.setEntityCompRefNumber(ExcelFileConstants.ATTENDED_TO, entityRefNo, 4);
            exPendingToAttendeTo.setIRDATokenNumber(ExcelFileConstants.ATTENDED_TO, complaintNo, 4);
            exPendingToAttendeTo.setStatusChangeDate(ExcelFileConstants.ATTENDED_TO, getCurrentDate(), 4);
            exPendingToAttendeTo.setPolicyProposalCertClaimNum(ExcelFileConstants.ATTENDED_TO, policyNo, 4);
            exPendingToAttendeTo.setTypeOfDisposal(ExcelFileConstants.ATTENDED_TO, "1", 4);
            //Upload complaint through Pending excel file
            UploadComplaintsPage upPendingToAttendeTo = new UploadComplaintsPage(driver);
            upPendingToAttendeTo.uploadExcelFile(System.getProperty("user.dir") + "/src/main/resources/TestData/" + ExcelFileConstants.ATTENDED_TO);
            upPendingToAttendeTo.clkUploadBtn();
            return upPendingToAttendeTo.verifySuccessMsg();
        }
        return false;
    }

    public boolean uploadComplaint_UpdateAttendedToClose() throws IOException, InterruptedException {
        if (uploadComplaint_UpdatePendingToAttendedTo()) {
            //Setup data to AttendedTo excel file
            ExcelHelper exAttendedToClose = new ExcelHelper();
            exAttendedToClose.setEntityCompRefNumber(ExcelFileConstants.CLOSED, entityRefNo, 4);
            exAttendedToClose.setIRDATokenNumber(ExcelFileConstants.CLOSED, complaintNo, 4);
            exAttendedToClose.setStatusChangeDate(ExcelFileConstants.CLOSED, getCurrentDate(), 4);
            exAttendedToClose.setPolicyProposalCertClaimNum(ExcelFileConstants.CLOSED, policyNo, 4);
            //Upload complaint through Pending excel file
            UploadComplaintsPage upAttendeToClose = new UploadComplaintsPage(driver);
            upAttendeToClose.uploadExcelFile(System.getProperty("user.dir") + "/src/main/resources/TestData/" + ExcelFileConstants.CLOSED);
            upAttendeToClose.clkUploadBtn();
            return upAttendeToClose.getMessage().equals("Closure request IN not allowed before 60 days");
        }
        return false;
    }

    public boolean uploadComplaint_UpdateAttendedToEscalated() throws IOException, InterruptedException {
        if (uploadComplaint_UpdatePendingToAttendedTo()) {
            //Setup data to Escalated excel file
            ExcelHelper exAttendeToEsc = new ExcelHelper();
            exAttendeToEsc.setEntityCompRefNumber(ExcelFileConstants.ESCALATED, entityRefNo, 4);
            exAttendeToEsc.setIRDATokenNumber(ExcelFileConstants.ESCALATED, complaintNo, 4);
            exAttendeToEsc.setStatusChangeDate(ExcelFileConstants.ESCALATED, getCurrentDate(), 4);
            exAttendeToEsc.setPolicyProposalCertClaimNum(ExcelFileConstants.ESCALATED, policyNo, 4);
            //Upload complaint through Escalated excel file
            UploadComplaintsPage upAttendeToEsc = new UploadComplaintsPage(driver);
            upAttendeToEsc.uploadExcelFile(System.getProperty("user.dir") + "/src/main/resources/TestData/" + ExcelFileConstants.ESCALATED);
            upAttendeToEsc.clkUploadBtn();
            return upAttendeToEsc.verifySuccessMsg();
        }
        return false;
    }


    public boolean uploadComplaint_UpdateEscalatedToReopen() throws IOException, InterruptedException {
        if (uploadComplaint_UpdateAttendedToEscalated()) {
            //Setup data to Escalated excel file
            ExcelHelper exEscToReopen = new ExcelHelper();
            exEscToReopen.setEntityCompRefNumber(ExcelFileConstants.RE_OPEN, entityRefNo, 4);
            exEscToReopen.setIRDATokenNumber(ExcelFileConstants.RE_OPEN, complaintNo, 4);
            exEscToReopen.setStatusChangeDate(ExcelFileConstants.RE_OPEN, getCurrentDate(), 4);
            exEscToReopen.setPolicyProposalCertClaimNum(ExcelFileConstants.RE_OPEN, policyNo, 4);
            //Upload complaint through Escalated excel file
            UploadComplaintsPage upEscToReopen = new UploadComplaintsPage(driver);
            upEscToReopen.uploadExcelFile(System.getProperty("user.dir") + "/src/main/resources/TestData/" + ExcelFileConstants.RE_OPEN);
            upEscToReopen.clkUploadBtn();
            return upEscToReopen.verifySuccessMsg();
        }
        return false;
    }

    public boolean uploadComplaint_UpdateReopenToAttendedTo() throws IOException, InterruptedException {
        if (uploadComplaint_UpdateEscalatedToReopen()) {
            //Setup data to Escalated excel file
            ExcelHelper exReopenToAttendedTo = new ExcelHelper();
            exReopenToAttendedTo.setEntityCompRefNumber(ExcelFileConstants.ATTENDED_TO_AFTER_ESCALATION, entityRefNo, 4);
            exReopenToAttendedTo.setIRDATokenNumber(ExcelFileConstants.ATTENDED_TO_AFTER_ESCALATION, complaintNo, 4);
            exReopenToAttendedTo.setStatusChangeDate(ExcelFileConstants.ATTENDED_TO_AFTER_ESCALATION, getCurrentDate(), 4);
            exReopenToAttendedTo.setPolicyProposalCertClaimNum(ExcelFileConstants.ATTENDED_TO_AFTER_ESCALATION, policyNo, 4);
            //Upload complaint through Escalated excel file
            UploadComplaintsPage upReopenToAttendedTo = new UploadComplaintsPage(driver);
            upReopenToAttendedTo.uploadExcelFile(System.getProperty("user.dir") + "/src/main/resources/TestData/" + ExcelFileConstants.ATTENDED_TO_AFTER_ESCALATION);
            upReopenToAttendedTo.clkUploadBtn();
            return upReopenToAttendedTo.verifySuccessMsg();
        }
        return false;
    }

    public boolean uploadComplaint_UpdateAttendedToClosureRequest() throws IOException, InterruptedException {
        if (uploadComplaint_UpdateReopenToAttendedTo()) {
            //Setup data to Escalated excel file
            ExcelHelper exAttendedToClsRequest = new ExcelHelper();
            exAttendedToClsRequest.setEntityCompRefNumber(ExcelFileConstants.CLOSED_AFTER_ESCALATION, entityRefNo, 4);
            exAttendedToClsRequest.setIRDATokenNumber(ExcelFileConstants.CLOSED_AFTER_ESCALATION, complaintNo, 4);
            exAttendedToClsRequest.setStatusChangeDate(ExcelFileConstants.CLOSED_AFTER_ESCALATION, getCurrentDate(), 4);
            exAttendedToClsRequest.setPolicyProposalCertClaimNum(ExcelFileConstants.CLOSED_AFTER_ESCALATION, policyNo, 4);
            exAttendedToClsRequest.setReqIRDAIForClosure(ExcelFileConstants.CLOSED_AFTER_ESCALATION, "1", 4);
            exAttendedToClsRequest.setClaimPaymentDate(ExcelFileConstants.CLOSED_AFTER_ESCALATION, getCurrentDate(), 4);
            exAttendedToClsRequest.setClaimPaymentChequeDate(ExcelFileConstants.CLOSED_AFTER_ESCALATION, getCurrentDate(), 4);
            exAttendedToClsRequest.setInsResolutionLetterDate(ExcelFileConstants.CLOSED_AFTER_ESCALATION, getCurrentDate(), 4);
            exAttendedToClsRequest.setDateOfHonoringSvc(ExcelFileConstants.CLOSED_AFTER_ESCALATION, getCurrentDate(), 4);

            //Upload complaint through Escalated excel file
            UploadComplaintsPage upAttendedToClsRequest = new UploadComplaintsPage(driver);
            upAttendedToClsRequest.uploadExcelFile(System.getProperty("user.dir") + "/src/main/resources/TestData/" + ExcelFileConstants.CLOSED_AFTER_ESCALATION);
            upAttendedToClsRequest.clkUploadBtn();
            return upAttendedToClsRequest.verifySuccessMsg();
        }
        return false;
    }

    public String getComplaintNo(String entity_ref_number) throws InterruptedException {
        //Search complaint
        SidebarHeader sb = new SidebarHeader(driver);
        sb.clickOnComplaints();
        Thread.sleep(1000);
        sb.clickviewEditcomplaintSidebarTab();

        AdvanceSearchPage asp = new AdvanceSearchPage(driver);
        asp.enterEntityCompRefNumber(entity_ref_number);
        asp.clickSearchBtn();

        //get complaint no
        ViewEditComplaintDetailsPage vcd = new ViewEditComplaintDetailsPage(driver);
        vcd.click_View_Edit_Complaints_Checkbox();
        Thread.sleep(2000);
        complaintNo = vcd.visibilityComplaintNumberOnComplaintDetailsPage();
        scrollToComplaint();
        vcd.click_Cancel_Button();

        return complaintNo;
    }
}
