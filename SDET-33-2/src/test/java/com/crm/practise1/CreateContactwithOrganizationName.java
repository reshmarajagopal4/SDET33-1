package com.crm.practise1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverInfo;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactwithOrganizationName {

	public static void main(String[] args) throws IOException {
		
		/*->SCENARIO:2: login to vtiger;click on organization;click on create organization;enter some value into Orgnaization textfield
		click on save;click on Contacts;enter somevalue in lastname textfiled;click on' +' symbol;new window will open
		switch to that window;go to Search textfield and Enter orgnaiztion name;click on searchnow;Switch back to Parent window;verify organization name & Last name ;
		Logout*  (Take data from property file)*/
		
		
		//Step:1:Fetch data from property file and store it in string
		FileInputStream fis=new FileInputStream("./src/test/resources/commondata.properties");
		Properties properties=new Properties();
		properties.load(fis);
		String url = properties.getProperty("url");
		String userName = properties.getProperty("userName");
        String password = properties.getProperty("password");
        String browser = properties.getProperty("browser");
        String excelsheetname = properties.getProperty("excelsheetname");
        String excelpath = properties.getProperty("excelpath");
        String timeouts = properties.getProperty("timeouts");
        long timeoutLong=Long.parseLong(timeouts);
        
        //Step:2:generate random number
        Random ran=new Random();
        int randomnumber = ran.nextInt(1000);
        
        //Step:3:Fetch data from the excel
        FileInputStream fis1=new FileInputStream(excelpath);
        Workbook wk = WorkbookFactory.create(fis1);
        String contactName = wk.getSheet(excelsheetname).getRow(0).getCell(0).getStringCellValue();
        String orgName = wk.getSheet(excelsheetname).getRow(0).getCell(0).getStringCellValue();
        wk.close();
        
      //Step:4:Launch the Browser
        WebDriver driver=null;
        
        if(browser.equalsIgnoreCase("chrome")) {
        	WebDriverManager.chromedriver().setup();
        	driver=new ChromeDriver();
        }
        else if(browser.equalsIgnoreCase("firefox")) {
        	WebDriverManager.firefoxdriver().setup();
        	driver=new FirefoxDriver(); 
        }
        else {
        	System.out.println("Browser is not specified properly");
        }
        
        //Step:3:Basic configuration for browser
        driver.manage().window().maximize();
 	    driver.manage().timeouts().implicitlyWait(timeoutLong,TimeUnit.SECONDS);
 	    
 	    //open the url and navigate to the application
 	    driver.get(url);
 	    
 	    //Login to the application
 	    driver.findElement(By.name("user_name")).sendKeys(userName);
 		driver.findElement(By.name("user_password")).sendKeys(password);
 		driver.findElement(By.id("submitButton")).click();
 		
 		//create organization
 		driver.findElement(By.linkText("Organizations")).click();//y sir xpath??
 		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
 		String actOrgName=orgName+randomnumber;
 		driver.findElement(By.name("accountname")).sendKeys(actOrgName);
 		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
 		
 		//verify the org name
 		String expOrgName = driver.findElement(By.id("dtlview_Organization Name")).getText();
 		if(expOrgName.equalsIgnoreCase(actOrgName)) {
 			System.out.println("Organization name is correct");
 		}
 		else {
 			System.out.println("Organization name is not correct");//else reqd or not??
 		}
 		
 		//create contact
 		driver.findElement(By.linkText("Contacts")).click();
 		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
 		String actContactName="Reshma";
 		driver.findElement(By.name("lastname")).sendKeys(actContactName);
 		
 		//add organization
 		driver.findElement(By.xpath("")).click();
 		
 		//get windowids and switch to child window
 		Set<String> windowIDs = driver.getWindowHandles();
 		
 		for(String id:windowIDs) {
 			if(driver.getTitle().contains("Accounts")) {
 				driver.switchTo().window(id);
 				break;                                  //right??
 			}
 			
 		}
 		
 		//search org name and select
 		driver.findElement(By.id("search_txt")).sendKeys(actOrgName);//act or exp??
 		driver.findElement(By.name("search")).click();
 		driver.findElement(By.linkText(actContactName)).click();//click tto add it to contacts
 		
 		//to get back control from child to parent window
 		
 		Set<String> windowIDs2 = driver.getWindowHandles();
 		for(String id:windowIDs2) {
 			driver.switchTo().window(id);
 			if(driver.getTitle().contains("Contacts")) {
 				break;
 				
 			}
 		}
 		
        driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
        
 		
 		//verify org and contact
        
        String expContName=driver.findElement(By.id("dtlview_Last Name")).getText();
        String expAddedOrgName=driver.findElement(By.id("")).getText();
        
        if(expContName.equalsIgnoreCase(actContactName)&&expAddedOrgName.equalsIgnoreCase(actOrgName))
        {
        	System.out.println("Contacted created successfully with organization");
        }
        
        //signout of app
        driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
        driver.findElement(By.linkText("Sign Out")).click();
        
        driver.quit();
  
        	
        
        
		
		
		
		

	}

}
