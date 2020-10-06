@Regression
Feature: Webuild Regression tests
	
	@TC01
    Scenario: Home page validation, Header and footer validations
    Given User loads the application 
	Then User validate that home page is loaded successfully
	And User validate header present in Home Page
	And User validate footer icons present in Home Page