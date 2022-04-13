package com.crm.genericutility;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;


import com.crm.objectRepository.HomePage;
import com.crm.objectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public WebDriver driver;
	public HomePage homepage;
	public static WebDriver sdriver;
	/**
	 * It is used to open the DataBase Connection.
	 * @throws Throwable
	 */
	@BeforeSuite(groups= {"smoke","regression"})
	public void openDatabase() throws Throwable {
		File_Utility.InitializePropertydata(ConstantPath.CommProp);
		Excel_Utitlity.openExcel(ConstantPath.Excel_Lucky);
	//	DataBase_Utility.getMySqlDatabaseConnection(File_Utility.getProperty("url_db"), File_Utility.getProperty("un_db"), File_Utility.getProperty("pwd_db"));	
	}
	/**
	 * It is used to Launch the browser & open the Web application 
	 * & maximize the browser with pageload timeout & deleteallcookies.
	 */
	//@Parameters("browser")
	String browser=System.getProperty("browser");
	@BeforeClass(groups= {"smoke","regression"})
	public  void launchTheBrowser(/*String browser*/) {
		if(browser.equals("chromedriver")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}else if(browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}else {
			WebDriver_Utility.println("browser mismatch",true);
		}
		String url=System.getProperty("url");
		WebDriver_Utility.launchApplicationwithMaximize(driver,url, java_utility.convertStringToLong(File_Utility.getProperty("timeouts")));
sdriver=driver;
}
	
	/**
	 * It is used to login to application by passing username & password values.
	 */
	@BeforeMethod(groups= {"smoke","regression"})
	public void loginToApplication() {
		Excel_Utitlity.openExcel(File_Utility.getProperty("loc"));
		LoginPage loginpage=new LoginPage(driver);
		loginpage.createLoginPage();
		 homepage=new HomePage(driver);

	}
	/**
	 * It is used to signout from the application.
	 * @throws Throwable 
	 */
	@AfterMethod(groups= {"smoke","regression"})
	public void signOutTheApplication() throws Throwable {
	
		homepage.logout(driver);
		
		
	}
	/**
	 * It is used to quit the browser.
	 */
	@AfterClass(groups= {"smoke","regression"})
	public void closeTheBrowser() {
		WebDriver_Utility.closeBrowser(driver);
	}
	/**
	 * It is used to close the connection in Database.
	 * @throws Throwable 
	 */
	@AfterSuite(groups= {"smoke","regression"})
	public void closeTheDatabase() throws Throwable {
		 Excel_Utitlity.closeTheExcel(ConstantPath.Excel_Lucky);
		WebDriver_Utility.println("connection closed succesfully", true);
		//DataBase_Utility.closeDatabaseConnection();
	}


}
