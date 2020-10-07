@Regression
Feature: Webuild Regression tests

Background:	
	Given User launch the url of the application
	
	@TC01
    Scenario: Home page validation, Header and footer validations
    Then User validate header present in Home Page
	Then User validate footer icons present in Home Page