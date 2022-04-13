package com.crm.practise;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Assert_Dataprovider {
	
	@Test(dataProvider="dataprovider")
	public void assert_dataprovider(String company,int year) {
		
		//System.out.println(company+"==="+year);
		
	}
	
	@DataProvider
	public Object[][] dataprovider(){
		 Object[][] arr = new Object[2][2];
		
		arr[0][0]="hi";
		arr[0][1]=2009;
		arr[1][0]="hello";
		arr[1][1]=2008;
		return arr;
		
 	}
	
}