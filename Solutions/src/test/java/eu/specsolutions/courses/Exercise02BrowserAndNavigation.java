package eu.specsolutions.courses;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class Exercise02BrowserAndNavigation {

    private ChromeDriver driver;

    @AfterEach
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    @Test
    public void TaskA() {
        // 1. Open a Chrome driver (the ChromeDriver is automatically downloaded by the WebDriverManager
        // by the 'setupChromeDriver' method below) by creating a new instance of the ChromeDriver class.
        driver = new ChromeDriver();
        pause();
        Assertions.fail("this is a simulated error");

        // 2. Close the Chrome instance by calling the 'quit' method. (The 'close' method seems to be also
        // working but that is NOT the proper call here. More on that in Exercise 5.)
        //driver.quit();

        // 3. Uncomment the Assertions.fail call above? What happens with the browser if you run the test?
        //    Change the test in a way that it closes the browser even if the test fails.
        //    Hint: Create a method that quits the driver and annotate it with '@AfterEach'.
    }

    @Test
    public void TaskB() {
        // 1. Open a Chrome driver
        driver = new ChromeDriver();
        // 2. Navigate to the URL https://testpages.herokuapp.com/styled/index.html using the 'get()'
        //    method of the driver.
        driver.get("https://testpages.herokuapp.com/styled/index.html");

        pause();
        // 3. Assert whether you are on the right URL by uncommenting and completing the next line
        //    Hint: use the 'getCurrentUrl()' method on the driver.
        assertEquals("https://testpages.herokuapp.com/styled/index.html", driver.getCurrentUrl());

        // 4. Change both URLs to an invalid link, e.g. https://wrong_testpages.herokuapp.com
        //    Do you see an error? Does the URL assertion fail? Why?

        // 5. Change back the URL to the right one and make an assertion on the browser title ('getTitle()')
        //    What are the benefits and disadvantages for asserting on the browser title?
        assertEquals("Selenium Test Pages", driver.getTitle());
    }

    @BeforeEach
    public void setupChromeDriver() {
        WebDriverManager.chromedriver().setup();
    }

    protected void pause(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
