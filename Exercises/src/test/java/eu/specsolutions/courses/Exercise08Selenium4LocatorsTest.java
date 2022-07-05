package eu.specsolutions.courses;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

public class Exercise08Selenium4LocatorsTest extends ExerciseTestBase {

    @Test
    public void TaskA() {
        driver = new ChromeDriver();
        driver.get("https://testpages.herokuapp.com/styled/validation/input-validation.html");

        // 1. Selenium 4 introduces new relative locators: above, below, toLeftOf, toRightOf, near.
        //    In Java these can be used as
        //      driver.findElement(RelativeLocator.with(<what you want to find>).above(<the locator or element relative to>));
        //    Find the 'Last name' input by searching for an 'input' tag below the CSS selector label[for=surname]
        //    and set the value to 'Beeblebrox'

        //WebElement lastNameTextInput = //TODO
        //lastNameTextInput.sendKeys("Beeblebrox");

        // 2. The relative to part can also be an existing element.
        //    Find the 'First name' text box by searching for and 'input' tag above the 'lastNameTextInput'
        //    and set the value to 'Zaphod'.

        //WebElement firstNameTextInput = //TODO
        //firstNameTextInput.sendKeys("Zaphod");

        // 3. The relative locators can also be chained (e.g. below X and to right of Y).
        //    In Java this can be done by chaining calls: ...with(...).below(...).toRightOf(...)
        //    Find the 'Age' input by searching for the CSS selector 'input[type=number]' near to
        //    the 'lastNameTextInput' and above the CSS selector 'label[for=country]' and
        //    set the value to '43'.

        //WebElement ageNumberInput = //TODO
        //ageNumberInput.sendKeys("43");

        pause();
    }
}
