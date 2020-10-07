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

}