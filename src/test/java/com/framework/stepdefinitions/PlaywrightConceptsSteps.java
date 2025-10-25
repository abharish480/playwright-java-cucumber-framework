package com.framework.stepdefinitions;

import com.framework.base.PlaywrightDriver;
import com.framework.pages.PlaywrightConceptsPage;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class PlaywrightConceptsSteps {

	private final Page page;
	private PlaywrightConceptsPage playwrightConceptsPage;
	
	public PlaywrightConceptsSteps() {
		this.page = PlaywrightDriver.getPage(); 
		this.playwrightConceptsPage = new PlaywrightConceptsPage(page);
	}
	
	@Given("User navigates to {string} link")
    public void user_navigates_to_link(String url) {
        page.navigate(url);
    }

    @When("User enters text in user name as {string}")
    public void user_enters_text_in_user_name_as(String username) {
      
    	// Locate the shadow host
    	Locator shadowHost = page.locator("#userName");

    	// Fill input inside shadow DOM
    	shadowHost.locator("#kils").fill("User Name");

    	// First iframe inside the shadow host
    	FrameLocator iframe1 = shadowHost.frameLocator("#pact1");

    	// Fill element inside iframe1
    	iframe1.locator("#jex").fill("World");

    	// Now, iframe3 is nested inside iframe1
    	FrameLocator iframe3 = iframe1.frameLocator("#pact3");

    	// Fill element inside iframe3
    	iframe3.locator("#glaf").fill("India");
    	
    Locator nestedShadow =	shadowHost.locator("#app2");
    nestedShadow.locator("#pizza").fill("Paneer");
    
    //shadow- root closed can't be tested
//    Locator nestedShadowConcepts =	shadowHost.locator("#concepts");
//    nestedShadowConcepts.locator("#training").fill("Java-Playwright");
//    Locator pwdShadow = page.locator("#userPass");
//    pwdShadow.locator("#pwd").fill("Password");
    }
	
}
