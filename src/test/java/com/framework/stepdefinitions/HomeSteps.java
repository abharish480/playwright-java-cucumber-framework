package com.framework.stepdefinitions;

import com.framework.base.PlaywrightDriver;
import com.framework.pages.HomePage;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class HomeSteps {
	HomePage home;

	@Given("User is on homepage")
	public void user_on_homepage() {
		home = new HomePage(PlaywrightDriver.getPage());
	}

	@Then("Page title should contain {string}")
	public void verify_title(String expected) {
		Assert.assertTrue(home.getTitle().contains(expected));
	}
}