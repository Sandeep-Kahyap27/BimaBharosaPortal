package Utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataReader {

    public static List<String> policyHolderDetails;

    //Abhishek 15/03/23
    @DataProvider(name = "CompAgainstInsComapny")
    public static Object[][] readTestDataCompAgainstInsCompany() throws Exception {

        String filePath = System.getProperty("user.dir") + "/src/main/resources/TestData/ComplaintRegistration.xlsx";

        //create an object of file class
        File file = new File(filePath);

        //To read excel file
        FileInputStream fis = new FileInputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("CompRegisterAgainstInsCompany");

        int rowCount = sheet.getLastRowNum();
        //System.out.println(rowCount);

        int columnCount = sheet.getRow(0).getLastCellNum();
        //System.out.println(columnCount);

        Object[][] testData = new Object[rowCount][columnCount];
        //System.out.println(testData.length);


        //Initialize to store from 0th index in testData
        int i=0;

        //Iterate excel rows and columns to store the data in testData Object
        for (int r = 1; r <= rowCount; r++) {
            Row row = sheet.getRow(r);
            for (int c = 0; c < columnCount; c++) {
                Cell cell = row.getCell(c);
                if (cell != null) {
                    cell.setCellType(CellType.STRING); // Ensure cell type is String
                    testData[i][c] = cell.getStringCellValue();
                }
            }
            i++;
        }

        workbook.close();
        fis.close();

        return testData;
    }

    @DataProvider(name = "CompAgainstUnRegEntity")
    public static Object[][] readTestDataCompAgainstUnRegEntity() throws Exception {

        String filePath = System.getProperty("user.dir") + "/src/main/resources/TestData/ComplaintRegistration.xlsx";

        //create an object of file class
        File file = new File(filePath);

        //To read excel file
        FileInputStream fis = new FileInputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("CompRegisterAgainstUnRegEntity");

        int rowCount = sheet.getLastRowNum();
        //System.out.println(rowCount);

        int columnCount = sheet.getRow(0).getLastCellNum();
        //System.out.println(columnCount);

        Object[][] testData = new Object[rowCount][columnCount];
        //System.out.println(testData.length);


        //Initialize to store from 0th index in testData
        int i=0;

        //Iterate excel rows and columns to store the data in testData Object
        for (int r = 1; r <= rowCount; r++) {
            Row row = sheet.getRow(r);
            for (int c = 0; c < columnCount; c++) {
                Cell cell = row.getCell(c);
                if (cell != null) {
                    cell.setCellType(CellType.STRING); // Ensure cell type is String
                    testData[i][c] = cell.getStringCellValue();
                }
            }
            i++;
        }

        workbook.close();
        fis.close();

        return testData;
    }

    @DataProvider(name = "CompAgainstIntermediary")
    public static Object[][] readTestDataCompAgainstIntermediary() throws Exception {

        String filePath = System.getProperty("user.dir") + "/src/main/resources/TestData/ComplaintRegistration.xlsx";

        //create an object of file class
        File file = new File(filePath);

        //To read excel file
        FileInputStream fis = new FileInputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("CompRegisterAgainstIntermediary");

        int rowCount = sheet.getLastRowNum();
        //System.out.println(rowCount);

        int columnCount = sheet.getRow(0).getLastCellNum();
        //System.out.println(columnCount);

        Object[][] testData = new Object[rowCount][columnCount];
        //System.out.println(testData.length);

        //Initialize to store from 0th index in testData
        int i=0;

        //Iterate excel rows and columns to store the data in testData Object
        for (int r = 1; r <= rowCount; r++) {
            Row row = sheet.getRow(r);
            for (int c = 0; c < columnCount; c++) {
                Cell cell = row.getCell(c);
                if (cell != null) {
                    cell.setCellType(CellType.STRING); // Ensure cell type is String
                    testData[i][c] = cell.getStringCellValue();
                }
            }
            i++;
        }

        workbook.close();
        fis.close();

        return testData;
    }

    @DataProvider(name = "CompAgainstUnRegWithoutInsCompany")
    public static Object[][] readTestDataCompAgainstUnRegWithoutInsCompany() throws Exception {

        String filePath = System.getProperty("user.dir") + "/src/main/resources/TestData/ComplaintRegistration.xlsx";

        //create an object of file class
        File file = new File(filePath);

        //To read excel file
        FileInputStream fis = new FileInputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("CompRegAgainstUnRegNoInsComp");

        int rowCount = sheet.getLastRowNum();
        //System.out.println(rowCount);

        int columnCount = sheet.getRow(0).getLastCellNum();
        //System.out.println(columnCount);

        Object[][] testData = new Object[rowCount][columnCount];
        //System.out.println(testData.length);

        //Initialize to store from 0th index in testData
        int i=0;

        //Iterate excel rows and columns to store the data in testData Object
        for (int r = 1; r <= rowCount; r++) {
            Row row = sheet.getRow(r);
            for (int c = 0; c < columnCount; c++) {
                Cell cell = row.getCell(c);
                if (cell != null) {
                    cell.setCellType(CellType.STRING); // Ensure cell type is String
                    testData[i][c] = cell.getStringCellValue();
                }
            }
            i++;
        }

        workbook.close();
        fis.close();

        return testData;
    }

    @DataProvider(name = "CompAgainstInsCompanyData")
    public static Object[][] readTestDataCompAgainstInsCompany_Negative() throws Exception {

        String filePath = System.getProperty("user.dir") + "/src/main/resources/TestData/ComplaintRegistration.xlsx";

        //create an object of file class
        File file = new File(filePath);

        //To read excel file
        FileInputStream fis = new FileInputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("CompRegTestData");

        int rowCount = sheet.getLastRowNum();
        //System.out.println(rowCount);

        int columnCount = sheet.getRow(0).getLastCellNum();
        //System.out.println(columnCount);

        Object[][] testData = new Object[rowCount][columnCount];
        //System.out.println(testData.length);

        //Initialize to store from 0th index in testData
        int i=0;

        //Iterate excel rows and columns to store the data in testData Object
        for (int r = 1; r <= rowCount; r++) {
            Row row = sheet.getRow(r);
            for (int c = 0; c < columnCount; c++) {
                Cell cell = row.getCell(c);
                if (cell != null) {
                    cell.setCellType(CellType.STRING); // Ensure cell type is String
                    testData[i][c] = cell.getStringCellValue();
                }
            }
            i++;
        }

        workbook.close();
        fis.close();

        return testData;
    }

    @DataProvider(name = "NewComplaintRegister")
    public static Object[][] readTestDataNewCompRegister() throws Exception {

        String filePath = System.getProperty("user.dir") + "/src/main/resources/TestData/ComplaintRegistration.xlsx";

        //create an object of file class
        File file = new File(filePath);

        //To read excel file
        FileInputStream fis = new FileInputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("NewCompRegister");

        int rowCount = sheet.getLastRowNum();
        //System.out.println(rowCount);

        int columnCount = sheet.getRow(0).getLastCellNum();
        //System.out.println(columnCount);

        Object[][] testData = new Object[rowCount][columnCount];
        //System.out.println(testData.length);

        //Initialize to store from 0th index in testData
        int i=0;

        //Iterate excel rows and columns to store the data in testData Object
        for (int r = 1; r <= rowCount; r++) {
            Row row = sheet.getRow(r);
            for (int c = 0; c < columnCount; c++) {
                Cell cell = row.getCell(c);
                if (cell != null) {
                    cell.setCellType(CellType.STRING); // Ensure cell type is String
                    testData[i][c] = cell.getStringCellValue();
                }
            }
            i++;
        }

        workbook.close();
        fis.close();

        return testData;
    }

    @DataProvider(name = "Call_Center_Register")
    public Object[][] readCallCenterRegisterByMobileNumber() throws IOException {

        String filePath = System.getProperty("user.dir")+"/src/main/resources/TestData/Call_Center_Registration.xlsx";
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("CallCenterReg_MobileNumber");

        int rowCount = sheet.getLastRowNum();
        int columnCount = sheet.getRow(0).getLastCellNum();

        Object[][] testData = new Object[rowCount][columnCount];

        //Initialize to store from 0th index in testData
        int i=0;

        //Iterate excel rows and columns to store the data in testData Object
        for (int r = 1; r <= rowCount; r++) {
            Row row = sheet.getRow(r);
            for (int c = 0; c < columnCount; c++) {
                Cell cell = row.getCell(c);
                if (cell != null) {
                    cell.setCellType(CellType.STRING); // Ensure cell type is String
                    testData[i][c] = cell.getStringCellValue();
                }
            }
            i++;
        }

        workbook.close();
        fis.close();

        return testData;

    }


    @DataProvider(name = "Call_Center_Register_EmailId")
    public Object[][] readCallCenterRegisterByEmailId() throws IOException {

        String filePath = System.getProperty("user.dir")+"/src/main/resources/TestData/Call_Center_Registration.xlsx";
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("CallCenterReg_EmailId");

        int rowCount = sheet.getLastRowNum();
        int columnCount = sheet.getRow(0).getLastCellNum();

        Object[][] testData = new Object[rowCount][columnCount];

        //Initialize to store from 0th index in testData
        int i=0;

        //Iterate excel rows and columns to store the data in testData Object
        for (int r = 1; r <= rowCount; r++) {
            Row row = sheet.getRow(r);
            for (int c = 0; c < columnCount; c++) {
                Cell cell = row.getCell(c);
                if (cell != null) {
                    cell.setCellType(CellType.STRING); // Ensure cell type is String
                    testData[i][c] = cell.getStringCellValue();
                }
            }
            i++;
        }

        workbook.close();
        fis.close();

        return testData;

    }

    @DataProvider(name = "Call_Center_UnregEntity")
    public Object[][] readCallCenterRegister_Against_UnregEntity() throws IOException {

        String filePath = System.getProperty("user.dir")+"/src/main/resources/TestData/Call_Center_Registration.xlsx";
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("CallCenterRegister_UnregEntity");

        int rowCount = sheet.getLastRowNum();
        int columnCount = sheet.getRow(0).getLastCellNum();

        Object[][] testData = new Object[rowCount][columnCount];

        //Initialize to store from 0th index in testData
        int i=0;

        //Iterate excel rows and columns to store the data in testData Object
        for (int r = 1; r <= rowCount; r++) {
            Row row = sheet.getRow(r);
            for (int c = 0; c < columnCount; c++) {
                Cell cell = row.getCell(c);
                if (cell != null) {
                    cell.setCellType(CellType.STRING); // Ensure cell type is String
                    testData[i][c] = cell.getStringCellValue();
                }
            }
            i++;
        }

        workbook.close();
        fis.close();

        return testData;

    }

    @DataProvider(name = "Call_Center_Intermediary")
    public Object[][] readCallCenterRegister_Against_InsIntermediary() throws IOException {

        String filePath = System.getProperty("user.dir")+"/src/main/resources/TestData/Call_Center_Registration.xlsx";
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("CallCenterRegister_Intermediary");

        int rowCount = sheet.getLastRowNum();
        int columnCount = sheet.getRow(0).getLastCellNum();

        Object[][] testData = new Object[rowCount][columnCount];

        //Initialize to store from 0th index in testData
        int i=0;

        //Iterate excel rows and columns to store the data in testData Object
        for (int r = 1; r <= rowCount; r++) {
            Row row = sheet.getRow(r);
            for (int c = 0; c < columnCount; c++) {
                Cell cell = row.getCell(c);
                if (cell != null) {
                    cell.setCellType(CellType.STRING); // Ensure cell type is String
                    testData[i][c] = cell.getStringCellValue();
                }
            }
            i++;
        }

        workbook.close();
        fis.close();

        return testData;

    }

    @DataProvider(name = "Call_Center_NewUser")
    public Object[][] readCallCenterRegister_NewUser() throws IOException {

        String filePath = System.getProperty("user.dir")+"/src/main/resources/TestData/Call_Center_Registration.xlsx";
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("CallCenterRegister_NewUser");

        int rowCount = sheet.getLastRowNum();
        int columnCount = sheet.getRow(0).getLastCellNum();

        Object[][] testData = new Object[rowCount][columnCount];

        //Initialize to store from 0th index in testData
        int i=0;

        //Iterate excel rows and columns to store the data in testData Object
        for (int r = 1; r <= rowCount; r++) {
            Row row = sheet.getRow(r);
            for (int c = 0; c < columnCount; c++) {
                Cell cell = row.getCell(c);
                if (cell != null) {
                    cell.setCellType(CellType.STRING); // Ensure cell type is String
                    testData[i][c] = cell.getStringCellValue();
                }
            }
            i++;
        }

        workbook.close();
        fis.close();

        return testData;

    }


    @DataProvider(name = "Call_Center_Login")
    public static Object[][] readCallCenterCredentials() throws Exception {

        String filePath = System.getProperty("user.dir") + "/src/main/resources/TestData/Call_Center_Registration.xlsx";
        //create an object of file class
        File file = new File(filePath);

        //To read excel file
        FileInputStream fis = new FileInputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Call_Center");

        int rowCount = sheet.getLastRowNum();
        //System.out.println(rowCount);

        int columnCount = sheet.getRow(0).getLastCellNum();
        //System.out.println(columnCount);

        Object[][] testData = new Object[rowCount][columnCount];
        //System.out.println(testData.length);


        //Initialize to store from 0th index in testData
        int i = 0;

        //Iterate excel rows and columns to store the data in testData Object
        for (int r = 1; r <= rowCount; r++) {
            Row row = sheet.getRow(r);
            for (int c = 0; c < columnCount; c++) {
                Cell cell = row.getCell(c);
                if (cell != null) {
                    cell.setCellType(CellType.STRING); // Ensure cell type is String
                    testData[i][c] = cell.getStringCellValue();
                }
            }
            i++;
        }

        workbook.close();
        fis.close();

        return testData;
    }

}
