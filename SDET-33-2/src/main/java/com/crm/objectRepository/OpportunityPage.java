package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpportunityPage {
	
	@FindBy(xpath="//*[@title='Create Opportunity...']")
	private WebElement createNewOpportunityPage;
	
	public OpportunityPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	public WebElement getCreateNewOpportunityPage() {
		return createNewOpportunityPage;
	}
	public void clickOnCreateNewOpportunity() {
		
	createNewOpportunityPage.click();
	}

}
