Feature:  Stock app navigation

  Scenario: Navigate to a specific screen
    Given the app is open
    And I click the get started button
    And I click the login in button
    And I click login button
    Then I should see the "portfolio_screen"