@errorValidation
Feature: Error Validation

  @invalidUserID
  Scenario Outline: Invalid UserID
  	Given I landed on the Centric Shopping Store    
		Given I logged in with username <user> and password <pass>
		Then "Login was unsuccessful. Please correct the errors and try again." error message should be displayed for "username"
    Examples: 
      | user  										| pass 						|
      | test@gmail.com						| seleniumtest 		|
      
  
  @invalidPass
  Scenario Outline: Invalid Password
  	Given I landed on the Centric Shopping Store    
		Given I logged in with username <user> and password <pass>
		Then "The credentials provided are incorrect" error message should be displayed for "password"
		
    Examples: 
      | user  										| pass 								|
      | test@gmail.com						| invalidPassword 		|
