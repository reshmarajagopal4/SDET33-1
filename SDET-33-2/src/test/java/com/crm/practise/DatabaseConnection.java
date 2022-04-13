package com.crm.practise;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;



public class DatabaseConnection {

	public static void main(String[] args) throws Throwable {
		
		Connection connection=null;String url=null;String username=null;String password=null;String lastname=null;
		
		try {
	
			String loc="./src/test/resources/commondata.properties";
			FileInputStream fis=new FileInputStream(loc);
		
			
			//Step1: we should create the object for the driver and register the driver.
		Driver d = new Driver();
		DriverManager.registerDriver(d);
		Workbook wb= WorkbookFactory.create(fis);
		
		
	
		connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/sdet33","root","root");
		 Statement statement= connection.createStatement();
		 //to fetch data(DQL)
		ResultSet result= statement.executeQuery("select * from vtiger;");
		
		while(result.next()) {
	        url=result.getString(1);
	        username=result.getString(2);
	        password=result.getString(3);
	        lastname=result.getString(4);
	
		}

		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.get(url);
		
		driver.findElement(By.name("user_name")).sendKeys(username);
		
		driver.findElement(By.name("user_password")).sendKeys(password);
		
		driver.findElement(By.id("submitButton")).click();
		 
		driver.findElement(By.linkText("Contacts")).click();
		
		driver.findElement(By.xpath("//img[@title='Create Contact...']/../..")).click();
		
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		
		driver.findElement(By.name("button")).click();
		
		String actualLastName="LuckyRaj";
		
		String expectedLastName =driver.findElement(By.id("dtlview_Last Name")).getText();
		
		if(expectedLastName.equals(actualLastName)) {
			System.out.println("pass : lastName is Correct");
		}else {
			System.out.println("fail : lastName is not Correct");
		}
		
		}
		finally {
		connection.close();
		System.out.println("connection is closed");
		}

	}

}
