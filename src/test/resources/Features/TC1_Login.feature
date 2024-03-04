@LoginAPI
Feature: Login Module API automation

  Scenario: Get user logtoken from login endpoint
    Given User add Header
    And User add basic authentication for login
    When User send "POST" request for Login endpoint
    Then User verify the status code is 200
    And User verify the login response body firstName present as "Suresh" and get the logtoken
