package com.crm.assignment;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;


public class CreateOrgnaizationNameTest {

	public static void main(String[] args) throws Throwable {
		try {
		String loc="./src/test/resources/commondata.properties";
		FileInputStream f = new FileInputStream(loc);
		Properties p = new Properties();
		p.load(f);
		
		WebDriver driver = null;
		String url=p.getProperty("url");
		String username=p.getProperty("username");
		String password=p.getProperty("password");
		String browser=p.getProperty("browser");
		String timeoouts=p.getProperty("timeouts");
		long timelong = Long.parseLong(timeoouts);
		
		if(browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}else {
			System.out.println("chromedriver is not instialized");
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
		driver.findElement(By.name("accountname")).sendKeys("LuckyRaj.Org");
		driver.findElement(By.name("button")).click();
		String expected_result="LuckyRaj.Org"; 
		String actual_result=driver.findElement(By.xpath ("//*[@id='dtlview_Organization Name']")).getText();
		if(actual_result.equals(expected_result)) {
			System.out.println("Pass: Organization name is verified");
		}else {
			System.out.println("Fail: Organization name is not verified");
		}
		
		Actions a=new Actions(driver);
		WebElement ele =driver.findElement(By.xpath("//img[contains(@src,'themes/softed/images/user.PNG')]"));
		a.moveToElement(ele).build().perform();
		WebElement ele1=driver.findElement(By.linkText("Sign Out"));
		ele1.click();
		}
		
		finally {
			System.out.println("connection is closed");
		}
		

		
	}

}
