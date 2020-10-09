package com.cg.webuild.pages;

import static org.junit.Assert.assertTrue;
import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.cg.webuild.helper.UtilClass;

public class RepositoriesPage extends UtilClass {

	private static Logger log = Logger.getLogger(RepositoriesPage.class);
	private WebDriver driver;
	
	@FindBy(xpath = "//h3[text()='repositories per programming languages']")
	private WebElement lnkHeaderText;
	
	@FindBy(xpath = "//label")
	private List<WebElement> radioLanguages;
	
	@FindBy(xpath = "//div[@id='repos-per-programming-language']//a")
	private List<WebElement> lnkRepo;

	public RepositoriesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void verifyHomePage() {
		assertTrue("Repositories per program language Page not loaded", isElementPresent(driver, lnkHeaderText));
		screenShot(driver);
		log.info("Home Page loaded successfully");
	}
	
	public void clickOnLink(String language) {
		for(WebElement event : radioLanguages)
		{
			if(event.getText().equalsIgnoreCase(language))
				event.click();
		}
		screenShot(driver);
	}
	
	public void verifyRepo(String repo) {
		String[] expected = repo.split(",");
		int count=0;
		for(int i=0;i<expected.length;i++) {
			for(WebElement repository : lnkRepo)
			{
				if(repository.getText().equalsIgnoreCase(expected[i]))
					count++;
			}
		}
		screenShot(driver);
		assertTrue("Repositories is not listed", count==expected.length);
	}
}