package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.crm.genericutility.WebDriver_Utility;

public class OpportunityInformationPage {

	@FindBy(id="dtlview_Opportunity Name")
	private WebElement OpportunityNametextFiled;

	@FindBy(xpath="//*[@title='Organizations']")
	private WebElement RelateToTextField;

	@FindBy(xpath="//span[@id='dtlview_Assigned To']/..")
	private WebElement AssignedToTextField;

	@FindBy(xpath="//*[contains(text(),'Expected Close Date')]/following-sibling::td")
	private WebElement ExpectedCloseDate;

	@FindBy(id="mouseArea_Sales Stage")
	private WebElement SalesStageDropdown;

	public OpportunityInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getOpportunityNametextFiled() {
		return OpportunityNametextFiled;
	}

	public WebElement getRelateToTextField() {
		return RelateToTextField;
	}

	public WebElement getAssignedToTextField() {
		return AssignedToTextField;
	}

	public WebElement getExpectedCloseDate() {
		return ExpectedCloseDate;
	}

	public WebElement getSalesStage() {
		return SalesStageDropdown;
	}

	public void ExpectedOpportunityInformation (String exp_oppname,String exp_relatedto,String exp_assignedto,String exp_expectedclosedate,String exp_salesStage) {
		String actual_oppname =OpportunityNametextFiled.getText();
		String actual_relatedto=RelateToTextField.getText();
		String actual_assignedto=AssignedToTextField.getText();
		String actual_expectedclosedate=ExpectedCloseDate.getText();
		String actual_salesStage=SalesStageDropdown.getText(); 
		Assert.assertTrue(actual_expectedclosedate.contains(exp_expectedclosedate) &&actual_oppname.equalsIgnoreCase(exp_oppname)&&actual_relatedto.equalsIgnoreCase(exp_relatedto)
				&& actual_assignedto.contains(exp_assignedto)&& 
				actual_salesStage.equalsIgnoreCase(exp_salesStage));
				WebDriver_Utility.println("details verified succesfully",true);
				
	}


}
