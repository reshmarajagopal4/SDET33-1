package com.crm.practise;

import com.crm.genericutility.Excel_Utitlity;


public class practise_01 {

	public static void main(String[] args) {
		
		Excel_Utitlity.openExcel("./src/test/resources/Lucky.xlsx");
		String orgname=Excel_Utitlity.fetchdata("SDET33", 12, 1);
		System.out.println(orgname);

		
	}

}
