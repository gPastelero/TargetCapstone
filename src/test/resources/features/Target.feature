Feature: Target
  Scenario Outline: Searching for Soccer Ball
    Given I am on the Target home page target.com
    When I search for "<Search>"
    Then I should get a result for "<Result>" with producer Franklin Sports
    Examples:
      | Search      | Result                                                |
      | soccer ball | Franklin Sports All Weather Size 5 Soccer Ball - Blue |

  Scenario: Shipping Selected
    Given I performed the above search
    When I click on the image of the soccer ball
    Then I should select "Shipping" before adding to cart

  Scenario: Add to cart
    Given I am on the page where "Shipping" is selected
    When I click "Add to Cart"
    Then I should see a message saying "Added to cart"

  Scenario: View Cart & Checkout
    Given I have performed the above actions
    When I click on "View Cart & Checkout"
    Then I should see the soccer ball listed with a subtotal

  Scenario: Delete Item
    Given I am on the above page
    When I click on "X"
    Then the page should tell me that my Target Cart is empty