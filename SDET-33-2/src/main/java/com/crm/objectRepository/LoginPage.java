package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.genericutility.File_Utility;

public class LoginPage {
		
		//Declaration
	
		@FindBy(name ="user_name") 
		private WebElement usernameTextField;
		
		@FindBy(name="user_password")
		private WebElement passwrodTextField;
		
		@FindBy(id="submitButton")
		private WebElement clickbutton;
		
		//Initialization
		
		public LoginPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
		
		public WebElement getUsernameTextField() {
			return usernameTextField;
		}

		//step4: Utilization
		
		public WebElement getPasswrodTextField() {
			return passwrodTextField;
		}

		public WebElement getClickbutton() {
			return clickbutton;
		}
		
		//way2:
		
		public void createLoginPage() {
			usernameTextField.sendKeys(File_Utility.getProperty("username"));
			passwrodTextField.sendKeys(File_Utility.getProperty("password"));
			clickbutton.click();
		}

		
		

	

}
