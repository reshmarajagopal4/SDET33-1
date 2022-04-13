package com.crm.contacts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContacts {

	public static void main(String[] args) throws InterruptedException {
		

		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.get("http://localhost:8888/index.php");
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		
		driver.findElement(By.name("user_password")).sendKeys("admin");
		
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Contacts")).click();
		
		driver.findElement(By.xpath("//img[@title='Create Contact...']/../..")).click();
		
		driver.findElement(By.name("lastname")).sendKeys("LuckyRaj");
		
		driver.findElement(By.name("button")).click();
		
		String actualLastName="LuckyRaj";
		
		String expectedLastName =driver.findElement(By.id("dtlview_Last Name")).getText();
		
		if(expectedLastName.equals(actualLastName)) {
			System.out.println("pass : lastName is Correct");
		}else {
			System.out.println("fail : lastName is not Correct");
		}
		
		
		
		
	}

}
