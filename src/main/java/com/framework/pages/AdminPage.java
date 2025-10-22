package com.framework.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import lombok.Getter;

@Getter
public class AdminPage {

	private Page page;

	private final Locator navbarMenu;

	private static final String NAVBAR_MENU = "nav[aria-label='Topbar Menu'] span";

	public AdminPage(Page page) {
		this.page = page;
		this.navbarMenu = page.locator(NAVBAR_MENU);
	}
}