package com.crm.practise1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationName {
	
	 //SCENARIO:login to vtiger, click on organization,click on create organization,enter some name into Orgnaization textfield
     //click on save,verify it(checking whether its on org list page) & Logout
	
	public static void main(String[] args) throws IOException {
		
		//Step:1.fetch data from external file and store in variable
		
	    FileInputStream fis=new FileInputStream("./src/test/resources/commondata.properties"); 
	    Properties properties =new Properties();
	    properties.load(fis);
	    String url = properties.getProperty("url");
	    String userName = properties.getProperty("userName");
	    String password = properties.getProperty("password");
	    String browser = properties.getProperty("browser");
	    String excelPath=properties.getProperty("excelPath");
        String excelSheetName = properties.getProperty("excelSheetName");
	    String timeouts = properties.getProperty("timeouts");
	    long timeoutLong = Long.parseLong(timeouts);
	    
	    //Step:2:generate random number
	    Random ran=new Random();
	    int randomnumber = ran.nextInt(1000);
	    
	    //Step:3:fetch data from excel file
	   FileInputStream fis1=new FileInputStream(excelPath);
	   Workbook wk = WorkbookFactory.create(fis1);
	   Sheet sh = wk.getSheet(excelSheetName);
	   Row row = sh.getRow(0);
	   Cell cell = row.getCell(0);
	   String orgName = cell.getStringCellValue();
	   System.out.println(orgName);
	    
	    
	    //Step2:Launch the browser
	    WebDriver driver=null;
	    
	    if(browser.equalsIgnoreCase("chrome"))
	    {
	    	WebDriverManager.chromedriver().setup();
	    	driver=new ChromeDriver();
	    }
	    
	    else if(browser.equalsIgnoreCase("firefox"))
	    {
	    	WebDriverManager.firefoxdriver().setup();
	    	driver=new FirefoxDriver();
	    	
	    }
	    
	    else
	    {
	        System.out.println("Browser is not specified properly");
	    }
	    
	    
	    //Step:4:Do basic configuration  for browser
	    
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(timeoutLong,TimeUnit.SECONDS);
	    
	    //open the url and navigate to the application
	    driver.get(url);
	    
	    //Login to the application
	    driver.findElement(By.name("user_name")).sendKeys(userName);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		//create organization
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		String actOrgName=orgName;
		driver.findElement(By.name("accountname")).sendKeys(actOrgName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//to verify the organization name is correct or not
		String expOrgName = driver.findElement(By.id("dtlview_Organization Name")).getText();
		
		if(expOrgName.equalsIgnoreCase(actOrgName))
		{
			System.out.println("Organization name is correct");
		}
		else {
			System.out.println("Organization name is not correct");
		}
	    
		//to logout
		Actions action=new Actions(driver);
		WebElement user = driver.findElement(By.xpath("//img[contains(@src,'/user.PNG')]"));
		action.moveToElement(user).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
		
		
	    
	    
	    
	    
	    	
	    
	    
		
		
	}
	
	
	
    
    
    
	
}
