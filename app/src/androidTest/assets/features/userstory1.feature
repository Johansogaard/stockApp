Feature:  Stock app navigation

  Scenario: Navigate to a specific screen
    Given i start the application
    When I click the "Get Started" button
    Then I should see the "login_screen"
