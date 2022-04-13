package com.crm.practise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Database_project {

	public static void main(String[] args) throws Throwable {
		

		Connection connection=null;String project_id=null;String created_by=null;String created_on=null;String project_name=null;String status=null;
		
		try {
	
			//Step1: we should create the object for the driver and register the driver.
		Driver d = new Driver();
		DriverManager.registerDriver(d);

		connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
		 Statement statement= connection.createStatement();
		 //to fetch data(DQL)
		int result= statement.executeUpdate("insert into project values('TY_PROJ_004','Lucky','24/03/2022','SDET-33','Ongoing',10)");
if(result==1) {
	System.out.println("rows get changed");
}else {
	System.out.println("rows not get changed");
}
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.get("http://localhost:8084/");
		
		driver.findElement(By.id("usernmae")).sendKeys("rmgyantra");
		
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		
		driver.findElement(By.xpath("//*[@type='submit']")).click();
		
		driver.findElement(By.linkText("Projects")).click();
		
		String actualLastName="SDET-33";
		
		String expectedLastName=driver.findElement(By.xpath("//*[contains(text(),'SDET-33')]")).getText();
		
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


