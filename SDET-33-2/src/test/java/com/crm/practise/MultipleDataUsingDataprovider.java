package com.crm.practise;

import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.genericutility.ConstantPath;
import com.crm.genericutility.Excel_Utitlity;
import com.crm.genericutility.File_Utility;



public class MultipleDataUsingDataprovider {

	@Test(dataProvider="readdata")
	public void readexcel(String un,String pwd) {
		Reporter.log(un+"==="+pwd,true);
	}
	
	
	
	@DataProvider
	public Object[][] readdata() {
		Excel_Utitlity.openExcel(ConstantPath.Excel_Testdata);
		return Excel_Utitlity.getMultipleDataFromExcel(File_Utility.getProperty("SheetNo"));
		
	}
	

}
