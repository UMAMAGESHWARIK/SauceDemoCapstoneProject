Feature: SauceDemo Login

  Scenario: Valid login with standard_user
    Given I open the SauceDemo login page
    When I login with username "standard_user" and password "secret_sauce"
    Then I should be redirected to the inventory page

  Scenario: Invalid login with wrong credentials
    Given I open the SauceDemo login page
    When I login with username "wrong" and password "wrong"
    Then I should see an error message containing "Epic sadface"
