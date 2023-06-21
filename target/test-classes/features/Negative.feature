Feature: Negative

  @negative
  Scenario: Going to the login page
    Given I am on the Target home page target.com
    When I click on the Sign in button
    Then I should see a side panel with account options
    When I click on the "Create Account" button
    Then I should be redirected to the login page
    And I can enter all of my account information

  @negative
  Scenario Outline: Password validation
    When I enter my <Password>, it should check if <Valid>
    Examples:
      | Password      | Valid  |
      |"qeaTeam1"     |"true"  |
      |"password1"    |"true"  |
      |"ABCDEFGh"     |"true"  |
      |"-1-1-1-1-1-1" |"true"  |
      |"abcdefghijklm"|"false" |
  Scenario: Cleanup
    Then I close the browser

