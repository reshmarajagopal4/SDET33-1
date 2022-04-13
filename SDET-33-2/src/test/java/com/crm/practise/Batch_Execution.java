package com.crm.practise;

import org.testng.annotations.Test;


import com.crm.genericutility.WebDriver_Utility;

public class Batch_Execution {
	
	@Test(priority = 0,groups = "smoke")
	public void Lucky3() {
		WebDriver_Utility.println("test3", true);
	}

	@Test(priority = 2 )
	public void Lucky1() {
		WebDriver_Utility.println("test1", true);
	}
	@Test(priority = 3)
	public void Lucky() {
		WebDriver_Utility.println("test", true);
	}
	
	@Test(priority = 1)
	public void Lucky2() {
		WebDriver_Utility.println("test2", true);
	}
	
}
