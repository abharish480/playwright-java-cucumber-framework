package com.framework.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class PlaywrightConceptsPage {

	private final Page page;
	private final Locator shadowHostUserName;

	private static final String SHADOWHOST_USERNAME = "[name='username']";

	public PlaywrightConceptsPage(Page page) {
		this.page = page;
		this.shadowHostUserName = page.locator(SHADOWHOST_USERNAME);
	}

}
