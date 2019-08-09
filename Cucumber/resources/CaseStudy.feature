Feature: Register on TestMeApp
  I want to use this template for my feature file

  @tag1
  Scenario: Successfully register on TestMeApp
    Given Navigate to Home Page
    And Go to the signIn
    When user enter username as "gjakhar123452"
    When user enter firstname as "FName"
    When user enter lastname as "LName"
    When user enter password as "PassWord124"
    When user enter confirm password as "PassWord124"
    When user select gender as "Male"
    And user enter email as "Fname.Lname@email.com"
    And user enter mobilenumbner as "7000887123"
    When user enter dob as "12/04/2013"
    When user enter address as "Adrress is in Mumbai"
    And user selects security question as "What is your favour color?" and enter answer as "Red"
    Then user clicks on Register
    And verify he is registered successsfully

  @Case4
  Scenario Outline: Login with valid credentials
    Given Navigate to Home Page
    When user enters "<username>" and "<password>"
    Then user logged in successfully

		Examples:
    | username | password  |
    | lalitha  | password123 |

  @Case4
  Scenario: The one where user picks different product through search functionality
    Given Larry has registered in to TestMeApp
    When Larry searches below products in the search box:
      | Head |
    Then product should be added in the cart if available

  @Case4
  Scenario: The user goes in payment page
    Given product added in the cart
    When user open cart
    And user click on checkout
    And user fills address as "hhshdghd" details
    And user clicks on proceed to pay
    Then verify Welcome to payment Gateway
