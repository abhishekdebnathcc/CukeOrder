@session
Feature:  Testing the session
  As a user
  in chrome browser

  Scenario Outline: Testing the session
    Given user has opened chrome
    When user has navigated to the funnel "https://www.brighthorizonmarket.com/v1/gianttue/"
    Then user has filled up the <email>
    And user has added the shipping information
      | Phone Number | First Name | Last Name | Address      | City      | State | Zip Code |  |
      | 1234567890   | TEST FIRST | TEST LAST | TEST ADDRESS | TEST CITY | +     | 12345    |  |
    And user has submitted the form
    Then user has typed the <creditCardNumber> and  <paymentType>
    And user has selected the order type as <orderType> and payment type as <paymentType>
    Then user opens a new tab
    When user has navigated to the funnel "https://www.brighthorizonmarket.com/v1/gianttue/"
    Then user has filled up the <email>
    And user has added the shipping information
      | Phone Number | First Name | Last Name | Address      | City      | State | Zip Code |  |
      | 1234567890   | TEST FIRST | TEST LAST | TEST ADDRESS | TEST CITY | +     | 12345    |  |
    And user has submitted the form
    Then user has typed the <creditCardNumber> and  <paymentType>
    And user has selected the order type as <orderType> and payment type as <paymentType>
    Then user submits the order
    And collect the order information from url
    Then user returns to the previous tab
    And user submits the order

    Examples:
      | email | creditCardNumber | orderType | paymentType |
  |       | 1444444444444440 | Regular   | Visa        |
#      |       | 5111111111111111 | Regular   | Master      |
