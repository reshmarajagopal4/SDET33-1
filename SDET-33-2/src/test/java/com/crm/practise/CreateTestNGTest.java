package com.crm.practise;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class CreateTestNGTest {
	
	@Test
	public void lucky1() {
		Reporter.log("hello", true);
	}
	@Test
	public void lucky() {
		Reporter.log("hi", true);
	}
	@Test
	public void lucky2() {
		Reporter.log("All rounder SDET", true);
	}
	
	
	
	
	
	
	

}
