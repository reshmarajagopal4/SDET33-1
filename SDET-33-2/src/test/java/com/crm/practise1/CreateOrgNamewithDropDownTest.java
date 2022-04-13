package com.crm.practise1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
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
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrgNamewithDropDownTest {

	public static void main(String[] args) throws IOException {
		
		//login to vtiger,click on organization,click on create organization,enter some name into Orgnaization textfield,click on industry dropdown and select Education
		//click on Type dropdown and select Analyst,click on Save,verify Organiaztion name, industry andType then Logout
		
		
		//Step:1.fetch data from external file and store in variable
		
         FileInputStream fis=new FileInputStream("./src/test/resources/commondata.properties");
         Properties properties=new Properties();
         properties.load(fis);
         String url = properties.getProperty("url");
         String userName = properties.getProperty("userName");
         String password = properties.getProperty("password");
         String browser = properties.getProperty("browser");
         String excelPath=properties.getProperty("excelPath");
         String excelSheetName = properties.getProperty("excelSheetName");
         String timeouts = properties.getProperty("timeouts");
         long timeoutLong = Long.parseLong(timeouts);
         
         //generate random number
         Random ran =new Random();
         int randomnumber = ran.nextInt(1000);
         
         //to fetch data from excel
         FileInputStream fis1=new FileInputStream(excelPath);
         Workbook wk = WorkbookFactory.create(fis1);
         String orgName = wk.getSheet(excelSheetName).getRow(11).getCell(1).getStringCellValue();
         String industry = wk.getSheet(excelSheetName).getRow(11).getCell(2).getStringCellValue();
         String type =wk.getSheet(excelSheetName).getRow(11).getCell(3).getStringCellValue();
         wk.close();
         
         //Step:2:Launch the Browser
         
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
 		driver.findElement(By.linkText("Organizations")).click();
 		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
 		String actOrgName=orgName+randomnumber;
 		driver.findElement(By.name("accountname")).sendKeys(actOrgName);
 		
  		//create industry dropdown
 		WebElement industryDropDown = driver.findElement(By.name("industry"));
 		Select select=new Select(industryDropDown);
 		select.selectByVisibleText(industry);
 		
 		//create type dropdown
 		WebElement typeDropDown = driver.findElement(By.name("accounttype"));
 		Select select1=new Select(typeDropDown);
 		select1.selectByVisibleText(type);
 		
 		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
 		
 		//to verify the organization name,industry and type is correct or not
 		
 		String expOrgName = driver.findElement(By.id("dtlview_Organization Name")).getText();
 		String expIndName = driver.findElement(By.id("dtlview_Industry")).getText();
 		String expTypeName = driver.findElement(By.id("dtlview_Type")).getText();
 		
 		if(expOrgName.equalsIgnoreCase(actOrgName)&&expIndName.equalsIgnoreCase(industry)&&expTypeName.equalsIgnoreCase(type))
 		{
 			System.out.println("Organization is created successfully with industry and type");
 			wk.getSheet(excelSheetName).getRow(randomnumber).createCell(randomnumber).setCellValue("pass");
 			FileOutputStream fos=new FileOutputStream(excelPath);
 			wk.write(fos);
 			
 		}
 		
 		wk.close();
 		
 		
 		//to logout
 		Actions action=new Actions(driver);
 		WebElement user = driver.findElement(By.xpath("//img[contains(@src,'/user.PNG')]"));
 		action.moveToElement(user).perform();
 		driver.findElement(By.linkText("Sign Out")).click();
 		driver.quit();
 
 	

	}

}
