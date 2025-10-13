@konnective
  Feature: Place an order
    As a user
  where the backend is Konnective CRM

  Scenario Outline: Place an order
    Given user has opened <browser>
    When user has navigated to the funnel "https://www.tech-surplus.net/spaceheater/checkout.php"
    Then user has filled up the <email>
    And user has selected the product type "2"
#    And user selects the <address>
    And user has added the shipping information
      | First Name | Last Name | Phone Number | Address | Zip Code | City      | State |
      | TEST       | TEST      | 1234567890   | TEST    | A1A 1A1  | Test City | +     |
    And user selects an <item>
    And user enters payment information <creditCard>
#    Then user submits the form
#    Then user submits the payment form
#
#    Then user chooses <upsell>

###    And user buys first upsell item
##    Then user buys second upsell item

    Examples:
      | browser | email  | creditCard       | item | upsell | address |
      | chrome  | +      | 7444444444444444 | 1    | 1      | 12      |
#      |         | TEST13 | 7444444444444444 | 2    | 1      | 235     |
#      |         | +      | 7444444444444444 | 3    | 1      | 23      |
#      | safari    | TEST04 | 1444444444444440 | 2    | 2      | 12      |
