package com.cg.webuild.test.testRunner;


import org.testng.annotations.DataProvider;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		strict = true,
		monochrome = true,
		plugin = { "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				   "html:target/cucumber-html-report",
		           "json:target/cucumber.json", "pretty:target/cucumber-pretty.txt",
		           "usage:target/cucumber-usage.json", 
		           "junit:target/cucumber-results.xml",
		         },
		features = "src/test/java/com/cg/webuild/test/feature/Regression.feature",
		glue={"com.cg.webuild.stepdefinition"}//,
		//tags = {"@TC02"}
		)

public class TestNgRunner extends AbstractTestNGCucumberTests{
	
	@Override
    @DataProvider(parallel=true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}