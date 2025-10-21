package com.framework.stepdefinitions;

import com.framework.base.PlaywrightDriver;
import com.framework.pages.DashboardPage;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

import io.cucumber.java.en.Then;
import static com.framework.utils.SoftAssertManager.*;
public class DashboardSteps {
	
	private final Page page;
	private DashboardPage dashboardPage;
	
	   public DashboardSteps() {
	        this.page = PlaywrightDriver.getPage(); // ThreadLocal-safe
	        this.dashboardPage = new DashboardPage(page);
	    }
	
	  @Then("User should see the dashboard")
	    public void user_should_see_the_dashboard() {
	    	//dashboardPage = new DashboardPage(page);
	    	page.waitForLoadState(LoadState.LOAD);
	    	page.waitForLoadState(LoadState.NETWORKIDLE);
	        assertTrue(dashboardPage.getDashboardTitle().isVisible(),"Dashboard page is not visible");
	    }

}
