package com.crm.genericutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * 
 * @author Sumanth
 *
 */

public class Excel_Utitlity {

	
	public	static Workbook wb;
	public	static FileInputStream fis;
	/**
	 * This method is used to fetch the data from excel
	 * @param sheetname
	 * @param rownum
	 * @param cellnum
	 * @return
	 */
	
	
	
	public static String  fetchdata(String sheetname,int rownum,int cellnum) {
		
		Sheet sh=wb.getSheet(sheetname);
		String data=sh.getRow(rownum).getCell(cellnum).toString();
		return data;
		
		
	}
	
	public static void writedatainExcel() {
	 	
	
	}

	
	/**
	 * This method is used to open the Excel
	 * @param path
	 * @return 
	 * @return 
	 */
	
	public static void openExcel(String path) {
		
	
		try {
			fis = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			System.out.println("file is not found");
		}
		try {
			 wb=WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException e) {
			System.out.println("Document is Encrypted");
		} catch (IOException e) {
			System.out.println("throwing Ios Exception");
		}
		
	}
	public static void closeTheExcel(String path) throws Throwable {
		FileOutputStream fos=new FileOutputStream(path);
		wb.write(fos);
		wb.close();
		
	}
	
	public static Object[][] getMultipleDataFromExcel(String sheetname) {
		
		 Sheet sh=wb.getSheet(sheetname);
		Object[][] arr = new Object[sh.getLastRowNum()][sh.getRow(0).getLastCellNum()];
		
		for(int i=0;i<sh.getLastRowNum();i++) {
			for(int j=0;j<sh.getRow(0).getLastCellNum();j++) {
				arr[i][j]=sh.getRow(i+1).getCell(j).toString();
			}
		}
		return arr;
	}
	
	
	
}
