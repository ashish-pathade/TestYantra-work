package com.practice;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteDataINExcel {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\demoCommon.xlsx");
        Workbook workbook=WorkbookFactory.create(fis);
       Sheet sheet= workbook.getSheet("Sheet1");
       sheet.createRow(4).createCell(0).setCellValue("Writing Data to Excel File");
       sheet.getRow(4).createCell(1).setCellValue("abc");
        FileOutputStream fos=new FileOutputStream(".\\src\\test\\resources\\demoCommon.xlsx");
        workbook.write(fos);
        fos.flush();
        workbook.close();

    }
}
