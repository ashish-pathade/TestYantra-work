package com.practice;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;

import java.io.IOException;

public class GetDataFromExcel {
    public static void main(String[] args) throws IOException {


        FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\demoCommon.xlsx");
       Workbook workbook= WorkbookFactory.create(fis);
        Sheet sheet=workbook.getSheet("Sheet1");
       int lastCell= sheet.getRow(2).getLastCellNum();
        int rowCount=sheet.getLastRowNum();
        for (int i=0;i<=rowCount;i++){
            for (int j=0;j<=lastCell-1;j++){
                String value =sheet.getRow(i).getCell(j).getStringCellValue();
                System.out.print(value);
                System.out.print("  ");
            }
            System.out.println();
        }
        fis.close();
    }
}
