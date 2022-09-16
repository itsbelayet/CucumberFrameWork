Feature: Login Functionality

  Background:
    Given User Launch browser
    When User opens URL "https://admin-demo.nopcommerce.com/login"

  @Sanity
  Scenario: Successful Login with Valid Credentials
    And User enters Email as "admin@yourstore.com" and Password as "admin"
    And Click on Login
    Then Page Title should be "Dashboard / nopCommerce administration"
    When User click on Logout link
    Then Page Title should be "Your store. Login"
#    And close browser

#  @Sanity
#  Scenario: Login with In-Valid Credentials
#    And User enters Email as "belayet@gmail.com" and Password as "admin"
#    And Click on Login
#    Then Page Title should be "Dashboard / nopCommerce administration"
#    When User cannot Login


  @Regration
  Scenario Outline: Successful Login with Valid Credentials
    And User enters Email as "<email>" and Password as "<password>"
    And Click on Login
    Then Page Title should be "Dashboard / nopCommerce administration"
    When User click on Logout link
    Then Page Title should be "Your store. Login"
#    And close browser

    Examples:
      | email               | password |
      | admin@yourstore.com | admin    |
      | belayet@gmail.com   | miru     |
#      | admin1@yourstore.com | admin123 |