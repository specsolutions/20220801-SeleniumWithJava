package eu.specsolutions.courses.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProcessedFormDetailsPage {

    private final WebDriver driver;

    private final By usernameListItemBy = By.id("_valueusername");
    private final By passwordListItemBy = By.id("_valuepassword");

    public ProcessedFormDetailsPage(WebDriver driver) {

        this.driver = driver;
    }

    public void assertOnPage() {
        assertEquals("Processed Form Details", driver.getTitle());
    }

    public String getUsername() {
        return driver.findElement(usernameListItemBy).getText();
    }
    public String getPassword() {
        return driver.findElement(passwordListItemBy).getText();
    }
}
