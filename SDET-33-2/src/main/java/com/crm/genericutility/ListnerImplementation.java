package com.crm.genericutility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class ListnerImplementation implements ITestListener {

	public void onTestStart(ITestResult result) {
		
		
	}

	public void onTestSuccess(ITestResult result) {
		
		
	}

	public void onTestFailure(ITestResult result) {
		String name=result.getMethod().getMethodName();
		try {
			WebDriver_Utility.takeScreenshotofFailedScript(BaseClass.sdriver, name);
		} catch (Throwable e) {
			
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
		
	}

	public void onStart(ITestContext context) {
	
		
	}

	public void onFinish(ITestContext context) {
		
		
	}

}
