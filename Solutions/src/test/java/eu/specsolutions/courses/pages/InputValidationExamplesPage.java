package eu.specsolutions.courses.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputValidationExamplesPage {
    private final WebDriver driver;

    private final By firstnameInputBy = By.id("firstname");
    private final By lastnameInputBy = By.id("surname");
    private final By ageInputBy = By.id("age");
    private final By countrySelectBy = By.id("country");
    private final By notesTextAreaBy = By.id("notes");
    private final By submitButtonBy = By.cssSelector("input[type=submit]");

    private final By errorMessagesDivBy = By.className("errormessage");
    private final By inputTagsBy = By.tagName("input");

    public InputValidationExamplesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goTo() {
        driver.get("https://testpages.herokuapp.com/styled/validation/input-validation.html");
    }

    public void assertOnPage() {
        assertEquals("Input Validation", driver.getTitle());
    }

    public Boolean hasErrors() {
        return getErrorMessages().length() > 0;
    }

    public String getErrorMessages() {
        Stream<WebElement> visibleErrorMessageDivs = driver.findElements(errorMessagesDivBy).stream()
                .filter(e -> e.getCssValue("visibility").contentEquals("visible"));
        Stream<String> errorMessagesFromDivs = visibleErrorMessageDivs
                .map(WebElement::getText);

        Stream<WebElement> inputsWithBrowserValidationErrors = driver.findElements(inputTagsBy).stream()
                .filter(e -> !e.getAttribute("validationMessage").contentEquals(""));
        Stream<String> errorMessagesFromBrowserValidationErrors = inputsWithBrowserValidationErrors
                .map(e -> e.getAttribute("validationMessage"));

        List<String> allErrorMessages = Stream.concat(errorMessagesFromDivs, errorMessagesFromBrowserValidationErrors)
                .collect(Collectors.toList());
        return String.join(", ", allErrorMessages);
    }

    public Boolean submitInputForm(String firstName, String lastName, int age, String country, String notes) {

        driver.findElement(firstnameInputBy).sendKeys(firstName);
        driver.findElement(lastnameInputBy).sendKeys(lastName);
        driver.findElement(ageInputBy).sendKeys(String.valueOf(age));
        Select countrySelect = new Select(driver.findElement(countrySelectBy));
        countrySelect.selectByValue(country);
        driver.findElement(notesTextAreaBy).sendKeys(notes);

        driver.findElement(submitButtonBy).click();

        return !hasErrors();
    }
}
