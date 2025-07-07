@api_contract
Feature: Addition API Endpoint
  As a system integrator
  I want the API to behave as documented
  So that I can rely on it for automated use

  Scenario: Successful addition with valid input
    When I send a GET request to "/api/add?a=10&b=20"
    Then the response code should be 200
    And the response content-type should be "application/json"
    And the response body should contain:
      | key    | value |
      | result | 30    |

  Scenario: Missing required query parameter
    When I send a GET request to "/api/add?a=10"
    Then the response code should be 400
    And the response body should contain error message "Missing parameter: b"

  Scenario: Invalid input type
    When I send a GET request to "/api/add?a=abc&b=10"
    Then the response code should be 400
    And the response body should contain error message "Invalid input type"
