package eu.specsolutions.courses.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasicHtmlFormExamplePage {
    // keep a reference to the driver
    private final WebDriver driver;

    // the locators of the different elements of the page
    private final By usernameInputBy = By.name("username");
    private final By passwordInputBy = By.name("password");
    private final By submitButtonBy = By.cssSelector("input[type=submit]");

    // basic common functions
    public BasicHtmlFormExamplePage(WebDriver driver) {
        this.driver = driver;
    }

    public void goTo() {
        driver.get("https://testpages.herokuapp.com/styled/basic-html-form-test.html");
    }

    public void assertOnPage() {
        assertEquals("HTML Form Elements", driver.getTitle());
    }

    // submit style -- better for smaller forms

    public void performLogin_submitStyle(String username, String password) {
        driver.findElement(usernameInputBy).sendKeys(username);
        driver.findElement(passwordInputBy).sendKeys(password);
        driver.findElement(submitButtonBy).click();
    }

    // fill style -- better for checking Javascript validations

    public void fillLogin(String username, String password) {
        driver.findElement(usernameInputBy).sendKeys(username);
        driver.findElement(passwordInputBy).sendKeys(password);
    }

    public void submitLogin_fillStyle() {
        driver.findElement(submitButtonBy).click();
    }

    // setXXX style -- when individual values trigger JavaScript logic

    public void setUsername(String username) {
        driver.findElement(usernameInputBy).sendKeys(username);
    }

    public void setPassword(String password) {
        driver.findElement(passwordInputBy).sendKeys(password);
    }

    public void submitLogin_setXXXStyle() {
        driver.findElement(submitButtonBy).click();
    }

    // page flow style -- makes it easier to automate chained events but more coupled (can be combined with any of above)

    public ProcessedFormDetailsPage performLogin_pageFlowStyle(String username, String password) {
        driver.findElement(usernameInputBy).sendKeys(username);
        driver.findElement(passwordInputBy).sendKeys(password);

        driver.findElement(submitButtonBy).click();

        ProcessedFormDetailsPage resultPage = new ProcessedFormDetailsPage(driver);
        resultPage.assertOnPage();
        return resultPage;
    }

    // attempt action style -- for testing validation errors and failure cases (can be combined with many above)

    public Boolean hasErrors() {
        return getErrorMessages().length() > 0;
    }

    public String getErrorMessages() {
        // This is for demonstration purposes only, in real it should collect error messages
        // from the form and combine them to a string, like we did in Exercise 6.
        return "Invalid login";
    }

    public Boolean performLogin_attemptActionStyle(String username, String password) {
        driver.findElement(usernameInputBy).sendKeys(username);
        driver.findElement(passwordInputBy).sendKeys(password);

        driver.findElement(submitButtonBy).click();

        if (hasErrors())
            return false; // failed. the caller can call 'getErrorMessages()' to check the details

        return true;
    }
}
