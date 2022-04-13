package com.crm.assignment;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationNameWindowHandlesTest {
	static WebDriver driver;
	public static void main(String[] args) throws Throwable {
		
		String loc="./src/test/resources/commondata.properties";
		FileInputStream f = new FileInputStream(loc);
		Properties p = new Properties();
		p.load(f);
		Random r = new Random();
		int rnum=r.nextInt(500);
		
		
		String url=p.getProperty("url");
		String username=p.getProperty("username");
		String password=p.getProperty("password");
		String browser=p.getProperty("browser");
		String timeouts=p.getProperty("timeouts");
		int timelong=Integer.parseInt(timeouts);
		String path=p.getProperty("loc");
		String sheetno=p.getProperty("SheetNo");
		
		FileInputStream fis = new FileInputStream(path);
		Workbook wb= WorkbookFactory.create(fis);
		String orgname=wb.getSheet(sheetno).getRow(8).getCell(1).getStringCellValue();
		String lastname=wb.getSheet(sheetno).getRow(4).getCell(1).getStringCellValue();
		String childtitle=wb.getSheet(sheetno).getRow(4).getCell(3).getStringCellValue();
		String parenttitle=wb.getSheet(sheetno).getRow(4).getCell(2).getStringCellValue();
		if(browser.equals("chrome")) {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		}else {
			System.out.println("chromdriver is not initialized");
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(timelong, TimeUnit.SECONDS);
		
		driver.get(url);

		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click(); 
	
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//*[@title='Create Organization...']")).click();
		String exporgname=orgname+rnum;
		
		
		driver.findElement(By.name("accountname")).sendKeys(exporgname);
		driver.findElement(By.name("button")).click();
		try {
			driver.findElement(By.linkText("Contacts")).click();
			
		}catch(Exception e) {
			driver.navigate().refresh();
			driver.findElement(By.linkText("Contacts")).click();
			
		}
		
		driver.findElement(By.xpath("//*[@title='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		String xpath="//*[text()='Organization Name 			']/following-sibling::td/img[contains(@src,'theme')]";
		driver.findElement(By.xpath(xpath)).click();
	
		Set<String>allwindowid=driver.getWindowHandles();
		
		for(String id:allwindowid) {
			
			driver.switchTo().window(id);
			if(driver.getTitle().contains(childtitle)) {
				break;
			}
			
		}
		driver.findElement(By.xpath("//*[@id='search_txt']")).sendKeys("");
		driver.findElement(By.xpath("//*[@id='search_txt']")).sendKeys(exporgname);
		driver.findElement(By.xpath("//*[@id='search_txt']")).sendKeys(Keys.ENTER);
		driver.findElement(By.linkText(exporgname)).click();
		
		for(String id:allwindowid) {
			driver.switchTo().window(id);
			if(driver.getTitle().contains(parenttitle)) {
				break;
			}
		}
		driver.findElement(By.name("button")).click();
		String actual_lastname=lastname;

		String expected_result=driver.findElement(By.linkText(exporgname)).getText();
		String actual_result=exporgname;
		
		if(expected_result.equals(actual_result)) {
			System.out.println("Pass: Organization name is verified");
		}else {
			System.out.println("fail");
		}
		
		
		
		
		String expected_lastname=driver.findElement(By.xpath("//*[@id='dtlview_Last Name']")).getText();
		if(expected_lastname.equals(actual_lastname)) {
			System.out.println("Pass: last name is verified");
		}else {
			System.out.println("Fail");
		}
		
		Actions a=new Actions(driver);
		WebElement ele =driver.findElement(By.xpath("//img[contains(@src,'themes/softed/images/user.PNG')]"));
		a.moveToElement(ele).build().perform();
		WebElement ele1=driver.findElement(By.linkText("Sign Out"));
		ele1.click();
		System.out.println("logged out successfully");
		driver.quit();
		}
}
