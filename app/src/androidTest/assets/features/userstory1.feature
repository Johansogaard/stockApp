Feature:  Stock app navigation

  Scenario: I want to view a list of stocks and their current value
    Given The stocks from S&P500 exist
    When I call for the for the stocks from S&P500 and their stock price
    Then I should see the a list of stocks from S&P500 and their stock price

