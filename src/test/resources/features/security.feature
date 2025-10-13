@security
Feature: To check the security of the funnel
  As a user
  By automating report generation

  Scenario Outline: Run security checks in different browser windows
    Given The base url is "https://www.brighthorizonmarket.com/v1/gianttue/"
#    And the checkout url is "https://www.primewayoutlet.com/checkout.php?AFFID=test&C1=testc1&C2=testc2&C3=testc3&prospect_id=378770"
#    And thank you url is "https://www.primewayoutlet.com/thank-you.php?AFFID=test&C1=testc1&C2=testc2&C3=testc3"
    When user has started <Browser>
    Then user navigates to security testing <Link>
    And user generates the report

    Examples:
      | Browser | Link                |
#      | edge    | http and robots.txt |
#      |         | https://tools.keycdn.com/http2-test                              |
#      |         | https://validator.w3.org/nu/                                     |
#      | chrome  | https://www.whatsmydns.net/                                      |
#      |         | https://transparencyreport.google.com/safe-browsing/search?hl=en |
#      |         | https://www.digicert.com/help/                                   |
#      |         | https://wheregoes.com/                                           |
#      |         | https://www.ssllabs.com/ssltest/                                 |
#      | chrome  | https://gtmetrix.com/                                            |
#      | Chrome  | https://pagespeed.web.dev/                                       |
      |         | https://www.opengraph.xyz/                                       |


