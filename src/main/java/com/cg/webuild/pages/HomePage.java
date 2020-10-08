package com.cg.webuild.pages;

import static org.junit.Assert.assertTrue;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.cg.webuild.helper.UtilClass;

public class HomePage extends UtilClass {

	private static Logger log = Logger.getLogger(HomePage.class);
	private WebDriver driver;
	
	@FindBy(xpath = "//h1[text()='we build!']")
	private WebElement lnkWebuild;
	
	@FindBy(xpath = "//p[text()='repositories per programming language']")
	private WebElement lnkRepository;
	
	@FindBy(xpath = "//p[text()='active user groups']")
	private WebElement lnkActiveUserGroups;
	
	@FindBy(xpath = "//p[text()='Twitter']")
	private WebElement lnkTwitter;
	
	@FindBy(xpath = "//p[text()='Facebook']")
	private WebElement lnkFacebook;
	
	@FindBy(xpath = "//p[text()='GitHub']")
	private WebElement lnkGithub;
	
	@FindBy(xpath = "//p[text()='Calendar']")
	private WebElement lnkCalendar;
	
	@FindBy(xpath = "//p[text()='RSS']")
	private WebElement lnkRSS;
	
	@FindBy(xpath = "//p[text()='iTunes']")
	private WebElement lnkiTunes;	
	

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void verifyHomePage() {
		assertTrue("Home Page not loaded", isElementPresent(driver, lnkRepository));
		assertTrue("Home Page not loaded", isElementPresent(driver, lnkActiveUserGroups));
		screenShot(driver);
		log.info("Home Page loaded successfully");
	}
	
	public void verifyHeader() {
		assertTrue("Header is not present", isElementPresent(driver, lnkWebuild));
		screenShot(driver);
		log.info("Headers are present");
	}
	
	public void verifyFooter() {
		assertTrue("Footer - Twitter is not present", isElementPresent(driver, lnkTwitter));
		assertTrue("Footer - Facebook is not present", isElementPresent(driver, lnkFacebook));
		assertTrue("Footer - Github is not present", isElementPresent(driver, lnkGithub));
		assertTrue("Footer - Calendar is not present", isElementPresent(driver, lnkCalendar));
		assertTrue("Footer - RSS is not present", isElementPresent(driver, lnkRSS));
		assertTrue("Footer - iTunes is not present", isElementPresent(driver, lnkiTunes));
		screenShot(driver);
		log.info("Footers are present");
	}
	
	public void clickOnLink(String link) {
		By lnkXpath = By.xpath("//p[text()='"+link+"']");
		clickElementByLocator(driver, lnkXpath);
		screenShot(driver);
		log.info("clicked on Link - "+ link);
	}
}