Feature: Login Functionality

@LogIn
  Scenario Outline: Login with valid credentials
    Given Navigate to Home Page
    When user enters "<username>" and "<password>"
    Then user logged in successfully
   
   Examples:
   |username|password|
   |Admin|password456|
   |lalitha|password123|

@data-driven
Scenario: The one where user picks different product through search functionality
	Given Larry has registered in to TestMeApp
	When Larry searches below products in the search box:
	 			|Head|
	 			|Travel|
	 			|Laptop|
	 	Then product should be added in the cart if available 
	
	