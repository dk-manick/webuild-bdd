@Regression
Feature: Webuild Regression tests

Background:	
	Given User launch the url of the application
	
	@TC01
    Scenario: Home page validation, Header and footer validations
    Then User validate that home page is loaded successfully
    Then User validate header present in Home Page
	Then User validate footer icons present in Home Page
	
	@TC02
    Scenario: Active user group validations with more than 5 Events
  	Then User validate that home page is loaded successfully
	When User clicks on "active user groups" link in Home Page
	Then User validate Active User Groups Page loaded successfully
	And User Validate Active User Groups with more than 10 events
	
	@TC03
    Scenario Outline: Repositories per programming language page validation
  	Then User validate that home page is loaded successfully
	When User clicks on "repositories per programming language" link in Home Page
	Then User validate Repositories per programming language Page loaded successfully
	When User clicks on "<language>" in Repositories Page
	Then User validate the "<repositories>" are listed in Repositories Page
	
	Examples:
	|language|repositories                           |
	|Java    |teammates,materialistic,FaceRecognition|
	|C++     |CurvatureFilter,cashew,uSpeech         |