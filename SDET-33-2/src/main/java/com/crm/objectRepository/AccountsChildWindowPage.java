package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountsChildWindowPage {
	
	

	@FindBy(name="search_text")
	private WebElement searchTextField;
	
	@FindBy(linkText="Raja")
	private WebElement OrganizationLinkbutton;
	
	public AccountsChildWindowPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getSearchTextField() {
		return searchTextField;
	}

	public WebElement getOrganizationLinkbutton() {
		return OrganizationLinkbutton;
	}
	
	public void childWindowAccountsPage(WebDriver driver,String name) {
		searchTextField.sendKeys(name);
		OrganizationLinkbutton.click();
	}
	
	

}
