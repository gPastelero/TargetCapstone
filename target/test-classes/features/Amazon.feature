Feature: Amazon
  Scenario Outline: Searching for Design Pattern
    Given I am on the Amazon home page amazon.com
    When I search for "<search>"
    Then I should get a result for "<result>" with authors Erich Gamma, Richard Helm, Ralph Johnson, and John Vlissides
    Examples:
      | search                   | result                                                           |
      | software design patterns | Design Patterns: Elements of Reusable Object-Oriented Software   |

  Scenario: Selected paperback
    Given I performed the above search
    When I click on the image of the book
    Then I should be on a paperback tab where "Buy New" is selected

  Scenario: Add to cart
    Given I am on the page where "Buy New" is selected
    When I click "Add to Cart"
    Then the anonymous shopping cart should have 1 item in it.

  Scenario: Subtotal
    Given I have performed the above actions
    When I click on the anonymous shopping cart,
    Then I should see the book listed with a subtotal

  Scenario: Delete
    Given I am on the above page
    When I click on "Delete"
    Then the page should tell me that my Amazon Cart is empty