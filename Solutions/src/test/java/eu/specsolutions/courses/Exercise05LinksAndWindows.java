package eu.specsolutions.courses;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class Exercise05LinksAndWindows extends ExerciseTestBase {

    @Test
    public void TaskA() {
        driver = new ChromeDriver();
        driver.get("https://testpages.herokuapp.com/styled/windows-test.html");

        // In these exercises we will navigate to different URLs in different tabs and windows.
        // Normally the automation is too fast to see the result. To slow down the test, feel free to
        // add 2 seconds pauses by calling the pause() method from the test base class.
        pause();

        WebElement indexLink = driver.findElement(By.linkText("Index"));
        // 1. Clicking a link is easy... just 'click()'
        //    Let's do this for the "Index" link on the page.
        indexLink.click();
        assertEquals("Selenium Test Pages", driver.getTitle());

        // 2. Now the tab has navigated to the index page, but we can use the "back button" to go back.
        //    The navigation commands can be invoked using the driver.navigate().xxx() methods.
        //    Navigate back to the Windows Example test page!
        driver.navigate().back();
        assertEquals("Windows Example Test", driver.getTitle());

        // 3. The page might have been outdated. Let's refresh the browser tab, again using the driver.navigate().xxx() methods.
        driver.navigate().refresh();

        // 4. If we work with multiple tabs/windows, we might need to "come back" to the previous tab or window.
        //    For that, we need to save the "handle" (the ID) of the current tab, using the 'driver.getWindowHandle' method.
        //    Save the current window handle to the 'firstTabHandle' variable.
        String firstTabHandle = driver.getWindowHandle();

        // 5. We can create new tabs and windows using the driver.switchTo().newWindow(...) method.
        //    The parameter of the method can be WindowType.TAB or WindowType.WINDOW.
        //    Open a new tab and load the page https://www.specsolutions.eu/ to the new tab.
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://www.specsolutions.eu/");
        assertEquals("Spec Solutions", driver.getTitle());
        pause();

        // 6. Open a new window and load the page https://www.selenium.dev/ to the new window.
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get("https://www.selenium.dev/");
        assertEquals("Selenium", driver.getTitle());

        // 7. Close the current tab (window) using 'driver.close'.
        //    What is the different between 'driver.close' and 'driver.quit'?
        driver.close();
        pause();

        // 8. As we closed the window, we are not sure in which tab we are currently.
        //    We can switch to a saved tab handle by using the 'driver.switchTo().window(...)'
        //    method, using the tab handle as parameter.
        //    Switch back to the tab saved to 'firstTabHandle'
        driver.switchTo().window(firstTabHandle);

        // 9. Click on the link 'Attributes in new page'.
        //    Make a pause and check what happened.
        WebElement attributesPageLink = driver.findElement(By.id("goattributes"));
        attributesPageLink.click();
        pause();

        // 10. Uncomment the next assertion and check if it passes!
        //     What is wrong? Don't try to "fix" this now, let's move on to the Task B.
        //assertEquals("Test Page For Element Attributes", driver.getTitle());
        assertEquals("Windows Example Test", driver.getTitle());
    }

    @Test
    public void TaskB() {
        driver = new ChromeDriver();
        driver.get("https://testpages.herokuapp.com/styled/windows-test.html");

        String mainTabHandle = driver.getWindowHandle();

        WebElement attributesPageLink = driver.findElement(By.id("goattributes"));
        attributesPageLink.click();

        // 1. When the link that opens in a new tab/window clicked, the browser switches to that
        //    new tab/window, but Selenium will not know this and keeps automating the original tab.
        //    In order to switch to the new tab, the handle of the new tab has to be obtained from the
        //    list of handles provided by the 'getWindowHandles' method.
        //    But to know which one is the new one, you have to know all other handles... :(
        //    Review the next lines. How does this code find the new handle?
        String newTabHandle = driver.getWindowHandles().stream()
                .filter(h -> !h.contentEquals(mainTabHandle))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("New tab handle cannot be found!"));
        driver.switchTo().window(newTabHandle);
        assertEquals("Test Page For Element Attributes", driver.getTitle());

        driver.switchTo().window(mainTabHandle);

        // 2. There is another way to handle this: change (hack?) the link to NOT go to a new tab.
        //    Normally Selenium cannot be used to "change" things. But with a small trick this can be done.
        //    Review the following code lines! What do they do? When would you use this trick?
        ((JavascriptExecutor)driver).executeScript("document.getElementById('gobasicajax').setAttribute('target', '_self');");
        WebElement basicAjaxPageLink = driver.findElement(By.id("gobasicajax"));
        basicAjaxPageLink.click();
        assertEquals("Test Page For Basic Ajax Example", driver.getTitle());
        pause();
    }
}
