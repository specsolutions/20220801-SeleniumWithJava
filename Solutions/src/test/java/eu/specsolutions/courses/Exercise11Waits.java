package eu.specsolutions.courses;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Exercise11Waits extends ExerciseTestBase {

    @Test
    public void TaskA() {
        driver = new ChromeDriver();

        // When we browse to a page it gets loaded, but to fully load a modern page
        // several activities might happen.
        // Knowing when the page is fully loaded is important because we might need
        // to wait for this to be able to perform activities (e.g. click a button)
        // or to verify the page content (e.g. assertions).
        // In the following exercises we play with a page that displays some result
        // in a green box with ID 'the-data'. We have 3 sample pages that illustrate
        // the three typical loading strategy. The sample files are in the 'samples' folder of
        // the project and can be opened in the browser from the file explorer.
        // a) The 'page_with_data.html' contains the data directly in the page HTML.
        // b) The 'page_with_dynamic_data.html' generates the green box in the window load
        //    event, using some synchronous calculation (no web requests made, just code logic).
        //    (The sample page just has a dummy loop that runs for a second.)
        // c) The 'page_with_dynamic_async_data.html' calculates the data asynchronously.
        //    That means it might perform an ajax request to the backend to retrieve the data.
        //    (The sample page just displays the result using a 1-second timer to simulate this.)

        // 1. Check and run the following 3 test actions that open the 3 pages respectively
        //    and asserts on the result.
        //    Which strategies are supported by Selenium by default?
        //    Comment the one(s) that do not work and move to task 2.

        driver.get(new File("./samples/page_with_data.html").getAbsolutePath());
        String result1 = driver.findElement(By.id("the-data")).getText();
        assertEquals("42", result1);

        driver.get(new File("./samples/page_with_dynamic_data.html").getAbsolutePath());
        String result2 = driver.findElement(By.id("the-data")).getText();
        assertEquals("42", result2);

        //driver.get(new File("./samples/page_with_dynamic_async_data.html").getAbsolutePath());
        //String result3 = driver.findElement(By.id("the-data")).getText();
        //assertEquals("42", result3);

        // 2. In order to make the assertion for the page with dynamic async data, we need to
        //    wait until the page is "fully" loaded. We will see in task "C" how this could be
        //    achieved, but for now let's make it simpler: Wait until the green box appears.
        //    Selenium can be configured to always wait for a findElement call ("implicit wait")
        //    but this would apply for all calls and therefore makes the code inefficient and hard
        //    to maintain: don't use that! A better approach is to use "explicit waits": specify
        //    for a particular 'findElement' call how much it should wait.
        //    The wait is implemented by polling: it tries to fulfill the 'findElement' call multiple
        //    times until the specified timeout period.
        //    Review the following code? How much does it wait maximum? How many times will it try to
        //    query the element maximum (default polling time is 500ms)? How long will it wait usually
        //    (the page provides the data in 1 seconds)?
        //    Try to set the wait time to 0.5 seconds? What happens? (Undo this after you tested)
        driver.get(new File("./samples/page_with_dynamic_async_data.html").getAbsolutePath());
        WebElement greenBox1 = new WebDriverWait(driver, Duration.ofSeconds(10))
            .pollingEvery(Duration.ofMillis(200)) // optional: override default polling time of 500ms
            .until(d -> d.findElement(By.id("the-data")));
        assertEquals("42", greenBox1.getText());

        // 3. Update the previous call to modify the polling time to 200ms. This can be done using
        //    the '.pollingEvery(Duration....)' method before the '.until' call.

        // 4. The previous example used the 'WebDriverWait' class. The 'WebDriverWait' class is
        //    a specialized version of the 'FluentWait<>' class.
        //    The following call performs exactly the same wait as we did above. (Uncomment it to run.)
        //    Compare the code with the 'WebDriverWait' call and try to explain what the 'WebDriverWait'
        //    is specialized for.
        driver.get(new File("./samples/page_with_dynamic_async_data.html").getAbsolutePath());
        WebElement greenBox2 = new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(10))
            .pollingEvery(Duration.ofMillis(200))
            .ignoring(NoSuchElementException.class)
            .until(d -> d.findElement(By.id("the-data")));
        assertEquals("42", greenBox2.getText());

        // 5. So far we were waiting for the 'findElement' method to succeed, but we can wait for
        //    more complex conditions as well, e.g. that the element is found and visible.
        //    The easiest way to make such complex wait conditions is to use the methods of the
        //    'ExpectedConditions' class. It contains plenty of different useful conditions. Check
        //    the full reference at
        //      https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/ui/ExpectedConditions.html
        //    For this exercise uncomment the following calls and complete it so that they wait until
        //    "the green box is found and becomes visible".
        //    Hint: Use the 'visibilityOfElementLocated' method on the 'ExpectedConditions' class.
        driver.get(new File("./samples/page_with_dynamic_async_data.html").getAbsolutePath());
        WebElement greenBox3 = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(By.id("the-data")));
        assertEquals("42", greenBox3.getText());

        // 6. Conditions can also be combined (and/or/not) using the 'and()', 'or()' and 'not()'
        //    methods of the ExpectedConditions class. In this case the "inner conditions" will be
        //    passed as the argument of these methods.
        //    Complete the code to wait for both the following conditions to be satisfied:
        //    * the browser title is 'Page with Dynamic Async Data'
        //    * the green box is found and visible
        //    Note that for this complex conditions the 'WebDriverWait' class does not
        //    return the element we were waiting for, but "just" waits. To make the assertion
        //    we need to make a second 'findElement' call.
        driver.get(new File("./samples/page_with_dynamic_async_data.html").getAbsolutePath());
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.and(
                ExpectedConditions.titleIs("Page with Dynamic Async Data"),
                ExpectedConditions.visibilityOfElementLocated(By.id("the-data"))));
        WebElement greenBox4 = driver.findElement(By.id("the-data"));
        assertEquals("42", greenBox4.getText());

        // 7. Waiting for a list of elements ('findElements') is harder because you cannot
        //    distinguish if the loading finished, but returned an empty list, or you still need to wait.
        //    The sample pages generate 0-3 hearts under the green box (divs with CSS class 'heart').
        //    The following code always shows '0 heart(s) on the page' because the 'findElements'
        //    never fails, but returns an empty list if the elements are not found.
        //    Try to fix it by performing a wait for the green box before. In this case we
        //    don't even need to have a wait for the hearts!
        //    What problems you see with this code?
        driver.get(new File("./samples/page_with_dynamic_async_data.html").getAbsolutePath());
        // wait for the green box
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(By.id("the-data")));
        // get the hearts -- waiting is not needed...
        //List<WebElement> hearts = new WebDriverWait(driver, Duration.ofSeconds(10))
        //        .until(d -> d.findElements(By.className("heart")));
        List<WebElement> hearts = driver.findElements(By.className("heart"));
        // assert/display the result
        int heartCount = hearts.size();
        System.out.printf("%s heart(s) on the page%n", heartCount);
        assertTrue(heartCount <= 3);

        pause();
    }

    @Test
    public void TaskB() {
        driver = new ChromeDriver();

        driver.get(new File("./samples/links_to_data_pages.html").getAbsolutePath());

        // 1. When you click on a link or submit a form that posts to another page,
        //    normally Selenium detects if the target page has been loaded in the same
        //    way, like we have seen in Task A. (No wait needed for direct and dynamic data,
        //    but waits to be done for dynamic async data).
        //    Review the following examples that show this.
        //    What happens if you remove the wait from the third case?

        driver.findElement(By.id("link-to-dynamic-data")).click();

        WebElement greenBox1 = driver.findElement(By.id("the-data"));
        assertEquals("42", greenBox1.getText());
        driver.navigate().back();

        driver.findElement(By.id("submit-to-dynamic-data")).click();

        WebElement greenBox2 = driver.findElement(By.id("the-data"));
        assertEquals("42", greenBox2.getText());
        driver.navigate().back();

        driver.findElement(By.id("link-to-dynamic-async-data")).click();

        WebElement greenBox3 = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(d -> d.findElement(By.id("the-data")));
        assertEquals("42", greenBox3.getText());
        driver.navigate().back();

        // 2. Unfortunately in modern applications link licks and form submissions are not always done
        //    directly, but there is a javascript code executed that moves to the target page asynchronously.
        //    These activities cannot be tracked by Selenium, so you have to wait even if the target page would
        //    be loaded otherwise.
        //    The following actions perform such link clicks and form submissions. Perform the necessary
        //    waits to make them work.

        driver.findElement(By.id("js-link-to-dynamic-data")).click();

        //WebElement greenBox4 = driver.findElement(By.id("the-data"));
        WebElement greenBox4 = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(d -> d.findElement(By.id("the-data")));
        assertEquals("42", greenBox4.getText());
        driver.navigate().back();

        driver.findElement(By.id("js-submit-to-dynamic-data")).click();

        //WebElement greenBox5 = driver.findElement(By.id("the-data"));
        WebElement greenBox5 = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(d -> d.findElement(By.id("the-data")));
        assertEquals("42", greenBox5.getText());
    }

    @Test
    public void TaskC() {
        driver = new ChromeDriver();

        driver.get(new File("./samples/links_to_data_pages.html").getAbsolutePath());

        // 1. In the following example we click a link that asynchronously transfers to a
        //    page that asynchronously loads the data.
        //    For some reason, we want to make two assertions (in this order!):
        //    * Check the heart count
        //    * Check the green box result
        //    It works incorrectly as it always shows '0 heart(s) on the page' and it also
        //    fails to find the green box.
        //    (If the order of these checks would be the opposite, it would be easy to fix,
        //    because we could just wait for the green box).
        //    Can you imagine a situation where the order of the checks cannot be changed?
        driver.findElement(By.id("js-submit-to-dynamic-async-data")).click();

        // 2. Fix the code by adding a wait for the green box before the heart count check.
        //new WebDriverWait(driver, Duration.ofSeconds(10))
        //    .until(ExpectedConditions.visibilityOfElementLocated(By.id("the-data")));
        waitForPageToBeLoaded(driver, "Page with Dynamic Async Data");

        // Check the heart count
        List<WebElement> hearts = driver.findElements(By.className("heart"));
        int heartCount = hearts.size();
        System.out.printf("%s heart(s) on the page%n", heartCount);
        assertTrue(heartCount <= 3);

        // Check the green box result
        String result = driver.findElement(By.id("the-data")).getText();
        assertEquals("42", result);

        // 3. In the code above, you use the "green box" as a load completeness indicator for the page.
        //    We basically can check for this indicator and then work with the other checks without further waits.
        //    Essentially we have changed our wait strategy finally:
        //    * Instead of waiting for the things we check (arbitrary, order dependent),
        //    * Now we wait for the page to be loaded (i.e. the link click to complete)!
        //    This new strategy is much more robust! The only problem is...
        //    That we have to find a load indicator for every page and make sure that we wait for the
        //    load indicator of the particular page we redirect to.
        //    Maybe we could use a generic page load indicator! (Maybe your JavaScript UI library already
        //    has one!)
        //    Check the HTML source code of samples/page_with_dynamic_async_data.html. Near to the end of the
        //    file, there is a line that sets an attribute on the <body> tag once the data is retrieved.
        //      document.getElementsByTagName('body')[0].setAttribute("data-page-loaded", "1")
        //    The same code is added to the other pages as well: this is a very basic implementation
        //    of a generic page load indicator.
        //    We can wait for this indicator using for example:
        //      ExpectedConditions.attributeToBe(By.tagName("body"), "data-page-loaded", "1"))
        //    Check the method 'waitForPageToBeLoaded' below that performs such a
        //    generic wait block for a particular page identified by the title.
        //    Replace your wait logic above with a call to this method:
        //      waitForPageToBeLoaded(driver, "Page with Dynamic Async Data");

        // 4. Sometimes the page load indicator is not an attribute, but a JavaScript call. But fortunately
        //    you can also wait for JavaScript call results!
        //    Check the following code that verifies if the 'data-page-loaded' attribute of the <body> is set
        //    to 1 using a JavaScript code snippet.
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(d -> ((JavascriptExecutor)d)
                .executeScript(
                    "return document.getElementsByTagName('body')[0].getAttribute('data-page-loaded') === '1'"));
    }

    @Test
    public void TaskD() {
        driver = new ChromeDriver();

        // A similar waiting problem can occur for pages that have buttons or forms that are
        // performing some action without moving to another page (self-posting).
        // In this case, after triggering the action, we have to wait until the action is fully
        // finished and start making the checks only after.

        // 1. Review the following test code that uses a generic "action-in-progress" indicator
        //    implemented in the page_with_event_result_data.html page to know when it is ready
        //    to perform the checks.
        driver.get(new File("./samples/page_with_event_result_data.html").getAbsolutePath());

        driver.findElement(By.id("get-data-button")).click();

        waitForActionToBeFinished(driver);

        // Check the heart count
        List<WebElement> hearts = driver.findElements(By.className("heart"));
        int heartCount = hearts.size();
        System.out.printf("%s heart(s) on the page%n", heartCount);
        assertTrue(heartCount <= 3);

        // Check the green box result
        String result = driver.findElement(By.id("the-data")).getText();
        assertEquals("42", result);
    }

    private void waitForPageToBeLoaded(WebDriver driver, String expectedTitle) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
            .until(ExpectedConditions.and(
                ExpectedConditions.titleIs(expectedTitle),
                ExpectedConditions.attributeToBe(By.tagName("body"), "data-page-loaded", "1")));
    }

    private void waitForActionToBeFinished(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
            .until(ExpectedConditions.attributeToBe(By.tagName("body"), "data-action-in-progress", "0"));
    }
}
