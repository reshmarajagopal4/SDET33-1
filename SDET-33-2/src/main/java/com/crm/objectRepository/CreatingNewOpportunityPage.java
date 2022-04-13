package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewOpportunityPage {
	
	@FindBy(name="potentialname")
	private WebElement OpportunityNameTextField;
	
	@FindBy(xpath="//*[@id='related_to_display']/following-sibling::img[contains(@src,'theme')]")
	private WebElement RelatedToclickbutton;

	@FindBy(xpath="(//*[@name='button'])[1]")
	private WebElement savebutton;
	
	public CreatingNewOpportunityPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getOpportunityNameTextField() {
		return OpportunityNameTextField;
	}

	public WebElement getRelatedToclickbutton() {
		return RelatedToclickbutton;
	}
	public WebElement getsavebutton() throws InterruptedException {
		Thread.sleep(10000);
		return savebutton;
	}
	
	public void creatingNewOpportunityPage(String name) {
		OpportunityNameTextField.sendKeys(name);
		RelatedToclickbutton.click();
	}
	
	
	
	
	

}
