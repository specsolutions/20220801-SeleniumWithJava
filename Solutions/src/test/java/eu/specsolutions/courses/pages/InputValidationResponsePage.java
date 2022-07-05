package eu.specsolutions.courses.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputValidationResponsePage {

    private final By firstNameListItemBy = By.id("firstname-value");
    private final By lastNameListItemBy = By.id("surname-value");
    private final By ageListItemBy = By.id("age-value");
    private final By countryListItemBy = By.id("country-value");
    private final By notesListItemBy = By.id("notes-value");

    private final WebDriver driver;

    public InputValidationResponsePage(WebDriver driver) {
        this.driver = driver;
    }

    public void assertOnPage() {
        assertEquals("Input Validation Page", driver.getTitle());
    }

    public String getFirstName() {
        return driver.findElement(firstNameListItemBy).getText();
    }

    public String getLastName() {
        return driver.findElement(lastNameListItemBy).getText();
    }

    public int getAge() {
        return Integer.parseInt(driver.findElement(ageListItemBy).getText());
    }

    public String getCountry() {
        return driver.findElement(countryListItemBy).getText();
    }

    public String getNotes() {
        return driver.findElement(notesListItemBy).getText();
    }
}
