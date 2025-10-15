@champ
Feature: To open the checkout champ
  order pages
  for taking screenshots

  Scenario: Open CheckoutChamp in chrome browser
Given user has navigated to the admin "https://crm.checkoutchamp.com/admin/users/user/?userId=203987"
    When user gives "devclouds21" and "Dev2025!" and logs in
    Then user provides the orders
      | 198054A3BC | 6DF26B27D3 | 810AE1AB9D | F42E00C72B | 2870BE967F | E21E410840 |  |
