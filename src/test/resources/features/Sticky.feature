@sticky
Feature: Open the sticky admin and check the orders

  Scenario: Open the sticky admin
  Use the orders from the list
  search the respective orders


    Given user has navigated to the admin "https://88startech.sticky.io/"
    When user provides "CC Dev" and "dz0UFE7em&2Z0&w#$##"
    And user logs in to the admin
    Then user searches with email address
#    Then user searches with order no