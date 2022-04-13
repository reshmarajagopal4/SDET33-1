package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage {
	
	@FindBy(xpath="//*[@title='Create Organization...']")
	private WebElement CreateAnOrganization;
	
	public OrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	public WebElement getCreateAnOrganization() {
		return CreateAnOrganization;
	}
	public void ClickOnCreateOrganization() {
	CreateAnOrganization.click();
}

}
