package Utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.FileOutputStream;
import java.io.IOException;

public class TestNGExcelReport implements ITestListener {
    private Workbook workbook;
    private Sheet sheet;
    private int rowNum;


    public void onStart(ITestContext context) {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Test Results");
        rowNum = 0;

        // Create header row
        Row headerRow = sheet.createRow(rowNum++);
        String[] headers = {"Test Name", "Status"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }
    }

    //@Override
    public void onFinish(ITestContext context) {
        // Write the Excel file
        try (FileOutputStream fileOut = new FileOutputStream("testng_results.xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //@Override
    public void onTestSuccess(ITestResult result) {
        addTestResult(result);
    }

    //@Override
    public void onTestFailure(ITestResult result) {
        addTestResult(result);
    }

    //@Override
    public void onTestSkipped(ITestResult result) {
        addTestResult(result);
    }

    // Add test result to Excel sheet
    private void addTestResult(ITestResult result) {
        Row row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue(result.getName());
        row.createCell(1).setCellValue(getStatusString(result.getStatus()));
        //row.createCell(2).setCellValue(result.getStartMillis());
        //row.createCell(3).setCellValue(result.getEndMillis());
    }

    // Helper method to convert TestNG status to string
    private String getStatusString(int status) {
        switch (status) {
            case ITestResult.SUCCESS: return "PASS";
            case ITestResult.FAILURE: return "FAIL";
            case ITestResult.SKIP: return "SKIP";
            default: return "UNKNOWN";
        }
    }
}
