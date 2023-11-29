package org.example;


import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginStepDefs extends AbstractStepDefs{

    @Then("the {string} email message is shown")
    public void theEmailErrorMessageIsShown(String errorMessage) {
        assertEquals(errorMessage, homePage.getEmailErrorMessage());
    }

    @Then("the {string} password message is shown")
    public void thePasswordErrorMessageIsShown(String errorMessage) {
        assertEquals(errorMessage, homePage.getPasswordErrorMessage());
    }

    @Then("the {string} login message is shown")
    public void theLoginErrorMessageIsShown(String errorMessage) {
        assertEquals(errorMessage, homePage.getLoginErrorMessage());
    }
}
