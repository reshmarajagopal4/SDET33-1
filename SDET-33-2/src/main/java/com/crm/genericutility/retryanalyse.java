package com.crm.genericutility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class retryanalyse implements IRetryAnalyzer {

	int count=0;
	int maximum=5;
	public boolean retry(ITestResult result) {
	
		while(count<maximum) {
			count++;
			return true;
		}
		return false;
	}

}
