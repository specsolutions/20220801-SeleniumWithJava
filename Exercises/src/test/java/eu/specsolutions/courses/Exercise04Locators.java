package eu.specsolutions.courses;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Exercise04Locators extends ExerciseTestBase {

    @Test
    public void TaskA() {
        driver = new ChromeDriver();
        driver.get("https://testpages.herokuapp.com/styled/basic-web-page-test.html");

        // 1. Assert if the page contains 'A paragraph of text' by getting the current page source
        //    ('getPageSource' method) and check containment. Is this a good idea?
        //String pageSource = //TODO
        //assertTrue(pageSource.contains("A paragraph of text"));

        // 2. Find the first paragraph by ID to make the assertion pass.
        //    To find an element, call the 'findElement' method on the driver with one of the
        //    locator methods of the 'By' class (e.g. 'By.id(...)').
        //WebElement paragraphOfText = //TODO
        //assertEquals("A paragraph of text", paragraphOfText.getText());

        // 3. Modify the locator in a way that it searches for a non-existing ID. What do you get? Null? Exception?

        // 4. Find the second paragraph by CSS class name ('By.className') to make the assertion pass.
        //WebElement anotherPara = //TODO
        //assertEquals("Another paragraph of text", anotherPara.getText());

        // 5. Find the first paragraph again, now with CSS ID selector ('By.cssSelector') to make the assertion pass.
        //    Finding something by ID in CSS selector can be done by using a '#' in front of
        //    the element ID: '#some-id'
        //WebElement paragraphOfTextWithCSS = //TODO
        //assertEquals("A paragraph of text", paragraphOfTextWithCSS.getText());

        // 6. Find the first paragraph again, now with CSS selector that searches for a class name.
        //    Finding something by class name in CSS selector can be done by using a '.' in front of
        //    the class name: .some-class-name
        //WebElement anotherParaWithCSS = //TODO
        //assertEquals("Another paragraph of text", anotherParaWithCSS.getText());

        // 7. Luckily we have only one h1 element. Try to find that element by tag name ('By.tagName')?
        //    Is the tag name locator case-sensitive?
        //WebElement heading1 = //TODO
        //assertEquals("Basic Web Page Example", heading1.getText());

        // 8. Uncomment the next lines and check what they print out to the test output?
        //    What is `innerHTML` and `outerHTML` provides.
        //System.out.println(heading1.getAttribute("innerHTML"));
        //System.out.println(heading1.getAttribute("outerHTML"));

        // 9. Now as we learned about `outerHTML`, we can redo the full-page search task we did above
        //    without using the 'getPageSource()' method? How? (Hint: consider the <html> element.)
        //String pageSourceDifferently = //TODO
        //assertTrue(pageSourceDifferently.contains("A paragraph of text"));
        //assertEquals(pageSource, pageSourceDifferently);
    }

    @Test
    public void TaskB() {
        driver = new ChromeDriver();
        driver.get("https://testpages.herokuapp.com/styled/find-by-playground-test.html");

        // 1. Find the first paragraph by name attribute to make the assertion pass.
        //    Why would someone need the "name" attribute?
        //WebElement paraA = //TODO
        //assertEquals("This is a paragraph text", paraA.getText());

        // 2. Find the first link below the page by link text 'jump to para 0' to make the assertion pass.
        //WebElement jumpToPara0Link = //TODO
        //assertEquals("jump to para 0", jumpToPara0Link.getText());

        // 3. The 'findElement' method works even if there are multiple matches to the locator.
        //    In this case it simply returns the first one.
        //    Find again the first paragraph, but now simply by tag name 'p'!
        //WebElement firstPara = //TODO
        //assertEquals("This is a paragraph text", firstPara.getText());

        // 4. The 'findElements' (note the 's'!) on the other hand returns all matches in a list.
        //    Find the second paragraph, by finding all 'p' elements and take the second item
        //    from the list (`.get(1)`).
        //    Is it efficient to find the second paragraph like this? What other options could you consider?
        //WebElement secondPara = driver.findElements //TODO
        //assertEquals("This is b paragraph text", secondPara.getText());

        // 5. A more efficient solution for finding the second element is to use CSS selectors.
        //    The ':nth-of-type()' selector for example does exactly what we want.
        //    (Find more selectors at https://www.w3schools.com/cssref/css_selectors.asp .)
        //    Try to find the paragraph using the selector 'p:nth-of-type(2)' with 'findElement'.
        //    Why did we use 2 in the selector and not 1 as with the 'findElements'?
        //WebElement secondParaWithCSS = driver.findElement //TODO
        //assertEquals("This is b paragraph text", secondParaWithCSS.getText());

        // 6. CSS selectors can be used to make complex queries for nested elements. They are very efficient.
        //    Find the first nested paragraph (the first 'p' within a dive with CSS class 'nestedDiv')
        //    using a CSS selector.
        //    Finding something by tag name in CSS selector can be done by simply using the tag name: 'tag-name'
        //    Finding nested items in CSS selector can be done by combining the container and nested
        //    expressions with a space: 'container-expression nested-expression'
        //WebElement firstNestedPara = driver.findElement //TODO
        //assertEquals("p26", firstNestedPara.getAttribute("id"));

        // 7. Complex selectors can also be done using XPath. XPath is powerful, but complex and slow. Try to avoid it!
        //    Find the same nested paragraph with the XPath expression '//div[@class='nestedDiv']/p' to make
        //    the assertion pass. How does this find the element? Isn't it ugly? :)
        //WebElement firstNestedParaWithXPath = //TODO
        //assertEquals("p26", firstNestedParaWithXPath.getAttribute("id"));

        // 8. Another way to find a nested element is to find first the container element and then
        //    invoke the findElement/findElements on the container instead of the driver.
        //    Try to find the first nested paragraph this way!
        //    This is less efficient than making it with a single query. When would you use that?
        //WebElement nestedDiv = driver.findElement //TODO
        //WebElement firstNestedParaWithNestedLocator = nestedDiv.findElement //TODO
        //assertEquals("p26", firstNestedParaWithNestedLocator.getAttribute("id"));

        // 9. What does the 'findElements' method return for a non-existing item locator?
        //    Does it throw an exception? Returns null? Or an empty list?
        //    Try it yourself!
        //    Can you imagine a use case where this behavior would be useful for testing?
        //List<WebElement> noSuchElements = driver.findElements(By.id("no-such-id"));
        //assertNotNull(noSuchElements);
        //assertEquals(0, noSuchElements.size());

        // 10. (Bonus) Try to find the second nested paragraph in different ways.
        //     Be careful, it has an unusual and ugly structure.
        //TODO
        //assertEquals("p27", secondNestedPara.getAttribute("id"));
    }
}
