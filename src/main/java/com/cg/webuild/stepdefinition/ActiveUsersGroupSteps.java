package com.cg.webuild.stepdefinition;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import com.cg.webuild.helper.BrowserManager;
import com.cg.webuild.helper.UtilClass;
import com.cg.webuild.pages.ActiveUsersGroupPage;
import cucumber.api.java.en.Then;

public class ActiveUsersGroupSteps extends UtilClass {

	public WebDriver driver;
	private static Logger log = Logger.getLogger(ActiveUsersGroupSteps.class);
	
	public ActiveUsersGroupSteps() {
		try {
			driver = BrowserManager.getInstance().getWebDriver();
		} catch (Exception e) {
			log.info(e.toString());
		}
	}

	@Then("^User validate Active User Groups Page loaded successfully$")
	public void checkPageloaded() {
		ActiveUsersGroupPage obj = new ActiveUsersGroupPage(driver);
		obj.verifyHomePage();
	}
	
	@Then("^User Validate Active User Groups with more than (\\d+) events$")
	public void checkCount(int count) {
		ActiveUsersGroupPage obj = new ActiveUsersGroupPage(driver);
		obj.checkCountMore(count);
	}
}