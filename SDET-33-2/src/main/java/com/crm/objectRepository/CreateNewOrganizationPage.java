package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.genericutility.WebDriver_Utility;

public class CreateNewOrganizationPage {
	
	@FindBy(name="accountname")
	private WebElement OrganizationNameTextField;
	
	@FindBy(xpath="(//*[@name='button'])[1]")
	private WebElement SaveClickbutton;
	
	//intitialization
	
	public CreateNewOrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getOrganizationNameTextField() {
		return OrganizationNameTextField;
	}

	public WebElement getSaveClickbutton() {
		return SaveClickbutton;
	}
	
	public void createNewOrganizationPage(String name) throws InterruptedException {
		OrganizationNameTextField.sendKeys(name);
		SaveClickbutton.click();
		Thread.sleep(10000);
	}
	
	

}
