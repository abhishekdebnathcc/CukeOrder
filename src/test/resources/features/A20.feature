@a20
Feature: Open A20
  As a user and
  take screenshots

  Scenario: Open A20 in edge browser
    Given user navigates to the website
    When user clicks on google authentication
    And user enters "abhishek.debnath@codeclouds.com" and "cispl123"
    Then user naviagtes to accounts page
    When user submits id "2323"
    Then user signs into the A20 control pages
    And user navigates to Initial Control
    Then user provides the order numbers "816091, 816092,816081, 816082,816083, 816084"
    And user navigates to Subscription Control
    Then user provides the same order numbers ""
    And user navigates to Subscription Control
    Then user provides the same order numbers ""
    And user edits order properties
  And user checks data capture
  Then user adds the order numbers

#    When user provides "codeclouds" and "~cjoRG7T<91/"

