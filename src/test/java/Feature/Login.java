package Feature;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Login {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path_to_chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Given("User is on Home Page")
    public void user_is_on_home_page() {
        driver.get("https://www.saucedemo.com/");
    }

    @When("User enters username and password from Excel")
    public void user_enters_username_and_password_from_excel() throws IOException, InterruptedException {
        Object[][] data = FileReader.readData("path_to_excel_file.xlsx", "Sheet1");
        String username = (String) data[0][0];
        String password = (String) data[0][1];

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        Thread.sleep(5000);
    }

    @Then("User clicks on login button")
    public void user_clicks_on_login_button() {
        WebElement loginButton = driver.findElement(By.name("login-button"));
        loginButton.click();
    }

    @Then("User should be logged in")
    public void user_should_be_logged_in() {
        System.out.println("Login Successfully");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
