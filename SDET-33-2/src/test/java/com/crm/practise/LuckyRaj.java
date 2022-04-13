package com.crm.practise;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.crm.genericutility.ConstantPath;
import com.crm.genericutility.Excel_Utitlity;
import com.crm.genericutility.File_Utility;
import com.crm.genericutility.WebDriver_Utility;
import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LuckyRaj {

	static WebDriver driver;
	static Workbook wb;
	public static void main(String[] args) throws SQLException, Throwable {
		
		
	
		File_Utility.InitializePropertydata(ConstantPath.RmgProp);
		 
		String url =File_Utility.getProperty("url");
		String username =File_Utility.getProperty("username");
		String password=File_Utility.getProperty("password");
		String browser=File_Utility.getProperty("browser");
		String timeouts=File_Utility.getProperty("timeouts");  
		Long timelong=Long.parseLong(timeouts);
		String sheetname=File_Utility.getProperty("sheetname");
		String path=File_Utility.getProperty("loc");
	
		
		Driver d = new Driver();
		DriverManager.registerDriver(d);
		Excel_Utitlity.openExcel(path);
		String url_db=Excel_Utitlity.fetchdata(sheetname, 12, 1);
		String un_db=Excel_Utitlity.fetchdata(sheetname, 12, 2);
		String pwd_db=Excel_Utitlity.fetchdata(sheetname, 12, 3);
		String browser_chrome=Excel_Utitlity.fetchdata(sheetname, 16, 1);
		String browser_firefox=Excel_Utitlity.fetchdata(sheetname, 16, 2);
		String projname=Excel_Utitlity.fetchdata(sheetname, 20, 1);
		String createdBy=Excel_Utitlity.fetchdata(sheetname , 20, 2);
		String status=Excel_Utitlity.fetchdata(sheetname, 20, 3);
		
		Random r=new Random();
		int rnum=r.nextInt(250);

		
		
		Connection c = DriverManager.getConnection(url_db,un_db,pwd_db);
	
		if(browser.equals(browser_chrome)) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		
		}
		else if(browser.equals(browser_firefox)){
			 driver = new FirefoxDriver();
			WebDriverManager.firefoxdriver().setup();
		}
		
		WebDriver_Utility.launchApplicationwithMaximize(driver, url, timelong);

		driver.findElement(By.id("usernmae")).sendKeys(username);
		driver.findElement(By.id("inputPassword")).sendKeys(password);
		driver.findElement(By.xpath("//*[@type='submit']")).click();
		driver.findElement(By.linkText("Projects")).click();
		driver.findElement(By.xpath("//*[contains(text(),'Create Project')]/..")).click();
		
		
		String projectName=projname+rnum;
		driver.findElement(By.name("projectName")).sendKeys(projectName);
		driver.findElement(By.name("createdBy")).sendKeys(createdBy);
		
		
		WebElement ele = driver.findElement(By.xpath("//*[contains(text(),'Project Status ')]/../select[@name='status']"));
		ele.click();
		WebDriver_Utility.select(status, ele);
		
		driver.findElement(By.xpath("//*[@type='submit']")).click();
		/*
		 * Statement statement = c.createStatement(); int result =statement.
		 * executeUpdate("insert into project values('SDET34','Lucky','24/03/22','andhra','ongoing',10)"
		 * );
		 */
		
		
	}
	

}
