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
    public void I_am_on_the_target_home_page()
    {
        // Setup Chrome options
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("window-size=1920, 1080");
        // Create the Chrome driver
        driver = new ChromeDriver(options);
        // Maximize the browser window
        driver.manage().window().maximize();
        // Add Implicit wait of 10 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // Navigate to webpage
        driver.get("https://www.target.com/");
    }

    @When("I search for {string}")
    public void I_search_for(String search)
    {
        // Enter search string into the search bar
        driver.findElement(By.xpath("//input[@id='search' and @placeholder='Search']")).sendKeys(search);
        // Click the search button
        driver.findElement(By.xpath("//button[@aria-label='go' and @type='submit']")).click();
    }

    @Then("I should get a result for {string} with producer Franklin Sports")
    public void I_should_get_a_result_for_string_with_producer_Franklin_Sports(String search)
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
    public void I_performed_the_above_search()
    {
        // Store actual element
        String actual = driver.findElement(By.xpath("//input[@id='search' and @placeholder='Search']")).getAttribute("value");
        // Store expected element
        String expected = "soccer ball";
        // Check for equality
        Assert.assertEquals(expected, actual);
    }

    @When("I click on the image of the soccer ball")
    public void I_click_on_the_image_of_the_soccer_ball()
    {
        // Click on the image
        WebElement soccerBall = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(
                        "//img[@alt='Franklin Sports All Weather Size 5 Soccer Ball - Blue']")));
        soccerBall.click();
    }

    @Then("I should select \"Shipping\" before adding to cart")
    public void I_should_select_Shipping_before_adding_to_cart()
    {
        // Click on shipping tab
        driver.findElement(By.xpath("//button[@data-test='fulfillment-cell-shipping']")).click();
    }

    @Given("I am on the page where \"Shipping\" is selected")
    public void I_am_on_the_page_where_shipping_is_selected()
    {
        String title = driver.getTitle();
        Assert.assertEquals(title, "Franklin Sports All Weather Size 5 Soccer Ball - Blue : Target");
    }
    @When("I click \"Add to Cart\"")
    public void I_click_add_to_cart()
    {
        //
        WebElement addToCart = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(
                        "//button[text()='Add to cart']")));
        // Click the add to cart button
        addToCart.click();
    }
    @Then("I should see a message saying \"Added to cart\"")
    public void I_should_see_a_message_saying_added_to_cart()
    {
        // Store actual element
        WebElement addedToCart = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                        "//span[text()='Added to cart']")));
        String actual = addedToCart.getText();
        // Store expected element
        String expected = "Added to cart";
        // Check for equality
        Assert.assertEquals(expected, actual);
    }
    @Given("I have performed the above actions")
    public void i_have_performed_the_above_actions()
    {
        // Store actual element
        String actual = driver.findElement(By.xpath(
                "//h4[text()='Franklin Sports All Weather Size 5 Soccer Ball - Blue']")).getText();
        // Store expected element
        String expected = "Franklin Sports All Weather Size 5 Soccer Ball - Blue";
        // Check for equality
        Assert.assertEquals(expected, actual);
    }

    @When("I click on \"View Cart & Checkout\"")
    public void i_click_on_the_anonymous_shopping_cart()
    {
        // Click on shopping cart
        driver.findElement(By.xpath("//a[@href='/cart']")).click();
    }

    @Then("I should see the soccer ball listed with a subtotal")
    public void i_should_see_the_book_listed_with_a_subtotal()
    {
        // Check element is displayed
        Assert.assertTrue(driver.findElement(By.xpath(
                "//div[text()='Franklin Sports All Weather Size 5 Soccer Ball - Blue']")).isDisplayed());
        // Check element is displayed
        Assert.assertTrue(driver.findElement(By.xpath(
                "//span[text()='Subtotal']")).isDisplayed());
    }

    @Given("I am on the above page")
    public void i_am_on_the_above_page()
    {
        // Store actual element
        String actual = driver.findElement(By.xpath("//h1[contains(text(),'Cart')]")).getText().toLowerCase();
        // Store expected element
        String expected = "cart";
        // Check for equality
        Assert.assertEquals(expected,actual);
    }

    @When("I click on \"X\"")
    public void I_click_on_x()
    {
        // Get the x element
        WebElement x = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(
                        "//button[@data-test='cartItem-deleteBtn']")));
        // Click on X
        x.click();
    }

    @Then("the page should tell me that my Target Cart is empty")
    public void the_page_should_tell_me_that_my_target_cart_is_empty()
    {
        // Store actual element
        WebElement cartEmpty = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(
                        "//h1[contains(text(),'Your cart is empty')]"))));
        String actual = cartEmpty.getText();
        // Store expected element
        String expected = "Your cart is empty";
        // Check for equality
        Assert.assertEquals(expected, actual);
        // Close the driver
        driver.quit();
    }
}