Feature:  Stock app navigation

  Scenario: i would like to see search for a stock by ticker
    Given a stock exists
    When I search a stock
    Then i should see it

