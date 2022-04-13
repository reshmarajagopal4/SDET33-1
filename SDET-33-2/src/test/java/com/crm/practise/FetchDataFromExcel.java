package com.crm.practise;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetchDataFromExcel {
	
	public static void main(String[] args) throws Exception {
		String loc="./src/test/resources/Testdata.xlsx";
		//Step1: convert the physicl file into java readable object
		FileInputStream f =new FileInputStream(loc);
		//Step2: open the excel file by using "WorbookFactory" class and create
		Workbook wb=WorkbookFactory.create(f);
		//Step3: we should get the control of particular sheet by using getSheet();
		Sheet s = wb.getSheet("SDET33");
		//step4: we should get the control of particular row by using getRow();
		Row r=s.getRow(1);
		//Step5: we should get the control of particular cell by using getCell();
		Cell c=r.getCell(0);
		//Step6: read/fetch the data by using getStringCellValue()  / toString();
		String data=c.getStringCellValue();
		System.out.println(data);
		//Step7: close the workbook by using close() which belongs to Workbook Interface
		wb.close();
		System.out.println("Connection Close  :Workbook is closed successfully");
	}

}
