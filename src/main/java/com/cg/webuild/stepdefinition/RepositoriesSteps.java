package com.cg.webuild.stepdefinition;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import com.cg.webuild.helper.BrowserManager;
import com.cg.webuild.helper.UtilClass;
import com.cg.webuild.pages.RepositoriesPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RepositoriesSteps extends UtilClass {

	public WebDriver driver;
	private static Logger log = Logger.getLogger(RepositoriesSteps.class);
	
	public RepositoriesSteps() {
		try {
			driver = BrowserManager.getInstance().getWebDriver();
		} catch (Exception e) {
			log.info(e.toString());
		}
	}

	@Then("^User validate Repositories per programming language Page loaded successfully$")
	public void checkPageloaded() {
		RepositoriesPage obj = new RepositoriesPage(driver);
		obj.verifyHomePage();
	}
	
	@When("^User clicks on \"([^\"]*)\" in Repositories Page$")
	public void clickLink(String link) throws InterruptedException {
		RepositoriesPage obj = new RepositoriesPage(driver);
		obj.clickOnLink(link);
	}
	
	@Then("^User validate the \"([^\"]*)\" are listed in Repositories Page$")
	public void checkRepo(String repo) {
		RepositoriesPage obj = new RepositoriesPage(driver);
		obj.verifyRepo(repo);
	}
}