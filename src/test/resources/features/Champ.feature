@champ
Feature: To open the checkout champ
  order pages
  for taking screenshots

  Scenario: Open CheckoutChamp in chrome browser
Given user has navigated to the admin "https://crm.checkoutchamp.com/admin/users/user/?userId=203987"
    When user gives "devclouds21" and "Dev2025!" and logs in
    Then user provides the orders
      | 4386F66CBC | A6F4F57F91 | 7102418A0A | 7998D438C9 | E97A81FA1D | 9498C8B4CC |
