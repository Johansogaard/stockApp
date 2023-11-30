Feature:  Stock app navigation

  Scenario: I want to check "Apple" stock price
    Given I have connection to the api
    When I call for the "Apple" stock price
    Then I should see the Apple stock price

