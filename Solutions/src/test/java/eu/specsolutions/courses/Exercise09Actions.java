package eu.specsolutions.courses;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Exercise09Actions extends ExerciseTestBase {

    @Test
    public void TaskA() {
        driver = new ChromeDriver();
        driver.get("https://testpages.herokuapp.com/styled/events/javascript-events.html");

        // 1. Complex mouse & keyboard actions can be performed by creating an Actions
        //    object. You can record a couple of actions (e.g. click()) to the actions object
        //    and finally by calling the perform() method, all recorded actions are performed.
        //    Click on the button with ID 'onclick' by extending the code lines below.
        new Actions(driver)
                .moveToElement(driver.findElement(By.id("onclick")))
                .click()
                .perform();

        // 2. You can chain multiple actions in the Actions object. For some visuals
        //    (e.g. a hover menu), you might need to include some waits as well to the 
        //    action chain. This can be done my the pause() method. It is recommeded to pass a 
        //    Duration object to pause, e.g. 'Duration.ofSeconds(1)'.
        //    Perform the following actions using a single action object:
        //    * move to the button with ID 'onmouseleave'
        //    * move to the button with ID 'onmouseover'
        //    * wait one second
        //    * double click on the element with ID 'ondoubleclick'
        new Actions(driver)
                .moveToElement(driver.findElement(By.id("onmouseleave")))
                .moveToElement(driver.findElement(By.id("onmouseover")))
                .pause(Duration.ofSeconds(1))
                .moveToElement(driver.findElement(By.id("ondoubleclick")))
                .doubleClick()
                .perform();

        // 3. If you performed the previous task, the 'ondoubleclick' is in focus right now.
        //    Move the focus to the next button by simulating a 'TAB' keyboard press using the 
        //    'sendKeys()' action method. For special keys, like TAB, you can use the 'Keys' 
        //    enumeration (e.g. 'Keys.BACK_SPACE').
        new Actions(driver)
                .sendKeys(Keys.TAB)
                .perform();

        // 4. Sending keys with modifiers (Shift/Alt/Ctrl) is a bit more tricky, because you 
        //    literally have to replay what you would do in the keyboard: 
        //    * move your modifier key down (with 'keyDown(...)')
        //    * press the key (with 'sendKeys()')
        //    * move your modifier key up (with 'keyUp(...)')
        //    Move back the focus to the previous button by sending a Shift+TAB to the browser.
        new Actions(driver)
                .keyDown(Keys.SHIFT)
                .sendKeys(Keys.TAB)
                .keyUp(Keys.SHIFT)
                .perform();

        // 5. The keyboard inputs are sent to the focused control by default.
        //    Uncomment the code below and check what it is doing. 
        //    Why the 'click()' call is necessary? Is there a simpler way to achieve the same?
        //    Hint: check the overload of 'sendKeys' with a first WebElement parameter.
        // new Actions(driver)
        //         .moveToElement(driver.findElement(By.id("onkeypress")))
        //         .click()
        //         .sendKeys("x")
        //         .perform();
        new Actions(driver)
                .sendKeys(driver.findElement(By.id("onkeypress")), "x")
                .perform();

        pause();
    }

    @Test
    public void TaskB() {
        driver = new ChromeDriver();
        driver.get("https://testpages.herokuapp.com/styled/expandingdiv.html");

        // 1. In this page there is a DIV that expands on mouse hover. 
        //    Make a single Actions operation that expands the div and clicks 
        //    to the link inside so that the assertion will pass.
        new Actions(driver)
                .moveToElement(driver.findElement(By.className("expand")))
                .pause(Duration.ofMillis(500))
                .click(driver.findElement(By.cssSelector(".expand a")))
                .perform();

        assertEquals("Expanded Div", driver.getTitle());
    }
}
