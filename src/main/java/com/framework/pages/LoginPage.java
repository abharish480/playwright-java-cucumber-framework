package com.framework.pages;

import com.microsoft.playwright.Page;

/*
 * public class LoginPage {
 * 
 * private Page page;
 * 
 * public LoginPage(Page page) { this.page = page; }
 * 
 * private String username = "[name='username']"; private String txtPassword =
 * "[name='password']"; private String loginBtn = "//button[text()=' Login ']";
 * private String invalidCredsMsg =
 * "//div[contains(@class,'oxd-alert-content')]//p";
 * 
 * public void loginPage(String name, String password) { page.fill(username,
 * name); page.fill(txtPassword, password); page.click(loginBtn); } }
 */

import com.microsoft.playwright.Locator;
import lombok.Getter;

@Getter
public class LoginPage {

	private final Page page;

	private final Locator usernameInput;
	private final Locator passwordInput;
	private final Locator loginButton;
	private final Locator invalidCredsMsg;

	private static final String USERNAME_INPUT = "[name='username']";
	private static final String PASSWORD_INPUT = "[name='password']";
	private static final String LOGIN_BUTTON = "//button[text()=' Login ']";
	private static final String INVALID_CREDS_MSG = "//div[contains(@class,'oxd-alert-content')]//p";

	public LoginPage(Page page) {
		this.page = page;
		this.usernameInput = page.locator(USERNAME_INPUT);
		this.passwordInput = page.locator(PASSWORD_INPUT);
		this.loginButton = page.locator(LOGIN_BUTTON);
		this.invalidCredsMsg = page.locator(INVALID_CREDS_MSG);
	}

	public void loginPage(String username, String password) {
		usernameInput.fill(username);
		passwordInput.fill(password);
		loginButton.click();
	}
}