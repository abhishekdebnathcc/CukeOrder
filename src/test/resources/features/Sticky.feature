@sticky
Feature: Open the sticky admin and check the orders

  Scenario: Open the sticky admin
  Use the orders from the list
  search the respective orders


    Given user has navigated to the admin "https://virellosolutions.sticky.io/"
    When user provides "devcc" and "\z7rBd~36G9E$"
    And user logs in to the admin
    Then user searches with email address
#    Then user searches with order no