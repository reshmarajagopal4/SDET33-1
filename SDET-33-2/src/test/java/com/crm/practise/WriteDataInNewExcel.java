package com.crm.practise;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataInNewExcel {

	public static void main(String[] args) throws Throwable {
		
		String loc="./src/test/resources/raja.xlsx";
		FileInputStream fis=new FileInputStream(loc);
		Workbook wb = WorkbookFactory.create(fis);
	//	WorkbookFactory.create(fis).getSheet("sdet33").getRow(0).createCell(0).setCellValue("StepNO");
		WorkbookFactory.create(fis).getSheet("sdet33").getRow(0).createCell(1).setCellValue("TestName");
		WorkbookFactory.create(fis).getSheet("sdet33").getRow(1).createCell(0).setCellValue("1");
		WorkbookFactory.create(fis).getSheet("sdet33").getRow(1).createCell(1).setCellValue("LuckyRaj");
		
		FileOutputStream fos=new FileOutputStream(loc);
		wb.write(fos);
		wb.close();
		
	
		

	}

}
