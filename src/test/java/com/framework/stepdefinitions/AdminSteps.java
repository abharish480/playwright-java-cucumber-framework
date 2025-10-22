package com.framework.stepdefinitions;

import static com.framework.utils.SoftAssertManager.*;

import java.util.List;

import com.framework.base.PlaywrightDriver;
import com.framework.pages.AdminPage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;

public class AdminSteps {

	private final Page page;
	private AdminPage adminPage;

	public AdminSteps() {
		this.page = PlaywrightDriver.getPage();
		adminPage = new AdminPage(page);
	}

	@Then("User should see nav bar menu items")
	public void userShouldSeeNavBarMenuItems(DataTable dataTable) {
		List<String> expectedItems = dataTable.asList();
		Locator menuItems = adminPage.getNavbarMenu();

		for (int i = 0; i < expectedItems.size(); i++) {
			String expectedText = expectedItems.get(i);
			Locator menuItem = menuItems.nth(i);
			String actualText = menuItem.textContent().trim();
			assertEquals(actualText, expectedText, "Menu item text mismatch at index " + i);
			boolean isIconVisible = menuItem.locator("i").isVisible();
			assertTrue(isIconVisible, "Dropdown icon not visible for menu item: " + expectedText);
		}
	}
}
