package eu.specsolutions.courses;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Exercise10TablesAndLists extends ExerciseTestBase {

    @Test
    public void TaskA() {
        File sampleFile = new File("./samples/tables_and_lists.html");
        driver = new ChromeDriver();
        driver.get(sampleFile.getAbsolutePath());

        // 1. Review the code below that prints out the content of #mytable to the console. 
        //    What is the element structure of a complete HTML table?
        // 2. The selectors contain a special ':scope' selector (the '>' selects a direct child).
        //    Why the ':scope' selector is needed? Try to remove the ':scope >' from the beginning 
        //    of the selector and check the difference.
        //    Why is it important to use '>' for tables?
        // 3. Complete the code by printing out the header.
        WebElement table = driver.findElement(By.id("mytable"));
        // print header -- complete with task A-3
        //WebElement headerRow = table.findElement(TODO);
        //TODO: printing header row
        // print header separator
        System.out.println("--------------------");
        // print body
        List<WebElement> bodyRowList = table.findElements(By.cssSelector(":scope > tbody > tr"));
        for (WebElement bodyRow : bodyRowList) {
            for (WebElement dataCell : bodyRow.findElements(By.cssSelector(":scope > td.table-data"))) {
                System.out.printf("| %s ", dataCell.getText());
            }
            System.out.println("|");
        }

        // 4. Click on the reminder link of 'Cindy' (the user ID of Cindy is '56C') to make 
        //    the assertion pass.
        //    Hint: The 'data-' attributes can annotate the visual elements with data, that is
        //    very useful for javascript codes and for testing as well.
        //    Which one is more useful for us, the 'data-entry-for-user' on the TR or 
        //    the 'data-user-id' on TD? 
        //WebElement reminderLinkOfCindy = driver.findElement(TODO);
        //TODO
        //assertTrue(driver.getCurrentUrl().endsWith("#send-reminder-56C"));

        // 5. Uncomment the code below and complete to get the amount of Bob (user ID '45B') 
        //    in order to make the assertion pass.
        //    Hint: the amount cell has a CSS class 'data-amount'
        //String amountByBobString = driver.findElement(TODO).getText();
        //System.out.println(amountByBobString);
        //Double amountByBob = Double.parseDouble(amountByBobString);
        //assertEquals(23.4, amountByBob);

        pause();
    }

    @Test
    public void TaskB() {
        File sampleFile = new File("./samples/tables_and_lists.html");
        driver = new ChromeDriver();
        driver.get(sampleFile.getAbsolutePath());

        // 1. The following tasks will perform the same actions we did using the #mytable,
        //    but now on the list with ID 'mylist'.
        //    Click on the reminder link of 'Cindy' (the user ID of Cindy is '56C') in the 
        //    #mylist to make the assertion pass.
        //TODO
        assertTrue(driver.getCurrentUrl().endsWith("#send-reminder-56C"));

        // 2. Get the amount of Bob (user ID '45B') to make the assertion pass. 
        //String amountByBobString = driver.findElement(TODO).getText();
        //System.out.println(amountByBobString);
        //Double amountByBob = Double.parseDouble(amountByBobString);
        //assertEquals(23.4, amountByBob);

        // 3. Print out the data of the #mylist in a similar form as we did with the table
        //    (without printing the headers now).
        WebElement list = driver.findElement(By.id("mylist"));
        // print body
        // TODO

        pause();
    }
}
