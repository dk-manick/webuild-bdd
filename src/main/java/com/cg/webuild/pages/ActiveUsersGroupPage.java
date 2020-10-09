package com.cg.webuild.pages;

import static org.junit.Assert.assertTrue;
import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.cg.webuild.helper.UtilClass;

public class ActiveUsersGroupPage extends UtilClass {

	private static Logger log = Logger.getLogger(ActiveUsersGroupPage.class);
	private WebDriver driver;
	
	@FindBy(xpath = "//h3[text()='active user groups with > 5 events']")
	private WebElement lnkHeaderText;
	
	@FindBy(xpath = "//div[@id='events-per-group']//div[@class='graph-bar']")
	private List<WebElement> events;
	
	public ActiveUsersGroupPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void verifyHomePage() {
		assertTrue("Active Users Group Page not loaded", isElementPresent(driver, lnkHeaderText));
		screenShot(driver);
		log.info("Active Users Group Page loaded successfully");
	}
	
	public void checkCountMore(int count) {
		screenShot(driver);
		for(WebElement event : events)
		{
			int actual = Integer.parseInt(event.getText());
			String userGroup=event.findElement(By.xpath("./preceding-sibling::div/a")).getText();
			assertTrue("active user groups is not >= 5 events - "+userGroup, actual>=count);
		}
	}
}