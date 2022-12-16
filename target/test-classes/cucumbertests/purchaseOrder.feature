@purchaseOrder
Feature: Purchase the order from Centric Shopping Store

	Background:
	Given I landed on the Centric Shopping Store
	
  @submitOrder
  Scenario Outline: Submit Order processed successfully
    Given I logged in with username <user> and password <pass>
    When I add product <productName> from cart
    And I checkout <productName> and submit the order
    Then "Your order has been successfully processed!" message is displayed on the Confirmation page

    Examples: 
      | user  										| pass 						| productName							|
      | seleniumtest@gmail.com 		| seleniumtest 		| HTC One Mini Blue				|
