package com.cg.webuild.helper;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
}
