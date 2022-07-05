package eu.specsolutions.courses;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class Exercise07AdvancedFormsTest extends ExerciseTestBase {

    @Test
    public void TaskA() {
        driver = new ChromeDriver();
        driver.get("https://testpages.herokuapp.com/styled/basic-html-form-test.html");

        // 1. In this form, there are a couple of usual, but a bit special controls that we will automate.
        //    First, set the comment field to a two-line text:
        //      Long comments come here!
        //      And a new line
        //    Hint: You can include a new line character to a Java String with \n
        //TODO

        // 2. File uploads are rarely automated and can be tricky. For a simple standard <input type=file>
        //    control, the easiest way to automate is to set the path to the uploaded file via 'sendKeys'
        //    as the example below shows.
        //    What alternatives can you imagine for automating a form that requires an uploaded file?
        File fileToUpload = new File("./target/test-output/screenshot-E03A.png");
        driver.findElement(By.name("filename")).sendKeys(fileToUpload.getAbsolutePath());

        // 3. Make sure that the 'Checkbox 1' is checked (.click()), but the 'Checkbox 3' not.
        //    Hint: Consider the CSS Selector: input[name='checkboxes[]'][value='<some-value>']
        //TODO

        // 4. Make sure only the 'radio 1' is selected
        //TODO

        // 5. Make sure that in the 'Multiple Select Values' list box:
        //    * The first item is selected
        //    * The item with the value 'ms2' is selected
        //    * The item with the text 'Selection Item 3'
        //    * And nothing else is selected
        //    In order to work with <select> elements, the easiest is to use the 'Select' helper class.
        //    For that first you need to create a new Select instance, like the example bewlow and then
        //    you can invoke the different methods of the select instance.
        //Select multiSelectListBox = new Select(driver.findElement(TODO));
        //multiSelectListBox. //TODO

        // 6. Make sure that in the 'Drop Down Item 2' is selected from the dropdown.
        //    Hint: This dropdown is also a <select> element...
        //TODO

        pause();

        // 7. Uncomment the next line and the assertions below. If you did the previous tasks correctly
        //    the assertions will pass, except the last two. Add the necessary code to satisfy those as well.
        //driver.findElement(By.cssSelector("#HTMLFormElements input[type=submit]")).click();

        //assertEquals("Processed Form Details", driver.getTitle());
        //assertEquals("Long comments come here! And a new line", driver.findElement(By.id("_valuecomments")).getText());
        //assertEquals("cb1", driver.findElement(By.cssSelector("#_checkboxes ul")).getText());
        //assertEquals("rd1", driver.findElement(By.cssSelector("#_radioval ul")).getText());
        //assertEquals("ms1\nms2\nms3", driver.findElement(By.cssSelector("#_multipleselect ul")).getText());
        //assertEquals("dd2", driver.findElement(By.cssSelector("#_dropdown ul")).getText());
        //assertEquals("Zaphod", driver.findElement(By.id("_valueusername")).getText());
        //assertEquals("secret1234!", driver.findElement(By.id("_valuepassword")).getText());
    }

    @Test
    public void TaskB() {
        driver = new ChromeDriver();
        driver.get("https://testpages.herokuapp.com/styled/html5-form-test.html");

        // 1. Review the automation code for the new HTML controls on this page.
        //    Some of these controls are not fully supported in all browsers and the way to set their input
        //    is also not defined by the standard. Nevertheless, these settings work in recent Chrome.
        //    In general, the Javascript trick can always be used:
        //    ((JavascriptExecutor)driver).executeScript("document.getElementById('<id>').setAttribute('value', '<value>');");

        // <input type=color>
        driver.findElement(By.id("colour-picker")).sendKeys("#FF0000");

        // <input type=date>
        driver.findElement(By.id("date-picker")).sendKeys("07-25-2022");

        // <input type=datetime-local>
        WebElement dateTimeLocalInput = driver.findElement(By.id("date-time-picker"));
        dateTimeLocalInput.sendKeys("07-25-2022");
        dateTimeLocalInput.sendKeys(Keys.TAB);
        dateTimeLocalInput.sendKeys("16:30");
        // or maybe in this case better:
        // ((JavascriptExecutor)driver).executeScript("document.getElementById('date-time-picker').setAttribute('value', '2022-06-25T16:30');");

        // <input type=email>
        WebElement emailInput = driver.findElement(By.id("email-field"));
        emailInput.clear();
        emailInput.sendKeys("santa@northpole.com");

        // <input type=month>
        WebElement monthInput = driver.findElement(By.id("month-field"));
        monthInput.sendKeys("07");
        monthInput.sendKeys(Keys.TAB);
        monthInput.sendKeys("2022");

        // <input type=number>
        WebElement numberInput = driver.findElement(By.id("number-field"));
        numberInput.clear();
        numberInput.sendKeys("43");

        pause();
    }
}
