package com.cg.webuild.stepdefinition;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import com.cg.webuild.helper.BrowserManager;
import com.cg.webuild.helper.BaseProperties;
import com.cg.webuild.helper.ReportAdapter;
import com.cg.webuild.helper.UtilClass;
import com.cg.webuild.pages.HomePage;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HomeSteps extends UtilClass {

	public WebDriver driver;
	private static Logger log = Logger.getLogger(HomeSteps.class);
	
	@Before
	public void runBeforeScenario(Scenario s) {
		String featureName=s.getId().split(";")[0];
		log.info("**********Started executing scenario : " + s.getName() + "**********");
		log.info("Feature Name : " +  featureName);
		try {
			driver = BrowserManager.getInstance().getDriver();
			ReportAdapter.setSystemInfo("Env", "staging");
		} catch (Exception e) {
			log.info(e.toString());
		}
	}

	@After
	public void runAfterScenario(Scenario s) {
		String status="PASSED";
		if (s.isFailed()) {
			screenShot(driver);
			status="FAILED";
		}
		BrowserManager.getInstance().quitDriver();
		log.info("**********Completed executing scenario : " + s.getName() + "  --->  " + status + "**********\n\n");
	}

	@Given("^User launch the url of the application$")
	public void loadWebuild() {
		log.info("Opening the Url - " + BaseProperties.url);
		driver.get(BaseProperties.url);
	}

	@Then("^User validate that home page is loaded successfully$")
	public void checkHomePageloaded() {
		HomePage obj = new HomePage(driver);
		obj.verifyHomePage();
	}
	
	@Then("^User validate header present in Home Page$")
	public void checkHeader() {
		HomePage obj = new HomePage(driver);
		obj.verifyHeader();
	}
	
	@Then("^User validate footer icons present in Home Page$")
	public void checkFooter() {
		HomePage obj = new HomePage(driver);
		obj.verifyFooter();
	}
	
}
