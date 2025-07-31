@konnective
  Feature: Place an order
    As a user
  where the backend is Konnective CRM

  Scenario Outline: Place an order
    Given user has opened <browser>
    When user has navigated to the funnel "https://getlumibat.com/checkout0/?AFFID=test&C1=testc1&C2=testc2&C3=testc3"
    Then user has filled up the <email>
    And user selects the <address>
    And user has added the shipping information
      | First Name   | Last Name  | Phone Number | Address      | Zip Code | City      | State   |
      | TEST   FNAME | TEST LNAME | 1234567890   | Test Address | 12345    | Test City | Florida |
#    Then user submits the form
#    And user selects an <item>
#    And user enters payment information <creditCard>
#    Then user submits the payment form

#    Then user chooses <upsell>
###    And user buys first upsell item
##    Then user buys second upsell item

    Examples:
      | browser | email  | creditCard       | item | upsell | address |
      | chrome  | TEST08 | 1444444444444440 | 2    | 1      | 12      |
#      | edge  | TEST13 | 1444444444444440 | 2    | 0      | 235     |
#      | firefox | TEST03 | 1444444444444440 | 3    | 1      | 23      |
#      | safari    | TEST04 | 1444444444444440 | 2    | 2      | 12      |
