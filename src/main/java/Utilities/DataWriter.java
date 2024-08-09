package Utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataWriter {

    public static void writeDataToExcel(String excelFileName, String data, int rowNum, int col) throws IOException {
        try {
            String filePath = System.getProperty("user.dir") + "/src/main/resources/TestData/" + excelFileName;

            //create an object of fileInputStream class
            FileInputStream fis = new FileInputStream(filePath);

            //Create workbook object
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Initialize variable to write records from 5th row

            //Retrieving specified row
            Row row = sheet.getRow(rowNum);

            //Writing record into cell
            Cell cell = row.getCell(col);
            cell.setCellValue(data);

            // Write the workbook to a file
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
            workbook.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void writeDataToExcel(String excelFileName, int data, int rowNum, int col) throws IOException {
        try {
            String filePath = System.getProperty("user.dir") + "/src/main/resources/TestData/" + excelFileName;

            //create an object of fileInputStream class
            FileInputStream fis = new FileInputStream(filePath);

            //Create workbook object
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Initialize variable to write records from 5th row

            //Retrieving specified row
            Row row = sheet.getRow(rowNum);

            //Writing record into cell
            Cell cell = row.getCell(col);
            cell.setCellValue(data);

            // Write the workbook to a file
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
            workbook.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




