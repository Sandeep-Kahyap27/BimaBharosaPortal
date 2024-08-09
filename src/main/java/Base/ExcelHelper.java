package Base;

import Utilities.DataWriter;

import java.io.IOException;

//Author : Abhishek
public class ExcelHelper {


    public void setFirstName(String excelFileName, String firstName, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, firstName, rowNum, 0);
    }

    public void setDistrictId(String excelFileName, String district_id, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, district_id, rowNum, 1);
    }

    public void setStateId(String excelFileName, String state_id, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, state_id, rowNum, 2);
    }

    public void setEmailId(String excelFileName, String emailId, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, emailId, rowNum, 3);
    }

    public void setMobileNumber(String excelFileName, String mob, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, mob, rowNum, 4);
    }

    public void setIsSeniorCitizen(String excelFileName, String senior_citizen, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, senior_citizen, rowNum, 5);
    }

    public void setUserType(String excelFileName, String userType, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, userType, rowNum, 6);
    }

    public void setOrganizationName(String excelFileName, String organization_name, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, organization_name, rowNum, 7);
    }

    public void setIRDATokenNumber(String excelFileName, String irda_token_number, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, irda_token_number, rowNum, 8);
    }

    public void setEntityCompRefNumber(String excelFileName, String entity_ref_number, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, entity_ref_number, rowNum, 9);
    }

    public void setComplaintStatusId(String excelFileName, String complaint_status_id, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, complaint_status_id, rowNum, 10);
    }

    public void setAckOfficerName(String excelFileName, String ack_officer_name, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, ack_officer_name, rowNum, 11);
    }

    public void setAckOfficerDesignation(String excelFileName, String officer_designation, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, officer_designation, rowNum, 12);
    }

    public void setStatusChangeDate(String excelFileName, String status_change_date, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, status_change_date, rowNum, 13);
    }

    public void setInsuranceTypeId(String excelFileName, String ins_type_id, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, ins_type_id, rowNum, 14);
    }

    public void setPolicyTypeId(String excelFileName, String policy_type_id, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, policy_type_id, rowNum, 15);
    }

    public void setComplaintTypeId(String excelFileName, String complaint_type_id, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, complaint_type_id, rowNum, 16);
    }

    public void setComplaintDescTypeId(String excelFileName, String complaint_desc_type_id, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, complaint_desc_type_id, rowNum, 17);
    }

    public void setComplaintDetails(String excelFileName, String complaint_details, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, complaint_details, rowNum, 18);
    }

    public void setComplaintAgainstTypeId(String excelFileName, int complaint_against_type_id, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, complaint_against_type_id, rowNum, 19);
    }

    public void setIntermediaryName(String excelFileName, String intermediary_name, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, intermediary_name, rowNum, 20);
    }

    public void setIntermediaryLicenseNumber(String excelFileName, String intermediary_license_number, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, intermediary_license_number, rowNum, 21);
    }

    public void setBrokerLicenseNumber(String excelFileName, String broker_license_number, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, broker_license_number, rowNum, 22);
    }

    public void setAddressToInsurer(String excelFileName, String address_to_insurer, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, address_to_insurer, rowNum, 23);
    }

    public void setBranchCode(String excelFileName, String branch_code, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, branch_code, rowNum, 24);
    }

    public void setSourceOfComplaint(String excelFileName, String source_of_complaint, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, source_of_complaint, rowNum, 25);
    }

    public void setComplaintDate(String excelFileName, String complaint_date, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, complaint_date, rowNum, 26);
    }

    public void setComplaintReceiptDate(String excelFileName, String complaint_receipt_date, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, complaint_receipt_date, rowNum, 27);
    }

    public void setPolicyNumber(String excelFileName, String policy_number, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, policy_number, rowNum, 28);
    }

    public void setProposalCoverNoteNumber(String excelFileName, String proposal_cover_note_number, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, proposal_cover_note_number, rowNum, 29);
    }

    public void setCertificateNumber(String excelFileName, String certificate_number, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, certificate_number, rowNum, 30);
    }

    public void setPremiumPaymentRefNumber(String excelFileName, String premium_num, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, premium_num, rowNum, 31);
    }

    public void setPolicyHolderOrClaimantName(String excelFileName, String name, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, name, rowNum, 32);
    }

    public void setClaimNumber(String excelFileName, String claim_number, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, claim_number, rowNum, 33);
    }

    public void setClaimIntimationDate(String excelFileName, String claim_intimation_date, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, claim_intimation_date, rowNum, 34);
    }

    public void setClaimRequestedAmt(String excelFileName, String claim_req_amt, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, claim_req_amt, rowNum, 35);
    }

    public void setClaimReceivedAmt(String excelFileName, String claim_received_amt, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, claim_received_amt, rowNum, 36);
    }

    public void setClaimPaymentDate(String excelFileName, String claim_payment_date, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, claim_payment_date, rowNum, 37);
    }

    public void setClaimPaymentChequeDate(String excelFileName, String claim_payment_cheque_date, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, claim_payment_cheque_date, rowNum, 38);
    }

    public void setClaimPaymentChequeNumber(String excelFileName, String claim_payment_cheque_num, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, claim_payment_cheque_num, rowNum, 39);
    }

    public void setDateOfHonoringSvc(String excelFileName, String date_of_honor_svc, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, date_of_honor_svc, rowNum, 40);
    }

    public void setInsResolutionLetterDate(String excelFileName, String ins_res_letter_date, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, ins_res_letter_date, rowNum, 41);
    }

    public void setTypeOfDisposal(String excelFileName, String type_of_disposal, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, type_of_disposal, rowNum, 42);
    }

    public void setClaimClsrAdditionalInfo(String excelFileName, String claim_clsr_add_info, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, claim_clsr_add_info, rowNum, 43);
    }

    public void setOtherClsrAdditionInfo(String excelFileName, String other_clsr_add_info, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, other_clsr_add_info, rowNum, 44);
    }

    public void setIsComplainantInformed(String excelFileName, String Is_complainant_informed, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, Is_complainant_informed, rowNum, 45);
    }

    public void setRemarks(String excelFileName, String remarks, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, remarks, rowNum, 46);
    }

    public void setOriginalIRDATokenNum(String excelFileName, String original_irda_token_num, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, original_irda_token_num, rowNum, 47);
    }

    public void setIsComplaintChangeByInsurer(String excelFileName, String Is_complaint_chg_by_ins, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, Is_complaint_chg_by_ins, rowNum, 48);
    }

    public void setInsurerPolicyNumber(String excelFileName, String ins_policy_num, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, ins_policy_num, rowNum, 49);
    }

    public void setInsurerPolicyTypeId(String excelFileName, String ins_policy_type_id, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, ins_policy_type_id, rowNum, 50);
    }

    public void setInsurerComplaintTypeId(String excelFileName, String ins_complaint_type_id, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, ins_complaint_type_id, rowNum, 51);
    }

    public void setInsurerComplaintDescTypeId(String excelFileName, String ins_complaint_desc_type_id, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, ins_complaint_desc_type_id, rowNum, 52);
    }

    public void setFinalResolutionRemark(String excelFileName, String final_resolution_remark, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, final_resolution_remark, rowNum, 53);
    }

    public void setFinalResolutionDate(String excelFileName, String final_resolution_date, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, final_resolution_date, rowNum, 54);
    }

    public void setTATCrossedRemark(String excelFileName, String tat_crossed_remark, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, tat_crossed_remark, rowNum, 55);
    }

    public void setResolutionSatisfactionLevel(String excelFileName, String res_satisfaction_level, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, res_satisfaction_level, rowNum, 56);
    }

    public void setResolutionFeedbackRemarks(String excelFileName, String res_feedback_remark, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, res_feedback_remark, rowNum, 57);
    }

    public void setEscalatedRemark(String excelFileName, String esc_remark, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, esc_remark, rowNum, 58);
    }

    public void setEscalatedDate(String excelFileName, String esc_date, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, esc_date, rowNum, 59);
    }

    public void setReqIRDAIForClosure(String excelFileName, String req_irdai_closure, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, req_irdai_closure, rowNum, 60);
    }

    public void setPolicyProposalCertClaimNum(String excelFileName, String policy_proposal_cert_claim_num, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, policy_proposal_cert_claim_num, rowNum, 61);
    }

    public void setIdentifierType(String excelFileName, String identifier_type, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, identifier_type, rowNum, 62);
    }

    public void setIsDoesNotPertain(String excelFileName, String is_does_not_pertain, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, is_does_not_pertain, rowNum, 63);
    }

    public void setIsDoesNotPertainRemarks(String excelFileName, String is_does_not_pertain_remarks, int rowNum) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, is_does_not_pertain_remarks, rowNum, 64);
    }


    //Added more methods to setup excel data
    public void setEntityShortCode(String excelFileName, String code) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, code, 1,0);
    }

    public void setNumberOfRecords(String excelFileName, int no_of_records) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, no_of_records, 1,1);
    }

    public void setBeginSeries(String excelFileName, int start) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, start, 1,2);
    }

    public void setEndSeries(String excelFileName, int end) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, end, 1,3);
    }

    public void setGeneratedDateTime(String excelFileName, String date_time) throws IOException {
        DataWriter.writeDataToExcel(excelFileName, date_time, 1,4);
    }


}
