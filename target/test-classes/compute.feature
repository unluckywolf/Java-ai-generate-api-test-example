@calculator
Feature: Simple Addition API
  As a user
  I want to send two numbers to the API and get their sum
  So that I can perform simple addition operations programmatically

  Scenario: Add two positive integers
    Given the calculator service is running
    When I send a request to add the numbers:
      | numberA | numberB |
      | 10      | 20      |
    Then the response should be:
      | result |
      | 30     |

  Scenario: Add a positive and a negative number
    Given the calculator service is running
    When I send a request to add the numbers:
      | numberA | numberB |
      | 15      | -5      |
    Then the response should be:
      | result |
      | 10     |

  Scenario: Add two negative numbers
    Given the calculator service is running
    When I send a request to add the numbers:
      | numberA | numberB |
      | -7      | -3      |
    Then the response should be:
      | result |
      | -10    |

  Scenario: Add zero and a number
    Given the calculator service is running
    When I send a request to add the numbers:
      | numberA | numberB |
      | 0       | 999     |
    Then the response should be:
      | result |
      | 999    |

  Scenario: Add two decimal numbers
    Given the calculator service is running
    When I send a request to add the numbers:
      | numberA | numberB |
      | 2.5     | 3.1     |
    Then the response should be:
      | result |
      | 5.6    |
