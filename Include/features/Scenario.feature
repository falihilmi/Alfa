@tag
Feature: Login
  I want to Login and see home alfagift

Background: 
Given I open application alfagift
## Account testing valid 085155055863 password : AdminTesting123
  @tag1
  Scenario Outline: Login success and direct to page dashboard 
    Given go to login page
    When I input credential <phone> and <password>
    And click button login
    Then I navigated home alfagift Verify <user> Account

    Examples: 
      | phone |password |user|
      | 085155055863 |AdminTesting123 |testing|
      
      
   @tag2
  Scenario: Login success and relogin with password invalid in device has logged in
    Given go to login page
    When I input credential 085155055863 and AdminTesting123
    And click button login
    And logout account
    When I input credential 085155055863 and invalidPassword 
    Then I see failed login with invalid phone number device has logged in

  @tag3
  Scenario: Login with phone invalid
    Given go to login page
    When I input credential 0852222222 and AdminTesting123
    And click button login
    Then I see error login invalid phone
    
    
      @tag4
 		Scenario: Login with phone valid and password invalid
    Given go to login page
    When I input credential 085155055863 and InvalidPass
    And click button login
    Then I see error login password invalid
    
     