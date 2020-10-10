package com.cg.webuild.test.testRunner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		strict = true,
		monochrome = true,
		plugin = { "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				   "html:target/cucumber-html-report",
		           "json:target/cucumber.json", "pretty:target/cucumber-pretty.txt",
		           "usage:target/cucumber-usage.json", 
		           "junit:target/cucumber-results.xml" 
		         },
		features = {"src/test/java/com/cg/webuild/test/feature/Regression.feature"},
		glue={"com.cg.webuild.stepdefinition"}//,
        //tags = {"@TC01"}
		)

public class JunitRunner {

}