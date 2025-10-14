@order
Feature: Open the funnel
  As a user
  and place orders
  without selecting an item

  @sweep
  Scenario Outline: Place orders without selecting any item
    Given user has opened <browser>
    When user has navigated to the funnel "https://www.glownestgoods.com/v1/walmartbox/?AFFID=test&C1=testc1&C2=testc2&C3=testc3"
    Then user has filled up the <email>
    And user has added the shipping information
      | Phone Number | First Name | Last Name | Address      | City      | State | Zip Code |  |
      | 1234567890   | TEST       | TEST      | TEST ADDRESS | TEST CITY | +     | 12345    |  |
    And user has submitted the form
#
    Then user has typed the <creditCardNumber> and  <paymentType>
    And user has selected the order type as <orderType> and payment type as <paymentType>
########
    And user has entered the billing information
      | First Name | Last Name | Address      | City      | State | Zip Code |
      | BILL FIRST | BILL LAST | BILL ADDRESS | BILL CITY | +     | 54321    |
#########
    Then user submits the order
    And collect the order information from url

    Examples:
      | browser | email | creditCardNumber | orderType | paymentType |
      |         |       | 1444444444444440 | Regular   | VISA        |
#      |         | | 1444444444444443 | Regular   | Master      |
#      |         |       | 1444444444444442 | Prepaid   | VISA        |
#      |         | | 1444444444444446 | Prepaid   | Master      |
#      |         |       | 1444444444444441 | Decline   | Visa        |
#      |         |       | 1444444444444445 | Decline   | Master      |
#      |         | | 4147090000000001 | Bin       | VISA        |
#      |         | | 5156760000000001 | Bin       | Master      |


#      |         |       | 5111111111111111 | Regular   | Master      |
#      |         |       | 4124511111111111 | Bin       | Visa        |
#      |         |       | 5156761111111111 | Bin       | Master      |




#      |         | test07 | 0000000000000000 | Regular        | Discover      |



