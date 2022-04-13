//package com.crm.Organization;
//
//import org.openqa.selenium.WebDriver;
//import org.testng.annotations.Test;
//
//import com.crm.genericutility.ConstantPath;
//import com.crm.genericutility.Excel_Utitlity;
//import com.crm.genericutility.File_Utility;
//import com.crm.genericutility.WebDriver_Utility;
//import com.crm.genericutility.java_utility;
//import com.crm.objectRepository.CreateNewOrganizationPage;
//import com.crm.objectRepository.LoginPage;
//import com.crm.objectRepository.OrganizationPage;
//
//public class CreateOrganizationTC_01_Test {
//
//	public static void main(String[] args) throws Throwable {
//
//		
//		@Test()
//		public void createOrganizationTest() {
//			
//			
//			
//			
//		}
//	/*	File_Utility.InitializePropertydata(ConstantPath.CommProp);
//		String url=File_Utility.getUrlValue();
//		String un=File_Utility.getUnValue();
//		String pwd=File_Utility.getPwdValue();
//		String browser=File_Utility.getBrowserValue();
//		String timeouts=File_Utility.gettimeoutsValue();long timeoutlong=Long.parseLong(timeouts); 
//		String sheetname=File_Utility.getSheetNoValue();
//		String Excelpath=File_Utility.getLocValue();
//
//		Excel_Utitlity.openExcel(Excelpath);
//		String chrome_browser=Excel_Utitlity.fetchdata(sheetname, 16, 1);
//		String firefox_browser=Excel_Utitlity.fetchdata(sheetname, 16, 2);
//		String organizationName=Excel_Utitlity.fetchdata(sheetname, 4, 1);
//		String browser_mismatch=Excel_Utitlity.fetchdata(sheetname, 16, 3);
//
//		//launch broswer
//		WebDriver driver = null ;
//		driver=WebDriver_Utility.toLaunchBrowser(driver, browser, chrome_browser, firefox_browser,browser_mismatch); 
//		WebDriver_Utility.launchApplicationwithMaximize(driver, url, timeoutlong);
//
//		LoginPage page=new LoginPage(driver); 	//login to application
//		page.createLoginPage(un, pwd);
//		//navigate to Organization
//		OrganizationPage organizationPage=new OrganizationPage(driver); 
//		organizationPage.createOrganizationPage();
//		String orgname=organizationName+java_utility.generateRandomNumber(250);
//		CreateNewOrganizationPage createNewOrgPage=new CreateNewOrganizationPage(driver);
//		createNewOrgPage.createNewOrganizationPage(orgname);
//		
//		
//*/
//	}
//
//}
