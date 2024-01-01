Feature:  Stock app navigation

  Scenario: i would like to see a stock with detailed info
    Given a stock exist
    When I call for the stock information
    Then I should see stock information

