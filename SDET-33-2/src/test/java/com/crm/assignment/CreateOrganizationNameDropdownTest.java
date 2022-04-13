package com.crm.assignment;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;



public class CreateOrganizationNameDropdownTest {

	static WebDriver driver;
	public static void main(String[] args) throws Throwable {

		String loc="./src/test/resources/commondata.properties";
		FileInputStream f = new FileInputStream(loc);
		Properties p = new Properties();
		p.load(f);

		String url=p.getProperty("url");
		String username=p.getProperty("username");
		String password=p.getProperty("password");
		String browser=p.getProperty("browser");
		String timeouts=p.getProperty("timeouts");
		int timelong=Integer.parseInt(timeouts);
		String path=p.getProperty("loc");
		String sheetno=p.getProperty("SheetNo");

		Random r=new Random();
		int rnum=r.nextInt(250);

		FileInputStream fis = new FileInputStream(path);
		Workbook wb=WorkbookFactory.create(fis);
		String orgname=wb.getSheet(sheetno).getRow(8).getCell(1).getStringCellValue();
		System.out.println(orgname);
		String industry_dd=wb.getSheet(sheetno).getRow(8).getCell(2).getStringCellValue();
		System.out.println(industry_dd);
		String type_dd=wb.getSheet(sheetno).getRow(8).getCell(3).getStringCellValue();
		System.out.println(type_dd);

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
		WebElement ele=driver.findElement(By.name("industry"));
		ele.click();
		Select s=new Select(ele);
		s.selectByValue(industry_dd);
		WebElement ele1=driver.findElement(By.name("accounttype"));
		Select s1=new Select(ele1);
		s1.selectByValue(type_dd);
		driver.findElement(By.name("button")).click();

		String actual_orgname=exporgname;
		String expected_orgname=driver.findElement(By.id("dtlview_Organization Name")).getText();

		String actual_Industry=industry_dd;
		String expected_Industry=driver.findElement(By.id("dtlview_Industry")).getText();

		String actual_type=type_dd;
		String expected_type=driver.findElement(By.id("dtlview_Type")).getText();
		if(expected_orgname.equalsIgnoreCase(actual_orgname)&&expected_Industry.equalsIgnoreCase(actual_Industry)&&expected_type.equalsIgnoreCase(actual_type)) {
			System.out.println("Pass: Organization name &Industry & Type name is verified");
			wb.getSheet(sheetno).getRow(8).createCell(4).setCellValue("Pass");
			FileOutputStream fos = new FileOutputStream(path);
			wb.write(fos);
			wb.close();

		}else {
			System.out.println("Fail: Type name is not verified");
		}
		Actions a=new Actions(driver);
		WebElement e =driver.findElement(By.xpath("//img[contains(@src,'themes/softed/images/user.PNG')]"));
		a.moveToElement(e).build().perform();
		WebElement e1=driver.findElement(By.linkText("Sign Out"));
		e1.click();
		System.out.println("logged out succesfully");
		driver.quit();

	}

}
