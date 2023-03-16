Feature: Target
  Scenario Outline: Searching for Soccer Ball
    Given I am on the Target home page target.com
    When I search for "<Search>"
    Then I should get a result for "<Result>" with producer Franklin Sports
    Examples:
      | Search      | Result                                                |
      | soccer ball | Franklin Sports All Weather Size 5 Soccer Ball - Blue |
