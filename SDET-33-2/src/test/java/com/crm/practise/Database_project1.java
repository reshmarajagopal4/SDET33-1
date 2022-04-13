package com.crm.practise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Database_project1 {

	public static void main(String[] args) throws SQLException {
		

	//	Connection connection=null;String project_id=null;String created_by=null;String created_on=null;String project_name=null;String status=null;
		
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
		
		driver.findElement(By.xpath("//*[contains(text(),'Create Project')]/..")).click();
		
		driver.findElement(By.name("projectName")).sendKeys("SDET33");
		driver.findElement(By.name("createdBy")).sendKeys("Deepak");
		WebElement ele = driver.findElement(By.xpath("//*[contains(text(),'Project Status ')]/../select[@name='status']"));
		ele.click();
		Select s = new Select(ele);
		s.selectByVisibleText("On Goging");
		
		driver.findElement(By.xpath("//*[@type='submit']")).click();
		
		Driver d = new Driver();
		DriverManager.registerDriver(d);
		Connection connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
		 Statement statement= connection.createStatement();
		 //to fetch data(DQL)
		ResultSet result= statement.executeQuery("select * from project;");
		
		while(result.next()) {
	       System.out.println(result.getString(1) +"   "+result.getString(2) +"      "+result.getString(3)+"         "+result.getString(4)+"       "+  result.getString(5)+"         "+result.getString(6));
	
		}
		connection.close();
		System.out.println("connection is closed");

		
	}

}
