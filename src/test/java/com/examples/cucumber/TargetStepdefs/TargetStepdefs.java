package com.examples.cucumber.TargetStepdefs;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TargetStepdefs
{
    private static WebDriver driver;

    @Given("I am on the Target home page target.com")
    public void target_homepage()
    {
        // Setup Chrome options
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        // Create the Chrome driver
        driver = new ChromeDriver(options);
        // Maximize the browser window
        driver.manage().window().maximize();
        // Add Implicit wait of 10 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        // Navigate to webpage
        driver.get("https://www.target.com/");
    }

    @When("I search for {string}")
    public void target_search(String search)
    {
        // Enter search string into the search bar
        driver.findElement(By.xpath("//input[@id='search' and @placeholder='Search']")).sendKeys(search);
        // Click the search button
        driver.findElement(By.xpath("//button[@aria-label='go' and @type='submit']")).click();
    }

    @Then("I should get a result for {string} with producer Franklin Sports")
    public void target_search_result(String search)
    {
        // Store actual element
        String actual = driver.findElement(By.xpath(
                "//a[contains(text(),'Franklin Sports All Weather Size 5 Soccer Ball - Blue')]")).getText();
        // Store expected element
        String expected = search;
        // Check for equality
        Assert.assertEquals(expected, actual);
    }

    @Given("I performed the above search")
    public void search()
    {
        // Store actual element
        String actual = driver.findElement(By.xpath("//input[@id='search' and @placeholder='Search']")).getText();
        // Store expected element
        String expected = "soccer ball";
        // Check for equality
        Assert.assertEquals(expected, actual);
    }

    @When("I click on the image of the soccer ball")
    public void click_image()
    {
        // Click on the image
        driver.findElement(By.xpath(
                "//img[@alt='Franklin Sports All Weather Size 5 Soccer Ball - Blue']")).click();
    }

    @Then("I should select \"Shipping\" before adding to cart")
    public void buy_new()
    {
        // Click on shipping tab
        driver.findElement(By.xpath("//button[@data-test='fulfillment-cell-shipping']")).click();
    }

    @Given("I am on the page where \"Shipping\" is selected")
    public void given_on_buy_new()
    {
        WebElement buyNew = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//i[@class='a-icon a-accordion-radio a-icon-radio-active']/following-sibling::h5/div/div/span[text()=' Buy new: ']")));
        Assert.assertTrue(buyNew.isDisplayed());
    }
    @When("I click \"Add to Cart\"")
    public void add_to_cart()
    {
        // Click the add to cart button
        driver.findElement(By.id("add-to-cart-button")).click();
    }
    @Then("I should see a message saying \"Added to cart\"")
    public void check_cart()
    {
        // Store actual element
        String actual = driver.findElement(By.xpath("//div[contains(text(),' (1 item)')]")).getText();
        // Store expected element
        String expected = "Proceed to checkout (1 item)";
        // Check for equality
        Assert.assertEquals(expected, actual);
    }
    @Given("I have performed the above actions")
    public void i_have_performed_the_above_actions()
    {
        // Store actual element
        String actual = driver.getTitle();
        // Store expected element
        String expected = "Amazon.com Shopping Cart";
        // Check for equality
        Assert.assertEquals(expected, actual);
    }

    @When("I click on \"View Cart & Checkout\"")
    public void i_click_on_the_anonymous_shopping_cart()
    {
        // Click on shopping cart
        driver.findElement(By.xpath("//div[@id='nav-cart-count-container']")).click();
    }

    @Then("I should see the soccer ball listed with a subtotal")
    public void i_should_see_the_book_listed_with_a_subtotal()
    {
        // Check element is displayed
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(@id,'sc-subtotal-label-activecart')]")).isDisplayed());
        // Check element is displayed
        Assert.assertTrue(driver.findElement(By.xpath(
                "//span[contains(text(),'Design Patterns: Elements of Reusable') and @class='a-truncate-cut']")).isDisplayed());
    }

    @Given("I am on the above page")
    public void delete_above_page()
    {
        // Store actual element
        String actual = driver.findElement(By.xpath("//*[@id='sc-active-cart']/div/div/div/h1")).getText().toLowerCase();
        // Store expected element
        String expected = "shopping cart";
        // Check for equality
        Assert.assertEquals(expected,actual);
    }

    @When("I click on \"X\"")
    public void click_delete()
    {
        // Click on delete
        driver.findElement(By.xpath("//input[@Value='Delete']")).click();
    }

    @Then("the page should tell me that my Target Cart is empty")
    public void delete_item_from_cart()
    {
        // Store actual element
        WebElement cartEmpty = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(
                        "//h1[contains(text(),'Your Amazon Cart is empty.')]")));
        String actual = cartEmpty.getText();
        // Store expected element
        String expected = "Your Amazon Cart is empty.";
        // Check for equality
        Assert.assertEquals(expected, actual);
        // Close the driver
        driver.quit();
    }
}