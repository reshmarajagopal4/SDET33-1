package com.crm.Opportunity;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.crm.genericutility.ConstantPath;
import com.crm.genericutility.Excel_Utitlity;
import com.crm.genericutility.File_Utility;
import com.crm.genericutility.WebDriver_Utility;
import com.crm.genericutility.java_utility;
import com.crm.objectRepository.AccountsChildWindowPage;
import com.crm.objectRepository.CreateNewOrganizationPage;
import com.crm.objectRepository.CreatingNewOpportunityPage;
import com.crm.objectRepository.LoginPage;
import com.crm.objectRepository.OpportunityInformationPage;
import com.crm.objectRepository.OpportunityPage;
import com.crm.objectRepository.OrganizationPage;
import com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File;
import com.crm.objectRepository.HomePage;

import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * 
 * @author Sumanth
 *
 */
public class CreateopportunityandMapContactandclearcontact_TC06_Test {

	public static void main(String[] args) throws Throwable {

		//navigate to application
		File_Utility.InitializePropertydata(ConstantPath.CommProp);
		String url=File_Utility.getProperty("url");
		String un=File_Utility.getProperty("username");
		String pwd=File_Utility.getProperty("password");
		String browser=File_Utility.getProperty("browser");
		String timeouts=File_Utility.getProperty("timeouts");
		long timeoutlong=Long.parseLong(timeouts); 
		String sheetname=File_Utility.getProperty("SheetNo");
		String Excelpath=File_Utility.getProperty("loc");
		
		
		Excel_Utitlity.openExcel(Excelpath);
		String chrome_browser=Excel_Utitlity.fetchdata(sheetname, 16, 1);
		String firefox_browser=Excel_Utitlity.fetchdata(sheetname, 16, 2);
		String OpportuntiyName=Excel_Utitlity.fetchdata(sheetname, 24, 1);
		String orgname=Excel_Utitlity.fetchdata(sheetname, 4, 1);
		String childwindow_Accounts=Excel_Utitlity.fetchdata(sheetname, 4, 3);
		String parentwindow_Potentials=Excel_Utitlity.fetchdata(sheetname, 4, 4);
		String browser_mismatch=Excel_Utitlity.fetchdata(sheetname, 16, 3);
		//launch broswer
		WebDriver driver = null ;
	//	driver=WebDriver_Utility.toLaunchBrowser(driver, browser, chrome_browser, firefox_browser,browser_mismatch); 
		WebDriver_Utility.launchApplicationwithMaximize(driver, url, timeoutlong);

		LoginPage page=new LoginPage(driver); 	//login to application
	//	page.createLoginPage(un, pwd);
		//navigate to Organization
		HomePage homePage=new HomePage(driver);
		homePage.clickOnOrganizationLinkText();
		OrganizationPage organizationPage=new OrganizationPage(driver); 
		organizationPage.ClickOnCreateOrganization();

		CreateNewOrganizationPage createNewOrgPage=new CreateNewOrganizationPage(driver);
		createNewOrgPage.createNewOrganizationPage(orgname);

		WebDriver_Utility.refreshthepage(driver);
		//"naviagate to ""create new Opportunity""page by click on ""+"" image"
		String oppname=OpportuntiyName+java_utility.generateRandomNumber(250); 		

		homePage.clickOnOpportunityLinkText();
		OpportunityPage opppage=new OpportunityPage(driver);
		opppage.clickOnCreateNewOpportunity();
		//Enter mandatory values & click on "Assigned to"&select "Oraganizations" Look-UP image
		CreatingNewOpportunityPage createNewOppPage=new CreatingNewOpportunityPage(driver); 
		createNewOppPage.creatingNewOpportunityPage(oppname);                                

		WebDriver_Utility.switchtoWindow(driver, childwindow_Accounts);

		AccountsChildWindowPage accountsChildWindowPage = new AccountsChildWindowPage(driver);
		accountsChildWindowPage.childWindowAccountsPage(driver, orgname);

		WebDriver_Utility.switchtoWindow(driver, parentwindow_Potentials);

		createNewOppPage.getsavebutton().click();

		OpportunityInformationPage oppinfo=new OpportunityInformationPage(driver);

		String exp_oppname=oppname;
		String exp_relatedto=orgname;
		String exp_assignedto=Excel_Utitlity.fetchdata(sheetname, 27, 2);
		String exp_expectedclosedate=Excel_Utitlity.fetchdata(sheetname, 27, 3);
		String exp_salesStage=Excel_Utitlity.fetchdata(sheetname, 27, 4);
		String passed=Excel_Utitlity.fetchdata(sheetname, 29, 1);
		String failed=Excel_Utitlity.fetchdata(sheetname, 29, 2);
		String LoggedOut=Excel_Utitlity.fetchdata(sheetname, 31, 1);
		//verifyOpportunityInformation
		oppinfo.ExpectedOpportunityInformation(exp_oppname,exp_relatedto,exp_assignedto,
				exp_expectedclosedate,exp_salesStage);
		//sigout from application             
		homePage.logout(driver);
	//	WebDriver_Utility.println(LoggedOut);
		WebDriver_Utility.closeBrowser(driver);  //quit browser

	}

}
