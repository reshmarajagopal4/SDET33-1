package com.crm.genericutility;

import java.io.File;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriver_Utility {
	
	/**
	 * 
	 * @param driver
	 * @param timeout
	 */
	
	public static void waitforPageLoad(WebDriver driver,Long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}
	/**
	 * 
	 * @param element
	 * @param driver
	 * @param timeout
	 */
	public static void waituntilElementVisible(WebElement element,WebDriver driver,int timeout) {
		WebDriverWait wait=new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(element));

	}
	/**
	 * 
	 *  @param element
	 * @param timeout
	 * @param driver
	 * @param pollingtime
	 */
	public static void waituntilElementVisibleWithCustomPoll(WebElement element,long timeout,WebDriver driver,long pollingtime) {

		WebDriverWait wait=new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.pollingEvery(Duration.ofSeconds(pollingtime));
		wait.ignoring(Throwable.class);
	}
	/**
	 * 
	 * @param element
	 * @param timeout
	 * @param pollingtime
	 * @throws InterruptedException
	 */
	public static void customWaitTillElementClickable (WebElement element,long timeout,int pollingtime) throws InterruptedException {
		int count=0;
		while(count<=timeout) {
			try {	
				element.click();
				break;
			}catch(NoSuchElementException e) {
				Thread.sleep(pollingtime*1000);
				count++;
			}
		}
	}
	/**
	 * 
	 * @param driver
	 */
	public static void maximizeBrowser (WebDriver driver) {
		driver.manage().window().maximize();
	}
	/**
	 * 
	 * @param driver
	 */
	public static void deleteallcookies(WebDriver driver) {
		driver.manage().deleteAllCookies();
	}
	/**
	 * 
	 * @param driver
	 * @param url
	 * @param timeout
	 * @return 
	 */
	public static void launchApplicationwithMaximize(WebDriver driver,String url,long timeout) {
		driver.get(url);
		maximizeBrowser(driver);
		deleteallcookies(driver);
		waitforPageLoad(driver,timeout);		
	}
	/**
	 * 
	 * @param driver
	 * @param url
	 * @param timeout
	 */
	public static void launchApplication(WebDriver driver,String url,long timeout) {
		driver.get(url);
		waitforPageLoad(driver,timeout);
	}
	public static void elementclick(WebElement element) {
		element.click();
	}
	/**
	 * 
	 * @param driver
	 * @param partialTitleText
	 */
	public static void switchtoWindow(WebDriver driver,String partialTitleText) {
		Set<String>allwids=driver.getWindowHandles();
		for (String id : allwids) {
			driver.switchTo().window(id);
			if(driver.getTitle().contains(partialTitleText)) {
				System.out.println(driver.getTitle());
				break;
			}
		}
	}
	/**
	 * 
	 * @param driver
	 * @param parentwindow
	 */
	public static void SwitchtoParentWindow(WebDriver driver,String parentwindow) {
		driver.switchTo().window(parentwindow);
	}
	/**
	 * 
	 * @param driver
	 * @param element
	 */
	public static void moveToElement(WebDriver driver,WebElement element) {
		Actions a = new Actions(driver);
		a.moveToElement(element).build().perform();
	}
	/**
	 * 
	 * @param driver
	 * @param element
	 */
	public static void rightClickonElement(WebDriver driver,WebElement element) {
		Actions a = new Actions(driver);
		a.contextClick(element).build().perform();
	}
	/**
	 * 
	 * @param driver
	 * @param element
	 */
	public static void doubleClickonElement(WebDriver driver,WebElement element) {
		Actions a=new Actions(driver);
		a.doubleClick(element).build().perform();
	}
	/**
	 * 
	 * @param element
	 * @param index
	 */
	public static void select(WebElement element,int index) {
		Select s = new Select(element);
		s.selectByIndex(index);	
	}
	/**
	 * 
	 * @param element
	 * @param VisibleText
	 */
	public static void select(WebElement element,String VisibleText) {
		Select s = new Select(element);
		s.selectByVisibleText(VisibleText);	
	}
	/**
	 * 
	 * @param value
	 * @param element
	 */
	public static void select(String value,WebElement element) {
		Select s=new Select(element);
		s.selectByValue(value);
	}
	/**
	 * 
	 * @param driver
	 */
	public static void closeBrowser(WebDriver driver) {
		driver.quit();
	}
	/**
	 * 
	 * @param driver
	 * @param index
	 */
	public static void frame(WebDriver driver,int index) {
		driver.switchTo().frame(index);
	}
	/**
	 * 
	 * @param driver
	 * @param NameorId
	 */
	public static void frame(WebDriver driver,String NameorId) {
		driver.switchTo().frame(NameorId);
	}
	/**
	 * 
	 * @param driver
	 * @param element
	 */
	public static void frame(WebDriver driver,WebElement element) {
		driver.switchTo().frame(element);
	}
	/**
	 * 
	 * @param driver
	 * @param FileName
	 * @throws Throwable
	 */
	public static void takeScreenshotofFailedScript(WebDriver driver,String FileName) throws Throwable {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File dest=new File("./Screenshot"+FileName+"_"+java_utility.getCurrentTimeAndDate()+".png");
		FileUtils.copyFile(src, dest);
	}
	/**
	 * 
	 * @param driver
	 * @param FileName
	 * @return
	 * @throws Throwable
	 */
	public static String takeScreenshotAndgetPath(WebDriver driver,String FileName) throws Throwable {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src=ts.getScreenshotAs(OutputType.FILE);

		File dest=new File("./Screenshot"+FileName+"_"+java_utility.getCurrentTimeAndDate()+".png");
		FileUtils.copyFile(src, dest);
		String absoultePath=dest.getAbsolutePath();
		return absoultePath;
	}
	/**
	 * 
	 * @param driver
	 * @param url
	 */
	public static void openApplicationThroughJS(WebDriver driver,String url) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.Location='"+url+"'", driver);
	}
	/**
	 * 
	 * 
	 * @param driver
	 * @param input
	 */
	public static void sendKeysThroughJS(WebDriver driver,String input) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].value='"+input+"'", driver);
	}
	/**
	 * 
	 * @param element
	 * @param driver
	 */
	public static void clickActionThroughJS(WebElement element,WebDriver driver) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", element);
	}
	/**
	 * 
	 * @param driver
	 * @param element
	 */
	public static void scrollTillElementThroughJS(WebDriver driver,WebElement element) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView;", element);
	}
	/**
	 * 
	 * @param driver
	 * @param upordown
	 */
	public static void scrollDownToPageThroughJS(WebDriver driver,String upordown) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,"+upordown+"document.body.scrollHeight)", driver);
	}
	/**
	 * 
	 * @param driver
	 */
	public static void refreshthepage(WebDriver driver) {
		driver.navigate().refresh();
	}
	/**
	 * 
	 * @param name
	 */
	public static void println(String name,boolean value) {
		Reporter.log(name,value);
	}






 


}
