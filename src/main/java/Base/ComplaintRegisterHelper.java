package Base;

import ObjectRepository.ComplaintRegPage;
import ObjectRepository.HomePage;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ComplaintRegisterHelper extends MainClass{

    public List<String> registerComplaintAgainstInsCompany() throws InterruptedException, IOException {

        String filePath = System.getProperty("user.dir") + "/src/main/resources/TestData/ComplaintRegistration.xlsx";
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("CompRegTestData");

        List<String> ls = new ArrayList<>();

        int columnCount = sheet.getRow(0).getLastCellNum();

        XSSFRow row = sheet.getRow(1);
        for (int c = 0; c < columnCount; c++) {
            XSSFCell cell = row.getCell(c);
            if (cell != null) {
                cell.setCellType(CellType.STRING); // Ensure cell type is String
                ls.add(cell.toString());
            }
        }


        loadURL("RegPageUrl");

        ComplaintRegPage reg = new ComplaintRegPage(driver);
        reg.enterName(ls.get(0));
        reg.enterMobileNumber(ls.get(1));
        reg.emailText();

        //Adding wait of 3 seconds to available OTP window
        Thread.sleep(3000);

        HomePage h = new HomePage(driver);
        h.enterOTP(ls.get(1));

        reg.enterPinCode(ls.get(2));
        reg.scrollToComplaint();

        reg.complaint_Against(ls.get(3));
        reg.insCompanyName(ls.get(4));
        //reg.scrollToComplaint();

        reg.policyType(ls.get(5));
        reg.complaintType(ls.get(6));
        reg.complaintDescType(ls.get(7));

        reg.policyNumberClick();
        reg.enterPolicyNumber(ls.get(8));

        reg.enterCompDesc(ls.get(9));
        reg.registerClick();

        reg.acceptAlertWindow();

        Thread.sleep(3000);
        ls.add(reg.getComplaintNumber());

        System.out.println(ls);

        return ls;

    }
}



