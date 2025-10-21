package com.framework.stepdefinitions;

import static com.framework.utils.SoftAssertManager.assertTrue;

import com.framework.base.PlaywrightDriver;
import com.framework.pages.LoginPage;
import com.microsoft.playwright.Page;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

	private final Page page;
	private LoginPage loginPage;
	
	   public LoginSteps() {
	        this.page = PlaywrightDriver.getPage(); // ThreadLocal-safe
	        this.loginPage = new LoginPage(page);
	    }

	   
	@Given("User launches the application")
    public void user_launches_the_application() {
		System.out.println("Launching the application.............");
    }

    @When("User login with valid username and password")
    public void user_login_with_valid_username_and_password() {
    	//loginPage = new LoginPage(page);
    	loginPage.loginPage("Admin", "admin123");
    }


    @When("User login with username as {string} and password as {string}")
    public void user_login_with_username_and_password(String username, String password) {
        //loginPage = new LoginPage(page);
        loginPage.loginPage(username, password);
    }
    
    @Then("User should see {string} error message")
    public void user_should_see_error_message(String expectedMessage) {
        String actualMessage = loginPage.getInvalidCredsMsg().innerText();

        assertTrue(
            actualMessage.contains(expectedMessage),
            "Expected error message: " + expectedMessage + " but found: " + actualMessage
        );
    }

}
