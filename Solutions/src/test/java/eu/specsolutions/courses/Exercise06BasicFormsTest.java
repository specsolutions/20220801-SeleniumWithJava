package eu.specsolutions.courses;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class Exercise06BasicFormsTest extends ExerciseTestBase {

    @Test
    public void TaskA() {
        driver = new ChromeDriver();
        driver.get("https://testpages.herokuapp.com/styled/validation/input-validation.html");

        // 1. The opened page contains a form with different fields.
        //    For the text input fields (e.g. <input type=text>) you can simulate typing in some text
        //    with the 'sendKeys' method.
        //    Set 'Zaphod' as first name in the form.
        WebElement firstNameTextInput = driver.findElement(By.id("firstname"));
        firstNameTextInput.sendKeys("Zaphod");

        // 2. Set 'Beeblebroxx' as last name (the typo in the name - double 'x' - is intentional!)
        //    To simply send some text to an element, you don't have to save it to a variable
        //    first. You can do it in one line as well...
        driver.findElement(By.id("surname")).sendKeys("Beeblebroxx");

        // 3. Set '42' as age.
        driver.findElement(By.id("age")).sendKeys("42");

        // 4. Ops. The age should have been 43. Update the age to 43 without removing the first
        //    'sendKeys' call above. What happens? How to fix this? (Hint: 'element.clear()')
        WebElement ageNumberInput = driver.findElement(By.id("age"));
        ageNumberInput.clear();
        ageNumberInput.sendKeys("43");

        // 5. Submit the form by clicking the submit button.
        //    As this button does not have an ID, you can try with a CSS selector 'input[type=submit]'.
        driver.findElement(By.cssSelector("form[name=userdata] input[type=submit]")).click();

        // 6. Consider using the CSS selector 'form[name=userdata] input[type=submit]' instead of the one
        //    you provided for the submit button. What is the difference? When would this be better?

        // 7. The following assertions verify that the form was successfully submitted
        //    and the right values were provided. (Uncomment them.)
        //    Why do we assert for the browser title?
        assertEquals("Input Validation Page", driver.getTitle());
        assertEquals("Zaphod", driver.findElement(By.id("firstname-value")).getText());
        assertEquals("Beeblebroxx", driver.findElement(By.id("surname-value")).getText());
        assertEquals("43", driver.findElement(By.id("age-value")).getText());

        pause();
    }

    @Test
    public void TaskB() {
        driver = new ChromeDriver();
        driver.get("https://testpages.herokuapp.com/styled/validation/input-validation.html");

        // 1. The following code fills out the form and submits it.
        //    Run the test. What happens? Why does the test not fail?
        driver.findElement(By.id("firstname")).sendKeys("Zaphod");
        driver.findElement(By.id("surname")).sendKeys("Beeblebrox");
        driver.findElement(By.id("age")).sendKeys("43");
        driver.findElement(By.cssSelector("form[name=userdata] input[type=submit]")).click();

        pause();

        // 2. Assert that the browser title should be 'Input Validation Page'.
        //    Now the test fails, but the error message is not very descriptive... How could we make it better?

        // 3. The method 'getErrorMessagesOnForm' below tries to collect the validation errors from the form
        //    and provides it as a comma separated list of messages.
        //    Use this value to provide a better error message for the assertion! (Hint: You can provide
        //    a third argument for the 'assertEquals' method with the error message to be reported.
        String errorMessagesOnForm = getErrorMessagesOnForm();
        //assertEquals("Input Validation Page", driver.getTitle(), "Form could not be submitted because: " + errorMessagesOnForm);

        // 4. Review the 'getErrorMessagesOnForm' method. It collects two kind of error messages.
        //    Why the second one ('errorMessagesFromBrowserValidationErrors') is necessary?
        //    Try to change the form inputs to see those messages (hint: do not provide first name
        //    or use a small number as age). Why do we have two kinds of errors?
        //    Can we reuse this method for any forms without change?

        // 5. Sometimes we want to test whether the validation works correctly.
        //    Make an assertion that verifies that the error message 'Surname provided is too short'
        //    was provided with 'Beeblebrox' as last name.
        //    Hint: To verify whether a String contains another one, check the first assertion of Exercise 04/A!
        assertTrue(errorMessagesOnForm.contains("Surname provided is too short"));
    }

    // helper method needed for Task B
    private String getErrorMessagesOnForm() {
        Stream<WebElement> visibleErrorMessageDivs = driver.findElements(By.className("errormessage")).stream()
                .filter(e -> e.getCssValue("visibility").contentEquals("visible"));
        Stream<String> errorMessagesFromDivs = visibleErrorMessageDivs
                .map(e -> e.getText());

        Stream<WebElement> inputsWithBrowserValidationErrors = driver.findElements(By.tagName("input")).stream()
                .filter(e -> !e.getAttribute("validationMessage").contentEquals(""));
        Stream<String> errorMessagesFromBrowserValidationErrors = inputsWithBrowserValidationErrors
                .map(e -> e.getAttribute("validationMessage"));

        List<String> allErrorMessages = Stream.concat(errorMessagesFromDivs, errorMessagesFromBrowserValidationErrors)
                .collect(Collectors.toList());
        return String.join(", ", allErrorMessages);
    }
}
