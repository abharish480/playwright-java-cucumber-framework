package com.framework.stepdefinitions;

import static com.framework.utils.SoftAssertManager.assertEquals;
import static com.framework.utils.SoftAssertManager.assertTrue;
import static com.framework.utils.Constants.*;

import java.util.List;

import com.framework.base.PlaywrightDriver;
import com.framework.pages.DashboardPage;
import com.framework.utils.WebActions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DashboardSteps {

	private final Page page;
	private DashboardPage dashboardPage;

	public DashboardSteps() {
		this.page = PlaywrightDriver.getPage(); // ThreadLocal-safe
		this.dashboardPage = new DashboardPage(page);
	}

	@Then("User should see the dashboard")
	public void user_should_see_the_dashboard() {
		// dashboardPage = new DashboardPage(page);
		page.waitForLoadState(LoadState.LOAD);
		page.waitForLoadState(LoadState.NETWORKIDLE);
		assertTrue(dashboardPage.getDashboardTitle().isVisible(), "Dashboard page is not visible");
		assertEquals(dashboardPage.getDashboardTitle().textContent(), DASHBOARD_TITLE, "Title is not matching");
	}

	@Then("User validates the below menu is displayed in left side panel")
	public void user_validates_left_side_menu(DataTable expectedTable) {
		List<String> expected = expectedTable.asList(String.class);
		List<String> actual = dashboardPage.getLeftMenuItems().allInnerTexts();
		assertEquals(actual.size(), expected.size(), "Verify left menu item count");
		int compareUntil = Math.min(actual.size(), expected.size());
		for (int i = 0; i < compareUntil; i++) {
			assertEquals(actual.get(i).trim(), expected.get(i).trim(),
					String.format("Verify menu item at position %d is '%s'", i + 1, expected.get(i)));
		}
		if (actual.size() > expected.size()) {
			for (int i = expected.size(); i < actual.size(); i++) {
				assertTrue(false,
						String.format("Unexpected extra menu item at position %d: '%s'", i + 1, actual.get(i)));
			}
		}
		if (expected.size() > actual.size()) {
			for (int i = actual.size(); i < expected.size(); i++) {
				assertTrue(false,
						String.format("Missing expected menu item at position %d: '%s'", i + 1, expected.get(i)));
			}
		}
	}

	@Then("User validates the dropdown menu")
	public void user_validates_the_dropdown_menu(DataTable dataTable) {
		List<String> expectedItems = dataTable.asList();
		dashboardPage.getDropdownMenu().hover();
		dashboardPage.getDropdownMenu().click();
		List<String> actualMenuItems = dashboardPage.getDropdownMenuList().allInnerTexts();
		assertEquals(actualMenuItems, expectedItems, "Dropdown menu items do not match expected values!");
	}

	@When("User clicks on {string} button in sidepanel")
	public void userClicksOnButtonInSidepanel(String buttonName) {
		dashboardPage.clickLeftMenuItem(buttonName);
	}

	@Then("User clicks on Help Icon and navigates to new tab")
	public void userNavigatesToNewTab() {
		Page newTab = page.context().waitForPage(() -> {
			dashboardPage.getHelpIcon().click(); // adjust locator
		});
		newTab.waitForLoadState();
		String newTabUrl = newTab.url();
		System.out.println("New tab URL: " + newTabUrl);

		String heading = newTab.locator("h1").first().textContent().trim();
		System.out.println("Heading in new tab: " + heading);

		assertTrue(newTabUrl.contains("starterhelp"), "URL should contain 'help'");
		dashboardPage = new DashboardPage(newTab);
		WebActions.waitForElementVisible(newTab, dashboardPage.getHELP_SEARCH_BAR(), 20);
		assertTrue(dashboardPage.getHelpSearchBar().isVisible(), "Search bar is not present in help page");
		newTab.close();
		dashboardPage = new DashboardPage(PlaywrightDriver.getPage());
		assertTrue(dashboardPage.getDashboardTitle().isVisible(), "Dashboard page is not visible");
	}

}
