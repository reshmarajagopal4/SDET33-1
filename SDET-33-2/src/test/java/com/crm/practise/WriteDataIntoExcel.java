package com.crm.practise;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class WriteDataIntoExcel {
	
	public static void main(String[] args) throws Throwable {
		
		String loc="./src/test/resources/Testdata.xlsx";
		FileInputStream fs = new FileInputStream(loc);
		
		Workbook wb=WorkbookFactory.create(fs);
		Sheet sh=wb.getSheet("SDET33");
		Row r=sh.getRow(1);Row r1=sh.getRow(0);
		Cell c=r.createCell(1);Cell c1=r1.createCell(1);
		c.setCellValue("Pass");c1.setCellValue("Organization");
		
		//WorkbookFactory.create(fs).getSheet("SDET33").getRow(0).createCell(0).setCellValue("Organization");
	
		
		FileOutputStream fos=new FileOutputStream(loc);
		wb.write(fos);
		wb.close();
		System.out.println("closed workbook succesfully");
	
	}

}
