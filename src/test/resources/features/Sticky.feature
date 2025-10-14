@sticky
Feature: Open the sticky admin and check the orders

  Scenario: Open the sticky admin
  Use the orders from the list
  search the respective orders


    Given user has navigated to the admin "https://virellosolutions.sticky.io/admin/login.php"
    When user provides "ACruz" and "9dqj88qasfhtBP27"
    And user logs in to the admin
    Then user searches with email address
#    Then user searches with order no