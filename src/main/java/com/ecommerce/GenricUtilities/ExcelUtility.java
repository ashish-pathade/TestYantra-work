package com.ecommerce.GenricUtilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelUtility {


    public String getSingleDataFromExcel(String sheetName, int rowNum, int cellNum) throws IOException {
        FileInputStream fi = new FileInputStream(IPathConstants.ExcelPath);
        Workbook workbook=WorkbookFactory.create(fi);
        DataFormatter dataFormatter=new DataFormatter();
        return dataFormatter.formatCellValue(workbook.getSheet(sheetName).getRow(rowNum).getCell(cellNum));
    }
    public void writeDataIntoExcel(String sheetName,int row,int cell,String data) throws IOException {
        FileInputStream fi = new FileInputStream(IPathConstants.ExcelPath);
        Workbook workbook=WorkbookFactory.create(fi);
        workbook.getSheet(sheetName).createRow(row).createCell(cell).setCellValue(data);
        FileOutputStream fos=new FileOutputStream(IPathConstants.ExcelPath);
        workbook.write(fos);
        workbook.close();
    }
    public Map<String,String> getMultipleDataFromExcel(String sheetName , int keyCellNum , int valueCellNum) throws IOException {
        FileInputStream fi = new FileInputStream(IPathConstants.ExcelPath);
        Workbook workbook=WorkbookFactory.create(fi);
        Sheet sheet= workbook.getSheet(sheetName);
        DataFormatter dataFormatter=new DataFormatter();
        Map<String,String> excelData = new HashMap<>();
        for (int i=0; i<=sheet.getLastRowNum();i++){
            String key =dataFormatter.formatCellValue(sheet.getRow(i).getCell(keyCellNum));
            String value = dataFormatter.formatCellValue(sheet.getRow(i).getCell(valueCellNum));
            excelData.put(key ,value);
        }
        return excelData;
    }
    public Map<String,String> getMultipleDataFromExcel(String sheetName ,int rowStartingIndex, int keyCellNum , int valueCellNum) throws IOException {
        FileInputStream fi = new FileInputStream(IPathConstants.ExcelPath);
        Workbook workbook=WorkbookFactory.create(fi);
        Sheet sheet= workbook.getSheet(sheetName);
        DataFormatter dataFormatter=new DataFormatter();
        Map<String,String> excelData = new HashMap<>();
        for (int i=rowStartingIndex; i<=sheet.getLastRowNum();i++){
            String key =dataFormatter.formatCellValue(sheet.getRow(i).getCell(keyCellNum));
            String value = dataFormatter.formatCellValue(sheet.getRow(i).getCell(valueCellNum));
            excelData.put(key ,value);
        }
        return excelData;
    }
    public Object [][] getDataByDP( String sheetName,int rowStart) throws IOException {
        FileInputStream fi = new FileInputStream(IPathConstants.ExcelPath);
        Workbook workbook=WorkbookFactory.create(fi);
        Sheet sheet= workbook.getSheet(sheetName);
        DataFormatter dataFormatter=new DataFormatter();
        int lastRow=sheet.getLastRowNum();
        int lastCell=sheet.getRow(1).getLastCellNum();
        System.out.println(lastRow);
        System.out.println(lastCell);
        Object[][] data = new Object[lastRow][2];
        for (int i=rowStart; i<=5; i++){
            for (int j=0; j<2;j++){
                data[i-rowStart][j]=dataFormatter.formatCellValue(sheet.getRow(i).getCell(j));
            }
        }
        return data;
    }

    // for entering the data from the map into the text field
}
