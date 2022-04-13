package com.crm.practise;


import org.testng.annotations.Test;

import com.crm.genericutility.BaseClass;
import com.crm.genericutility.Excel_Utitlity;
import com.crm.genericutility.File_Utility;
import com.crm.genericutility.WebDriver_Utility;
import com.crm.genericutility.java_utility;
import com.crm.objectRepository.AccountsChildWindowPage;
import com.crm.objectRepository.CreatingNewOpportunityPage;
import com.crm.objectRepository.OpportunityInformationPage;
import com.crm.objectRepository.OpportunityPage;



public class LuckyTest extends BaseClass {
	
@Test
public void luckyTest() throws Throwable {
	
	homepage.clickOnOpportunityLinkText();
	OpportunityPage opp=new OpportunityPage(driver);
	opp.clickOnCreateNewOpportunity();
	CreatingNewOpportunityPage newopp=new CreatingNewOpportunityPage(driver);
	String oppname=Excel_Utitlity.fetchdata(File_Utility.getProperty("SheetNo"), 24, 1)+java_utility.generateRandomNumber(250);
	newopp.creatingNewOpportunityPage(oppname);
	OpportunityInformationPage oppinfo=new OpportunityInformationPage(driver);
	WebDriver_Utility.switchtoWindow(driver, Excel_Utitlity.fetchdata(File_Utility.getProperty("SheetNo"), 4, 3));
	AccountsChildWindowPage accountsChildWindowPage = new AccountsChildWindowPage(driver);
	accountsChildWindowPage.childWindowAccountsPage(driver, Excel_Utitlity.fetchdata(File_Utility.getProperty("SheetNo"), 4, 1));
	WebDriver_Utility.switchtoWindow(driver, Excel_Utitlity.fetchdata(File_Utility.getProperty("SheetNo"), 4, 4));
	newopp.getsavebutton().click();
	String exp_oppname=oppname;
	String exp_relatedto=Excel_Utitlity.fetchdata(File_Utility.getProperty("SheetNo"), 4, 1);
	String exp_assignedto=Excel_Utitlity.fetchdata(File_Utility.getProperty("SheetNo"), 26, 2);
	String exp_expectedclosedate=Excel_Utitlity.fetchdata(File_Utility.getProperty("SheetNo"), 26, 3);
	String exp_salesStage=Excel_Utitlity.fetchdata(File_Utility.getProperty("SheetNo"), 26, 4);
	oppinfo.ExpectedOpportunityInformation(exp_oppname, exp_relatedto, exp_assignedto, exp_expectedclosedate, 
			exp_salesStage);

}
}
