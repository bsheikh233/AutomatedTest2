package org.example;


import io.cucumber.java.AfterAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommonStepDefs extends AbstractStepDefs {

    @Given("the application is loaded")
    public void theHomePageIsOpened() {
        homePage.openPage();
    }

    @Given("the {string} field contains {string}")
    public void theFieldIsFilledWithText(String field, String text) {
        homePage.fillOutField(field, text);
    }

    @When("the {string} button is clicked")
    public void theButtonIsClicked(String button) {
        homePage.clickButton(button);
    }
    @Then("the user is redirected to {string}")
    public void theUserIsDirectedToPageUrl(String pageUrl) {
        assertEquals(pageUrl, homePage.getPageUrl());
    }

    @AfterAll
    public static void cleanUp() {
        homePage.closePage();
    }
}