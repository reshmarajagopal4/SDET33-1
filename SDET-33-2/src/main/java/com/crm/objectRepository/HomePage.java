package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.genericutility.WebDriver_Utility;

public class HomePage {
//declaration
	@FindBy(linkText="Organizations")
	private WebElement OrganizationsLinkText;

	@FindBy(linkText="Opportunities")
	private WebElement Opportunitylinktext;

	@FindBy(xpath="//img[contains(@src,'user')]")
	private WebElement mousehoverImg;

	@FindBy(linkText="Sign Out")
	private WebElement signoutbutton;
//initialization	
	public HomePage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
//utilization way1:
	public WebElement getOrganizationsLinkText() {
		return OrganizationsLinkText;
	}


//way2@
	public void clickOnOrganizationLinkText() {
		OrganizationsLinkText.click();
	}

	public WebElement getOpportunitylinktext() {
		return Opportunitylinktext;
	}


//way2:
	public void clickOnOpportunityLinkText() {
		Opportunitylinktext.click();
	}

	public WebElement getMousehoverImg() {
		return mousehoverImg;
	}

	public WebElement getSignoutbutton() {
		return signoutbutton;
	}


public void logout(WebDriver driver) {
	WebElement mousehoverImg =getMousehoverImg();
	WebDriver_Utility.moveToElement(driver, mousehoverImg);

	WebElement signoutlinktext=getSignoutbutton();
	WebDriver_Utility.elementclick(signoutlinktext);
	
}



}
