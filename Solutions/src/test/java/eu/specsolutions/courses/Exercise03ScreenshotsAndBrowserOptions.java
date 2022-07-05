package eu.specsolutions.courses;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

public class Exercise03ScreenshotsAndBrowserOptions extends ExerciseTestBase {

    @Test
    public void TaskA() throws IOException {
        driver = new ChromeDriver();
        driver.get("https://testpages.herokuapp.com/styled/index.html");

        File targetFile = new File("./target/test-output/screenshot-E03A.png");

        // 1. Uncomment and complete the line below to make a screenshot of the current page
        //    and put the result to the local variable 'screenshotFile'.
        //    To make a screenshot to a file the driver has to be "casted" to 'TakesScreenshot' and
        //    the call 'getScreenshotAs' with 'FILE' output type.
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // 2. Save the file to the file path initialized above as 'targetFile' using the FileUtils.copyFile method.
        FileUtils.copyFile(screenshotFile, targetFile);
        // 3. Run the test and check if the screenshot is there (in the target/test-output folder of the project)
        // 4. Uncomment the next line and run the test again. What does this line do? Why it is useful?
        System.out.printf("The screenshot was saved to: %s%n", targetFile.getAbsolutePath());
    }

    @Test
    public void TaskB() throws IOException {
        // 1. Configure Chrome to run in "headless" mode. Use ChromeOptions for that.
        //    Hint: Look for a 'setHeadless' method.
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(true);
        // alternatively: chromeOptions.addArguments("headless", "disable-gpu");

        // sets initial window settings
        chromeOptions.addArguments("window-size=400,400");
        chromeOptions.addArguments("start-maximized");

        // disables info bar that says "Chrome is being controlled by automated software..."
        chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        chromeOptions.setExperimentalOption("useAutomationExtension", false);

        driver = new ChromeDriver(chromeOptions);
        driver.get("https://testpages.herokuapp.com/styled/index.html");

        // 2. Copy the screenshot taking code from TaskA, change the output file name to 'screenshot-E03B.png'

        // 3. Extract screenshot taking code to a helper method called 'takeScreenshot' so that you can
        //    invoke it with the line below (you need to uncomment it).
        takeScreenshot("screenshot-E03B.png");

        // 4. Does the screenshot has the same size as the one from TaskA? Why?
        // 5. Set the browser size for the headless mode to 400x400 pixels by calling the 'addArguments' method
        //    on the Chrome options.
        //    The page https://peter.sh/experiments/chromium-command-line-switches/ contains a reference of
        //    all options. For setting the size, the "window-size=800,600"option can be used.
        //    Another commonly used option is the "start-maximized".

        // 6. Switch back no non-headless mode and apply the following options for your Chrome.
        //    (Enable both options at the same time!) Do you notice any difference?
        //chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        //chromeOptions.setExperimentalOption("useAutomationExtension", false);
    }

    private void takeScreenshot(String fileName) throws IOException {
        File targetFile = new File("./target/test-output/" + fileName);
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile, targetFile);
        System.out.printf("The screenshot was saved to: %s%n", targetFile.getAbsolutePath());
    }
}
