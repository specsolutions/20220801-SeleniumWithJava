package eu.specsolutions.courses;

import eu.specsolutions.courses.pages.BasicHtmlFormExamplePage;
import eu.specsolutions.courses.pages.InputValidationResponsePage;
import eu.specsolutions.courses.pages.ProcessedFormDetailsPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class Exercise12PageObjects extends ExerciseTestBase {

    @Test
    public void TaskA() {
        driver = new ChromeDriver();

        // 1. The page object class 'BasicHtmlFormExamplePage' contains an implementation of the
        //    page at https://testpages.herokuapp.com/styled/basic-html-form-test.html
        //    (we've used this in Exercise 7).
        //    Page objects are encapsulating all page implementation details (input locators,
        //    input behavior, etc.).
        //    Review the page object class, and its usage below. Unlike a real page object class
        //    this one implements the same actions (fill out username and password and submit the form)
        //    in multiple different styles. Review all these stiles as well.
        //    Do you have a favorite? What are the advantages and disadvantages of the different styles?

        BasicHtmlFormExamplePage page = new BasicHtmlFormExamplePage(driver);

        // use generic page methods
        page.goTo();
        page.assertOnPage();

        // perform login - submit style
        page.goTo();
        page.performLogin_submitStyle("Marvin", "1234");

        // perform login - fill style
        page.goTo();
        page.fillLogin("Marvin", "1234");
        page.submitLogin_fillStyle();

        // perform login - setXXX style
        page.goTo();
        page.setUsername("Marvin");
        page.setPassword("1234");
        page.submitLogin_setXXXStyle();

        // perform login & work with result page - page flow style
        page.goTo();
        ProcessedFormDetailsPage resultPage = page.performLogin_pageFlowStyle("Marvin", "1234");
        assertEquals("Marvin", resultPage.getUsername());

        // check login validation errors - attempt action style
        page.goTo();
        Boolean loginResult = page.performLogin_attemptActionStyle("Marvin", "1234");
        assertEquals(false, loginResult);
        assertEquals("Invalid login", page.getErrorMessages());

        pause();
    }

    @Test
    public void TaskB() {
        driver = new ChromeDriver();

        // 1. Create a page object class 'InputValidationExamplesPage' to encapsulate the automation details
        //    of the https://testpages.herokuapp.com/styled/validation/input-validation.html we've used in Exercise 6.

        //InputValidationExamplesPage page = new InputValidationExamplesPage(driver);
        //page.goTo();

        // 2. Choose one of the styles you have seen in Task A (or mix your own) and perform a successful
        //    submission to the page with
        //    * First name: Zaphod
        //    * Last name: Beeblebroox
        //    * Age: 43
        //    * Country, Notes: set them to some arbitrary value
        //    The page object class of the result page ('InputValidationResponsePage') is already created
        //    for you.
        //page.goTo();
        //TODO: submit form with the values above

        //InputValidationResponsePage resultPage = TODO (use new InputValidationResponsePage(driver) if you not used the page flow style
        //resultPage.assertOnPage();
        //assertEquals("Zaphod", resultPage.getFirstName());
        //assertEquals("Beeblebroox", resultPage.getLastName());
        //assertEquals(43, resultPage.getAge());

        // 3. Now automate the case where we expect an error. For that submit 'Beeblebrox'
        //    as last name and make sure the error message 'Surname provided is too short'
        //    was provided.
        //page.goTo();
        //TODO: submit form with the values above
        //assertTrue(page.getErrorMessages().contains("Surname provided is too short"));
    }
}
