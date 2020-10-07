package com.cg.webuild.helper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UtilClass {

	private static Logger Log = Logger.getLogger(UtilClass.class);
	private WebDriverWait wait;

	public void clickElement(WebDriver driver, WebElement element) {
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	
	public void clickElementByLocator(WebDriver driver, By bylocator) {
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(bylocator));
		wait.until(ExpectedConditions.elementToBeClickable(bylocator));
	    driver.findElement(bylocator).click();
	}
	
	public void mouseoverElementAndClick(WebDriver driver, WebElement element, By locator) {
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		Actions a = new Actions(driver);
		a.moveToElement(element).build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		a.moveToElement(driver.findElement(locator)).click().build().perform();
	}

	public void setText(WebDriver driver, WebElement element, String text) {
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.clear();
		element.sendKeys(text);
	}
	
	public String getText(WebDriver driver, WebElement element) {
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(element));
		Log.info("Element text : "+ element.getText());
		return element.getText();
	}
	
	public String getText(WebDriver driver, By locator) {
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		String value = driver.findElement(locator).getText();
		Log.info("Element text : "+ value);
		return value;
	}
	
	public void sleepinSeconds(int sec) {
		try {
			Log.info("Sleeping for " + sec + " seconds");
			Thread.sleep(sec * 1000);
		} catch (Exception e) {
			Log.info("Problem in sleep");
		}
	}

	
	protected boolean isElementPresent(WebDriver driver, WebElement element) {
		boolean value=false;
		try {
			element.isDisplayed();
			value=true;
		}
		catch(Exception e) {
			Log.info("Element is not present " + element);			
		}
		return value;
	}
	
	protected boolean isElementPresent(WebDriver driver, By locator) {
		boolean value=false;
		try {
			driver.findElement(locator).isDisplayed();
			value=true;
		}
		catch(Exception e) {
			Log.info("Element is not present " + locator);			
		}
		return value;
	}

	public static void screenShot(WebDriver driver)
    {
    	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	try {
        	String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
        	String workingDir = System.getProperty("user.dir") + "/target/surefire-reports/Screenshots/";
        	String path=timeStamp+new Random().nextInt(10000)+".png";
			FileUtils.copyFile(scrFile, new File(workingDir+path));
			String relativepath="./surefire-reports/Screenshots/" + path;
			try {
		    	ReportAdapter.addScreenCaptureFromPath(relativepath);
		    }
		    catch(Exception e) {
		    	Log.info("Problem in adding screenshot to the Reports- " + e.getMessage());
		    }
    	} catch (IOException e) {
			e.printStackTrace();
			Log.info("Problem in taking screenshot - " + e.getMessage());
		}
    }
}


